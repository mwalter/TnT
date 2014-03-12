/*
 * TnT - Things and tasks to do
 * Licenced under General Public Licence v3 (GPLv3)
 * newInstance.org, 2013-2014
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

package org.newinstance.tnt.utility;

import org.junit.Assert;
import org.junit.Test;
import org.newinstance.tnt.model.Status;

/**
 * Tests methods of class {@link ResourceLoader}.
 *
 * @author mwalter
 */
public class ResourceLoaderTest {

    @Test
    public void loadMessage() {
        final String message = ResourceLoader.getMessage("errorDueDateInvalidSummary");
        Assert.assertNotNull(message);
        Assert.assertFalse(message.isEmpty());
    }

    @Test
    public void loadResource() {
        final String statusOpen = ResourceLoader.getResource("status." + Status.OPEN.name());
        Assert.assertNotNull(statusOpen);
        Assert.assertFalse(statusOpen.isEmpty());
    }
}
