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

package org.newinstance.tnt.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.newinstance.tnt.base.BaseTest;
import org.newinstance.tnt.model.Status;
import org.newinstance.tnt.model.Task;

/**
 * Tests methods of the {@link TaskService}.
 *
 * @author mwalter
 */
public class TaskServiceTest extends BaseTest {

    @Test
    public void crudTask() {
        final Task newTask = taskService.createTask();
        newTask.setDescription(JUNIT);
        assertTrue(newTask.isNew());

        // create
        taskRepository.save(newTask);
        assertFalse(newTask.isNew());

        // read
        List<Task> result = taskService.searchAllTasks();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        final Task taskToUpdate = result.get(0);

        assertEquals(JUNIT, taskToUpdate.getDescription());
        assertEquals(Status.OPEN, taskToUpdate.getStatus());

        taskToUpdate.setDescription("Something different");

        // update
        taskRepository.save(taskToUpdate);

        result = taskService.searchAllTasks();
        final Task updatedTask = result.get(0);
        assertNotEquals(JUNIT, updatedTask.getDescription());

        // finish
        taskService.finishTask(updatedTask);

        result = taskService.searchAllTasks();
        final Task finishedTask = result.get(0);
        assertFalse(finishedTask.isOpen());
        assertEquals(Status.DONE, finishedTask.getStatus());

        // delete
        taskService.deleteTask(updatedTask);
        result = taskService.searchAllTasks();
        assertTrue(result.isEmpty());
    }

    @Test
    public void findAllNothing() {
        assertTrue(taskService.searchAllTasks().isEmpty());
    }

    @Test
    public void findAllTaskWithStatusDone() {
        assertTrue(taskService.searchAllTasksWithStatusDone().isEmpty());

        final Task newTask = taskService.createTask();
        newTask.setDescription(JUNIT);
        newTask.setStatus(Status.DONE);

        // create
        taskRepository.save(newTask);

        assertEquals(1, taskService.searchAllTasksWithStatusDone().size());
    }
}
