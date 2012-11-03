/*
 * TnT - Things and tasks to do
 * Licenced under General Public Licence v3 (GPLv3)
 * newInstance.org, 2012
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

import java.util.Calendar;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Formats a log entry in log4j style.
 *
 * @author mwalter
 */
public class Log4jStyleFormatter extends Formatter {

    @Override
    public String format(final LogRecord record) {
        final StringBuilder logEntry = new StringBuilder();
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(record.getMillis());
        logEntry.append(DateHelper.formatDate(cal));
        logEntry.append(" [");
        logEntry.append(record.getThreadID());
        logEntry.append("] ");
        logEntry.append(record.getLevel().getName());
        logEntry.append(" ");
        logEntry.append(record.getSourceClassName());
        logEntry.append(" ");
        logEntry.append(record.getSourceMethodName());
        logEntry.append(" - ");
        logEntry.append(record.getMessage());
        logEntry.append("\n");
        return logEntry.toString();
    }
}
