package domain;

import exceptions.TaskPriorityNotValidException;

import java.io.Serializable;

public record TaskPriority(int priority) implements Serializable {
    /**
     * @param priority Taskpriority, defined by values betwwen 1 and 10.
     * @throws TaskPriorityNotValidException Exception thrown for invalid task priorities.
     */
    public TaskPriority(int priority) {
        if (priority > 0 && priority <= 10) {
            this.priority = priority;
        } else {
            throw new TaskPriorityNotValidException();
        }
    }
}
