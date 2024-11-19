
>>>>>>>GESTION DE TORNEO DE EQUIPOS DE FUTBOL

## **Descripción**
Esta aplicación permite gestionar de manera eficiente un torneo de fútbol, desde el ingreso de equipos hasta la determinación del campeón. El programa guía al usuario a través de las diferentes etapas del torneo (octavos, cuartos, semifinales y final), permitiéndole seleccionar los ganadores de cada partido. Los resultados se guardan en un archivo de texto legible, que incluye los enfrentamientos, ganadores por etapa y el equipo campeón.

## **Características**
- **Ingreso de Equipos**:  
  Los usuarios pueden ingresar los nombres de los equipos que participarán en el torneo, asegurando que no existan duplicados.

- **Selección de Etapas**:  
  Permite iniciar el torneo desde una etapa específica (octavos, cuartos o semifinales).

- **Partidos Aleatorios**:  
  Los equipos son sorteados aleatoriamente para cada etapa.

- **Interactividad**:  
  El usuario elige los ganadores de cada partido, garantizando una experiencia dinámica.

- **Guardado de Resultados**:  
  Los resultados se almacenan en un archivo de texto que incluye todas las etapas y el equipo campeón.

- **Manejo de Errores**:  
  Se implementan excepciones personalizadas para validar entradas y garantizar un flujo robusto.

- **Uso de Recursividad**:  
  Las etapas del torneo avanzan automáticamente hasta determinar al campeón mediante un método recursivo.

## **Requisitos**
- **Java 11 o superior** Se recomienda usar jdk 17 para evitar complicaciones.

## **Instrucciones de Uso**
1. Ejecuta el programa.
2. Selecciona la etapa de inicio (octavos, cuartos o semifinales).
3. Ingresa los nombres de los equipos.
4. Observa los partidos generados aleatoriamente.
5. Selecciona al ganador de cada partido.
6. Repite el proceso hasta que se determine el campeón.
7. Consulta los resultados completos en el archivo `ResultadosTorneo.txt`.

## **Ejemplo de Archivo de Resultados**
```txt
=== Octavos de Final ===
Equipo A VS Equipo B -> Ganador: Equipo A
Equipo C VS Equipo D -> Ganador: Equipo D

=== Cuartos de Final ===
Equipo A VS Equipo D -> Ganador: Equipo A

=== Final ===
Equipo A VS Equipo E -> Ganador: Equipo A

== CAMPEÓN DEL TORNEO: Equipo A ==
