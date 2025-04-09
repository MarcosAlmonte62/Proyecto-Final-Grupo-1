package Visual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Logico.*;

public class Lesionados extends JFrame {

    public Lesionados() {
        setTitle("Jugadores Lesionados");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        String[] columnNames = {"Nombre", "Equipo", "Tipo de Lesi�n", "Duraci�n"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Jugador j : SerieNacional.getInstance().getTodosLosJugadores()) {
            Lesion l = j.getLesion();
            if (l != null && l.isLesionado()) {
                model.addRow(new Object[]{
                    j.getNombre(),
                    j.getEquipo() != null ? j.getEquipo().getNombre() : "Agente Libre",
                    l.getTipoLesion(),
                    l.getDuracion() + " d�as"
                });
            }
        }

        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        add(scroll);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lesionados().setVisible(true));
    }
}
