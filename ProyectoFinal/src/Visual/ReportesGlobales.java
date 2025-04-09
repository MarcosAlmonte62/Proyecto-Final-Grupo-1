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
        fondo.setBounds(0, 0, 600, 400);
        layeredPane.add(fondo, Integer.valueOf(0));

        JLabel lblMejorJugador = new JLabel();
        JLabel lblMejorEquipo = new JLabel();
        JLabel lblStatsGlobales = new JLabel();

        lblMejorJugador.setFont(new Font("Arial", Font.BOLD, 16));
        lblMejorJugador.setForeground(Color.WHITE);
        lblMejorJugador.setBounds(50, 80, 500, 30);

        lblMejorEquipo.setFont(new Font("Arial", Font.BOLD, 16));
        lblMejorEquipo.setForeground(Color.WHITE);
        lblMejorEquipo.setBounds(50, 130, 500, 30);

        lblStatsGlobales.setFont(new Font("Arial", Font.BOLD, 16));
        lblStatsGlobales.setForeground(Color.WHITE);
        lblStatsGlobales.setBounds(50, 180, 500, 30);

        layeredPane.add(lblMejorJugador, Integer.valueOf(1));
        layeredPane.add(lblMejorEquipo, Integer.valueOf(1));
        layeredPane.add(lblStatsGlobales, Integer.valueOf(1));

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

        lblMejorEquipo.setText("Equipo con m�s victorias: " +
                (mejorEquipo != null ? mejorEquipo.getNombre() : "No disponible"));

        lblStatsGlobales.setText("Total de partidos jugados: " + partidosJugados);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReportesGlobales().setVisible(true));
    }
}
