package org.example.task3;

public enum TimeSlice {
    MOMENT(1),
    INSTANT(2),
    MINUTE_AND_A_HALF(3),
    HOUR(4);

    public final int label;


    private TimeSlice(int label) {
        this.label = label;
    }

}
