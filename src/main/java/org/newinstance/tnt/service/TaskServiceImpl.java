/*
 * TnT - Things and tasks to do
 * Licenced under General Public Licence v3 (GPLv3)
 * newInstance.org, 2012
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

import org.newinstance.tnt.model.Task;
import org.newinstance.tnt.persistence.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implements the services related to {@link Task}.
 *
 * @author mwalter
 */
@Transactional
public class TaskServiceImpl implements TaskService {

    private static final Logger LOG = Logger.getLogger(TaskServiceImpl.class.getName());

    @Autowired
    private GenericDao genericDao;

    public void deleteTask(final Task task) {
        LOG.log(Level.INFO, "Deleting task: {0}", task.toString());
        genericDao.delete(task);
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
