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

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Tests methods of class {@link Log4jStyleFormatter}.
 *
 * @author mwalter
 */
public class Log4jStyleFormatterTest extends BaseTest {

    @Test
    public void format() {
        final LogRecord record = new LogRecord(Level.WARNING, "Testing Log4jSytleFormatter");
        record.setSourceClassName("Log4jStyleFormatterTest");
        record.setSourceMethodName("format");
        record.setThreadID((int) Thread.currentThread().getId());

        final Log4jStyleFormatter formatter = new Log4jStyleFormatter();
        final String result = formatter.format(record);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("WARNING"));
        Assert.assertTrue(result.contains("format"));
    }
}
