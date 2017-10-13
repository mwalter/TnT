/*
 * TnT - Things and tasks to do
 * Licenced under General Public Licence v3 (GPLv3)
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

package org.newinstance.tnt.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections4.IteratorUtils;
import org.newinstance.tnt.model.Status;
import org.newinstance.tnt.model.Task;
import org.newinstance.tnt.model.TaskList;
import org.newinstance.tnt.persistence.TaskListRepository;
import org.newinstance.tnt.persistence.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements the services related to {@link Task}.
 *
 * @author mwalter
 */
@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger LOG = Logger.getLogger(TaskServiceImpl.class.getName());

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskListRepository taskListRepository;

    @Override
    public Task createTask() {
        final Task task = new Task();
        task.setStatus(Status.OPEN);
        task.setTaskList(taskListRepository.findByName("New"));
        return task;
    }

    @Override
    public void deleteTask(final Task task) {
        final Task taskToDelete = taskRepository.findOne(task.getId());
        LOG.log(Level.INFO, "Deleting task: {0}", taskToDelete.toString());
        taskRepository.delete(taskToDelete);
    }

    @Override
    public void finishTask(final Task task) {
        task.setStatus(Status.DONE);
        taskRepository.save(task);
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> searchAllTasks() {
        LOG.log(Level.INFO, "Searching all tasks.");
        return IteratorUtils.toList(taskRepository.findAll().iterator());
    }

    @Override
    public List<Task> searchAllTasksBy(TaskList taskList) {
        LOG.log(Level.INFO, "Searching all tasks by task list " + taskList.getName());
        return IteratorUtils.toList(taskRepository.findByTaskList(taskList).iterator());
    }

}
