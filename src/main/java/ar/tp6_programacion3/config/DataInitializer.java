package ar.tp6_programacion3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.tp6_programacion3.model.Estudiante;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public List<Estudiante> estudiantes() {
        List<Estudiante> lista = new ArrayList<>();
        // TODO: Agregar 10 estudiantes con empates intencionales en promedio y edad
        /* Ejemplo:
        lista.add(new Estudiante("LU-2024-001", "Martín Quiroga", 8.5, 22, 18));
        lista.add(new Estudiante("LU-2024-002", "Valeria Díaz", 8.5, 20, 15)); // empata promedio
        lista.add(new Estudiante("LU-2024-003", "Facundo Castro", 7.2, 24, 22));
        */
        return lista;
    }
}
