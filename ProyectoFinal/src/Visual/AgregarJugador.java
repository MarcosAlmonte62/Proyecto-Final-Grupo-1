package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import Logico.*;

public class AgregarJugador extends JFrame {

    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtAltura;
    private JTextField txtPeso;
    private JTextField txtDorsal;
    private JTextField txtRating;
    private JComboBox<String> cbNacionalidad;
    private JComboBox<String> cbPosicion;
    private JComboBox<String> cbEstado;
    private JButton btnAgregar;
    private JButton btnCancelar;

    public AgregarJugador() {
        setTitle("Agregar Jugador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(909, 612);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 917, 612);
        panel.setOpaque(false);
        getContentPane().add(panel);

        txtNombre = new JTextField();
        txtNombre.setBounds(96, 218, 197, 25);
        estilizarInput(txtNombre);
        panel.add(txtNombre);

        cbPosicion = new JComboBox<>();
        cbPosicion.setBounds(344, 218, 202, 25);
        cbPosicion.setFont(new Font("Arial", Font.PLAIN, 14));
        cbPosicion.addItem("Base");
        cbPosicion.addItem("Escolta");
        cbPosicion.addItem("Alero");
        cbPosicion.addItem("Ala-P�vot");
        cbPosicion.addItem("P�vot");
        panel.add(cbPosicion);

        txtEdad = new JTextField();
        txtEdad.setBounds(96, 284, 197, 22);
        estilizarInput(txtEdad);
        panel.add(txtEdad);

        txtAltura = new JTextField();
        txtAltura.setBounds(349, 283, 197, 24);
        estilizarInput(txtAltura);
        panel.add(txtAltura);

        cbNacionalidad = new JComboBox<>();
        cbNacionalidad.setBounds(96, 351, 197, 23);
        cbNacionalidad.setFont(new Font("Arial", Font.PLAIN, 14));
        String[] paises = {"Dominicana", "Estadounidense", "Espa�ola", "Argentina", "Brasilera",
                "Francesa", "Alemana", "Italiana", "Canadiense", "Portuguesa"};
        for (String pais : paises) {
            cbNacionalidad.addItem(pais);
        }
        panel.add(cbNacionalidad);

        txtPeso = new JTextField();
        txtPeso.setBounds(344, 352, 197, 22);
        estilizarInput(txtPeso);
        panel.add(txtPeso);

        cbEstado = new JComboBox<>();
        cbEstado.setBounds(96, 417, 197, 25);
        cbEstado.setFont(new Font("Arial", Font.PLAIN, 14));
        cbEstado.addItem("Activo");
        cbEstado.addItem("Lesionado");
        panel.add(cbEstado);

        txtRating = new JTextField();
        txtRating.setBounds(349, 417, 197, 25);
        estilizarInput(txtRating);
        panel.add(txtRating);

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
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1);
    }

    private void estilizarInput(JTextField txt) {
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        txt.setOpaque(false);
        txt.setBorder(null);
        txt.setForeground(Color.BLACK);
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

            Lesion lesion = estado.equals("Lesionado") ?
                    new Lesion("Esguince", 1, true, 1, "Inicial", "Reposo") : null;

            StatsJugador stats = new StatsJugador(null);
            Jugador jugador = new Jugador(nombre, edad, nacionalidad, lesion, null, stats,
                    dorsal, altura, peso, posicion, new Date(System.currentTimeMillis()));
            jugador.setRating(rating);
            stats.setJugador(jugador);

            SerieNacional.getInstance().agregarJugador(jugador);
            JOptionPane.showMessageDialog(this, "Jugador creado como agente libre (sin equipo).");
            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los campos num�ricos deben contener n�meros v�lidos.");
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
