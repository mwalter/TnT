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
    private Task newTask;
    private Long selectedOwnerId;

    /**
     * Returns the task.
     *
     * @return the task
     */
    public Task getTask() {
        return newTask;
    }

    public Long getSelectedOwnerId() {
        return selectedOwnerId;
    }

    public void setSelectedOwnerId(final Long selectedOwnerId) {
        LOGGER.log(Level.INFO, "Setting owner " + selectedOwnerId);
        this.selectedOwnerId = selectedOwnerId;
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

    /**
     * Shows the create task view.
     *
     * @return the create task view
     */
    public String showCreateTask() {
        LOGGER.log(Level.INFO, "Initialising new task...");
        newTask = new Task();
        newTask.setCreationDate(Calendar.getInstance());
        newTask.setStatus(Status.OPEN);
        newTask.setPriority(Priority.MEDIUM);
        return "showCreateTask";
    }

    /**
     * Saves the task to the database.
     *
     * @return the tasks view
     */
    public String saveTask() {
        newTask.setOwner(ownerService.searchOwnerById(getSelectedOwnerId()));
        LOGGER.log(Level.INFO, "Saving task: " + newTask.toString());
        taskService.saveTask(newTask);
        // reload tasks to update task list
        tasks = taskService.searchAllTask();

        return "showTasks";
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
     * Returns the list of owners.
     *
     * @return the list of owners
     */
    public List<SelectItem> getOwners() {
        LOGGER.log(Level.INFO, "TaskBean getOwners() called.");
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
}
