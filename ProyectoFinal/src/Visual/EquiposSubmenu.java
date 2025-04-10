package Visual;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.net.URL;

public class EquiposSubmenu extends JFrame {

    private JButton agregarEquipo;
    private JButton listarEquipos;

    public EquiposSubmenu() {
        setTitle("Equipos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(541, 431);
        setResizable(false);
        setLocationRelativeTo(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel("/images/equipos.png");
        backgroundPanel.setLayout(null);

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

        backgroundPanel.add(agregarEquipo);
        backgroundPanel.add(listarEquipos);

        getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EquiposSubmenu().setVisible(true));
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl == null) throw new RuntimeException("Imagen no encontrada en recursos!");
                backgroundImage = ImageIO.read(imageUrl);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error cargando la imagen: " + e.getMessage());
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
