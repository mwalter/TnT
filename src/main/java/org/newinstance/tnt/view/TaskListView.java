/*
 * Licensed under General Public License v3 (GPLv3)
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

package org.newinstance.tnt.view;

import java.io.Serializable;
import java.util.List;

import org.newinstance.tnt.model.TaskList;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Provides the {@link TaskList} model.
 *
 * @author mwalter
 */
@Component
@Scope(value = "session")
public class TaskListView implements Serializable {

    private List<TaskList> taskLists;

    public List<TaskList> getTaskLists() {
        return taskLists;
    }

    public void setTaskLists(final List<TaskList> taskLists) {
        this.taskLists = taskLists;
    }
}
