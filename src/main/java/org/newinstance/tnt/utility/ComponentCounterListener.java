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

import javax.faces.component.visit.VisitContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Logs some information about the component tree before rendering the current view.
 *
 * @author mwalter
 */
public class ComponentCounterListener implements PhaseListener {

    private static final Logger LOGGER = LogManager.getLogger(ComponentCounterListener.class.getName());

    @Override
    public void afterPhase(final PhaseEvent event) {
        // do nothing
    }

    @Override
    public void beforePhase(final PhaseEvent event) {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final VisitContext visitContext = VisitContext.createVisitContext(facesContext);

        final ComponentCounterVisitCallback callback = new ComponentCounterVisitCallback();
        facesContext.getViewRoot().visitTree(visitContext, callback);

        LOGGER.info("Number of Components: " + callback.getCount());

        if (LOGGER.isDebugEnabled()) {
            for (final String info : callback.getComponentList()) {
                LOGGER.debug("Component found: " + info);
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}
