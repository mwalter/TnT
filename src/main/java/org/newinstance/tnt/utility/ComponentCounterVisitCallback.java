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

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;

/**
 * Implements the visit callback to count all components in the context.
 *
 * @author mwalter
 */
public class ComponentCounterVisitCallback implements VisitCallback {

    /** Counter for components. */
    private int counter = 0;

    /** List of all components. */
    private List<String> componentList = new ArrayList<>();

    @Override
    public VisitResult visit(final VisitContext context, final UIComponent target) {
        counter++;
        componentList.add(target.getClientId() + " [" + target.getClass().getSimpleName() + "]");
        return VisitResult.ACCEPT;
    }

    /**
     * Returns the counter.
     *
     * @return the counter
     */
    public int getCount() {
        return counter;
    }

    /**
     * Return the list with all components.
     *
     * @return list of all components
     */
    public List<String> getComponentList() {
        return componentList;
    }
}
