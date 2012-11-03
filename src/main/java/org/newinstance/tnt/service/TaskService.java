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

import java.util.List;

/**
 * Provides services related to {@link Task}.
 *
 * @author mwalter
 */
public interface TaskService {

    /**
     * Deletes a task.
     *
     * @param task the task to delete.
     */
    void deleteTask(final Task task);

    /**
     * Saves a task.
     *
     * @param task the task to save.
     */
    void saveTask(final Task task);

    /**
     * Searches all tasks.
     *
     * @return all tasks
     */
    List<Task> searchAllTask();
}
