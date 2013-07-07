package org.newinstance.tnt.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

/**
 * Tests methods of class {@link Task}.
 *
 * @author mwalter
 */
public class TaskTest {

    @Test
    public void toStringTest() {
        final Owner owner = new Owner();
        owner.setName("John");

        final Task task = new Task();
        task.setCreationDate(Calendar.getInstance());
        task.setDueDate(task.getCreationDate().getTime());
        task.setDescription("A description");
        task.setStatus(Status.OPEN);
        task.setPriority(Priority.LOW);
        task.setName("Task");
        task.setOwner(owner);

        final String result = task.toString();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("Task"));
        Assert.assertTrue(result.contains("OPEN"));
        Assert.assertTrue(result.contains("LOW"));
        Assert.assertTrue(result.contains("John"));
    }
}
