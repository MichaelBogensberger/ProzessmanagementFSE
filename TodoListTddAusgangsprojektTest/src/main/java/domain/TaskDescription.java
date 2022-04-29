package domain;


import exceptions.TaskDescriptionNotValidException;

import java.io.Serializable;

public record TaskDescription(String taskdescription) implements Serializable {
    public TaskDescription(String taskdescription) {
        if (taskdescription != null && taskdescription.length() >= 1) {
            this.taskdescription = taskdescription;
        } else {
            throw new TaskDescriptionNotValidException();
        }
    }
}
