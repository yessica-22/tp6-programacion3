package ar.edu.unlar.prog3.controller;

import ar.edu.unlar.prog3.model.Estudiante;
import ar.edu.unlar.prog3.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EstudianteController {
    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    @GetMapping("/api/estudiantes")
    public ResponseEntity<List<Estudiante>> getEstudiantes(
            @RequestParam(defaultValue = "promedio") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        try {
            List<Estudiante> datos = service.findAll();
            List<Estudiante> ordenados = service.ordenar(datos, sortBy, order);
            return ResponseEntity.ok(ordenados);
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> handleInvalidSort(IllegalArgumentException ex) {
        String criterio = ex.getMessage();
        Map<String,Object> body = new HashMap<>();
        body.put("error", "Criterio de ordenamiento no válido");
        body.put("criterioRecibido", criterio);
        body.put("criteriosAceptados", new String[]{"promedio","edad","nombre","materiasAprobadas","legajo"});
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
