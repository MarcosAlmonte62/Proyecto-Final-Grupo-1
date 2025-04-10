package Visual;

import javax.swing.*;
import java.awt.*;
import Logico.*;

public class ReportesGlobales extends JFrame {

    private static final long serialVersionUID = 1L;

    public ReportesGlobales() {
        setTitle("Reportes Globales");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 550);
        setResizable(false);
        setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        setContentPane(layeredPane);

        JLabel fondo = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon(getClass().getResource("/images/fondo500.png"));
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        fondo.setBounds(0, 0, 700, 550);
        layeredPane.add(fondo, Integer.valueOf(0));

        // Configuración de los JLabels
        JLabel lblTitulo = new JLabel("REPORTES GLOBALES DEL TORNEO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.YELLOW);
        lblTitulo.setBounds(50, 20, 600, 30);
        
        JLabel[] labels = new JLabel[8];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setFont(new Font("Arial", Font.BOLD, 14));
            labels[i].setForeground(Color.WHITE);
            labels[i].setBounds(50, 60 + (i * 30), 600, 20);
            layeredPane.add(labels[i], Integer.valueOf(1));
        }

        layeredPane.add(lblTitulo, Integer.valueOf(1));

        // Obtener datos estadísticos
        SerieNacional serie = SerieNacional.getInstance();
        
        // Jugadores destacados
        Jugador mvp = serie.mejorDelTorneo();
        Jugador maxAnotador = serie.maximoAnotador();
        Jugador maxAsistidor = serie.maximoAsistidor();
        Jugador mejorTirador = serie.mejorLanzadorFaltas();
        
        // Datos de equipos
        Equipo mejorEquipo = serie.liderClasificacion();
        int totalEquipos = serie.getEquipos().size();
        
        // Datos de partidos
        int partidosJugados = serie.contarPartidosJugados();
        int totalJugadores = serie.getTodosLosJugadores().size();
        

        // Establecer textos en los JLabels
        labels[0].setText("MVP del torneo: " + formatJugadorMVP(mvp));
        labels[1].setText("Máximo anotador: " + formatJugadorAnotador(maxAnotador));
        labels[2].setText("Máximo asistidor: " + formatJugadorAsistidor(maxAsistidor));
        labels[3].setText("Mejor % tiros libres: " + formatJugadorTL(mejorTirador));
        labels[4].setText("Equipo líder: " + (mejorEquipo != null ? mejorEquipo.getNombre() : "No disponible"));
        labels[5].setText("Partidos jugados: " + partidosJugados);
        labels[6].setText("Total jugadores registrados: " + totalJugadores);
    }

    private String formatJugadorMVP(Jugador jugador) {
        if (jugador == null) return "No disponible";
        return jugador.getNombre() + " (" + 
               (jugador.getEquipo() != null ? jugador.getEquipo().getNombre() : "Sin equipo") + ") " +
               (jugador.getStats() != null ? "| Puntos MVP: " + jugador.getStats().puntosMvp() : "");
    }
    private String formatJugadorAnotador(Jugador jugador) {
        if (jugador == null) return "No disponible";
        return jugador.getNombre() + " (" + 
               (jugador.getEquipo() != null ? jugador.getEquipo().getNombre() : "Sin equipo") + ") " +
               (jugador.getStats() != null ? "| Puntos: " + jugador.getStats().puntosGenerados() : "");
    }
    private String formatJugadorAsistidor(Jugador jugador) {
        if (jugador == null) return "No disponible";
        return jugador.getNombre() + " (" + 
               (jugador.getEquipo() != null ? jugador.getEquipo().getNombre() : "Sin equipo") + ") " +
               (jugador.getStats() != null ? "| Asistencias: " + jugador.getStats().getAsistencias() : "");
    }
    private String formatJugadorTL(Jugador jugador) {
        if (jugador == null) return "No disponible";
        return jugador.getNombre() + " (" + 
               (jugador.getEquipo() != null ? jugador.getEquipo().getNombre() : "Sin equipo") + ") " +
               (jugador.getStats() != null ? "| Efectividad: " + jugador.getStats().eFTARate() : "");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReportesGlobales().setVisible(true);
            }
        });
    }
}