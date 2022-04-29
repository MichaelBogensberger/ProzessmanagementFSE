package domain;

import exchange.DataImport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class TaskList implements Serializable {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add Task to Tasklist.
     *
     * @param task Task to add to list.
     */
    public void addTask(Task task) {
        if (task != null) {
            this.tasks.add(task);
        }
    }

    /**
     * Delete taskt from tasklist at specified index.
     *
     * @param index Indexposition to delete.
     */
    public void deleteTaskAtIndex(int index) {
        if (isValidIndex(index)) {
            this.tasks.remove(index);
        }
    }

    /**
     * Get task from tasklist at specifeid index.
     *
     * @param index Indexposition to get.
     * @return Task at specified indexposition
     */
    public Task getTaskFromIndex(int index) {
        if (isValidIndex(index)) {
            return this.tasks.get(index);
        } else {
            return null;
        }
    }

    /**
     * Pin task at specified indexposition. Pinned tasks are tasks, that should occur on top when list ist presented to user.
     *
     * @param index Indexposition for task to pin.
     */
    public void pinTaskWithIndex(int index) {
        if (isValidIndex(index)) {
            this.tasks.get(index).pinTask();
        }
    }

    /**
     * Unpin pinned task at specified index.
     *
     * @param index Indexposition for task to unpin.
     */
    public void unpinTaskWithIndex(int index) {
        if (isValidIndex(index)) {
            this.tasks.get(index).unpinTask();
        }
    }

    /**
     * Get all Tasks from list.
     *
     * @return Tasklist.
     */
    public List<Task> getAllTasks() {
        return Collections.unmodifiableList(this.tasks);
    }

    /**
     * Get all tasks, that ar tagged with a specified tag.
     *
     * @param tag Tag for which the tasks should be returned.
     * @return A list of tasks, tagged with a specified tag.
     */
    public List<Task> getAllTasksWithTag(Tag tag) {
        return this.tasks.stream().filter(task -> task.getTagList().contains(tag)).toList();
    }


    private boolean isValidIndex(int i) {
        return i >= 0 && i < this.tasks.size();
    }

    /**
     * Get all tasks from list, with the pinned tasks upfront.
     *
     * @return Tasklist with pinned tasks upfront.
     */
    public List<Task> getAllTasksPinnedInFront() {
        ArrayList<Task> result = new ArrayList<>();
        result.addAll(this.tasks.stream().filter(task -> task.isPinned()).collect(Collectors.toList()));
        result.addAll(this.tasks.stream().filter(task -> !task.isPinned()).collect(Collectors.toList()));
        return Collections.unmodifiableList(result);
    }

    /**
     * Get all tasks sorted by taskpriority.
     *
     * @return Tasklist sorted by task priority.
     */
    public List<Task> getAllTasksSortedByPriority() {
        ArrayList<Task> result = new ArrayList<>(this.tasks);
        Collections.sort(result, new TaskComparatorPriority());
        return Collections.unmodifiableList(result);
    }

    /**
     * Get all tasks, with tasks with deadlines upfront.
     *
     * @return
     */
    public List<Task> getAllTasksSortedDeadlinesFirst() {
        List<Task> deadlineTasks = new ArrayList<>();
        List<Task> noDeadlineTasks = new ArrayList<>();
        deadlineTasks = this.tasks.stream().filter(task -> task.getDeadline() != null).collect(Collectors.toList());
        Collections.sort(deadlineTasks, new TaskComparatorDeadline());
        noDeadlineTasks = this.tasks.stream().filter(task -> task.getDeadline() == null).collect(Collectors.toList());
        Collections.sort(noDeadlineTasks, new TaskAgeComparator());
        ArrayList<Task> result = new ArrayList<>();
        result.addAll(deadlineTasks);
        result.addAll(noDeadlineTasks);
        return result;
    }

    /**
     * Import tasks from defined DataImport interface and add imported tasks to internal list.
     *
     * @param importData Interface for dataimport.
     */
    public void importData(DataImport importData) {
        List<Task> importedTasks = importData.importTasks();
        this.tasks.addAll(importedTasks);
    }

}
