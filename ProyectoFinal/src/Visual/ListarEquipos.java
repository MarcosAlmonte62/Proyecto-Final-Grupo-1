package Visual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import Logico.*;

public class ListarEquipos extends JFrame {

    private JTable tablaEquipos;

    public ListarEquipos() {
        setTitle("Listar Equipos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(917, 600);
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

        tablaEquipos = new JTable();
        JScrollPane scroll = new JScrollPane(tablaEquipos);
        tablaPanel.add(scroll, BorderLayout.CENTER);

        JButton btnRegresar = new JButton();
        btnRegresar.setBounds(688, 488, 173, 40);
        hacerInvisible(btnRegresar);
        btnRegresar.addActionListener((ActionEvent e) -> dispose());
        panel.add(btnRegresar);

        cargarEquipos();

        JLabel fondo = new JLabel(new ImageIcon(getClass().getResource("/images/listarequipos.png")));
        fondo.setBounds(0, 0, 905, 559);
        panel.add(fondo);
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1);
    }

    private void cargarEquipos() {
        String[] columnas = {"Nombre", "Ciudad", "Estadio", "Jugadores"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Equipo eq : SerieNacional.getInstance().getClasificacion()) {
            Object[] fila = {
                eq.getNombre(),
                eq.getCiudad(),
                eq.getEstadio(),
                eq.getNomina() != null ? eq.getNomina().size() : 0
            };
            modelo.addRow(fila);
        }

        tablaEquipos.setModel(modelo);
    }

    private void hacerInvisible(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListarEquipos().setVisible(true));
    }
}
