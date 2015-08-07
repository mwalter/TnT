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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.newinstance.tnt.base.BaseTest;
import org.newinstance.tnt.model.Owner;
import org.newinstance.tnt.model.Priority;
import org.newinstance.tnt.model.Status;
import org.newinstance.tnt.model.Task;
import org.newinstance.tnt.model.TaskList;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests methods of class {@link TaskListBean}.
 *
 * @author mwalter
 */
public class TaskListBeanTest extends BaseTest {

    @Autowired
    private TaskListBean taskListBean;

    private TaskList taskList;

    @Before
    public void setUp() {
        addTaskList();
    }

    @After
    public void tearDown() {
        final List<Task> tasks = taskService.searchAllTask();
        for (final Task task : tasks) {
            taskService.deleteTask(task);
        }

        final Iterable<Owner> owners = ownerRepository.findAll();
        for (final Owner owner : owners) {
            ownerRepository.delete(owner);
        }

        final Iterable<TaskList> taskLists = taskListRepository.findAll();
        for (TaskList taskList : taskLists) {
            taskListRepository.delete(taskList);
        }
    }

    @Test
    public void getTaskLists() {
        final List<TaskList> taskLists = taskListBean.getTaskLists();
        assertNotNull(taskLists);
        assertEquals(1, taskLists.size());
    }

    @Test
    public void testGetTasksCount() {
        assertEquals(2, taskListBean.getTasksCount(taskList));
    }

    @Test
    public void testGetTasksCountWithStatusDone() {
        assertEquals(1, taskListBean.getTasksCountWithStatusDone());
    }

    private void addTaskList() {
        final Owner owner = new Owner();
        owner.setName("John");
        ownerRepository.save(owner);

        // there is always a task list named 'new'
        taskList = taskListRepository.findByName("New");

        taskListRepository.save(taskList);

        final Task task1 = new Task();
        task1.setCreationDate(new Date());
        task1.setOwner(owner);
        task1.setName("Test task 1");
        task1.setPriority(Priority.MEDIUM);
        task1.setStatus(Status.DONE);
        task1.setTaskList(taskList);

        final Task task2 = new Task();
        task2.setCreationDate(new Date());
        task2.setOwner(owner);
        task2.setName("Test task 2");
        task2.setPriority(Priority.LOW);
        task2.setStatus(Status.OPEN);
        task2.setTaskList(taskList);

        taskRepository.save(task1);
        taskRepository.save(task2);
    }
}