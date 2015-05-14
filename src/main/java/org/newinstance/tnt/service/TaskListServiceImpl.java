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

import org.apache.commons.collections4.IteratorUtils;
import org.newinstance.tnt.model.TaskList;
import org.newinstance.tnt.persistence.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implements the services related to {@link TaskList}.
 *
 * @author mwalter
 */
@Service
public class TaskListServiceImpl implements TaskListService {

    private static final Logger LOG = Logger.getLogger(TaskListServiceImpl.class.getName());

    @Autowired
    private TaskListRepository taskListRepository;

    @Override
    public List<TaskList> searchAllLists() {
        LOG.log(Level.INFO, "Searching all task lists.");
        return IteratorUtils.toList(taskListRepository.findAll().iterator());
    }
}
