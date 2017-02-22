/*
 * TnT - Things and tasks to do
 * Licenced under General Public Licence v3 (GPLv3)
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

package org.newinstance.tnt.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.newinstance.tnt.base.BaseTest;
import org.newinstance.tnt.model.Task;

/**
 * Tests methods of the {@link TaskRepository}.
 *
 * @author mwalter
 */
public class TaskRepositoryTest extends BaseTest {

    @Test
    public void saveAndFindAllTask() {
        final Task task1 = taskService.createTask();
        task1.setDescription("Buy milk");
        assertTrue(task1.isNew());

        final Task task2 = taskService.createTask();
        task2.setDescription("Write blog");
        assertTrue(task2.isNew());

        // create
        taskRepository.save(task1);
        taskRepository.save(task2);
        assertFalse(task1.isNew());
        assertFalse(task2.isNew());

        // fetch all tasks
        final List<Task> result = taskService.searchAllTasks();
        assertEquals(2, result.size());
    }
}
