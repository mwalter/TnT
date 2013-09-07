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

package org.newinstance.tnt.view;

import org.newinstance.tnt.model.Owner;
import org.newinstance.tnt.model.Priority;
import org.newinstance.tnt.model.Task;
import org.newinstance.tnt.service.OwnerService;
import org.newinstance.tnt.service.TaskService;
import org.newinstance.tnt.utility.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The task managed bean.
 *
 * @author mwalter
 */
@Component
@Scope(value = "session")
public class TaskBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(TaskBean.class.getName());

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private TaskService taskService;

    private List<Task> tasks;

    private Task task;

    /** Holds the new owner. */
    private String ownerName;

    /**
     * Returns the task.
     *
     * @return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * Retrieves all tasks from the database.
     *
     * @return the list of tasks
     */
    public List<Task> getTasks() {
        if (tasks == null) {
            LOG.log(Level.INFO, "Loading all tasks.");
            tasks = taskService.searchAllTask();
        }
        return tasks;
    }

    /**
     * Create a new task.
     *
     * @return the edit task view
     */
    public String createTask() {
        LOG.log(Level.INFO, "Initialising new task...");
        task = taskService.createTask();
        return "showCreateTask";
    }

    /**
     * Deletes the task.
     *
     * @param task the task to delete
     * @return the tasks view
     */
    public String deleteTask(final Task task) {
        LOG.log(Level.INFO, "Deleting task: " + task.toString());
        taskService.deleteTask(task);
        // reload tasks to update task list
        tasks = taskService.searchAllTask();
        return "showTasks";
    }

    /**
     * Edit a task.
     *
     * @param task the task to edit
     * @return the edit task view
     */
    public String editTask(final Task task) {
        LOG.log(Level.INFO, "Editing task: " + task.toString());
        this.task = task;
        return "showEditTask";
    }

    /**
     * Finish the task by setting status to done.
     *
     * @param task the task to finish
     * @return the tasks view
     */
    public String finishTask(final Task task) {
        LOG.log(Level.INFO, "Finishing task: " + task.toString());
        taskService.finishTask(task);
        // reload tasks to update task list
        tasks = taskService.searchAllTask();
        return "showTasks";
    }

    /**
     * Returns the list of owners.
     *
     * @return the list of owners
     */
    public List<SelectItem> getOwners() {
        LOG.log(Level.INFO, "Loading all owners.");
        // always fetch all owners to make sure to list newly created ones as well
        final List<Owner> owners = ownerService.searchAllOwner();
        final List<SelectItem> ownerList = new ArrayList<SelectItem>();

        for (final Owner owner : owners) {
            // create select item for every owner
            final SelectItem item = new SelectItem();
            item.setLabel(owner.getName());
            item.setValue(owner.getId());
            ownerList.add(item);
        }
        return ownerList;
    }

    /**
     * Returns the list of priorities.
     *
     * @return the list of priorities
     */
    public List<SelectItem> getPriorities() {
        final List<SelectItem> priorityList = new ArrayList<SelectItem>();

        // get all priorities
        for (final Priority priority : Priority.values()) {
            // create select item for every priority
            final SelectItem item = new SelectItem();
            item.setLabel(ResourceLoader.getResource("priority." + priority.name()));
            item.setValue(priority);

            // add item to list
            priorityList.add(item);
        }
        return priorityList;
    }

    /**
     * Saves the new task to the database or updates an existing one.
     *
     * @return the tasks view
     */
    public String saveTask() {
        // save task
        taskService.saveTask(task, getOwnerName());

        // reload tasks to update task list
        tasks = taskService.searchAllTask();
        // reset owner name to prevent old owner name being shown next time
        setOwnerName(null);

        return "showTasks";
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(final String name) {
        ownerName = name;
    }

    /**
     * Returns the client's locale language.
     *
     * @return the client's locale language
     */
    public String getLocale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
    }
}
