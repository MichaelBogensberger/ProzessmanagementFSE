package persistence;

import domain.TaskList;
import exceptions.LoadTaskListFromFileException;
import exceptions.SaveTaskListToFileException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManagementImplementation implements FileManagement {
    @Override
    public TaskList loadTaskListFromFile() throws LoadTaskListFromFileException {
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(FileManagement.getFileName()))) {
            return (TaskList) os.readObject();
        } catch (Exception exception) {
            throw new LoadTaskListFromFileException("Tasklist could not be load from file: " + exception.getMessage());
        }
    }

    @Override
    public void saveTaskListToFile(TaskList taskList) throws SaveTaskListToFileException {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FileManagement.getFileName()))) {
            os.writeObject(taskList);
            os.flush();
        } catch (Exception exception) {
            throw new SaveTaskListToFileException("Tasklist could not be saved! " + exception.getMessage());
        }
    }

}
