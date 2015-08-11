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

package org.newinstance.tnt.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

import org.apache.commons.collections4.IteratorUtils;
import org.newinstance.tnt.model.TaskList;
import org.newinstance.tnt.persistence.TaskListRepository;
import org.newinstance.tnt.persistence.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements the services related to {@link TaskList}.
 *
 * @author mwalter
 */
@Service
public class TaskListServiceImpl implements TaskListService {

    private static final Logger LOG = Logger.getLogger(TaskListServiceImpl.class.getName());

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskListRepository taskListRepository;

    @PostConstruct
    private void init() {
        // TODO move this to DDL init script
        final TaskList result = taskListRepository.findByName("New");
        if (result == null) {
            final TaskList newTaskList = new TaskList();
            newTaskList.setName("New");
            LOG.info("Creating task list 'New'.");
            taskListRepository.save(newTaskList);
        }
    }

    @Override
    public List<TaskList> searchAllTaskLists() {
        LOG.log(Level.INFO, "Searching all task lists.");
        return IteratorUtils.toList(taskListRepository.findAll().iterator());
    }

    @Override
    public int getTasksCount(TaskList taskList) {
        return taskRepository.findByTaskList(taskList).size();
    }
}
