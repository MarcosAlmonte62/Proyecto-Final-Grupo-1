package Visual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import Logico.*;

public class ListarEquipos extends JFrame {

    private JTable tablaEquipos;
    private DefaultTableModel modelo;

    public ListarEquipos() {
        setTitle("Listar Equipos");
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

        tablaEquipos = new JTable();
        tablaEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEquipos.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaEquipos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tablaEquipos.getSelectedRow() != -1) {
                    int index = tablaEquipos.getSelectedRow();
                    Equipo equipo = SerieNacional.getInstance().getClasificacion().get(index);
                    ModificarEquipo mod = new ModificarEquipo(equipo);
                    mod.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            cargarEquipos();
                        }
                    });
                    mod.setVisible(true);
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tablaEquipos);
        tablaPanel.add(scroll, BorderLayout.CENTER);

        JButton btnRegresar = new JButton();
        btnRegresar.setBounds(688, 488, 173, 40);
        hacerInvisible(btnRegresar);
        btnRegresar.addActionListener(e -> dispose());
        panel.add(btnRegresar);

        cargarEquipos();

        JLabel fondo = new JLabel(new ImageIcon(getClass().getResource("/images/listarequipos.png")));
        fondo.setBounds(0, 0, 917, 565);
        panel.add(fondo);
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1);
    }

    private void cargarEquipos() {
        String[] columnas = {"Nombre", "Ciudad", "Estadio", "Jugadores"};
        modelo = new DefaultTableModel(columnas, 0);

        for (Equipo e : SerieNacional.getInstance().getClasificacion()) {
            modelo.addRow(new Object[]{
                    e.getNombre(),
                    e.getCiudad(),
                    e.getEstadio(),
                    e.getNomina().size()
            });
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