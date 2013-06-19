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

import org.newinstance.tnt.model.Owner;

import java.util.List;

/**
 * Provides services related to {@link Owner}.
 *
 * @author mwalter
 */
public interface OwnerService {

    /**
     * Deletes an owner from the database.
     *
     * @param owner the owner to delete
     */
    void deleteOwner(final Owner owner);

    /**
     * Saves an owner to the database.
     *
     * @param owner the owner to save
     */
    void saveOwner(final Owner owner);

    /**
     * Searches all owners.
     *
     * @return all owners
     */
    List<Owner> searchAllOwner();

    /**
     * Searches an owner by primary key.
     *
     * @param ownerId the primary key
     * @return the owner
     */
    Owner searchOwnerById(final Long ownerId);

    /**
     * Searches an owner by a name.
     *
     * @param name the name of the owner
     * @return the owner
     */
    Owner searchOwnerByName(final String name);
}
