package bao;

import bao.task.Task;
import bao.task.Todo;
import bao.task.Deadline;
import bao.task.Event;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Storage {
    public static final String MSG_CORRUPTED_LINE = " Skipping corrupted line: ";
    public static final String TODO = "T";
    public static final String DEADLINE = "D";
    public static final String EVENT = "E";

    private String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);

        // Ensure the directory exists (e.g., /data/)
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        //Open the file for writing (overwrites the old file by default)
        FileWriter fw = new FileWriter(file);
        for (Task t : tasks) {
            fw.write(t.toFileFormat() + System.lineSeparator());
        }
        fw.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        ArrayList<Task> loadedTasks = new ArrayList<>();

        if (!f.exists()) {
            return loadedTasks; // Return empty list if no file found
        }

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            try {
                loadedTasks.add(parseTaskLine(line));
            } catch (Exception e) {
                System.out.println(MSG_CORRUPTED_LINE + line);
            }
        }
        return loadedTasks;
    }

    private Task parseTaskLine(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];     // "T", "D", or "E"
        boolean isDone = parts[1].equals("1"); // "1" means done status
        Task task = null;

        switch (type) {
        case TODO:
            task = new Todo(parts[2]); // Desc
            break;
        case DEADLINE:
            task = new Deadline(parts[2], parts[3]); // Desc, By
            break;
        case EVENT:
            task = new Event(parts[2], parts[3], parts[4]); // Desc, From, To
            break;
        default:
            System.out.println(MSG_CORRUPTED_LINE + line);
            break;
        }

        if (isDone) task.markAsDone();
        return task;
    }
}
