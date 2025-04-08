package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Optional;
import Logico.*;

public class Login extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnLogin, btnSalir;

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(540, 578);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 540, 540);
        panel.setOpaque(false);
        getContentPane().add(panel);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 202, 231, 25);
        estilizarInput(txtUsuario);
        panel.add(txtUsuario);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(150, 289, 231, 25);
        estilizarInput(txtContrasena);
        panel.add(txtContrasena);

        btnLogin = new JButton();
        btnLogin.setBounds(132, 347, 268, 35);
        hacerInvisible(btnLogin);
        panel.add(btnLogin);

        btnSalir = new JButton();
        btnSalir.setBounds(382, 470, 122, 35);
        hacerInvisible(btnSalir);
        panel.add(btnSalir);

        btnLogin.addActionListener((ActionEvent e) -> {
            String usuario = txtUsuario.getText().trim();
            String clave = new String(txtContrasena.getPassword()).trim();

            if (usuario.isEmpty() || clave.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete los campos para ingresar.");
                return;
            }

            Optional<User> user = Control.getInstance().confirmarLogin(usuario, clave);
            if (user.isPresent()) {
                Control.setUsuarioLogueado(user.get());
                new Principal().setVisible(true);
                dispose();
            } else {
                int opcion = JOptionPane.showConfirmDialog(this, "Usuario no encontrado. ¿Desea registrarse?", "Login", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    new RegUsuario().setVisible(true);
                    dispose();
                }
            }
        });

        btnSalir.addActionListener(e -> System.exit(0));

        JLabel fondo = new JLabel(new ImageIcon(getClass().getResource("/images/login.png")));
        fondo.setBounds(0, 0, 528, 540);
        panel.add(fondo);
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1);
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
        SwingUtilities.invokeLater(() -> {
            SerieNacional.getInstance().cargarUsuariosDesdeArchivo();
            new Login().setVisible(true);
        });
    }
}
