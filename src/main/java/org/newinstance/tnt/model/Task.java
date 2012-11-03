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

package org.newinstance.tnt.model;

import org.newinstance.tnt.utility.DateHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

/**
 * A task to do.
 *
 * @author mwalter
 */
@Entity
@NamedQuery(name="SEARCH_ALL_TASK", query="from Task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Calendar creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "due_date")
    private Calendar dueDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Calendar getCreationDate() {
        return creationDate;
    }

    public String getCreationDateFormatted() {
        return DateHelper.formatDate(creationDate);
    }

    public String getDescription() {
        return description;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public String getDueDateFormatted() {
        return DateHelper.formatDate(dueDate);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setCreationDate(final Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setDueDate(final Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setOwner(final Owner owner) {
        this.owner = owner;
    }

    public void setPriority(final Priority priority) {
        this.priority = priority;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Task [");
        builder.append("id=").append(id).append(", ");
        builder.append("name=").append(name).append(", ");
        builder.append("description=").append(description).append(", ");
        builder.append("creationDate=").append(DateHelper.formatDate(creationDate)).append(", ");
        builder.append("dueDate=").append(DateHelper.formatDate(dueDate)).append(", ");
        builder.append("status=").append(status.name()).append(", ");
        builder.append("priority=").append(priority.name()).append(", ");
        builder.append("owner=").append(owner.getName());
        builder.append("]");
        return builder.toString();
    }
}
