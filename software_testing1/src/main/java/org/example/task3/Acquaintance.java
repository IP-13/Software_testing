package org.example.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Acquaintance {
    private TimeSlice duration;
    private Person person;

    public String acquaintanceStage() {
        if (duration.label >= TimeSlice.MINUTE_AND_A_HALF.label && person.getNumOfLaughs() > 2) {
            return "Hate";
        } else if (duration.label <= TimeSlice.INSTANT.label) {
            return "Fun";
        }
        return "OK";
    }
}
