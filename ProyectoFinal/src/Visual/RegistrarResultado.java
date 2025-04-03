package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import Logico.*;

public class RegistrarResultado extends JFrame {

    private JComboBox<String> cbPartido;
    private JTextField txtUbicacion;
    private JTextField txtFecha;
    private JTextField txtMarcadorLocal;
    private JTextField txtMarcadorVisitante;
    private JButton btnCancelar;
    private JButton btnAgendar;

    public RegistrarResultado() {
        setTitle("Registro de Resultados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(910, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setOpaque(false);
        panel.setBounds(0, 0, 917, 600);
        getContentPane().add(panel);

        cbPartido = new JComboBox<>();
        cbPartido.setBounds(121, 252, 180, 30);
        panel.add(cbPartido);

        txtUbicacion = new JTextField();
        txtUbicacion.setBounds(340, 253, 250, 30);
        estiloCampo(txtUbicacion);
        panel.add(txtUbicacion);

        txtFecha = new JTextField();
        txtFecha.setBounds(619, 252, 163, 30);
        estiloCampo(txtFecha);
        panel.add(txtFecha);

        txtMarcadorLocal = new JTextField();
        txtMarcadorLocal.setBounds(279, 339, 60, 30);
        estiloCampo(txtMarcadorLocal);
        panel.add(txtMarcadorLocal);

        txtMarcadorVisitante = new JTextField();
        txtMarcadorVisitante.setBounds(628, 339, 60, 30);
        estiloCampo(txtMarcadorVisitante);
        panel.add(txtMarcadorVisitante);

        btnCancelar = new JButton("");
        btnCancelar.setBounds(240, 438, 180, 45);
        hacerInvisible(btnCancelar);
        panel.add(btnCancelar);

        btnAgendar = new JButton("");
        btnAgendar.setBounds(472, 438, 180, 45);
        hacerInvisible(btnAgendar);
        panel.add(btnAgendar);

        JLabel fondo = new JLabel(new ImageIcon(RegistrarResultado.class.getResource("/images/registrarresultado.png")));
        fondo.setBounds(0, 0, 894, 553);
        panel.add(fondo);
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1); // Enviar al fondo

        cargarPartidos();

        btnCancelar.addActionListener(e -> dispose());

        btnAgendar.addActionListener(e -> {
            try {
                int index = cbPartido.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un partido.");
                    return;
                }

                Partido partido = SerieNacional.getInstance().getCalendario().get(index);

                String ubicacion = txtUbicacion.getText().trim();
                String fechaStr = txtFecha.getText().trim();
                int marcadorLocal = Integer.parseInt(txtMarcadorLocal.getText().trim());
                int marcadorVisitante = Integer.parseInt(txtMarcadorVisitante.getText().trim());

                partido.setUbicacion(ubicacion);
                partido.setFecha(new SimpleDateFormat("dd/MM/yyyy").parse(fechaStr));
                partido.setPuntosLocal(marcadorLocal);
                partido.setPuntosVisitante(marcadorVisitante);
                partido.setJugado(true);

                JOptionPane.showMessageDialog(this, "Resultado registrado correctamente.");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar resultado: " + ex.getMessage());
            }
        });
    }

    private void cargarPartidos() {
        cbPartido.removeAllItems();
        for (Partido p : SerieNacional.getInstance().getCalendario()) {
            if (!p.isJugado()) {
                cbPartido.addItem(p.getEquipoLocal().getNombre() + " vs " + p.getEquipoVisitante().getNombre());
            }
        }
    }

    private void hacerInvisible(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    private void estiloCampo(JTextField txt) {
        txt.setOpaque(false);
        txt.setBorder(null);
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        txt.setForeground(Color.BLACK);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrarResultado().setVisible(true));
    }
}
