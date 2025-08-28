package Monarch.Core;

import Monarch.Exceptions.MonException;
import Monarch.Tasks.Deadline;
import Monarch.Tasks.Event;
import Monarch.Tasks.toDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void add_eachTypeOfTask() {
        TaskList tasks = new TaskList();
        tasks.set();
        try {
            tasks.add(new toDo("Borrow book"));
            tasks.add(new Deadline("Study ahead", "1/01/2025 0000"));
            tasks.add(new Event("Visit library", "Morning", "Evening"));

            assertEquals(3, tasks.size());
        } catch (RuntimeException e) {
            fail();
        }
    }

    @Test
    public void clear() {
        TaskList tasks = new TaskList();
        tasks.set();
        try {
            tasks.add(new toDo("Borrow book"));
            tasks.add(new Deadline("Study ahead", "1/01/2025 0000"));
            tasks.add(new Event("Visit library", "Morning", "Evening"));
            tasks.clear();

            assertEquals(0, tasks.size());
        } catch (RuntimeException e) {
            fail();
        }
    }
}
