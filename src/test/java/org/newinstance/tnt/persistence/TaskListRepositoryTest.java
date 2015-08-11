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

package org.newinstance.tnt.persistence;

import org.junit.Test;
import org.newinstance.tnt.base.BaseTest;
import org.newinstance.tnt.model.TaskList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests methods of the {@link TaskListRepository}.
 *
 * @author mwalter
 */
public class TaskListRepositoryTest extends BaseTest {

    @Test
    public void findAll() {
        final TaskList privateList = new TaskList();
        privateList.setName("Private");
        final TaskList workList = new TaskList();
        workList.setName("Work");

        taskListRepository.save(privateList);
        taskListRepository.save(workList);

        TaskList result = taskListRepository.findByName("Private");
        assertTrue(result.getName().equals(privateList.getName()));
        result = taskListRepository.findByName("Work");
        assertTrue(result.getName().equals(workList.getName()));
    }

    @Test
    public void saveAndFindTaskListByName() {
        final TaskList newTaskList = new TaskList();
        newTaskList.setName(JUNIT);

        taskListRepository.save(newTaskList);

        // we have 2 task lists here because task list 'new' is always created at startup
        assertEquals(2, taskListRepository.count());

        final TaskList foundTaskList = taskListRepository.findByName(JUNIT);
        assertEquals(newTaskList.getName(), foundTaskList.getName());
    }
}
