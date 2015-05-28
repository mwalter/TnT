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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.newinstance.tnt.base.BaseTest;
import org.newinstance.tnt.model.Owner;
import org.newinstance.tnt.model.Priority;
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
        final Owner newOwner = new Owner();
        newOwner.setName(JUNIT);

        ownerRepository.save(newOwner);

        final Task newTask = taskService.createTask();
        newTask.setName(JUNIT);
        newTask.setDescription(JUNIT);
        newTask.setPriority(Priority.LOW);
        newTask.setOwner(newOwner);
        assertTrue(newTask.isNew());

        // create
        taskRepository.save(newTask);
        assertFalse(newTask.isNew());

        // read
        List<Task> result = taskService.searchAllTask();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        final Task taskToUpdate = result.get(0);

        assertEquals(JUNIT, taskToUpdate.getDescription());
        assertEquals(Priority.LOW, taskToUpdate.getPriority());
        assertEquals(Status.OPEN, taskToUpdate.getStatus());

        taskToUpdate.setDescription("Something different");

        // update
        taskRepository.save(taskToUpdate);

        result = taskService.searchAllTask();
        final Task updatedTask = result.get(0);
        assertNotEquals(JUNIT, updatedTask.getDescription());

        // finish
        taskService.finishTask(updatedTask);

        result = taskService.searchAllTask();
        final Task finishedTask = result.get(0);
        assertFalse(finishedTask.isOpen());
        assertEquals(Status.DONE, finishedTask.getStatus());

        // delete
        taskService.deleteTask(updatedTask);
        result = taskService.searchAllTask();
        assertTrue(result.isEmpty());
    }

    @Test
    public void saveTaskWithNewOwner() {
        final Task newTask = taskService.createTask();
        newTask.setName(JUNIT);
        newTask.setDescription(JUNIT);
        newTask.setPriority(Priority.HIGH);

        // save task with owner
        taskService.saveTask(newTask, JUNIT);

        List<Task> result = taskService.searchAllTask();
        final Task task = result.get(0);

        assertNotNull(task.getOwner());
        assertEquals(JUNIT, task.getOwner().getName());
    }

    @Test
    public void findAllNothing() {
        assertTrue(taskService.searchAllTask().isEmpty());
    }
}
