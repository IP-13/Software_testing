import org.example.task1.Acos;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@Tag("TaylorAcos")
public class TaylorAcosTest {
    private final double absoluteError = 0.04;
    private static Acos acos;


    @BeforeAll
    public static void init() {
        acos = new Acos();
    }


    private double countAbsoluteError(double x) {
        return Math.abs(Math.acos(x) - acos.countTaylorAcos(x));
    }


    @ParameterizedTest
    @ValueSource(doubles = {-0.5, -0.99, 0.005, 0.8, 0.99999, -0.04})
    public void testRegularPoints(double d) {
        assertTrue(countAbsoluteError(d) < absoluteError);
    }


    @ParameterizedTest
    @ValueSource(doubles = {-1, 0, 1})
    public void testExtremePoints(double d) {
        assertEquals(Math.acos(d), acos.countTaylorAcos(d), absoluteError);
    }


    @ParameterizedTest
    @ValueSource(doubles = {-1.01, 1.01, 2, -10000, 5, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NaN})
    public void testOutOfBoundPoints(double d) {
        assertThrows(IllegalArgumentException.class, () -> acos.countTaylorAcos(d));
    }

}
