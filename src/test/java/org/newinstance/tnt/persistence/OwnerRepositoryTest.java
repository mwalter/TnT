/*
 * TnT - Things and tasks to do
 * Licenced under General Public Licence v3 (GPLv3)
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

package org.newinstance.tnt.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.newinstance.tnt.base.BaseTest;
import org.newinstance.tnt.model.Owner;

/**
 * Tests methods of the {@link OwnerRepository}.
 *
 * @author mwalter
 */
public class OwnerRepositoryTest extends BaseTest {

    @Test
    public void findAll() {
        final Owner tom = new Owner();
        tom.setName("Tom");
        final Owner jerry = new Owner();
        jerry.setName("Jerry");

        ownerRepository.save(tom);
        ownerRepository.save(jerry);

        final Iterable<Owner> result = ownerRepository.findAll();
        for (final Owner owner : result) {
            assertTrue(owner.getName().equals(tom.getName()) || owner.getName().equals(jerry.getName()));
        }
    }

    @Test
    public void saveAndFindOwnerByName() {
        final Owner newOwner = new Owner();
        newOwner.setName(JUNIT);

        ownerRepository.save(newOwner);

        assertEquals(1, ownerRepository.count());

        final Owner foundOwner = ownerRepository.findByName(JUNIT);
        assertEquals(newOwner.getName(), foundOwner.getName());
    }
}
