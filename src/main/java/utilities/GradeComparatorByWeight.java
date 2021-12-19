package utilities;

import model.Grade;

import java.util.Comparator;

public class GradeComparatorByWeight implements Comparator<Grade> {
    @Override
    public int compare(Grade o1, Grade o2) {
        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException("Grade passed to the comparator should not be null");
        }
        return Integer.compare(o1.getWeight(), o2.getWeight());
    }
}
