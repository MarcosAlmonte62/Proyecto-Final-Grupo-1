package socket;

import java.io.*;
import java.net.*;
import java.util.*;
import Logico.*;

public class ServidorSN extends Thread {

    public static void main(String[] args) {
        ServerSocket sfd = null;

        try {
            sfd = new ServerSocket(7000);
            System.out.println("Servidor escuchando en el puerto 7000...");
        } catch (IOException ioe) {
            System.out.println("Comunicación rechazada: " + ioe.getMessage());
            System.exit(1);
        }

        while (true) {
            try {
                Socket nsfd = sfd.accept();
                System.out.println("Conexión aceptada de: " + nsfd.getInetAddress());

                DataInputStream in = new DataInputStream(nsfd.getInputStream());
                DataOutputStream out = new DataOutputStream(nsfd.getOutputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                PrintWriter writer = new PrintWriter(out, true);

                writer.println("Bienvenido al servidor de la Serie Nacional.");
                writer.println("Comandos disponibles: EQUIPOS, JUGADORES <nombre_equipo>, MVP, SALIR");

                String linea;
                while ((linea = reader.readLine()) != null) {
                    String respuesta = procesarComando(linea);
                    writer.println(respuesta);
                    if (linea.equalsIgnoreCase("SALIR")) {
                        break;
                    }
                }

                reader.close();
                writer.close();
                nsfd.close();
            } catch (IOException ioe) {
                System.out.println("Error: " + ioe.getMessage());
            }
        }
    }

    private static String procesarComando(String entrada) {
        String[] partes = entrada.trim().split(" ", 2);
        String comando = partes[0].toUpperCase();
        String argumento = (partes.length > 1) ? partes[1] : "";

        switch (comando) {
            case "EQUIPOS":
                return listarEquipos();
            case "JUGADORES":
                return listarJugadores(argumento);
            case "MVP":
                return obtenerMVP();
            case "SALIR":
                return "Desconectando del servidor.";
            default:
                return "Comando no reconocido.";
        }
    }

    private static String listarEquipos() {
        List<Equipo> equipos = SerieNacional.getInstance().getClasificacion();
        if (equipos.isEmpty()) return "No hay equipos registrados.";
        StringBuilder sb = new StringBuilder();
        for (Equipo e : equipos) {
            sb.append("• ").append(e.getNombre()).append(" (").append(e.getCiudad()).append(")\n");
        }
        return sb.toString();
    }

    private static String listarJugadores(String equipoNombre) {
        Equipo equipo = SerieNacional.getInstance().buscarEquipoNombre(equipoNombre);
        if (equipo == null) return "Equipo no encontrado.";
        List<Jugador> jugadores = equipo.getNomina();
        if (jugadores.isEmpty()) return "El equipo no tiene jugadores.";
        StringBuilder sb = new StringBuilder();
        for (Jugador j : jugadores) {
            sb.append("- ").append(j.getNombre()).append(" | ").append(j.getPosicion()).append(" | Rating: ")
              .append(j.getRating()).append("\n");
        }
        return sb.toString();
    }

    private static String obtenerMVP() {
        Jugador mvp = SerieNacional.getInstance().mejorDelTorneo();
        if (mvp == null) return "No hay MVP aún.";
        return "MVP del torneo: " + mvp.getNombre() + " (" + mvp.getEquipo().getNombre() + ")";
    }
}