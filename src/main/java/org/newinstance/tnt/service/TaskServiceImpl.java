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
import org.newinstance.tnt.persistence.OwnerRepository;
import org.newinstance.tnt.persistence.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private OwnerRepository ownerRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask() {
        final Task task = new Task();
        task.setCreationDate(new Date());
        task.setStatus(Status.OPEN);
        task.setPriority(Priority.MEDIUM);
        return task;
    }

    public void deleteTask(final Task task) {
        final Task taskToDelete = taskRepository.findOne(task.getId());
        LOG.log(Level.INFO, "Deleting task: {0}", taskToDelete.toString());
        taskRepository.delete(taskToDelete);
    }

    public void finishTask(final Task task) {
        task.setStatus(Status.DONE);
        taskRepository.save(task);
    }

    @Override
    public void saveTask(final Task task, final String ownerName) {
        final Owner existingOwner = ownerRepository.findByName(ownerName);

        // create new owner if name does not exist in database yet
        if (existingOwner == null) {
            LOG.log(Level.INFO, "Creating new owner with name: " + ownerName);
            final Owner owner = new Owner();
            owner.setName(ownerName);
            // persist new owner
            ownerRepository.save(owner);
            task.setOwner(owner);
        } else {
            task.setOwner(existingOwner);
        }

        taskRepository.save(task);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Task> searchAllTask() {
        LOG.log(Level.INFO, "Searching all tasks.");
        // convert Iterable to something more useful
        final List<Task> taskList = new ArrayList<>();
        final Iterable<Task> result = taskRepository.findAll();
        for (final Task task : result) {
            taskList.add(task);
        }
        return taskList;
    }

}
