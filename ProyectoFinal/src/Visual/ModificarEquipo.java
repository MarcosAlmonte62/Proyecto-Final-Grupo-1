package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Logico.*;

public class ModificarEquipo extends JFrame {

    private JTextField txtNombre, txtEstadio;
    private JComboBox cbCiudad;
    private JButton btnGuardar, btnCancelar, btnEliminar;

    private Equipo equipoOriginal;

    public ModificarEquipo(Equipo equipo) {
        this.equipoOriginal = equipo;

        setTitle("Modificar Equipo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(909, 575);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 917, 612);
        panel.setOpaque(false);
        getContentPane().add(panel);

        JLabel lblNombre = crearLabel("Nombre:", 96, 160);
        panel.add(lblNombre);
        txtNombre = new JTextField(equipo.getNombre());
        txtNombre.setBounds(200, 160, 300, 25);
        estilizarInput(txtNombre);
        panel.add(txtNombre);

        JLabel lblCiudad = crearLabel("Ciudad:", 96, 215);
        panel.add(lblCiudad);
        String[] provincias = {
            "Azua", "Bahoruco", "Barahona", "Dajabón", "Distrito Nacional", "Duarte", "Elías Piña",
            "El Seibo", "Espaillat", "Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia",
            "La Romana", "La Vega", "María Trinidad Sánchez", "Monseñor Nouel", "Monte Cristi",
            "Monte Plata", "Pedernales", "Peravia", "Puerto Plata", "Samaná", "San Cristóbal",
            "San José de Ocoa", "San Juan", "San Pedro de Macorís", "Sánchez Ramírez",
            "Santiago", "Santiago Rodríguez", "Santo Domingo", "Valverde"
        };
        cbCiudad = new JComboBox(provincias);
        cbCiudad.setBounds(200, 215, 300, 25);
        cbCiudad.setFont(new Font("Arial", Font.PLAIN, 14));
        cbCiudad.setSelectedItem(equipo.getCiudad());
        panel.add(cbCiudad);

        JLabel lblEstadio = crearLabel("Estadio:", 96, 270);
        panel.add(lblEstadio);
        txtEstadio = new JTextField(equipo.getEstadio());
        txtEstadio.setBounds(200, 270, 300, 25);
        estilizarInput(txtEstadio);
        panel.add(txtEstadio);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(90, 440, 180, 45);
        estilizarBoton(btnCancelar);
        btnCancelar.addActionListener(e -> dispose());
        panel.add(btnCancelar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(270, 440, 180, 45);
        estilizarBoton(btnEliminar);
        btnEliminar.addActionListener(e -> {
            SerieNacional.getInstance().getClasificacion().remove(equipoOriginal);
            JOptionPane.showMessageDialog(this, "Equipo eliminado correctamente.");
            dispose();
        });
        panel.add(btnEliminar);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBounds(450, 440, 220, 45);
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
        label.setBounds(x, y, 100, 20);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private void guardarCambios() {
        try {
            equipoOriginal.setNombre(txtNombre.getText().trim());
            equipoOriginal.setCiudad(cbCiudad.getSelectedItem().toString());
            equipoOriginal.setEstadio(txtEstadio.getText().trim());

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
            if (!SerieNacional.getInstance().getClasificacion().isEmpty()) {
                new ModificarEquipo(SerieNacional.getInstance().getClasificacion().get(0)).setVisible(true);
            }
        });
    }
}