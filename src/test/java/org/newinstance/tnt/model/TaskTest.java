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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Calendar;

import org.junit.Test;

/**
 * Tests methods of class {@link Task}.
 *
 * @author mwalter
 */
public class TaskTest {

    @Test
    public void toStringTest() {
        final Task task = new Task();
        task.setDescription("A description");
        task.setStatus(Status.OPEN);

        final String result = task.toString();
        assertNotNull(result);
        assertTrue(result.contains("A description"));
        assertTrue(result.contains("OPEN"));
    }

    @Test
    public void overdueTest() {
        final LocalDate date = LocalDate.now().minusDays(1);

        final Task task = new Task();
        task.setDueDate(date);
        task.setDescription("A description");
        task.setStatus(Status.OPEN);

        assertTrue(task.isOpen());
        assertTrue(task.isOverdue());

        task.setDueDate(LocalDate.now());
        assertFalse(task.isOverdue());
    }

    @Test
    public void newTest() {
        final Calendar cal = Calendar.getInstance();

        final Task task = new Task();
        task.setDueDate(LocalDate.now());
        task.setDescription("A description");
        task.setStatus(Status.OPEN);

        assertTrue(task.isNew());
    }
}
