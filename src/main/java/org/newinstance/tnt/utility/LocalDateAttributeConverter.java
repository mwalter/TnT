/*
 * Licensed under General Public Licence v3 (GPLv3)
 * newInstance.org, 2015
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

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converts the new Java 8 Date API to java.sql.Date and vice versa. Will hopefully be obsolete with JPA 2.2.
 *
 * @author mwalter
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(final LocalDate localDate) {
        return (localDate == null ? null : Date.valueOf(localDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(final Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}
