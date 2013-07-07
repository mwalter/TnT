/*
 * TnT - Things and tasks to do
 * Licensed under General Public License v3 (GPLv3)
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

package org.newinstance.tnt.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Tests methods of class {@link Task}.
 *
 * @author mwalter
 */
public class TaskTest {

    @Test
    public void toStringTest() {
        final Owner owner = new Owner();
        owner.setName("John");

        final Task task = new Task();
        task.setCreationDate(new Date());
        task.setDueDate(task.getCreationDate());
        task.setDescription("A description");
        task.setStatus(Status.OPEN);
        task.setPriority(Priority.LOW);
        task.setName("Task");
        task.setOwner(owner);

        final String result = task.toString();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("Task"));
        Assert.assertTrue(result.contains("OPEN"));
        Assert.assertTrue(result.contains("LOW"));
        Assert.assertTrue(result.contains("John"));
    }
}
