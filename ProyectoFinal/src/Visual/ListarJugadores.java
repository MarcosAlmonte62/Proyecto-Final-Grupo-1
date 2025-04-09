package Visual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import Logico.*;

public class ListarJugadores extends JFrame {

    private JTable tablaJugadores;

    public ListarJugadores() {
        setTitle("Listar Jugadores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(917, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 917, 600);
        panel.setOpaque(false);
        getContentPane().add(panel);

        JPanel tablaPanel = new JPanel(new BorderLayout());
        tablaPanel.setBounds(76, 138, 760, 290);
        tablaPanel.setBackground(Color.WHITE);
        panel.add(tablaPanel);

        tablaJugadores = new JTable();
        JScrollPane scroll = new JScrollPane(tablaJugadores);
        tablaPanel.add(scroll, BorderLayout.CENTER);

        JButton btnRegresar = new JButton();
        btnRegresar.setBounds(688, 488, 173, 40);
        hacerInvisible(btnRegresar);
        btnRegresar.addActionListener((ActionEvent e) -> dispose());
        panel.add(btnRegresar);

        cargarJugadores();

        JLabel fondo = new JLabel(new ImageIcon(getClass().getResource("/images/listarjugadores.png")));
        fondo.setBounds(0, 0, 917, 565);
        panel.add(fondo);
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1);
    }

    private void cargarJugadores() {
        String[] columnas = {"Nombre", "Edad", "Nacionalidad", "Equipo", "Posici�n", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Jugador j : SerieNacional.getInstance().getTodosLosJugadores()) {
            String equipo = (j.getEquipo() != null) ? j.getEquipo().getNombre() : "Sin equipo";
            String estado = (j.getLesion() != null && j.getLesion().isLesionado()) ? "Lesionado" : "Activo";
            modelo.addRow(new Object[]{
                    j.getNombre(),
                    j.getEdad(),
                    j.getNacionalidad(),
                    equipo,
                    j.getPosicion(),
                    estado
            });
        }

        tablaJugadores.setModel(modelo);
    }

    private void hacerInvisible(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListarJugadores().setVisible(true));
    }
}
