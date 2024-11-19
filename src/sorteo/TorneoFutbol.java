package sorteo;

import java.io.IOException;
import java.util.*;
import java.util.Scanner;

public class TorneoFutbol {
    private static final Scanner scanner = new Scanner(System.in);
    //Lista para guardar lo resultados de la etapa.
    private List<String> resultadosEtapa;
    private Resultado resultado;
    //Objeto de la clase Equipo
    Equipo equipo = new Equipo();

    // Constructor que inicializa resultadoManager y resultadosEtapa
    public TorneoFutbol(Resultado resultadoManager) {
        this.resultadosEtapa = new ArrayList<>();
        this.resultado = resultadoManager;
    }
    //Metodo Main
    public static void main(String[] args) throws EtapaInvalidaException {
        Resultado objetoResultado = new Resultado();
        TorneoFutbol torneo = new TorneoFutbol(objetoResultado);
        // Metodo para Iniciar el torneo
        torneo.iniciarTorneo();
    }

    public void iniciarTorneo() {
        try {
            //Llamada al metodos para obtener el numero de equipos a jugar
            int numeroEtapa = seleccionarEtapaInicio();
            //Guardar los equipos ingresados en una listas tipo string
            List<String> equipos = equipo.ingresarEquiposSegunEtapa(numeroEtapa);
            //Realizar el sorteo de los equipos ingresados y devuelve quien es el ganador.
            String equipoCampeon = sortearSeleccionarGanadores(equipos, numeroEtapa, "ResultadosTorneo.txt");

            //Imprimir los resultados
            System.out.println("\n¡El torneo ha finalizado! \nFELICIDADES AL EQUIPO CAMPEÓN: " + equipoCampeon);
            System.out.println("\nPuede ver los resultados en el archivo generado: 'ResultadosTorneo.txt'");
            // Guardar resultados
            resultado.guardarResultados(resultadosEtapa, "ResultadosTorneo.txt");

        } catch (EtapaInvalidaException | NumeroEquiposInvalidoException | GanadorInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al guardar los resultados: " + e.getMessage());
        }
    }



    private int seleccionarEtapaInicio() throws EtapaInvalidaException {
        int numeroEtapa = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.println("\nBIENVENIDOS! AL PROGRAMA PARA SORTEAR SUS PARTIDOS:\n");
                System.out.println("Seleccione la etapa de Inicio:");
                System.out.println("1. Octavos de Final");
                System.out.println("2. Cuartos de Final");
                System.out.println("3. Semifinales");

                numeroEtapa = scanner.nextInt();

                if (numeroEtapa < 1 || numeroEtapa > 3) {
                    throw new EtapaInvalidaException("La etapa seleccionada no es válida. Intente nuevamente.");
                } else {
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero. Inténtelo de nuevo.");
                scanner.next();
            }
        }
        return numeroEtapa;
    }

    // Método recursivo para sortear equipos y seleccionar ganadores
    private String sortearSeleccionarGanadores(List<String> equipos, int numeroEtapa, String filename) throws IOException, GanadorInvalidoException {
        if (equipos.size() == 1) {
            String campeon = equipos.get(0);
            resultadosEtapa.add("\n== CAMPEÓN DEL TORNEO: " + campeon + " ==");
            resultado.guardarResultados(resultadosEtapa, filename);
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

        System.out.println("\nPartidos a disputarse en la Etapa " + etapa + ":");
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
        resultado.guardarResultados(resultadosEtapa, filename);

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

        while (!entradaValida) {
            try {
                System.out.println("Ganador de este Partido, ingrese un numero: 1: " + equipo1 + "    2: " + equipo2);
                int opcion = scanner.nextInt();

                if (opcion == 1) {
                    ganador = equipo1;
                    entradaValida = true;
                } else if (opcion == 2) {
                    ganador = equipo2;
                    entradaValida = true;
                } else {
                    // Excepción de opción inválida
                    throw new GanadorInvalidoException("La opción seleccionada no es válida. Inténtelo nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero. Inténtelo de nuevo.");
                scanner.next(); // Limpiar el buffer para evitar un bucle infinito
            } catch (GanadorInvalidoException e) {
                System.out.println(e.getMessage()); // Mostrar el mensaje de la excepción personalizada
            }
        }
        return ganador;
    }

}

// Excepción para etapa inválida
class EtapaInvalidaException extends Exception {
    public EtapaInvalidaException(String message) {
        super(message);
    }
}

// Excepción para ganador inválido
class GanadorInvalidoException extends Exception {
    public GanadorInvalidoException(String message) {
        super(message);
    }
}

