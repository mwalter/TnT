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
import java.util.ArrayList;
import java.util.List;

import org.newinstance.tnt.model.Task;
import org.newinstance.tnt.model.TaskList;
import org.newinstance.tnt.service.TaskListService;
import org.newinstance.tnt.service.TaskService;
import org.newinstance.tnt.view.TaskListView;
import org.newinstance.tnt.view.TaskView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * The task lists managed bean.
 *
 * @author mwalter
 */
@Component
@Scope(value = "request")
public class TaskListBean implements Serializable {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskListService taskListService;

    @Autowired
    private TaskView taskView;

    @Autowired
    private TaskListView taskListView;

    /**
     * Retrieves all task lists from the database when the bean is instantiated.
     *
     * @return the task lists
     */
    public List<TaskList> getTaskLists() {
        if (taskListView.getTaskLists() == null) {
            taskListView.setTaskLists(taskListService.searchAllTaskLists());
        }
        return taskListView.getTaskLists();
    }

    public int getTasksCount(TaskList taskList) {
        return taskListService.getTasksCount(taskList);
    }

    private List<Task> getTasksWithStatusDone() {
        final List<Task> tasks = taskView.getTasks();
        final List<Task> tasksWithStatusDone = new ArrayList<>();
        for (final Task task : tasks) {
            if (task.isDone()) {
                tasksWithStatusDone.add(task);
            }
        }
        return tasksWithStatusDone;
    }

    /**
     * Returns the number of all tasks with status done.
     *
     * @return the number of all tasks with status done
     */
    public int getTasksCountWithStatusDone() {
        return getTasksWithStatusDone().size();
    }

    private List<Task> getTasksWithStatusOpen() {
        final List<Task> tasks = taskView.getTasks();
        final List<Task> tasksWithStatusOpen = new ArrayList<>();
        for (final Task task : tasks) {
            if (task.isOpen()) {
                tasksWithStatusOpen.add(task);
            }
        }
        return tasksWithStatusOpen;
    }

    /**
     * Returns the number of all tasks with status open.
     *
     * @return the number of all tasks with status open
     */
    public int getTasksCountWithStatusOpen() {
        return getTasksWithStatusOpen().size();
    }

    /**
     * Shows all tasks by a given task list.
     */
    public void showTasksBy(final TaskList taskList) {
        taskView.setTasks(taskService.searchAllTasksBy(taskList));
    }

    /**
     * Shows all tasks.
     */
    public void showAllTasks() {
        taskView.setTasks(taskService.searchAllTasks());
    }

    /**
     * Shows all tasks with status open.
     */
    public void showTasksWithStatusOpen() {
        taskView.setTasks(getTasksWithStatusOpen());
    }

    /**
     * Shows all tasks with status done.
     */
    public void showTasksWithStatusDone() {
        taskView.setTasks(getTasksWithStatusDone());
    }

}
