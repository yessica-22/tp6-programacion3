package ar.edu.unlar.prog3;

import ar.edu.unlar.prog3.model.Estudiante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainDePrueba {
    public static void main(String[] args) {
        List<Estudiante> lista = new ArrayList<>();
        lista.add(new Estudiante("LU-2024-001", "Martín Quiroga", 8.5, 22, 18));
        lista.add(new Estudiante("LU-2024-002", "Valeria Díaz", 8.5, 20, 15));
        lista.add(new Estudiante("LU-2024-003", "Facundo Castro", 7.2, 24, 22));
        lista.add(new Estudiante("LU-2024-004", "Camila Torres", 9.1, 21, 24));
        lista.add(new Estudiante("LU-2024-005", "Lucas González", 9.1, 23, 24));
        
        // Ejercicio 1 - Descomentar la siguiente línea, ver el error de compilación, copiarlo y pegarlo abajo.
        // Collections.sort(lista);
        
        /* 
         * ERROR DE COMPILACIÓN OBTENIDO:
         * java: no suitable method found for sort(java.util.List<ar.edu.unlar.prog3.model.Estudiante>)
         *   method java.util.Collections.<T>sort(java.util.List<T>) is not applicable
         *     (inference variable T has incompatible bounds
         *       equality constraints: ar.edu.unlar.prog3.model.Estudiante
         *       lower bounds: java.lang.Comparable<? super T>)
         *   method java.util.Collections.<T>sort(java.util.List<T>,java.util.Comparator<? super T>) is not applicable
         *     (cannot infer type-variable(s) T
         *       (actual and formal argument lists differ in length))
         */
         
         // Ejercicio 2 - Después de implementar Comparable en Estudiante, verificar que se ordena bien y mostrar por consola iterando la lista.
         System.out.println("--- Lista antes de ordenar ---");
         lista.forEach(System.out::println);

         Collections.sort(lista);

         System.out.println("\n--- Lista después de ordenar (por promedio descendente) ---");
         lista.forEach(System.out::println);
    }
}
