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

import java.util.Iterator;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;

/**
 * Mocks the {@link FacesContext} to be used in unit tests.
 *
 * @author mwalter
 */
public class FacesContextMock extends FacesContext {

    @Override
    public void addMessage(final String clientId, final FacesMessage message) {
    }

    @Override
    public Application getApplication() {
        return null;
    }

    @Override
    public Iterator<String> getClientIdsWithMessages() {
        return null;
    }

    @Override
    public ExternalContext getExternalContext() {
        return null;
    }

    @Override
    public FacesMessage.Severity getMaximumSeverity() {
        return null;
    }

    @Override
    public Iterator<FacesMessage> getMessages() {
        return null;
    }

    @Override
    public Iterator<FacesMessage> getMessages(final String clientId) {
        return null;
    }

    @Override
    public RenderKit getRenderKit() {
        return null;
    }

    @Override
    public boolean getRenderResponse() {
        return false;
    }

    @Override
    public boolean getResponseComplete() {
        return false;
    }

    @Override
    public ResponseStream getResponseStream() {
        return null;
    }

    @Override
    public ResponseWriter getResponseWriter() {
        return null;
    }

    @Override
    public UIViewRoot getViewRoot() {
        return null;
    }

    @Override
    public void release() {
    }

    @Override
    public void renderResponse() {
    }

    @Override
    public void responseComplete() {
    }

    @Override
    public void setResponseStream(final ResponseStream responseStream) {
    }

    @Override
    public void setResponseWriter(final ResponseWriter responseWriter) {
    }

    @Override
    public void setViewRoot(final UIViewRoot root) {
    }
}
