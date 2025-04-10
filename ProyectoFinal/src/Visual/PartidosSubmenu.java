package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PartidosSubmenu extends JFrame {

    private JButton btnAgendar, btnRegistrar, btnConsultar;

    public PartidosSubmenu() {
        setTitle("Submenu Partidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(541, 431);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 541, 431);
        panel.setOpaque(false);
        getContentPane().add(panel);

        btnAgendar = new JButton();
        btnAgendar.setBounds(95, 195, 335, 46);
        hacerInvisible(btnAgendar);
        btnAgendar.addActionListener(e -> new AgregarPartido().setVisible(true));
        panel.add(btnAgendar);

        btnRegistrar = new JButton();
        btnRegistrar.setBounds(92, 254, 338, 46);
        hacerInvisible(btnRegistrar);
        btnRegistrar.addActionListener(e -> new RegistrarResultado().setVisible(true));
        panel.add(btnRegistrar);

        btnConsultar = new JButton();
        btnConsultar.setBounds(95, 313, 338, 46);
        hacerInvisible(btnConsultar);
        btnConsultar.addActionListener(e -> new ConsultarPartidos().setVisible(true));
        panel.add(btnConsultar);

        JLabel fondo = new JLabel(new ImageIcon(PartidosSubmenu.class.getResource("/images/partidos11 - Copy.png")));
        fondo.setBounds(0, 0, 529, 385);
        panel.add(fondo);
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1); // Enviar al fondo
    }

    private void hacerInvisible(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PartidosSubmenu().setVisible(true));
    }
}

