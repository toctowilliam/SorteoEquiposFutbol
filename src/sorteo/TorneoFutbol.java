package sorteo;

import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class TorneoFutbol {
    private static final Scanner scanner = new Scanner(System.in);
    private List<String> resultadosEtapa;
    private Resultado resultadoManager;
    Equipo equipo = new Equipo();

    // Constructor que inicializa resultadoManager y resultadosEtapa
    public TorneoFutbol(Resultado resultadoManager) {
        this.resultadosEtapa = new ArrayList<>();
        this.resultadoManager = resultadoManager;
    }
    public static void main(String[] args) {
        Resultado resultadoManager = new Resultado();
        TorneoFutbol torneo = new TorneoFutbol(resultadoManager);
        // Metodo para Iniciar el sorteo de los partidos
        torneo.iniciarTorneo();
    }

    public void iniciarTorneo() {
        int numeroEtapa = seleccionarEtapaInicio();
        try {
            List<String> equipos = equipo.ingresarEquiposSegunEtapa(numeroEtapa);
            String equipoCampeon = sortearSeleccionarGanadores(equipos, numeroEtapa, "ResultadosTorneo.txt");

            System.out.println("\n¡El torneo ha finalizado! \nFELICIDADES AL EQUIPO CAMPEÓN: " + equipoCampeon);
            System.out.println("\nPuede ver los resultados en el archivo generado: 'ResultadosTorneo.txt'");

            // Guardar los resultados al final del torneo
            Resultado resultado = new Resultado();
            resultado.guardarResultados(resultadosEtapa, "ResultadosTorneo.txt");

        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número entero. Inténtelo de nuevo.");
            scanner.next();
        } catch (IOException | EtapaInvalidaException | NumeroEquiposInvalidoException | NombreEquipoDuplicadoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private int seleccionarEtapaInicio() {
        int numeroEtapa = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                //Menú de selección de etapa
                System.out.println("\nBIENVENIDOS! AL PROGRAMA PARA SORTEAR SUS PARTIDOS:\n");
                System.out.println("Seleccione la etapa de Inicio:");
                System.out.println("1. Octavos de Final");
                System.out.println("2. Cuartos de Final");
                System.out.println("3. Semifinales");

                numeroEtapa = scanner.nextInt();

                // Verificar si el número de etapa es válido
                if (numeroEtapa < 1 || numeroEtapa > 3) {
                    System.out.println("Opción no válida. Intente de nuevo.");
                } else {
                    entradaValida = true;
                }

            } catch (InputMismatchException e) {
                // Manejar excepción si el usuario ingresa un valor incorrecto en este caso un valor no entero
                System.out.println("Error: Debe ingresar un número entero. Inténtelo de nuevo.");
                scanner.next();
            }
        }
        return numeroEtapa;
    }

    // Método recursivo para sortear equipos y seleccionar ganadores
    private String sortearSeleccionarGanadores(List<String> equipos, int numeroEtapa, String filename) throws IOException {
        if (equipos.size() == 1) {
            String campeon = equipos.get(0);
            resultadosEtapa.add("\n== CAMPEÓN DEL TORNEO: " + campeon + " ==");
            resultadoManager.guardarResultados(resultadosEtapa, filename);
            return campeon;  // El último equipo es el campeón
        }

        // Determinar el nombre de la etapa
        String etapa;
        switch (numeroEtapa) {
            case 1 -> etapa = "Octavos de Final";
            case 2 -> etapa = "Cuartos de Final";
            case 3 -> etapa = "Semifinales";
            default -> etapa = "Final";
        }

        System.out.println("\nPartidos a disputarse en la Etapa de " + etapa + ":");
        resultadosEtapa.add("\n=== " + etapa + " ===");

        // Mezclar la lista de equipos aleatoriamente
        Collections.shuffle(equipos);
        List<String> partidos = new ArrayList<>();

        // Imprimir todos los partidos para seleccionar ganadores
        for (int i = 0; i < equipos.size(); i += 2) {
            String equipo1 = equipos.get(i);
            String equipo2 = equipos.get(i + 1);
            String partido = equipo1 + " VS " + equipo2;
            partidos.add(partido);
            System.out.println(partido);
        }

        // Seleccionar ganadores después de mostrar todos los partidos
        System.out.println("\n===================== Seleccione el ganador de cada partido =====================");
        List<String> ganadores = new ArrayList<>();
        for (String partido : partidos) {
            String[] equiposPartido = partido.split(" VS ");
            String ganador = seleccionarGanador(equiposPartido[0], equiposPartido[1]);
            ganadores.add(ganador);
            // Guardar el resultado del partido en la lista de resultados
            String resultado = partido + " -> Ganador: " + ganador;
            resultadosEtapa.add(resultado);
        }
        // Guardar resultados de la etapa en el archivo de texto
        resultadoManager.guardarResultados(resultadosEtapa, filename);

        // Si la etapa actual no es la final, mostrar mensaje de sorteo para la siguiente etapa
        if (!etapa.equals("Final")) {
            System.out.println("\n--- Realizando sorteo de la siguiente etapa ---");
        }
        // Llamada recursiva para la siguiente etapa
        return sortearSeleccionarGanadores(ganadores, numeroEtapa + 1, filename);
    }

    private String seleccionarGanador(String equipo1, String equipo2) {
        String ganador = null;
        boolean entradaValida = false;
        // Bucle para verificar al equipo ganador por el usuario
        while (!entradaValida) {
            try {
                System.out.println("Ganador de este Partido, ingrese un numero: 1: " + equipo1 + "    2: " + equipo2);
                int opcion = scanner.nextInt(); // Leer la opción del usuario

                // Verificar si la opción es válida de los ganadores
                if (opcion == 1) {
                    ganador = equipo1;
                    entradaValida = true;
                } else if (opcion == 2) {
                    ganador = equipo2;
                    entradaValida = true;
                } else {
                    System.out.println("Opción no válida. Intente nuevamente.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero. Inténtelo de nuevo.");
                scanner.next();
            }
        }
        return ganador;
    }
}