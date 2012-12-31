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

package org.newinstance.tnt.service;

import org.junit.Assert;
import org.junit.Test;
import org.newinstance.tnt.base.BaseTest;
import org.newinstance.tnt.model.Owner;

import java.util.List;

/**
 * Tests methods of the {@link OwnerService}.
 *
 * @author mwalter
 */
public class OwnerServiceTest extends BaseTest {

    @Test
    public void saveAndSearchOwner() {
        final Owner newOwner = new Owner();
        newOwner.setName(JUNIT);

        ownerService.saveOwner(newOwner);

        final List<Owner> result = ownerService.searchAllOwner();
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(1, result.size());

        final Owner foundOwner = ownerService.searchOwnerById(result.get(0).getId());
        Assert.assertEquals(newOwner.getName(), foundOwner.getName());
    }
}
