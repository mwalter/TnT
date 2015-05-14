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

import org.newinstance.tnt.model.TaskList;
import org.newinstance.tnt.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The task lists managed bean.
 *
 * @author mwalter
 */
@Component
@Scope(value = "request")
public class TaskListBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(TaskListBean.class.getName());

    @Autowired
    private TaskListService taskListService;

    public List<TaskList> getTaskLists() {
        LOG.log(Level.INFO, "Loading all task lists.");
        return taskListService.searchAllLists();
    }
}
