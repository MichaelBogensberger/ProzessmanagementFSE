package domain;

import exceptions.TaskTitleNotValidException;

import java.io.Serializable;

public record TaskTitle(String taskTitle) implements Serializable {

    /**
     * @param taskTitle Title for the task
     * @throws TaskTitleNotValidException Task title has to have at least 2 characters.
     */
    public TaskTitle(String taskTitle) {
        if (taskTitle != null && taskTitle.length() > 1) {
            this.taskTitle = taskTitle;
        } else {
            throw new TaskTitleNotValidException();
        }
    }
}
