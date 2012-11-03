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

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Helper class for methods related to date.
 *
 * @author mwalter
 */
public final class DateHelper {

    private DateHelper() {
        // hide constructor
    }

    /**
     * Returns a date in a readable format. Time is not displayed.
     *
     * @param date the date to format
     * @return the date value as medium formatted string
     * @see DateFormat
     */
    public static String formatDate(final Calendar date) {
        if (date == null) {
            return null;
        }

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        return df.format(date.getTime());
    }


}
