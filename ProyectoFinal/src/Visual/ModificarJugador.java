package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Logico.*;

public class ModificarJugador extends JFrame {

    private JTextField txtNombre, txtEdad, txtAltura, txtPeso, txtRating;
    private JComboBox cbNacionalidad, cbPosicion, cbEstado;
    private JButton btnGuardar, btnCancelar, btnEliminar;
    private Jugador jugadorOriginal;

    public ModificarJugador(Jugador jugador) {
        this.jugadorOriginal = jugador;

        setTitle("Modificar Jugador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(909, 575);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 917, 612);
        panel.setOpaque(false);
        getContentPane().add(panel);

        // Labels
        panel.add(crearLabel("Nombre:", 96, 160));
        panel.add(crearLabel("Posicion:", 344, 160));
        panel.add(crearLabel("Edad:", 96, 216));
        panel.add(crearLabel("Altura (cm):", 344, 216));
        panel.add(crearLabel("Nacionalidad:", 96, 278));
        panel.add(crearLabel("Peso (kg):", 344, 278));
        panel.add(crearLabel("Estado:", 96, 333));
        panel.add(crearLabel("Rating:", 344, 333));

        txtNombre = new JTextField(jugador.getNombre());
        txtNombre.setBounds(96, 180, 197, 25);
        estilizarInput(txtNombre);
        panel.add(txtNombre);

        cbPosicion = new JComboBox();
        cbPosicion.setBounds(344, 180, 197, 25);
        cbPosicion.setFont(new Font("Arial", Font.PLAIN, 14));
        cbPosicion.addItem("Base");
        cbPosicion.addItem("Escolta");
        cbPosicion.addItem("Alero");
        cbPosicion.addItem("Ala-Pivot");
        cbPosicion.addItem("Pívot");
        cbPosicion.setSelectedItem(jugador.getPosicion());
        panel.add(cbPosicion);

        txtEdad = new JTextField(String.valueOf(jugador.getEdad()));
        txtEdad.setBounds(96, 236, 197, 25);
        estilizarInput(txtEdad);
        panel.add(txtEdad);

        txtAltura = new JTextField(String.valueOf(jugador.getAltura()));
        txtAltura.setBounds(344, 236, 197, 25);
        estilizarInput(txtAltura);
        panel.add(txtAltura);

        cbNacionalidad = new JComboBox();
        cbNacionalidad.setBounds(96, 298, 197, 25);
        cbNacionalidad.setFont(new Font("Arial", Font.PLAIN, 14));
        String[] paises = {"Dominicana", "Estadounidense", "Española", "Argentina", "Brasilera",
                "Francesa", "Alemana", "Italiana", "Canadiense", "Portuguesa"};
        for (int i = 0; i < paises.length; i++) cbNacionalidad.addItem(paises[i]);
        cbNacionalidad.setSelectedItem(jugador.getNacionalidad());
        panel.add(cbNacionalidad);

        txtPeso = new JTextField(String.valueOf(jugador.getPeso()));
        txtPeso.setBounds(344, 298, 197, 25);
        estilizarInput(txtPeso);
        panel.add(txtPeso);

        cbEstado = new JComboBox();
        cbEstado.setBounds(96, 353, 197, 25);
        cbEstado.setFont(new Font("Arial", Font.PLAIN, 14));
        cbEstado.addItem("Activo");
        cbEstado.addItem("Lesionado");
        cbEstado.setSelectedItem((jugador.getLesion() != null) ? "Lesionado" : "Activo");
        panel.add(cbEstado);

        txtRating = new JTextField(String.valueOf(jugador.getRating()));
        txtRating.setBounds(344, 353, 197, 25);
        estilizarInput(txtRating);
        panel.add(txtRating);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(90, 475, 180, 45);
        estilizarBoton(btnCancelar);
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(270, 475, 180, 45);
        estilizarBoton(btnEliminar);
        btnEliminar.addActionListener(e -> {
            SerieNacional.getInstance().getTodosLosJugadores().remove(jugadorOriginal);
            JOptionPane.showMessageDialog(this, "Jugador eliminado correctamente.");
            dispose();
        });
        panel.add(btnEliminar);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBounds(450, 475, 220, 45);
        estilizarBoton(btnGuardar);
        btnGuardar.addActionListener(e -> guardarCambios());
        panel.add(btnGuardar);

        JLabel fondo = new JLabel(new ImageIcon(getClass().getResource("/images/fondo900.png")));
        fondo.setBounds(0, -40, 900, 622);
        panel.add(fondo);
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1);
    }

    private JLabel crearLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, 200, 20);
        return label;
    }

    private void guardarCambios() {
        try {
            jugadorOriginal.setNombre(txtNombre.getText().trim());
            jugadorOriginal.setEdad(Integer.parseInt(txtEdad.getText().trim()));
            jugadorOriginal.setAltura(Integer.parseInt(txtAltura.getText().trim()));
            jugadorOriginal.setPeso(Integer.parseInt(txtPeso.getText().trim()));
            jugadorOriginal.setRating(Float.parseFloat(txtRating.getText().trim()));
            jugadorOriginal.setPosicion(cbPosicion.getSelectedItem().toString());
            jugadorOriginal.setNacionalidad(cbNacionalidad.getSelectedItem().toString());

            if ("Lesionado".equals(cbEstado.getSelectedItem().toString())) {
                if (jugadorOriginal.getLesion() == null) {
                    jugadorOriginal.setLesion(new Lesion("Esguince", 1, true, 1, "Inicial", "Reposo"));
                }
            } else {
                jugadorOriginal.setLesion(null);
            }

            JOptionPane.showMessageDialog(this, "Cambios guardados correctamente.");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
    }

    private void estilizarInput(JTextField txt) {
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        txt.setOpaque(false);
        txt.setBorder(null);
        txt.setForeground(Color.GRAY);
    }

    private void estilizarBoton(JButton b) {
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(new Color(0xEF7116));
        b.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Jugador ejemplo = SerieNacional.getInstance().getTodosLosJugadores().isEmpty()
                    ? null : SerieNacional.getInstance().getTodosLosJugadores().get(0);
            if (ejemplo != null) new ModificarJugador(ejemplo).setVisible(true);
        });
    }
}