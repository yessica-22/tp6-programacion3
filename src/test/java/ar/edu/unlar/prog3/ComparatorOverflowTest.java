package ar.edu.unlar.prog3;

import ar.edu.unlar.prog3.model.Estudiante;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class ComparatorOverflowTest {
    @Test
    public void restaTramposa_producesWrongOrder_dueToOverflow() {
        Estudiante a = new Estudiante("A","A",0.0, Integer.MAX_VALUE,0);
        Estudiante b = new Estudiante("B","B",0.0, -1,0);

        Comparator<Estudiante> resta = (e1, e2) -> e1.getEdad() - e2.getEdad();
        Estudiante[] arr = new Estudiante[]{a,b};
        Arrays.sort(arr, resta);
        // With overflow, subtraction gives negative, incorrectly placing a before b
        assertNotEquals(Integer.compare(a.getEdad(), b.getEdad()), Integer.signum(resta.compare(a,b)));
    }

    @Test
    public void integerCompare_correctlyOrders() {
        Estudiante a = new Estudiante("A","A",0.0, Integer.MAX_VALUE,0);
        Estudiante b = new Estudiante("B","B",0.0, -1,0);

        Comparator<Estudiante> cmp = (e1, e2) -> Integer.compare(e1.getEdad(), e2.getEdad());
        Estudiante[] arr = new Estudiante[]{a,b};
        Arrays.sort(arr, cmp);
        assertTrue(Arrays.asList(arr).get(0).getEdad() < Arrays.asList(arr).get(1).getEdad());
    }
}
