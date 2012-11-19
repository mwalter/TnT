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

    private static final Logger LOGGER = Logger.getLogger(TaskBean.class.getName());

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private TaskService taskService;

    private List<Owner> owners;
    private List<Task> tasks;
    private Task task;
    private Long selectedOwnerId;

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
            LOGGER.log(Level.INFO, "Loading all tasks.");
            tasks = taskService.searchAllTask();
        }
        return tasks;
    }

    public void setSelectedOwnerId(final Long selectedOwnerId) {
        LOGGER.log(Level.INFO, "Setting owner " + selectedOwnerId);
        this.selectedOwnerId = selectedOwnerId;
    }

    /**
     * Create a new task.
     *
     * @return the edit task view
     */
    public String createTask() {
        LOGGER.log(Level.INFO, "Initialising new task...");
        task = new Task();
        task.setCreationDate(Calendar.getInstance());
        task.setStatus(Status.OPEN);
        task.setPriority(Priority.MEDIUM);
        return "showEditTask";
    }

    /**
     * Deletes the task.
     *
     * @param task the task to delete
     * @return the tasks view
     */
    public String deleteTask(final Task task) {
        LOGGER.log(Level.INFO, "Deleting task: " + task.toString());
        taskService.deleteTask(task);
        // reload tasks to update task list
        tasks = taskService.searchAllTask();
        return "showTasks";
    }

    /**
     * Edit a task.
     * @param task the task to edit
     * @return the edit task view
     */
    public String editTask(final Task task) {
        LOGGER.log(Level.INFO, "Editing task: " + task.toString());
        System.out.println(task.toString());
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
        LOGGER.log(Level.INFO, "Finishing task: " + task.toString());
        task.setStatus(Status.DONE);
        // task.setDueDate(Calendar.getInstance());
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
            LOGGER.log(Level.INFO, "Loading all owners.");
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
        final Priority[] priorities = Priority.values();
        for (int i = 0; i < priorities.length; i++) {
            final Priority priority = priorities[i];

            // create select item for every priority
            final SelectItem item = new SelectItem();
            item.setLabel(priority.name());
            item.setValue(priority);

            // add item to list
            priorityList.add(item);
        }
        return priorityList;
    }

    /**
     * Saves the task to the database.
     *
     * @return the tasks view
     */
    public String saveTask() {
        task.setOwner(ownerService.searchOwnerById(getSelectedOwnerId()));
        LOGGER.log(Level.INFO, "Saving task: " + task.toString());
        taskService.saveTask(task);
        // reload tasks to update task list
        tasks = taskService.searchAllTask();
        return "showTasks";
    }

    public Long getSelectedOwnerId() {
        return selectedOwnerId;
    }
}
