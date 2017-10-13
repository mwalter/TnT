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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/**
 * Faces converter for {@link LocalDate}.
 *
 * @author mwalter
 */
@FacesConverter(value = LocalDateConverter.ID)
public class LocalDateConverter extends DateTimeConverter {

    static final String ID = "org.newinstance.tnt.utility.LocalDateConverter";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        Object o = super.getAsObject(facesContext, uiComponent, value);

        if (o == null) {
            return null;
        }

        if (o instanceof Date) {
            Date date = (Date) o;
            final Instant instant = Instant.ofEpochMilli(date.getTime());
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        } else {
            throw new IllegalArgumentException(String.format("value=%s could not be converted to a LocalDate, result super.getAsObject=%s", value, o));
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {

        if (value == null) {
            return super.getAsString(facesContext, uiComponent, null);
        }

        if (value instanceof LocalDate) {
            LocalDate localDate = (LocalDate) value;
            final Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            return super.getAsString(facesContext, uiComponent, new Date(instant.toEpochMilli()));
        } else {
            throw new IllegalArgumentException(String.format("value=%s is not an instance of LocalDate", value));
        }
    }

}
