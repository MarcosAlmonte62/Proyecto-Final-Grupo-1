package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import Logico.*;

public class RegistrarResultado extends JFrame {

    private JComboBox<String> cbPartido;
    private JComboBox<String> cbUbicacion;
    private JSpinner spnFecha;
    private JSpinner spnMarcadorLocal, spnMarcadorVisitante;
    private JButton btnCancelar, btnAgendar;

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

        cbUbicacion = new JComboBox<>();
        cbUbicacion.setBounds(340, 253, 250, 30);
        cbUbicacion.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(cbUbicacion);

        spnFecha = new JSpinner(new SpinnerDateModel());
        spnFecha.setBounds(619, 252, 163, 30);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spnFecha, "dd/MM/yyyy");
        spnFecha.setEditor(editor);
        panel.add(spnFecha);

        spnMarcadorLocal = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        spnMarcadorLocal.setBounds(279, 339, 60, 30);
        panel.add(spnMarcadorLocal);

        spnMarcadorVisitante = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        spnMarcadorVisitante.setBounds(628, 339, 60, 30);
        panel.add(spnMarcadorVisitante);

        btnCancelar = new JButton();
        btnCancelar.setBounds(240, 438, 180, 45);
        hacerInvisible(btnCancelar);
        panel.add(btnCancelar);

        btnAgendar = new JButton();
        btnAgendar.setBounds(472, 438, 180, 45);
        hacerInvisible(btnAgendar);
        panel.add(btnAgendar);

        JLabel fondo = new JLabel(new ImageIcon(getClass().getResource("/images/registrarresultado.png")));
        fondo.setBounds(0, 0, 894, 553);
        panel.add(fondo);
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1);

        cargarPartidos();
        cargarEstadios();

        btnCancelar.addActionListener(e -> dispose());

        btnAgendar.addActionListener(e -> {
            try {
                int index = cbPartido.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un partido.");
                    return;
                }

                Partido partido = SerieNacional.getInstance().getCalendario().get(index);

                String ubicacion = cbUbicacion.getSelectedItem().toString().trim();
                Date fecha = (Date) spnFecha.getValue();
                int marcadorLocal = (Integer) spnMarcadorLocal.getValue();
                int marcadorVisitante = (Integer) spnMarcadorVisitante.getValue();

                partido.setUbicacion(ubicacion);
                partido.setFecha(fecha);
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

    private void cargarEstadios() {
        cbUbicacion.removeAllItems();
        for (Equipo e : SerieNacional.getInstance().getClasificacion()) {
            if (e.getEstadio() != null && !e.getEstadio().trim().isEmpty()) {
                cbUbicacion.addItem(e.getEstadio());
            }
        }
    }

    private void hacerInvisible(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrarResultado().setVisible(true));
    }
}
