package Visual;

import javax.swing.*;
import java.awt.*;
import Logico.*;

public class ReportesGlobales extends JFrame {

    private static final long serialVersionUID = 1L;

    public ReportesGlobales() {
        setTitle("Reportes Globales");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1));

        JLabel lblMejorJugador = new JLabel();
        JLabel lblMejorEquipo = new JLabel();
        JLabel lblStatsGlobales = new JLabel();

        Jugador mvp = SerieNacional.getInstance().mejorDelTorneo();

        Equipo mejorEquipo = SerieNacional.getInstance().getClasificacion()
                .stream()
                .max((a, b) -> a.getStats().getVictorias() - b.getStats().getVictorias())
                .orElse(null);

        int partidosJugados = (int) SerieNacional.getInstance()
                .getCalendario()
                .stream()
                .filter(Partido::isJugado)
                .count();

        lblMejorJugador.setText("MVP del torneo: " +
                (mvp != null ? mvp.getNombre() + " (" + mvp.getEquipo().getNombre() + ")" : "No disponible"));

        lblMejorEquipo.setText("Equipo con mas victorias: " +
                (mejorEquipo != null ? mejorEquipo.getNombre() : "No disponible"));

        lblStatsGlobales.setText("Total de partidos jugados: " + partidosJugados);

        lblMejorJugador.setFont(new Font("Arial", Font.BOLD, 16));
        lblMejorEquipo.setFont(new Font("Arial", Font.BOLD, 16));
        lblStatsGlobales.setFont(new Font("Arial", Font.BOLD, 16));

        add(lblMejorJugador);
        add(lblMejorEquipo);
        add(lblStatsGlobales);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReportesGlobales().setVisible(true));
    }
}
