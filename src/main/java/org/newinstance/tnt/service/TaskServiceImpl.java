/*
 * TnT - Things and tasks to do
 * Licenced under General Public Licence v3 (GPLv3)
 * newInstance.org, 2012-2013
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.newinstance.tnt.service;

import org.newinstance.tnt.model.Owner;
import org.newinstance.tnt.model.Priority;
import org.newinstance.tnt.model.Status;
import org.newinstance.tnt.model.Task;
import org.newinstance.tnt.persistence.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implements the services related to {@link Task}.
 *
 * @author mwalter
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private static final Logger LOG = Logger.getLogger(TaskServiceImpl.class.getName());

    @Autowired
    private GenericDao genericDao;

    @Autowired
    private OwnerService ownerService;

    public Task createTask() {
        final Task task = new Task();
        task.setCreationDate(new Date());
        task.setStatus(Status.OPEN);
        task.setPriority(Priority.MEDIUM);
        return task;
    }

    public void deleteTask(final Task task) {
        final Task taskToDelete = genericDao.find(Task.class, task.getId());
        LOG.log(Level.INFO, "Deleting task: {0}", taskToDelete.toString());
        genericDao.delete(taskToDelete);
    }

    public void finishTask(final Task task) {
        task.setStatus(Status.DONE);
        updateTask(task);
    }

    @Override
    public void saveTask(final Task task, final String ownerName) {
        final Owner existingOwner = ownerService.searchOwnerByName(ownerName);

        // create new owner if name does not exist in database yet
        if (existingOwner == null) {
            LOG.log(Level.INFO, "Creating new owner with name: " + ownerName);
            final Owner owner = new Owner();
            owner.setName(ownerName);
            // persist new owner
            ownerService.saveOwner(owner);
            task.setOwner(owner);
        } else {
            task.setOwner(existingOwner);
        }

        LOG.log(Level.INFO, "Creating new task with name: " + task.getName());
        if (task.isNew()) {
            // no primary key so it's a new task
            saveTask(task);
        } else {
            updateTask(task);
        }
    }

    public void saveTask(final Task task) {
        LOG.log(Level.INFO, "Saving task: {0}", task.toString());
        genericDao.save(task);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Task> searchAllTask() {
        LOG.log(Level.INFO, "Searching all tasks.");
        return genericDao.findByNamedQuery("SEARCH_ALL_TASK", Task.class);
    }

    public void updateTask(final Task task) {
        LOG.log(Level.INFO, "Updating task: {0}", task.toString());
        genericDao.update(task);
    }
}
