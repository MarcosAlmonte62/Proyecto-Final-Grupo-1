package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import Logico.*;

public class AgregarJugador extends JFrame {

    private JTextField txtNombre, txtEdad, txtAltura, txtPeso, txtDorsal, txtRating;
    private JComboBox<String> cbNacionalidad, cbPosicion, cbEstado;
    private JButton btnAgregar, btnCancelar;

    public AgregarJugador() {
        setTitle("Agregar Jugador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(909, 612);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 917, 612);
        panel.setOpaque(false);
        getContentPane().add(panel);

        txtNombre = crearCampo(panel, 96, 218, 197, 25);
        cbPosicion = crearCombo(panel, new String[]{"Base", "Escolta", "Alero", "Ala-Pívot", "Pívot"}, 344, 218, 202, 25);
        txtEdad = crearCampo(panel, 96, 281, 197, 25);
        txtAltura = crearCampo(panel, 349, 281, 197, 25);
        cbNacionalidad = crearCombo(panel, new String[]{"Dominicana", "Estadounidense", "Española", "Argentina", "Brasilera",
                "Francesa", "Alemana", "Italiana", "Canadiense", "Portuguesa"}, 96, 348, 185, 25);
        txtPeso = crearCampo(panel, 349, 348, 197, 25);
        cbEstado = crearCombo(panel, new String[]{"Activo", "Lesionado"}, 96, 417, 197, 25);
        txtRating = crearCampo(panel, 349, 417, 197, 25);

        txtDorsal = new JTextField("0");
        txtDorsal.setVisible(false);
        panel.add(txtDorsal);

        btnCancelar = new JButton();
        btnCancelar.setBounds(240, 465, 180, 45);
        hacerInvisible(btnCancelar);
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);

        btnAgregar = new JButton();
        btnAgregar.setBounds(472, 465, 180, 45);
        hacerInvisible(btnAgregar);
        btnAgregar.addActionListener(e -> agregarJugador());
        panel.add(btnAgregar);

        JLabel fondo = new JLabel(new ImageIcon(getClass().getResource("/images/agregarjugador11.png")));
        fondo.setBounds(0, 0, 894, 569);
        panel.add(fondo);
    }

    private JTextField crearCampo(JPanel panel, int x, int y, int w, int h) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, w, h);
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        txt.setOpaque(false);
        txt.setBorder(null);
        txt.setForeground(Color.BLACK);
        panel.add(txt);
        return txt;
    }

    private JComboBox<String> crearCombo(JPanel panel, String[] items, int x, int y, int w, int h) {
        JComboBox<String> cb = new JComboBox<>();
        for (String item : items) cb.addItem(item);
        cb.setBounds(x, y, w, h);
        cb.setFont(new Font("Arial", Font.PLAIN, 14));
        cb.setOpaque(false);
        cb.setForeground(Color.BLACK);
        panel.add(cb);
        return cb;
    }

    private void hacerInvisible(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    private void agregarJugador() {
        try {
            String nombre = txtNombre.getText().trim();
            int edad = Integer.parseInt(txtEdad.getText().trim());
            int altura = Integer.parseInt(txtAltura.getText().trim());
            String nacionalidad = cbNacionalidad.getSelectedItem().toString();
            int peso = Integer.parseInt(txtPeso.getText().trim());
            String posicion = cbPosicion.getSelectedItem().toString();
            int dorsal = Integer.parseInt(txtDorsal.getText().trim());
            String estado = cbEstado.getSelectedItem().toString();
            float rating = Float.parseFloat(txtRating.getText().trim());

            Lesion lesion = estado.equals("Lesionado") ? new Lesion("Esguince", 1, true, 1, "Inicial", "Reposo") : null;
            StatsJugador stats = new StatsJugador(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);

            Jugador jugador = new Jugador(
                nombre, edad, nacionalidad, lesion, null, stats,
                dorsal, altura, peso, posicion, new Date(System.currentTimeMillis())
            );
            jugador.setRating(rating);
            stats.setJugador(jugador);

            SerieNacional.getInstance().agregarJugador(jugador);

            JOptionPane.showMessageDialog(this, "Jugador creado como agente libre (sin equipo).");
            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los campos numéricos deben contener números válidos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar jugador: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEdad.setText("");
        txtAltura.setText("");
        txtPeso.setText("");
        txtRating.setText("");
        cbNacionalidad.setSelectedIndex(0);
        cbPosicion.setSelectedIndex(0);
        cbEstado.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgregarJugador().setVisible(true));
    }
}
