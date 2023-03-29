import org.example.task3.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@Tag("DomainModel")
public class DomainModelTest {
    @Test
    public void testVaporize() {
        Person laughingMan = new Person("Laughing man", Gender.MALE, null, null, 0, new ArrayList<>());

        ChemicalElement[] state = new ChemicalElement[]
                {
                        ChemicalElement.CARBON_OXIDE,
                        ChemicalElement.HYDROGEN,
                        ChemicalElement.OZONE
                };

        assertEquals(laughingMan.vaporize(state, TimeSlice.MINUTE_AND_A_HALF),
                "The Laughing man will be vaporized to [CARBON_OXIDE, HYDROGEN, OZONE] in MINUTE_AND_A_HALF");

        assertEquals(laughingMan.getState(), state);


    }


    @Test
    public void personAcquaintanceStageTest() {
        Person girl = new Person("Girl", Gender.FEMALE, null, null, 0, new ArrayList<>());

        Person person1 = new Person("Good person", Gender.FEMALE, null, null, 0, null);
        Person person2 = new Person("Laughing man", Gender.MALE, null, null, 3, null);
        Person person3 = new Person("Some person", Gender.MALE, null, null, 1, null);
        Person person4 = new Person("Other person", Gender.MALE, null, null, 1, null);

        Acquaintance acquaintance1 = new Acquaintance(TimeSlice.MOMENT, person1);
        Acquaintance acquaintance2 = new Acquaintance(TimeSlice.MINUTE_AND_A_HALF, person2);
        Acquaintance acquaintance3 = new Acquaintance(TimeSlice.HOUR, person3);
        Acquaintance acquaintance4 = new Acquaintance(TimeSlice.MINUTE_AND_A_HALF, person4);

        girl.addAcquaintance(acquaintance1);
        girl.addAcquaintance(acquaintance2);
        girl.addAcquaintance(acquaintance3);
        girl.addAcquaintance(acquaintance4);

        assertEquals(girl.getAcquaintanceByPerson(person1).acquaintanceStage(), "Fun");
        assertEquals(girl.getAcquaintanceByPerson(person2).acquaintanceStage(), "Hate");
        assertEquals(girl.getAcquaintanceByPerson(person3).acquaintanceStage(), "OK");
        assertEquals(girl.getAcquaintanceByPerson(person4).acquaintanceStage(), "OK");
    }


    @Test
    public void personAcquaintanceAddTest() {
        Person person = new Person("Some person", null, null, null, 0, new ArrayList<>());
        Acquaintance acquaintance = new Acquaintance(TimeSlice.INSTANT, person);
        assertDoesNotThrow(() -> person.addAcquaintance(acquaintance));
    }


    @Test
    public void isPayingAttentionFalseTest() {
        ChemicalElement[] state = new ChemicalElement[]{ChemicalElement.OZONE};
        Person person = new Person("Some Person", Gender.FEMALE, state, null, 0, new ArrayList<>());
        assertFalse(person.isPayingAttention());
    }


    @Test
    public void isPayingAttentionTrueTest() {
        Person person = new Person("Some Person", Gender.FEMALE, null, null, 0, new ArrayList<>());
        person.setState(null);
        assertTrue(person.isPayingAttention());
        person.setState(new ChemicalElement[0]);
        assertTrue(person.isPayingAttention());
    }

}
