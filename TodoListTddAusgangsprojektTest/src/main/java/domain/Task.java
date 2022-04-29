package domain;

import exceptions.DeadlineNotValidException;
import exceptions.TaskDescriptionNotValidException;
import exceptions.TaskPriorityNotValidException;
import exceptions.TaskTitleNotValidException;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Getter
public class Task implements Serializable {

    private TaskTitle taskTitle;
    private TaskDescription taskDescription;
    private TaskStatus taskStatus;
    private LocalDateTime creationTimestamp;
    private LocalDateTime deadline;
    private TaskPriority taskPriority;
    private Set<Tag> tagList;
    private boolean pinned;

    public Task(TaskTitle taskTitle, TaskDescription taskDescription, LocalDateTime deadline, TaskPriority taskPriority) {
        if (taskTitle == null) throw new TaskTitleNotValidException();
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription; // can be null
        this.taskStatus = TaskStatus.open;
        this.creationTimestamp = LocalDateTime.now();
        this.deadline = deadline; //can be null
        if (taskPriority == null) {
            this.taskPriority = new TaskPriority(1);
        } else {
            this.taskPriority = taskPriority;
        }
        this.tagList = new HashSet<>();
        this.pinned = false;
        TreeSet treeSet = new TreeSet();
    }

    /**
     * Tag a task.
     *
     * @param tag Tag to be used for tagging the task.
     */
    public void addTag(Tag tag) {
        if (tag != null) {
            this.tagList.add(tag);
        }
    }

    /**
     * Get the list of tags used for tagging the task.
     *
     * @return Set of tags used for tagging the task.
     */
    public Set<Tag> getTagList() {
        return Collections.unmodifiableSet(tagList);
    }

    public void changeTagName(Tagname from, Tagname to) {
        if (this.tagList.contains(new Tag(from))) {
            this.tagList.remove(new Tag(from));
            this.tagList.add(new Tag(to));
        }
    }

    /**
     * Setter for task title
     *
     * @param taskTitle Title to be set.
     * @throws TaskTitleNotValidException
     */
    public void setTaskTitle(TaskTitle taskTitle) {
        if (taskTitle == null) throw new TaskTitleNotValidException();
        this.taskTitle = taskTitle;
    }

    /**
     * Setter for task description
     *
     * @param taskDescription Description to be set
     * @throws TaskDescriptionNotValidException
     */
    public void setTaskDescription(TaskDescription taskDescription) {
        if (taskDescription == null) throw new TaskDescriptionNotValidException();
        this.taskDescription = taskDescription;
    }

    /**
     * Set deadline for task.
     *
     * @param newDeadline Deadline to be set for task.
     */
    public void setDeadline(LocalDateTime newDeadline) {
        if (newDeadline == null) throw new DeadlineNotValidException();
        this.deadline = newDeadline;
    }

    /**
     * Pin a task.
     */
    public void pinTask() {
        this.pinned = true;
    }

    /**
     * Unpin a task.
     */
    public void unpinTask() {
        this.pinned = false;
    }

    /**
     * Set tast status
     *
     * @param taskStatus Status for task to be set
     */
    public void setTaskStatusTo(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * Set task priority
     *
     * @param taskPriority Taskpriority to be set
     */
    public void setTaskPriority(TaskPriority taskPriority) {
        if (taskPriority == null) throw new TaskPriorityNotValidException();
        this.taskPriority = taskPriority;
    }
}