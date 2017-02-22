/*
 * Licensed under General Public Licence v3 (GPLv3)
 * newInstance.org, 2015
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

import org.newinstance.tnt.model.Task;
import org.newinstance.tnt.service.TaskService;
import org.newinstance.tnt.view.TaskView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * The edit task managed bean.
 *
 * @author mwalter
 */
@Component
@Scope(value = "request")
public class EditTaskBean implements Serializable {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskView taskView;

    public Task getTask() {
        return taskView.getTask();
    }

    /**
     * Saves the new task to the database.
     *
     * @return the tasks controller
     */
    public String saveTask() {
        // save task
        taskService.saveTask(taskView.getTask());
        updateTasks();
        return "tasks";
    }

    /**
     * Updates the task list in the view.
     */
    private void updateTasks() {
        taskView.setTasks(taskService.searchAllTasks());
    }
}
