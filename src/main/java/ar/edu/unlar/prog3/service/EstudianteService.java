package ar.edu.unlar.prog3.service;

import ar.edu.unlar.prog3.model.Estudiante;
import ar.edu.unlar.prog3.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EstudianteService {
    private final EstudianteRepository repository;
    private final Map<String, Comparator<Estudiante>> comparators = new HashMap<>();

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
        initComparators();
    }

    private void initComparators() {
        // Base comparators are ascending; caller may request reversed order
        Comparator<Estudiante> byPromedio = Comparator.comparingDouble(Estudiante::getPromedio);
        Comparator<Estudiante> byEdad = Comparator.comparingInt(Estudiante::getEdad);
        Comparator<Estudiante> byNombre = Comparator.comparing(Estudiante::getNombre, String.CASE_INSENSITIVE_ORDER);
        Comparator<Estudiante> byMaterias = Comparator.comparingInt(Estudiante::getCantidadMateriasAprobadas);
        Comparator<Estudiante> byLegajo = Comparator.comparing(Estudiante::getLegajo);

        // ensure predictable tie-breaker by legajo
        comparators.put("promedio", byPromedio.thenComparing(byLegajo));
        comparators.put("edad", byEdad.thenComparing(byLegajo));
        comparators.put("nombre", byNombre.thenComparing(byLegajo));
        comparators.put("materiasAprobadas", byMaterias.thenComparing(byLegajo));
        comparators.put("legajo", byLegajo);
    }

    public List<Estudiante> ordenar(List<Estudiante> lista, String sortBy, String order) {
        // soporta múltiples criterios separados por coma: sortBy=promedio,edad  order=desc,asc
        String[] keys = sortBy.split("\\s*,\\s*");
        String[] orders = order == null ? new String[0] : order.split("\\s*,\\s*");

        Comparator<Estudiante> comp = null;
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            Comparator<Estudiante> c = comparators.get(key);
            if (c == null) {
                throw new IllegalArgumentException(key);
            }
            String ord = (i < orders.length) ? orders[i] : "asc";
            if ("desc".equalsIgnoreCase(ord)) {
                c = c.reversed();
            }
            comp = (comp == null) ? c : comp.thenComparing(c);
        }
        if (comp == null) {
            throw new IllegalArgumentException(sortBy);
        }
        return lista.stream().sorted(comp).collect(Collectors.toList());
    }

    public List<Estudiante> findAll() {
        return repository.findAll();
    }
}
