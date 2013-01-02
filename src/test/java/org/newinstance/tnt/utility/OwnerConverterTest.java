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

package org.newinstance.tnt.utility;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests methods of class {@link OwnerConverter}.
 *
 * @author mwalter
 */
public class OwnerConverterTest {

    @Test
    public void getAsObject() {
        final OwnerConverter converter = new OwnerConverter();
        Long ownerId = (Long) converter.getAsObject(null, null, "3");
        Assert.assertEquals(3L, ownerId.longValue());
    }

    @Test
    public void getAsString() {
        final Long ownerId = 3L;

        final OwnerConverter converter = new OwnerConverter();
        final String result = converter.getAsString(null, null, ownerId);
        Assert.assertEquals("3", result);
    }
}
