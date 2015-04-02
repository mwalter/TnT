/*
 * TnT - Things and tasks to do
 * Licensed under General Public License v3 (GPLv3)
 * newInstance.org, 2013
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

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.newinstance.tnt.base.BaseTest;
import org.newinstance.tnt.model.Owner;
import org.newinstance.tnt.model.Priority;
import org.newinstance.tnt.model.Status;
import org.newinstance.tnt.model.Task;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests methods of class {@link TasksBean}.
 *
 * @author mwalter
 */
public class TasksBeanTest extends BaseTest {

    @Autowired
    private TasksBean tasksBean;

    @Before
    public void setUp() throws Exception {
        addTask();
    }

    @After
    public void tearDown() throws Exception {
        final List<Task> tasks = taskService.searchAllTask();
        for (final Task task : tasks) {
            taskService.deleteTask(task);
        }

        final Iterable<Owner> owners = ownerRepository.findAll();
        for (final Owner owner : owners) {
            ownerRepository.delete(owner);
        }
    }

    private void addTask() {
        final Owner owner = new Owner();
        owner.setName("John");
        ownerRepository.save(owner);

        final Task task = new Task();
        task.setCreationDate(new Date());
        task.setOwner(owner);
        task.setName("Test task");
        task.setPriority(Priority.LOW);
        task.setStatus(Status.OPEN);

        taskRepository.save(task);
    }

    @Test
    public void getTasks() {
        final List<Task> tasks = tasksBean.getTasks();
        Assert.assertNotNull(tasks);
        Assert.assertTrue(tasks.size() == 1);
    }

//    @Test
//    public void editTask() {
//        // change a task
//        final Task task = tasksBean.getTasks().get(0);
//        task.setDescription("something very important");
//        // convenience method call to set task into task bean
//        tasksBean.editTask(task);
//        tasksBean.saveTask();
//        // verify that the owner hasn't changed
//        Assert.assertNotNull(taskService.searchAllTask().get(0).getOwner());
//    }
}
