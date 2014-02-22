/*
 * TnT - Things and tasks to do
 * Licensed under General Public License v3 (GPLv3)
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

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests methods of class {@link ComponentCounterVisitCallback}.
 *
 * @author mwalter
 */
public class ComponentCounterVisitCallbackTest {

    @Test
    public void visit() {
        final VisitContext context = Mockito.mock(VisitContext.class);
        final UIComponent target = Mockito.mock(UIInput.class);
        final ComponentCounterVisitCallback callback = new ComponentCounterVisitCallback();
        final VisitResult result = callback.visit(context, target);
        Assert.assertEquals(VisitResult.ACCEPT, result);
        Assert.assertEquals(1, callback.getCount());
        Assert.assertFalse(callback.getComponentList().isEmpty());
    }
}
