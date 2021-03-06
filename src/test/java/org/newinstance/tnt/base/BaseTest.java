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

package org.newinstance.tnt.base;

import org.junit.runner.RunWith;
import org.newinstance.tnt.persistence.TaskListRepository;
import org.newinstance.tnt.persistence.TaskRepository;
import org.newinstance.tnt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base test class for all tests.
 *
 * @author mwalter
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-test.xml", "/scopes.xml"})
public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

    protected static final String JUNIT = "JUnit";

    @Autowired
    protected TaskRepository taskRepository;

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected TaskListRepository taskListRepository;

}