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

import org.junit.Assert;
import org.junit.Test;
import org.newinstance.tnt.base.BaseTest;
import org.newinstance.tnt.model.Owner;
import org.newinstance.tnt.model.Task;

import java.util.List;

/**
 * Tests methods of the {@link TaskRepository}.
 *
 * @author mwalter
 */
public class TaskRepositoryTest extends BaseTest {

    @Test
    public void saveAndFindAllTask() {
        final Owner newOwner = new Owner();
        newOwner.setName(JUNIT);

        ownerRepository.save(newOwner);

        final Task task1 = taskService.createTask();
        task1.setName("Buy milk");
        task1.setOwner(newOwner);
        Assert.assertTrue(task1.isNew());

        final Task task2 = taskService.createTask();
        task2.setName("Write blog");
        task2.setOwner(newOwner);
        Assert.assertTrue(task2.isNew());

        // create
        taskRepository.save(task1);
        taskRepository.save(task2);
        Assert.assertFalse(task1.isNew());
        Assert.assertFalse(task2.isNew());

        // fetch all tasks
        final List<Task> result = taskService.searchAllTask();
        Assert.assertEquals(2, result.size());
    }
}
