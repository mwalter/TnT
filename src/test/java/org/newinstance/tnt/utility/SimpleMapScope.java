/*
 * TnT - Things and tasks to do
 * Licensed under General Public License v3 (GPLv3)
 * newInstance.org, 2013
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

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * This simple scope implementation uses an internal {@link Map} to hold objects
 * in the scope. This scope implementation can be used for testing beans which
 * should be kept in session or request scope for example.
 * <p>
 * To use this scope in your test cases, simply configure a
 * {@link org.springframework.beans.factory.config.CustomScopeConfigurer} with
 * the required scope name:
 *
 * <pre>
 * &lt;bean class=&quot;org.springframework.beans.factory.config.CustomScopeConfigurer&quot;&gt;
 *   &lt;property name=&quot;scopes&quot;&gt;
 *     &lt;map&gt;
 *       &lt;entry key=&quot;session&quot;&gt;
 *         &lt;bean class=&quot;org.waffel.spring.SessionScope&quot; /&gt;
 *       &lt;/entry&gt;
 *     &lt;/map&gt;
 *   &lt;/property&gt;
 * &lt;/bean&gt;
 * </pre>
 *
 * Because the {@link org.springframework.beans.factory.config.CustomScopeConfigurer} is a
 * {@link org.springframework.beans.factory.config.BeanPostProcessor} all
 * requested beans for the scope are loaded with that post processor if the
 * registered scope match.
 * </p>
 *
 * @author waffel
 */
public class SimpleMapScope implements Scope {

    /**
     * This map contains for each bean name or ID the created object. The objects
     * are created with a spring object factory.
     */
    private final Map<String, Object> objectMap = new HashMap<String, Object>();

    @Override
    public Object get(final String theName, final ObjectFactory theObjectFactory) {
        Object object = objectMap.get(theName);
        if (null == object) {
            object = theObjectFactory.getObject();
            objectMap.put(theName, object);
        }
        return object;
    }

    @Override
    public String getConversationId() {
        return null;
    }

    @Override
    public void registerDestructionCallback(final String theName, final Runnable theCallback) {
        // nothing to do ... this is optional and not required
    }

    @Override
    public Object resolveContextualObject(final String key) {
        return null;
    }

    @Override
    public Object remove(final String theName) {
        return objectMap.remove(theName);
    }

}