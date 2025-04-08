package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import Logico.*;

public class AgregarEquipo extends JFrame {

    private JTextField txtNombre, txtEstadio;
    private JComboBox<String> cbCiudad;
    private JButton btnVerJugadores, btnCancelar, btnAgregar;
    private List<Jugador> jugadoresSeleccionados = new ArrayList<>();

    public AgregarEquipo() {
        setTitle("Agregar Equipo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(910, 612);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setBounds(0, 0, 917, 612);
        controlPanel.setOpaque(false);
        getContentPane().add(controlPanel);

        txtNombre = new JTextField();
        txtNombre.setBounds(146, 246, 248, 21);
        estilizarInput(txtNombre);
        controlPanel.add(txtNombre);

        cbCiudad = new JComboBox<>(getProvinciasDominicanas());
        cbCiudad.setBounds(146, 310, 248, 25);
        cbCiudad.setFont(new Font("Arial", Font.PLAIN, 14));
        cbCiudad.setOpaque(false);
        cbCiudad.setFocusable(false);
        controlPanel.add(cbCiudad);

        txtEstadio = new JTextField();
        txtEstadio.setBounds(146, 376, 248, 25);
        estilizarInput(txtEstadio);
        controlPanel.add(txtEstadio);

        btnVerJugadores = new JButton();
        btnVerJugadores.setBounds(447, 242, 269, 25);
        invis(btnVerJugadores);
        controlPanel.add(btnVerJugadores);

        btnCancelar = new JButton();
        btnCancelar.setBounds(243, 460, 171, 40);
        invis(btnCancelar);
        controlPanel.add(btnCancelar);

        btnAgregar = new JButton();
        btnAgregar.setBounds(475, 460, 176, 40);
        invis(btnAgregar);
        controlPanel.add(btnAgregar);

        btnCancelar.addActionListener(e -> dispose());

        btnVerJugadores.addActionListener(e -> {
            new SeleccionJugadores(seleccionados -> {
                jugadoresSeleccionados = seleccionados;
                JOptionPane.showMessageDialog(this, "Seleccionaste " + seleccionados.size() + " jugador(es).");
            }).setVisible(true);
        });

        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String ciudad = cbCiudad.getSelectedItem().toString().trim();
            String estadio = txtEstadio.getText().trim();

            if (nombre.isEmpty() || ciudad.isEmpty() || estadio.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completa todos los campos.");
                return;
            }

            if (jugadoresSeleccionados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar al menos un jugador para crear el equipo.");
                return;
            }

            if (SerieNacional.getInstance().buscarEquipoNombre(nombre) != null) {
                JOptionPane.showMessageDialog(this, "Ese equipo ya existe.");
                return;
            }

            Equipo nuevoEquipo = new Equipo(nombre, ciudad, estadio, "", "", 0, null);
            nuevoEquipo.setNomina(new ArrayList<>(jugadoresSeleccionados));
            nuevoEquipo.setHistorial(new ArrayList<>());
            nuevoEquipo.setStats(new StatsEquipo(nuevoEquipo));

            for (Jugador j : jugadoresSeleccionados) {
                j.setEquipo(nuevoEquipo);
            }

            SerieNacional.getInstance().agregarEquipo(nuevoEquipo);
            JOptionPane.showMessageDialog(this, "Equipo registrado correctamente.");

            txtNombre.setText("");
            txtEstadio.setText("");
            cbCiudad.setSelectedIndex(0);
            jugadoresSeleccionados.clear();
        });

        JLabel fondo = new JLabel(new ImageIcon(AgregarEquipo.class.getResource("/images/agregarnuevoequipo.png")));
        fondo.setBounds(0, 0, 893, 566);
        controlPanel.add(fondo);
    }

    private void estilizarInput(JTextField txt) {
        txt.setOpaque(false);
        txt.setBorder(null);
        txt.setForeground(Color.BLACK);
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private void invis(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    private String[] getProvinciasDominicanas() {
        return new String[]{
            "Azua", "Bahoruco", "Barahona", "Dajabón", "Distrito Nacional", "Duarte", "Elías Piña",
            "El Seibo", "Espaillat", "Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia",
            "La Romana", "La Vega", "María Trinidad Sánchez", "Monseñor Nouel", "Monte Cristi", 
            "Monte Plata", "Pedernales", "Peravia", "Puerto Plata", "Samaná", "San Cristóbal", 
            "San José de Ocoa", "San Juan", "San Pedro de Macorís", "Sánchez Ramírez", 
            "Santiago", "Santiago Rodríguez", "Santo Domingo", "Valverde"
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgregarEquipo().setVisible(true));
    }
}
