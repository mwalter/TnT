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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.newinstance.tnt.model.Owner;
import org.newinstance.tnt.model.Priority;
import org.newinstance.tnt.model.Task;
import org.newinstance.tnt.service.OwnerService;
import org.newinstance.tnt.service.TaskService;
import org.newinstance.tnt.utility.ResourceLoader;
import org.newinstance.tnt.view.TaskView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * The create task managed bean.
 *
 * @author mwalter
 */
@Component
@Scope(value = "request")
public class CreateTaskBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(CreateTaskBean.class.getName());

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskView taskView;

    /** Holds the new owner. */
    private String ownerName;

    @PostConstruct
    public void init() {
        taskView.setTask(taskService.createTask());
    }

    public Task getTask() {
        return taskView.getTask();
    }

    /**
     * Saves the new task to the database.
     *
     * @return the tasks view
     */
    public String saveTask() {
        LOG.log(Level.INFO, "Creating new task: " + taskView.getTask().getName());
        // save task
        taskService.saveTask(taskView.getTask(), getOwnerName());
        updateTasks();
        return "tasks";
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(final String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Returns the list of priorities.
     *
     * @return the list of priorities
     */
    public List<SelectItem> getPriorities() {
        final List<SelectItem> priorityList = new ArrayList<>();

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
     * Returns the list of owners.
     *
     * @return the list of owners
     */
    public List<SelectItem> getOwners() {
        LOG.log(Level.INFO, "Loading all owners.");
        // always fetch all owners to make sure to list newly created ones as well
        final Iterable<Owner> owners = ownerService.searchAllOwner();
        final List<SelectItem> ownerList = new ArrayList<>();

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
     * Returns the client's locale language.
     *
     * @return the client's locale language
     */
    public String getLocale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
    }

    /**
     * Updates the task list in the view.
     */
    private void updateTasks() {
        taskView.setTasks(taskService.searchAllTask());
    }
}