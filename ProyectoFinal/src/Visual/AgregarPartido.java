package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.SpinnerDateModel;

import Logico.*;

public class AgregarPartido extends JFrame {

    private JComboBox<String> cbLocal;
    private JComboBox<String> cbVisitante;
    private JSpinner spFecha;
    private JButton btnAgendar;
    private JButton btnCancelar;

    public AgregarPartido() {
        setTitle("Agendar Partido");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(917, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 917, 600);
        panel.setOpaque(false);
        getContentPane().add(panel);

        cbLocal = new JComboBox<String>();
        cbLocal.setBounds(132, 223, 217, 30);
        panel.add(cbLocal);

        cbVisitante = new JComboBox<String>();
        cbVisitante.setBounds(132, 292, 218, 30);
        panel.add(cbVisitante);

        spFecha = new JSpinner(new SpinnerDateModel());
        spFecha.setBounds(628, 262, 139, 30);
        spFecha.setFont(new Font("Arial", Font.PLAIN, 14));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spFecha, "dd/MM/yyyy");
        spFecha.setEditor(editor);
        panel.add(spFecha);

        btnAgendar = new JButton("");
        btnAgendar.setBounds(480, 455, 177, 40);
        hacerInvisible(btnAgendar);
        panel.add(btnAgendar);

        btnCancelar = new JButton("");
        btnCancelar.setBounds(243, 451, 180, 47);
        hacerInvisible(btnCancelar);
        panel.add(btnCancelar);

        cargarEquipos();

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnAgendar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombreLocal = cbLocal.getSelectedItem().toString();
                    String nombreVisitante = cbVisitante.getSelectedItem().toString();

                    if (nombreLocal.equals(nombreVisitante)) {
                        JOptionPane.showMessageDialog(AgregarPartido.this, "Los equipos no pueden ser iguales.");
                        return;
                    }

                    Equipo local = SerieNacional.getInstance().buscarEquipoNombre(nombreLocal);
                    Equipo visitante = SerieNacional.getInstance().buscarEquipoNombre(nombreVisitante);
                    Date fecha = (Date) spFecha.getValue();

                    Partido partido = new Partido(local, visitante);
                    partido.setFecha(fecha);

                    SerieNacional.getInstance().agregarPartido(partido);

                    JOptionPane.showMessageDialog(AgregarPartido.this, "Partido agendado exitosamente.");
                    dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AgregarPartido.this, "Error al agendar partido: " + ex.getMessage());
                }
            }
        });

        JLabel fondo = new JLabel(new ImageIcon(getClass().getResource("/images/agregarpartido.png")));
        fondo.setBounds(0, 0, 900, 555);
        panel.add(fondo);
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1);
    }

    private void hacerInvisible(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    private void cargarEquipos() {
        cbLocal.removeAllItems();
        cbVisitante.removeAllItems();
        for (Equipo e : SerieNacional.getInstance().getClasificacion()) {
            cbLocal.addItem(e.getNombre());
            cbVisitante.addItem(e.getNombre());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AgregarPartido().setVisible(true);
            }
        });
    }
}
