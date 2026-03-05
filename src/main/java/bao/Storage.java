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

/**
 * Handles the loading and saving of task data to a local data text file.
 * This class ensures that tasks is saved and stored across different
 * sessions of the application.
 */
public class Storage {
    public static final String MSG_CORRUPTED_LINE = " Skipping corrupted line: ";
    public static final String TODO = "T";
    public static final String DEADLINE = "D";
    public static final String EVENT = "E";
    public static final String DONE_STATUS = "1";

    private String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Saves the current list of tasks to the local disk.
     * If the directory or file does not exist, they will be created.
     *
     * @param tasks The {@link ArrayList} of {@link Task} objects to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
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

    /**
     * Loads the tasks from the hard disk and returns them as an {@link ArrayList}.
     * If the file does not exist, an empty list is returned.
     *
     * @return An ArrayList containing the loaded tasks.
     * @throws FileNotFoundException If the file cannot be accessed.
     */
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

    /**
     * Interprets a single line from the data file and converts it into a Task object.
     *
     * @param line A pipe-separated string from the storage file.
     * @return The corresponding {@link Task}, or <code>null</code> if the type is unknown.
     */
    private Task parseTaskLine(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];     // "T", "D", or "E"
        boolean isDone = parts[1].equals(DONE_STATUS);
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

        if (isDone && task != null) {
            task.markAsDone();
        }
        return task;
    }
}
