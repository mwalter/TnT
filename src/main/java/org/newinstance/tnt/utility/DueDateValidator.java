/*
 * Licensed under General Public Licence v3 (GPLv3)
 * newInstance.org, 2014
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
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.stereotype.Component;

/**
 * Prevents the user from entering a due date in the past.
 *
 * @author mwalter
 */
@Component
public class DueDateValidator implements Validator {

    @Override
    public void validate(final FacesContext context, final UIComponent component, final Object value) throws ValidatorException {
        final Date dueDate = (Date) value;
        final Calendar now = Calendar.getInstance();
        // reset time to zero
        resetTime(now);

        if (dueDate.before(now.getTime())) {
            final String summaryMsg = ResourceLoader.getMessage("errorDueDateInvalidSummary");
            final String detailMsg = ResourceLoader.getMessage("errorDueDateInvalidDetail");
            final FacesMessage facesMessage = new FacesMessage(summaryMsg, detailMsg);
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(facesMessage);
        }
    }

    /**
     * Sets the time of the date to zero.
     *
     * @param calendar the date to modify
     */
    private void resetTime(final Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
