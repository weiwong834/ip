import task.Task;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                tasks.add(Task.parse(line)); // You will need a method in task.Task to parse lines from the file
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, starting with an empty task list.");
        }
        return tasks;
    }

    public void saveTasksToFile(List<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Task task : tasks) {
                writer.println(task.toFileFormat()); // task.Task must override toString to format for file saving
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file.");
        }
    }
}
