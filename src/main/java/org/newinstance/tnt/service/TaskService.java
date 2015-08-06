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

import java.util.List;

import org.newinstance.tnt.model.Task;
import org.newinstance.tnt.model.TaskList;

/**
 * Provides services related to {@link Task}.
 *
 * @author mwalter
 */
public interface TaskService {

    /**
     * Creates a new task. Sets the creation date to now, status to open and priority to medium.
     *
     * @return the new task
     */
    Task createTask();

    /**
     * Deletes a task.
     *
     * @param task the task to delete
     */
    void deleteTask(final Task task);

    /**
     * Finishes a task.
     *
     * @param task the task to finish
     */
    void finishTask(final Task task);

    /**
     * Saves a task.
     *
     * @param task the task to save
     */
    void saveTask(final Task task);

    /**
     * Saves a task with owner.
     *
     * @param task      the task to save
     * @param ownerName the name of the new owner
     */
    void saveTask(final Task task, String ownerName);

    /**
     * Searches all tasks.
     *
     * @return all tasks
     */
    List<Task> searchAllTask();

    /**
     * Searches all tasks which are done.
     *
     * @return all tasks with status done
     */
    List<Task> searchAllTaskWithStatusDone();

    /**
     * Searches all tasks by a task list.
     *
     * @return all tasks by a task list
     */
    List<Task> searchAllTasksBy(TaskList taskList);
}
