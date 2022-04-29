import domain.*;
import exchange.DataImport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TaskListTests {
    // Alle Methoden der Taskliste sind mit maximaler Code-Coverage zu testen
    // Es sind an passenden Stellen parametrisierte Tests zu verwenden
    // Die importData-Methode muss mit Mock getestet werden.

    private TaskList taskList;


    private TaskTitle taskTitle;
    private TaskDescription taskDescription;
    private TaskPriority taskPriority;
    private Task task;
    private Task task1;
    private LocalDateTime date;

    private Tag tag;

    @Mock
    private DataImport dataImportMock;


    @BeforeEach
    void setup() {
        LocalDateTime date = LocalDateTime.of(2023, 1, 1, 4, 22);
        taskList = new TaskList();

        taskTitle = new TaskTitle("Hausaufgaben");
        taskDescription = new TaskDescription("mache alle wichtigen Hausaufgaben!");
        taskPriority = new TaskPriority(1);
        task = new Task(taskTitle, taskDescription, date, taskPriority);
        task1 = new Task(taskTitle, taskDescription, date, taskPriority);

        tag = new Tag("Hausaufgaben");

    }

    @Test
    public void testGetTaskByIndex() {
        taskList.addTask(task);
        Task foundTask = taskList.getTaskFromIndex(0);
        assertFalse(foundTask instanceof Task);

    }

    @Test
    public void testListSize() {
        taskList.addTask(task);
        taskList.addTask(task1);

        assertEquals(2, taskList.getAllTasks().size());

    }


    @Test
    public void testDeleteTaskFromList() {
        taskList.addTask(task);
        taskList.addTask(task1);

        taskList.deleteTaskAtIndex(1);

        assertEquals(1, taskList.getAllTasks().size());
    }


    @Test
    public void testDataImport() {

        List<Task> dataList = new ArrayList<Task>();
        dataList.add(task);
        dataList.add(task1);

        Mockito.when(dataImportMock.importTasks()).thenReturn(dataList);

        taskList.importData(dataImportMock);

        assertEquals(2, taskList.getAllTasks().size());

        Mockito.verify(dataImportMock).importTasks();

    }


    @Test
    public void testGetAllTasksByTag() {
        task.addTag(tag);
        task1.addTag(tag);

        taskList.addTask(task);
        taskList.addTask(task1);

        assertEquals(2, taskList.getAllTasksWithTag(tag).size());

    }


    @Test
    public void testSortedByPriority() {
        TaskPriority high = new TaskPriority(1);
        TaskPriority low = new TaskPriority(10);

        Task taskWithPrioLow = new Task(taskTitle, taskDescription, date, low);
        Task taskWithPrioHigh = new Task(taskTitle, taskDescription, date, high);

        taskList.addTask(taskWithPrioHigh);
        taskList.addTask(taskWithPrioLow);


        List<Task> foundTaskList = taskList.getAllTasksSortedByPriority();


        assertAll("assert sortion",
                () -> assertEquals(taskWithPrioLow, foundTaskList.get(0)),
                () -> assertEquals(taskWithPrioHigh, foundTaskList.get(1))
        );


    }


    @ParameterizedTest
    @DisplayName("Get all with specific Tag")
    @CsvSource({
            "Hausaufgabe, mache die Hausaufgaben, 8, hausi",
            "Für Mathe lernen, für die SA vorbereiten, 1, test",
            "Deutsch Text schreiben, schreibe eine Textanalyse, 4, freiwillig"
    })
    void testGetWithTag(String title, String description, int priority, String tag) {

        TaskTitle taskTitleParam = new TaskTitle(title);
        TaskDescription taskDescriptionParam = new TaskDescription(description);
        TaskPriority taskPriorityParam = new TaskPriority(priority);
        Task taskParam = new Task(taskTitleParam, taskDescriptionParam, date, taskPriorityParam);
        taskParam.addTag(new Tag(tag));

        taskList.addTask(taskParam);
        //System.out.println(taskList.getAllTasksWithTag(new Tag(tag)).size());

        assertAll("check tasks",
                () -> assertEquals(taskParam.getTaskTitle(), taskTitleParam),
                () -> assertEquals(taskParam.getTaskDescription(), taskDescriptionParam),
                () -> assertTrue(taskList.getAllTasksWithTag(new Tag(tag)).size() > 0)

        );


    }


    @Test
    public void testPinTask() {
        taskList.addTask(task);
        taskList.addTask(task1);

        taskList.pinTaskWithIndex(1);

        List<Task> foundTasks = taskList.getAllTasksPinnedInFront();

        assertEquals(task1, foundTasks.get(0));

    }


}
