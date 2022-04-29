import domain.*;
import exceptions.TaskTitleNotValidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import persistence.FileManagementImplementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasksTest {
    // Alle Methoden der Taskklasse sind mit maximaler Code-Coverage zu testen
    // Es sind an passenden Stellen parametrisierte Tests zu verwenden


    private LocalDateTime date;


    private TaskTitle taskTitle;
    private TaskDescription taskDescription;
    private TaskPriority taskPriority;



    @BeforeEach
    void setup() {
        LocalDateTime date = LocalDateTime.of(2023, 1, 1,4,22);

        taskTitle = new TaskTitle("Titel vom task");
        taskDescription = new TaskDescription("desc");
        taskPriority = new TaskPriority(1);

    }


    @Test
    public void testTaskTitleNullThrowsExecption() {
        assertThrows(TaskTitleNotValidException.class, () -> new Task(null, taskDescription, date, taskPriority), "Task title null");
    }




    @Test
    public void testCreateTask() {


        Task task = new Task(taskTitle, taskDescription, date, taskPriority);

        assertTrue(task instanceof Task);

        assertAll("tickets",
                () -> assertEquals(taskTitle, task.getTaskTitle(), "Fehler bei Title"),
                () -> assertEquals(taskDescription, task.getTaskDescription(), "Fehler bei Desc"),
                () -> assertEquals(date, task.getDeadline(), "Fehler bei deadline"),
                () -> assertEquals(taskPriority, task.getTaskPriority(), "Fehler bei priority")
        );

    }


    @Test
    public void testSetTaskDescription() {
        Task task = new Task(taskTitle, taskDescription, date, taskPriority);
        TaskDescription taskDescriptionNew = new TaskDescription("NEW Desc");

        task.setTaskDescription(taskDescriptionNew);
        assertEquals(taskDescriptionNew, task.getTaskDescription(), "Fehler bei desc");
    }


    @Test
    public void testSetTaskTitle() {
        Task task = new Task(taskTitle, taskDescription, date, taskPriority);
        TaskTitle taskTitleNew = new TaskTitle("NEW Title");

        task.setTaskTitle(taskTitleNew);
        assertEquals(taskTitleNew, task.getTaskTitle(), "Fehler bei title");
    }


    @Test
    public void testSetTaskPriority() {
        Task task = new Task(taskTitle, taskDescription, date, taskPriority);
        TaskPriority taskPriorityNew = new TaskPriority(4);

        task.setTaskPriority(taskPriorityNew);
        assertEquals(taskPriorityNew, task.getTaskPriority(), "Fehler bei Priority");
    }

    @Test
    public void testSetTaskDeadline() {
        Task task = new Task(taskTitle, taskDescription, date, taskPriority);
        LocalDateTime dateNew = LocalDateTime.of(2034, 4, 2,4,22);

        task.setDeadline(dateNew);
        assertEquals(dateNew, task.getDeadline(), "Fehler bei Deadline");
    }



    @ParameterizedTest
    @DisplayName("Create diffrent tasks and test getters")
    @CsvSource({
            "Hausaufgabe, mache die Hausaufgaben, 8, 2021-04-08 12:30",
            "Für Mathe lernen, für die SA vorbereiten, 1, 2022-06-08 15:30",
            "Deutsch Text schreiben, schreibe eine Textanalyse, 4, 2022-04-08 17:30"
    })
    public void testAddDiffrentTasks(String title, String description, int priority, String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateParam = LocalDateTime.parse(dateString, formatter);

        TaskTitle taskTitleParam = new TaskTitle(title);
        TaskDescription taskDescriptionParam = new TaskDescription(description);
        TaskPriority taskPriorityParam = new TaskPriority(priority);

        Task taskParam = new Task(taskTitleParam,taskDescriptionParam,dateParam, taskPriorityParam);

        assertTrue(taskParam instanceof Task);

        assertAll("tickets",
                () -> assertEquals(taskTitleParam, taskParam.getTaskTitle(), "Fehler bei Title"),
                () -> assertEquals(taskDescriptionParam, taskParam.getTaskDescription(), "Fehler bei Desc"),
                () -> assertEquals(dateParam, taskParam.getDeadline(), "Fehler bei deadline"),
                () -> assertEquals(taskPriorityParam, taskParam.getTaskPriority(), "Fehler bei priority")
        );


    }


    @Test
    public void testPinTask() {
        Task pinnedTask = new Task(taskTitle,taskDescription,date,taskPriority);
        Task normalTask = new Task(taskTitle,taskDescription,date,taskPriority);

        pinnedTask.pinTask();

        assertAll("isPinned",
                () -> assertTrue(pinnedTask.isPinned()),
                () -> assertFalse(normalTask.isPinned())
        );


    }


    @Test
    public void testTaskWithPriorityNull() {
        Task taskWithPrioNull = new Task(taskTitle,taskDescription,date,null);
        assertEquals(new TaskPriority(1), taskWithPrioNull.getTaskPriority());
    }



    @Test
    public void testTaskStatus() {
        Task task = new Task(taskTitle,taskDescription,date,taskPriority);
        assertEquals(TaskStatus.open, task.getTaskStatus());
    }


    @Test
    public void testChangeTaskStatus() {
        Task task = new Task(taskTitle,taskDescription,date,taskPriority);
        task.setTaskStatusTo(TaskStatus.done);
        assertEquals(TaskStatus.done, task.getTaskStatus());
    }



}
