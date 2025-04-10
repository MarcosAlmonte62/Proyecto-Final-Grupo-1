package Visual;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.net.URL;

public class JugadoresSubmenu extends JFrame {

    private JButton agregarJugador;
    private JButton listarJugadores;

    public JugadoresSubmenu() {
        setTitle("Jugadores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(541, 431);
        setResizable(false);
        setLocationRelativeTo(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel("/images/jugadores.png");
        backgroundPanel.setLayout(null);

        agregarJugador = new JButton();
        listarJugadores = new JButton();

        agregarJugador.setBounds(137, 175, 230, 32);  
        listarJugadores.setBounds(137, 213, 230, 32);

        for (JButton button : new JButton[]{agregarJugador, listarJugadores}) {
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
        }

        agregarJugador.addActionListener(e -> new AgregarJugador().setVisible(true));
        listarJugadores.addActionListener(e -> new ListarJugadores().setVisible(true));

        backgroundPanel.add(agregarJugador);
        backgroundPanel.add(listarJugadores);

        getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JugadoresSubmenu().setVisible(true));
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl == null) throw new RuntimeException("Imagen no encontrada!");
                backgroundImage = ImageIO.read(imageUrl);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error cargando imagen: " + e.getMessage());
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
