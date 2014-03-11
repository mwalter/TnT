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
import javax.faces.validator.ValidatorException;

import org.junit.Test;

/**
 * Tests methods of class {@link DueDateValidator}.
 *
 * @author mwalter
 */
public class DueDateValidatorTest {

    @Test
    public void validateDueDate() {
        final FacesContextMock facesContextMock = new FacesContextMock();
        final DueDateValidator validator = new DueDateValidator();
        final Calendar calendar = Calendar.getInstance();
        validator.validate(facesContextMock, null, calendar.getTime());
    }

    @Test(expected = ValidatorException.class)
    public void validateInvalidDueDate() {
        final FacesContextMock facesContextMock = new FacesContextMock();
        final DueDateValidator validator = new DueDateValidator();
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        validator.validate(facesContextMock, null, calendar.getTime());
    }

}
