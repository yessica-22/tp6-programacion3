# RESPUESTAS

## Pregunta 1
Collections.sort() no compila porque Estudiante no implementa Comparable<T>. Java exige que los elementos tengan un orden natural (Comparable) o se le pase un Comparator al método de orden.

## Pregunta 2
Elegí `promedio` como orden natural porque representa mérito académico, un criterio intuitivo para estudiantes. Si mañana se pide ordenar por `cantidadMateriasAprobadas`, no modificaría `compareTo`; mejor crear un Comparator externo para evitar violar SRP y OCP.

## Pregunta 3
Comparable fija un único criterio, lo que viola SRP (la clase tiene dos responsabilidades: datos y política de orden) y OCP (cambio de criterio exige modificar la clase). Comparator permite añadir estrategias sin cambiar la clase.

## Pregunta 4
Overflow de enteros ocurre cuando una operación excede el rango representable de int y el valor se envuelve (wrap-around). La resta `a - b` puede overflow y devolver un signo incorrecto, rompiendo el contrato de compare/compareTo (simetría/transitividad). `Integer.compare(a,b)` evita overflow internamente y devuelve -1/0/1 correctamente.

## Pregunta 5
Usando `Map<String, Comparator<T>>` se aplica el patrón Strategy: se selecciona una estrategia (comparator) por clave, usando polimorfismo para aplicar comportamiento sin `switch`. Es más extensible y desacoplado.

## Ejercicio 10 (opcional)
Implementada Opción A — Orden multinivel parametrizable: el endpoint acepta múltiples criterios separados por coma, por ejemplo `sortBy=promedio,edad` y `order=desc,asc` y los aplica en el orden recibido.
