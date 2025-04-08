 package Visual;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import Logico.*;

public class RegUsuario extends JFrame {

    private JTextField txtNombre;
    private JPasswordField txtContrasena, txtConfirmar;
    private JComboBox cbTipoUsuario;
    private JButton btnRegistrar, btnCancelar;

    public RegUsuario() {
        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(540, 578);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 540, 578);
        panel.setOpaque(false);
        getContentPane().add(panel);

        txtNombre = new JTextField();
        txtNombre.setBounds(152, 189, 234, 25);
        estilizarInput(txtNombre);
        panel.add(txtNombre);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(152, 241, 234, 25);
        estilizarInput(txtContrasena);
        panel.add(txtContrasena);

        txtConfirmar = new JPasswordField();
        txtConfirmar.setBounds(152, 295, 234, 25);
        estilizarInput(txtConfirmar);
        panel.add(txtConfirmar);

        cbTipoUsuario = new JComboBox();
        cbTipoUsuario.setBounds(152, 349, 234, 25);
        cbTipoUsuario.addItem("Administrador");
        cbTipoUsuario.addItem("Usuario");
        cbTipoUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        cbTipoUsuario.setFocusable(false);
        cbTipoUsuario.setBackground(Color.WHITE);
        cbTipoUsuario.setForeground(Color.BLACK);
        panel.add(cbTipoUsuario);

        btnRegistrar = new JButton();
        btnRegistrar.setBounds(128, 387, 270, 37);
        hacerInvisible(btnRegistrar);
        panel.add(btnRegistrar);

        btnCancelar = new JButton();
        btnCancelar.setBounds(384, 469, 122, 44);
        hacerInvisible(btnCancelar);
        panel.add(btnCancelar);

        btnCancelar.addActionListener(e -> {
            dispose();
            new Login().setVisible(true);
        });

        btnRegistrar.addActionListener((ActionEvent e) -> registrarUsuario());

        JLabel fondo = new JLabel(new ImageIcon(RegUsuario.class.getResource("/images/registrousuario.png")));
        fondo.setBounds(0, 0, 528, 540);
        panel.add(fondo);
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1);
    }

    private void registrarUsuario() {
        String nombreUsuario = txtNombre.getText().trim();
        String contrasena = new String(txtContrasena.getPassword()).trim();
        String confirmar = new String(txtConfirmar.getPassword()).trim();
        String tipo = cbTipoUsuario.getSelectedItem().toString();

        if (nombreUsuario.isEmpty() || contrasena.isEmpty() || confirmar.isEmpty() || tipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos");
            return;
        }

        if (!contrasena.equals(confirmar)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
            return;
        }

        boolean isAdmin = tipo.equalsIgnoreCase("Administrador");

        User user = new User(tipo, nombreUsuario, contrasena, isAdmin);
        Control.getInstance().registrarUsuario(user);

        try (FileOutputStream fos = new FileOutputStream("usuarios.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(Control.getInstance().getUsuarios());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error guardando el usuario");
            return;
        }

        JOptionPane.showMessageDialog(this, "Usuario registrado correctamente");
        dispose();
        new Login().setVisible(true);
    }

    private void hacerInvisible(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    private void estilizarInput(JTextField txt) {
        txt.setOpaque(false);
        txt.setBorder(null);
        txt.setForeground(Color.BLACK);
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegUsuario().setVisible(true));
    }
} 