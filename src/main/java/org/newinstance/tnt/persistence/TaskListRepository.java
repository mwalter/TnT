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

package org.newinstance.tnt.persistence;

import org.newinstance.tnt.model.TaskList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides CRUD repository methods related to entity {@link TaskList}.
 *
 * @author mwalter
 */
@Repository
public interface TaskListRepository extends CrudRepository<TaskList, Long> {

    /**
     * Searches a list of tasks by a name.
     *
     * @param name the name of the task list
     * @return the list
     */
    TaskList findByName(final String name);
}
