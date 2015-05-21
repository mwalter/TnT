/*
 * TnT - Things and tasks to do
 * Licensed under General Public License v3 (GPLv3)
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

package org.newinstance.tnt.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.newinstance.tnt.model.Task;
import org.newinstance.tnt.service.TaskService;
import org.newinstance.tnt.view.TaskView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * The tasks managed bean.
 *
 * @author mwalter
 */
@Component
@Scope(value = "request")
public class TasksBean implements Serializable {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskView taskView;

    /**
     * Retrieves all tasks from the database.
     *
     * @return the list of tasks
     */
    public List<Task> getTasks() {
        if (taskView.getTasks() == null) {
            taskView.setTasks(taskService.searchAllTask());
        }
        return taskView.getTasks();
    }

    public void createTask() {
        final Task task = taskService.createTask();
        task.setDescription(taskView.getDescription());
        task.setName(taskView.getDescription());
        task.setDueDate(new Date());
        taskService.saveTask(task);
        taskView.setDescription(null);
        updateTasks();
    }

    /**
     * Deletes the task.
     *
     * @param task the task to delete
     */
    public void deleteTask(final Task task) {
        taskService.deleteTask(task);
        updateTasks();
    }

    /**
     * Edits a task.
     *
     * @param task the task to edit
     * @return the edit task controller
     */
    public String editTask(final Task task) {
        // put the task to edit into the view
        taskView.setTask(task);
        return "editTask";
    }

    /**
     * Finishes the task by setting status to done.
     *
     * @param task the task to finish
     */
    public void finishTask(final Task task) {
        taskService.finishTask(task);
        updateTasks();
    }

    /**
     * Updates the task list in the view.
     */
    private void updateTasks() {
        taskView.setTasks(taskService.searchAllTask());
    }
}
