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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.newinstance.tnt.base.BaseTest;
import org.newinstance.tnt.model.Status;
import org.newinstance.tnt.model.Task;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests methods of class {@link TaskBean}.
 *
 * @author mwalter
 */
public class TaskBeanTest extends BaseTest {

    @Autowired
    private TaskBean taskBean;

    @Before
    public void setUp() {
        addTask();
    }

    @After
    public void tearDown() {
        final List<Task> tasks = taskService.searchAllTask();
        for (final Task task : tasks) {
            taskService.deleteTask(task);
        }
    }

    @Test
    public void getTasks() {
        final List<Task> tasks = taskBean.getTasks();
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
    }

    @Test
    public void editTask() {
        // change a task
        final Task task = taskBean.getTasks().get(0);
        task.setDescription("something very important");
        // convenience method call to set task into task bean
        taskBean.editTask(task);
        taskRepository.save(task);
    }

    private void addTask() {
        final Task task = new Task();
        task.setDescription("Test task");
        task.setStatus(Status.OPEN);

        taskRepository.save(task);
    }
}
