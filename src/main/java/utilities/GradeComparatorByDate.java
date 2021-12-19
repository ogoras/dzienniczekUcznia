package utilities;

import model.Grade;

import java.util.Comparator;

public class GradeComparatorByDate implements Comparator<Grade> {
    @Override
    public int compare(Grade o1, Grade o2) {
        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException("Grade passed to the comparator should not be null");
        }
        if (o1.getDate().isAfter(o2.getDate())) {
            return 1;
        } else if (o1.getDate().isBefore(o2.getDate())) {
            return -1;
        } else
            return 0;
    }
}
