package Visual;

import javax.swing.*;
import java.awt.*;
import Logico.*;

public class StatsPorJugador extends JFrame {

    private JComboBox<String> cbJugadores;
    private JTextArea txtStats;

    public StatsPorJugador() {
        setTitle("Estadísticas por Jugador");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cbJugadores = new JComboBox<>();
        txtStats = new JTextArea();
        txtStats.setEditable(false);

        for (Jugador j : SerieNacional.getInstance().getTodosLosJugadores()) {
            cbJugadores.addItem(j.getNombre());
        }

        cbJugadores.addActionListener(e -> {
            int index = cbJugadores.getSelectedIndex();
            if (index >= 0) {
                Jugador j = SerieNacional.getInstance().getTodosLosJugadores().get(index);
                StatsJugador s = j.getStats();
                txtStats.setText(
                        "Puntos: " + s.getPuntos() + "\n" +
                        "Asistencias: " + s.getAsistencias() + "\n" +
                        "Rebotes: " + s.getRebotes() + "\n" +
                        "Valoración: " + s.valoracion()
                );
            }
        });

        add(cbJugadores, BorderLayout.NORTH);
        add(new JScrollPane(txtStats), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StatsPorJugador().setVisible(true));
    }
}
