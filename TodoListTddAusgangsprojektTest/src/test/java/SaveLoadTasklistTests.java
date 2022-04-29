import domain.*;
import exceptions.LoadTaskListFromFileException;
import exceptions.SaveTaskListToFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.FileManagement;
import persistence.FileManagementImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SaveLoadTasklistTests {
    // Alle Methoden der File-Management-Klasse mit maximaler Code-Coverage sind zu testen


    private FileManagement fileManagement;
    private TaskList taskList;
    private LocalDateTime now;

    private TaskTitle taskTitle;
    private TaskDescription taskDescription;
    private TaskPriority taskPriority;
    private Task task;
    private Task task1;

    @BeforeEach
    void setup() {
        fileManagement = new FileManagementImplementation();

        LocalDateTime date = LocalDateTime.of(2023, 1, 1,4,22);
        taskList = new TaskList();

        taskTitle = new TaskTitle("Titel vom task");
        taskDescription = new TaskDescription("desc");
        taskPriority = new TaskPriority(1);
        task = new Task(taskTitle,taskDescription,date,taskPriority);
        task1 = new Task(taskTitle,taskDescription,date,taskPriority);

    }

    @Test
    public void textLoadTaskListFromFile() {

        try {
            TaskList foundList = fileManagement.loadTaskListFromFile();
            //System.out.println(foundList.getAllTasks().size());
            assertEquals(2, foundList.getAllTasks().size());

        } catch (LoadTaskListFromFileException e) {
            fail("Loading nicht erfolgreich: " + e.getMessage());
        }

    }




    @Test
    public void testSaveTest() {
        taskList.addTask(task);
        taskList.addTask(task1);

        try {
            fileManagement.saveTaskListToFile(taskList);
        } catch (SaveTaskListToFileException e) {
            fail("Speicherung nicht erfolgreich: " + e.getMessage());
        }

    }







}
