package sorteo;

import java.util.*;

public class Equipo {

    private final Scanner scanner = new Scanner(System.in);

    // Método para ingresar equipos según la etapa del torneo
    public List<String> ingresarEquiposSegunEtapa(int numeroEtapa) throws NumeroEquiposInvalidoException {
        List<String> equipos;

        switch (numeroEtapa) {
            case 1 -> {
                System.out.println("ETAPA DE OCTAVOS DE FINAL");
                equipos = ingresarEquipos(16);  // Ingresar 16 equipos
            }
            case 2 -> {
                System.out.println("ETAPA DE CUARTOS DE FINAL");
                equipos = ingresarEquipos(8);  // Ingresar 8 equipos
            }
            case 3 -> {
                System.out.println("ETAPA DE SEMIFINALES");
                equipos = ingresarEquipos(4);  // Ingresar 4 equipos
            }
            default -> throw new NumeroEquiposInvalidoException("La etapa seleccionada no es válida. Intente nuevamente.");
        }
        return equipos;
    }

    public List<String> ingresarEquipos(int numEquipos) throws NumeroEquiposInvalidoException {
        // Verificar si el número de equipos es válido
        if (numEquipos <= 0) {
            throw new NumeroEquiposInvalidoException("Número de equipos inválido. Debe ser positivo.");
        }

        List<String> equipos = new ArrayList<>();
        Set<String> equipoDuplicados = new HashSet<>();

        System.out.println("Ingrese los nombres de los Equipos:");

        // Ingresar los nombres de los equipos
        for (int i = 0; i < numEquipos; i++) {
            boolean nombreValido = false;

            while (!nombreValido) {
                System.out.print("Equipo " + (i + 1) + ": ");
                String nombreEquipo = scanner.nextLine();

                // Verificar si el equipo ya existe
                if (equipoDuplicados.contains(nombreEquipo)) {
                    try {
                        throw new NombreEquipoDuplicadoException("El nombre del Equipo '" + nombreEquipo + "' ya existe. Intente nuevamente.");
                    } catch (NombreEquipoDuplicadoException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    // Si el nombre del equipo no está duplicado, agregarlo a la lista
                    equipos.add(nombreEquipo);
                    equipoDuplicados.add(nombreEquipo);
                    nombreValido = true;
                }
            }
        }
        return equipos; //Retornar la lista de Equipos
    }
}

// Excepción para número de equipos inválido
class NumeroEquiposInvalidoException extends Exception {
    public NumeroEquiposInvalidoException(String message) {
        super(message);
    }
}

// Excepción para nombre de equipo duplicado
class NombreEquipoDuplicadoException extends Exception {
    public NombreEquipoDuplicadoException(String message) {
        super(message);
    }
}