package org.example.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Setter
@Getter
public class Person {
    private final String name;
    private final Gender gender;
    private ChemicalElement[] state;
    private Boolean isPayingAttention;
    private int numOfLaughs;
    private ArrayList<Acquaintance> acquaintances;


    public void laugh() {
        numOfLaughs++;
    }


    public String vaporize(ChemicalElement[] state, TimeSlice timeSlice) {
        numOfLaughs--;
        this.state = state;
        return "The " + this.name + " will be vaporized to " + Arrays.toString(state) + " in " + timeSlice.toString();
    }


    public boolean isPayingAttention() {
        numOfLaughs--;
        return isPayingAttention = state == null || state.length == 0;
    }


    public void addAcquaintance(Acquaintance acquaintance) {
        if (acquaintance.getPerson() != this && !acquaintance.equals(getAcquaintanceByPerson(acquaintance.getPerson()))) {
            this.acquaintances.add(acquaintance);
        }
    }


    public Acquaintance getAcquaintanceByPerson(Person person) {
        Optional<Acquaintance> result = acquaintances.stream().
                filter((Acquaintance acquaintance) -> acquaintance.getPerson().equals(person)).findFirst();
        return result.orElse(null);
    }


    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass()) && ((Person) obj).name.equals(this.name);
    }
}
