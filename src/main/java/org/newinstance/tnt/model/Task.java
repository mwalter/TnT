/*
 * TnT - Things and tasks to do
 * Licensed under General Public License v3 (GPLv3)
 * newInstance.org, 2012-2013
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

package org.newinstance.tnt.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.newinstance.tnt.utility.ResourceLoader;

/**
 * A task to do.
 *
 * @author mwalter
 */
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public String getStatusLocalized() {
        return ResourceLoader.getResource("status." + status.name());
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setDueDate(final LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public boolean isNew() {
        return id == null;
    }

    public boolean isOpen() {
        return Status.OPEN.equals(status);
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate);
    }

    public boolean isDone() {
        return Status.DONE.equals(status);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
