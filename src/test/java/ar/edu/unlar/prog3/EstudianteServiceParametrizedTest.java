package ar.edu.unlar.prog3;

import ar.edu.unlar.prog3.model.Estudiante;
import ar.edu.unlar.prog3.repository.EstudianteRepository;
import ar.edu.unlar.prog3.service.EstudianteService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EstudianteServiceParametrizedTest {

    @ParameterizedTest
    @CsvSource({
            "promedio,desc,LU-2024-005,LU-2024-004",
            "promedio,asc,LU-2024-009,LU-2024-006",
            "edad,asc,LU-2024-006,LU-2024-002",
            "materiasAprobadas,desc,LU-2024-005,LU-2024-004"
    })
    void ordenar_variasCombinaciones(String sortBy, String order, String expectedFirst, String expectedSecond) {
        EstudianteRepository repo = new EstudianteRepository();
        repo.init();
        EstudianteService service = new EstudianteService(repo);

        List<Estudiante> res = service.ordenar(repo.findAll(), sortBy, order);
        assertNotNull(res);
        assertTrue(res.size() >= 2);
        assertEquals(expectedFirst, res.get(0).getLegajo());
        assertEquals(expectedSecond, res.get(1).getLegajo());
    }
}
