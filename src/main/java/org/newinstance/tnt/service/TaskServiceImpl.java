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

import org.hibernate.SessionFactory;
import org.newinstance.tnt.model.Task;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implements the services related to {@link Task}.
 *
 * @author mwalter
 */
public class TaskServiceImpl implements TaskService {

    private static final Logger LOG = Logger.getLogger(TaskServiceImpl.class.getName());

    private SessionFactory sessionFactory;

    public void setSessionFactory(final SessionFactory sessionFactory) {
        LOG.log(Level.INFO, "Injecting SessionFactory.");
        this.sessionFactory = sessionFactory;
    }

    public void deleteTask(final Task task) {
        LOG.log(Level.INFO, "Deleting task: {0}", task.toString());
        sessionFactory.openSession().delete(task);
    }

    public void saveTask(final Task task) {
        LOG.log(Level.INFO, "Saving task: {0}", task.toString());
        sessionFactory.openSession().saveOrUpdate(task);
    }

    public List<Task> searchAllTask() {
        LOG.log(Level.INFO, "Searching all tasks.");
        return sessionFactory.openSession().getNamedQuery("SEARCH_ALL_TASK").list();
    }
}
