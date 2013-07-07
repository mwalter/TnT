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

package org.newinstance.tnt.view;

import org.newinstance.tnt.model.Owner;
import org.newinstance.tnt.model.Priority;
import org.newinstance.tnt.model.Status;
import org.newinstance.tnt.model.Task;
import org.newinstance.tnt.service.OwnerService;
import org.newinstance.tnt.service.TaskService;
import org.newinstance.tnt.utility.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The task managed bean.
 *
 * @author mwalter
 */
@Named
@Scope("session")
public class TaskBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(TaskBean.class.getName());

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private TaskService taskService;

    private List<Owner> owners;

    private List<Task> tasks;

    private Task task;

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
        task = new Task();
        task.setCreationDate(Calendar.getInstance());
        task.setStatus(Status.OPEN);
        task.setPriority(Priority.MEDIUM);
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
        task.setStatus(Status.DONE);
        taskService.updateTask(task);
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
        if (owners == null) {
            LOG.log(Level.INFO, "Loading all owners.");
            owners = ownerService.searchAllOwner();
        }
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
     * Returns the list of status.
     *
     * @return the list of status
     */
    public List<SelectItem> getStatus() {
        final List<SelectItem> statusList = new ArrayList<SelectItem>();

        // get all status
        for (final Status status : Status.values()) {
            // create select item for every status
            final SelectItem item = new SelectItem();
            item.setLabel(ResourceLoader.getResource("status." + status.name()));
            item.setValue(status);

            // add item to list
            statusList.add(item);
        }
        return statusList;
    }

    /**
     * Saves the new task to the database or updates an existing one.
     *
     * @return the tasks view
     */
    public String saveTask() {
        // initialize owner
        if (task.getOwner() == null) {
            final String ownerName = getOwnerName();
            LOG.log(Level.INFO, "Creating new owner with name: " + ownerName);
            final Owner owner = new Owner();
            owner.setName(ownerName);
            // persist new owner
            ownerService.saveOwner(owner);
            task.setOwner(owner);
        }

        LOG.log(Level.INFO, "Saving task: " + task.toString());
        if (task.getId() == null) {
            // no primary key so it's a new task
            taskService.saveTask(task);
        } else {
            taskService.updateTask(task);
        }
        // reload tasks to update task list
        tasks = taskService.searchAllTask();
        return "showTasks";
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(final String name) {
        ownerName = name;
    }
}
