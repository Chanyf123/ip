package bao;

import bao.task.Task;
import java.util.ArrayList;

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

    public Task deleteTask(int taskId){
        return this.tasks.remove(taskId);
    }

    public Task getTask(int taskId){
        return this.tasks.get(taskId);
    }

    public int getSize(){
        return this.tasks.size();
    }
}
