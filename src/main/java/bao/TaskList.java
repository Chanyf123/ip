package bao;

import bao.task.Task;
import java.util.ArrayList;

/**
 * Provides methods to for operations (e.g. add, delete, and get tasks) handling
 * an <code>ArrayList</code> of <code>Task</code> objects.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public ArrayList<Task> getList(){
        return this.tasks;
    }

    public void addTask(Task taskToAdd){
        this.tasks.add(taskToAdd);
    }

    public Task deleteTask(int taskId) throws BaoException {
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new BaoException(BaoException.OUT_OF_BOUNDS);
        }
        return this.tasks.remove(taskId);
    }

    public Task getTask(int taskId) throws BaoException {
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new BaoException(BaoException.OUT_OF_BOUNDS);
        }
        return this.tasks.get(taskId);
    }

    public int getSize() {
        return this.tasks.size();
    }
}
