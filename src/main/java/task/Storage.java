package task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages loading and saving of tasks to a file.
 * This class provides the necessary functionality to store task data persistently,
 * ensuring that tasks are available upon restarting the application.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        createFileIfNotExist();
    }

    private void createFileIfNotExist() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs(); // Ensure the directory exists
                file.createNewFile(); // Create the file if it does not exist
            } catch (IOException e) {
                System.out.println("Unable to create the file: " + e.getMessage());
            }
        }
    }

    public List<Task> loadTasksFromFile() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tasks.add(Task.parse(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, starting with an empty task list.");
        }
        return tasks;
    }

    public void saveTasksToFile(List<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Task task : tasks) {
                writer.println(task.toFileFormat());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file.");
        }
    }
}
