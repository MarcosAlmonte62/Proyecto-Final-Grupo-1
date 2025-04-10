package Visual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import Logico.*;

public class SeleccionJugadores extends JFrame {

    private JPanel controlPanel;
    private JTable table;
    private JTextField txtBuscar;
    private JButton btnBuscar, btnCancelar, btnAceptar;
    private PlayerSelectionListener listener;
    private List<Jugador> todosLosJugadores = new ArrayList<>();

    public SeleccionJugadores(PlayerSelectionListener listener) {
        this.listener = listener;
        setTitle("Seleccionar Jugadores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(813, 565);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setBounds(0, 0, 813, 565);
        controlPanel.setOpaque(false);
        getContentPane().add(controlPanel);

        agregarComponentes(controlPanel);

        URL bgUrl = getClass().getResource("/images/seleccionjugador.png");
        JLabel lblFondo = new JLabel(bgUrl != null ? new ImageIcon(bgUrl) : null);
        lblFondo.setBounds(-12, -28, 813, 580);
        controlPanel.add(lblFondo);
        controlPanel.setComponentZOrder(lblFondo, controlPanel.getComponentCount() - 1);
    }

    private void agregarComponentes(JPanel panel) {
        txtBuscar = new JTextField();
        txtBuscar.setBounds(437, 94, 129, 23);
        estilizarInput(txtBuscar);
        panel.add(txtBuscar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(595, 86, 93, 31);
        panel.add(btnBuscar);

        JPanel whitePanel = new JPanel(new BorderLayout());
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setBounds(49, 130, 688, 296);
        panel.add(whitePanel);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        whitePanel.add(scrollPane, BorderLayout.CENTER);

        btnCancelar = new JButton();
        btnCancelar.setBounds(216, 439, 150, 40);
        hacerInvisible(btnCancelar);
        panel.add(btnCancelar);

        btnAceptar = new JButton();
        btnAceptar.setBounds(422, 439, 150, 40);
        hacerInvisible(btnAceptar);
        panel.add(btnAceptar);

        btnCancelar.addActionListener(e -> dispose());

        btnAceptar.addActionListener(e -> {
            int[] selectedRows = table.getSelectedRows();
            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(this, "No se selecciono ningun jugador.");
                return;
            }

            List<Jugador> seleccionados = new ArrayList<>();
            for (int row : selectedRows) {
                String nombre = table.getValueAt(row, 0).toString();
                for (Jugador j : todosLosJugadores) {
                    if (j.getNombre().equalsIgnoreCase(nombre)) {
                        seleccionados.add(j);
                        break;
                    }
                }
            }

            if (listener != null) {
                listener.onPlayersSelected(seleccionados);
            }

            dispose();
        });

        btnBuscar.addActionListener(e -> filtrarPorNombre(txtBuscar.getText().trim()));

        cargarJugadoresReales();
        actualizarTabla(todosLosJugadores);
    }

    private void cargarJugadoresReales() {
        todosLosJugadores.clear();
        for (Jugador j : SerieNacional.getInstance().getTodosLosJugadores()) {
            if (j.getEquipo() == null) {
                todosLosJugadores.add(j);
            }
        }
    }

    private void actualizarTabla(List<Jugador> jugadores) {
        String[] columnas = {"Nombre", "Posici�n", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Jugador j : jugadores) {
            String estado = (j.getLesion() != null && j.getLesion().isLesionado()) ? "Lesionado" : "Activo";
            modelo.addRow(new Object[]{j.getNombre(), j.getPosicion(), estado});
        }

        table.setModel(modelo);
    }

    private void filtrarPorNombre(String nombre) {
        if (nombre.isEmpty()) {
            actualizarTabla(todosLosJugadores);
            return;
        }

        List<Jugador> filtrados = new ArrayList<>();
        for (Jugador j : todosLosJugadores) {
            if (j.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                filtrados.add(j);
            }
        }

        actualizarTabla(filtrados);
    }

    private void estilizarInput(JTextField txt) {
        txt.setOpaque(false);
        txt.setBorder(null);
        txt.setForeground(Color.BLACK);
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private void hacerInvisible(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SeleccionJugadores(jugadores -> {
            for (Jugador j : jugadores) {
                System.out.println("Seleccionado: " + j.getNombre());
            }
        }).setVisible(true));
    }
}
