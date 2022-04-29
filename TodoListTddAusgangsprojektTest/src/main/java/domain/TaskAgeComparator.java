package domain;

import java.util.Comparator;

public class TaskAgeComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        if (o1 == null && o2 != null) return 1;
        if (o1 != null && o2 == null) return -1;
        if (o1 == o2) return 0;
        if (o1.getCreationTimestamp().isAfter(o2.getCreationTimestamp())) {
            return 1;
        } else if (o1.getCreationTimestamp().isBefore(o2.getCreationTimestamp())) {
            return -1;
        } else {
            return 0;
        }


    }
}
