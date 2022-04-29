package persistence;

import domain.TaskList;
import exceptions.LoadTaskListFromFileException;
import exceptions.SaveTaskListToFileException;

public interface FileManagement {
    static String getFileName() {
        return "data.bin";
    }

    /**
     * Load a tasklist from file.
     *
     * @return Loaded tasklist.
     */
    TaskList loadTaskListFromFile() throws LoadTaskListFromFileException;

    /**
     * Save a tasklist to file.
     *
     * @param taskList Task list to be safed.
     */
    void saveTaskListToFile(TaskList taskList) throws SaveTaskListToFileException;
}
