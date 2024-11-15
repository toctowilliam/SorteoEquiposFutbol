package sorteo;

import java.util.*;

public class Equipo {

    private static final Scanner scanner = new Scanner(System.in);

    // Método para ingresar equipos según la etapa del torneo
    public List<String> ingresarEquiposSegunEtapa(int numeroEtapa) throws EtapaInvalidaException, NumeroEquiposInvalidoException, NombreEquipoDuplicadoException {

        List<String> equipos;
        // Verificar la etapa del torneo
        switch (numeroEtapa) {
            case 1 -> {
                System.out.println("ETAPA DE OCTAVOS DE FINAL");
                equipos = ingresarEquipos(16);  // Ingresar 16 equipos
            }
            case 2 -> {
                System.out.println("ETAPA DE CUARTOS DE FINAL");
                equipos = ingresarEquipos(8); // Ingresar 8 equipos
            }
            case 3 -> {
                System.out.println("ETAPA DE SEMIFINALES");
                equipos = ingresarEquipos(4); // Ingresar 4 equipos
            }
            default -> equipos = new ArrayList<>(); // Dejar vacío para el caso de error
        }
        return equipos;
    }


    public List<String> ingresarEquipos(int numEquipos) throws NumeroEquiposInvalidoException {
        // Verificar si el número de equipos es válido
        if (numEquipos <= 0) {
            throw new NumeroEquiposInvalidoException("Número de equipos inválido. Debe ser positivo.");
        }

        List<String> equipos = new ArrayList<>(); // Lista de equipos
        Set<String> equipoDuplicados = new HashSet<>(); // Para verificar equipos duplicados

        System.out.println("Ingrese los nombres de los Equipos:");
        // Ingresar los nombres de los equipos
        for (int i = 0; i < numEquipos; i++) {
            boolean nombreValido = false;
            String nombreEquipo = "";

            while (!nombreValido) {
                System.out.print("Equipo " + (i + 1) + ": ");
                nombreEquipo = scanner.nextLine();
                // Verificar si el equipo ya existe
                if (equipoDuplicados.contains(nombreEquipo)) {
                    System.out.println("El nombre del Equipo '" + nombreEquipo + "' ya existe. Ingrese otro Nombre.");
                } else {
                    // Si el nombre del equipo no está duplicado, agregarlo a la lista
                    nombreValido = true;
                }
            }
            equipos.add(nombreEquipo);// Agregar el equipo a la lista
            equipoDuplicados.add(nombreEquipo); // Agregar el equipo al conjunto para futuras verificaciones
        }
        return equipos;
    }

}

// Excepción para etapa inválida
class EtapaInvalidaException extends Exception {
    public EtapaInvalidaException(String message) {
        super(message);
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