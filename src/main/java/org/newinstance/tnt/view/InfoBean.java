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

package org.newinstance.tnt.view;

import javax.faces.component.visit.VisitContext;
import javax.faces.context.FacesContext;

import org.newinstance.tnt.utility.ComponentCounterVisitCallback;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Gathers information about the number of components found in the current faces context.
 *
 * @author mwalter
 */
@Component
@Scope(value = "request")
public class InfoBean {

    /**
     * Retrieves the number of components from the view in the current faces context.
     *
     * @return the number of components
     */
    public int getNumberOfComponentsInTree() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final VisitContext visitContext = VisitContext.createVisitContext(context);
        final ComponentCounterVisitCallback visitCallback = new ComponentCounterVisitCallback();
        context.getViewRoot().visitTree(visitContext, visitCallback);
        return visitCallback.getCount();
    }
}
