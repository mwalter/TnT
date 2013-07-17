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

package org.newinstance.tnt.utility;

import org.junit.Assert;
import org.junit.Test;
import org.newinstance.tnt.base.BaseTest;

import java.util.Calendar;

/**
 * Tests methods of class {@link DateHelper}.
 *
 * @author mwalter
 */
public class DateHelperTest extends BaseTest {

    @Test
    public void formatDate() {
        final Calendar cal = Calendar.getInstance();
        final String result = DateHelper.formatDate(cal);
        Assert.assertNotNull(result);
    }

    @Test
    public void formatNullDate() {
        final String result = DateHelper.formatDate(null);
        Assert.assertNull(result);
    }

}
