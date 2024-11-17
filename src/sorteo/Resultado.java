package sorteo;

import java.io.*;
import java.util.List;

public class Resultado{

    //Metodo para guardar los resultados en un archivo
    public void guardarResultados(List<String> resultados, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("RESULTADOS DEL TORNEO:\n");
            for (String resultado : resultados) {
                writer.write(resultado);// Guardar cada resultado en una nueva línea
                writer.newLine();  // Agregar una nueva línea después de cada resultado
            }
        } catch (IOException e) {
            // Lanzar una excepción si ocurre un error al guardar
            throw new IOException("Error al guardar: " + e.getMessage(), e);
        }
    }

}