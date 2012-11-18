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
import org.newinstance.tnt.persistence.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implements the services related to {@link Owner}.
 *
 * @author mwalter
 */
@Transactional
public class OwnerServiceImpl implements OwnerService {

    private static final Logger LOG = Logger.getLogger(OwnerServiceImpl.class.getName());

    @Autowired
    private GenericDao genericDao;

    public void deleteOwner(final Owner owner) {
        LOG.log(Level.INFO, "Deleting owner: {0}", owner.toString());
        genericDao.delete(owner);
    }

    public void saveOwner(final Owner owner) {
        LOG.log(Level.INFO, "Saving task: {0}", owner.toString());
        genericDao.save(owner);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Owner> searchAllOwner() {
        LOG.log(Level.INFO, "Searching all owner.");
        return genericDao.findByNamedQuery("SEARCH_ALL_OWNER", Owner.class);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Owner searchOwnerById(final Long ownerId) {
        LOG.log(Level.INFO, "Searching owner by id " + ownerId);
        return genericDao.find(Owner.class, ownerId);
    }
}
