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

import org.junit.Assert;
import org.junit.Test;
import org.newinstance.tnt.base.BaseTest;
import org.newinstance.tnt.model.Owner;
import org.newinstance.tnt.model.Priority;
import org.newinstance.tnt.model.Status;
import org.newinstance.tnt.model.Task;

import java.util.List;

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

        ownerService.saveOwner(newOwner);

        final Task newTask = new Task();
        newTask.setName(JUNIT);
        newTask.setDescription(JUNIT);
        newTask.setPriority(Priority.LOW);
        newTask.setStatus(Status.OPEN);
        newTask.setOwner(newOwner);

        // create
        taskService.saveTask(newTask);

        // read
        List<Task> result = taskService.searchAllTask();
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(1, result.size());

        final Task taskToUpdate = result.get(0);

        Assert.assertEquals(JUNIT, taskToUpdate.getDescription());
        Assert.assertEquals(Priority.LOW, taskToUpdate.getPriority());
        Assert.assertEquals(Status.OPEN, taskToUpdate.getStatus());

        taskToUpdate.setDescription("Something different");
        taskToUpdate.setStatus(Status.IN_PROGRESS);

        // update
        taskService.updateTask(taskToUpdate);

        result = taskService.searchAllTask();
        final Task updatedTask = result.get(0);
        Assert.assertNotEquals(JUNIT, updatedTask.getDescription());
        Assert.assertEquals(Status.IN_PROGRESS, updatedTask.getStatus());

        // finish
        taskService.finishTask(updatedTask);

        result = taskService.searchAllTask();
        final Task finishedTask = result.get(0);
        Assert.assertFalse(finishedTask.isOpen());
        Assert.assertEquals(Status.DONE, finishedTask.getStatus());

        // delete
        taskService.deleteTask(updatedTask);
        result = taskService.searchAllTask();
        Assert.assertTrue(result.isEmpty());
    }
}
