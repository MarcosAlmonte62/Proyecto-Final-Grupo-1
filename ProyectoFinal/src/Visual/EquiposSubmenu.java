package Visual;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class EquiposSubmenu extends JFrame {

    private JButton agregarEquipo, listarEquipos;

    public EquiposSubmenu() {
        setTitle("Equipos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(522, 395);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        setContentPane(layeredPane);

        // Custom panel that correctly resizes the image
        BackgroundPanel backgroundPanel = new BackgroundPanel("/images/equipos.png");
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight());

        configurarBotones(layeredPane);

        layeredPane.add(backgroundPanel, Integer.valueOf(1));
    }

    private void configurarBotones(JLayeredPane layeredPane) {
        agregarEquipo = new JButton();
        listarEquipos = new JButton();

        agregarEquipo.setBounds(137, 175, 230, 32);
        listarEquipos.setBounds(137, 213, 230, 32);

        for (JButton button : new JButton[]{agregarEquipo, listarEquipos}) {
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
        }

        agregarEquipo.addActionListener(e -> new AgregarEquipo().setVisible(true));
        listarEquipos.addActionListener(e -> new ListarEquipos().setVisible(true));

        layeredPane.add(agregarEquipo, Integer.valueOf(2));
        layeredPane.add(listarEquipos, Integer.valueOf(2));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EquiposSubmenu().setVisible(true));
    }

    // Custom panel that scales the background image properly
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl == null) throw new RuntimeException("Image not found in resources!");

                backgroundImage = new ImageIcon(imageUrl).getImage();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage());
                System.exit(1);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
