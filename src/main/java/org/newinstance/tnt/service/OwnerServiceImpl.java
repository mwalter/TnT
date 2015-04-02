/*
 * Licensed under General Public Licence v3 (GPLv3)
 * newInstance.org, 2015
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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections4.IteratorUtils;
import org.newinstance.tnt.model.Owner;
import org.newinstance.tnt.persistence.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements the services related to {@link Owner}.
 *
 * @author mwalter
 */
@Service
public class OwnerServiceImpl implements OwnerService {

    private static final Logger LOG = Logger.getLogger(OwnerServiceImpl.class.getName());

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<Owner> searchAllOwner() {
        LOG.log(Level.INFO, "Searching all owners.");
        return IteratorUtils.toList(ownerRepository.findAll().iterator());
    }
}
