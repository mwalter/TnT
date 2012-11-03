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

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Converts values to and from the model. Used to set an owner into the model from an UI component.
 *
 * @author mwalter
 */
@FacesConverter(value = "ownerConverter")
public class OwnerConverter implements Converter {

    /**
     * @see Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, String)
     */
    public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
        return Long.parseLong(value);
    }

    /**
     * @see Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, Object)
     */
    public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
        return ((Long) value).toString();
    }
}

