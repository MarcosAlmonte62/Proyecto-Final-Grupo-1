package Visual;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.net.URL;

public class Principal extends JFrame {

    private JLayeredPane layeredPane;
    private JButton btnEquipos, btnJugadores, btnPartidos, btnReportes, btnButton5;

    public Principal() {
        initComponents();
        setupActions();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("StatHoops");
        setResizable(false);
        setSize(966, 564);
        setLocationRelativeTo(null);

        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        setContentPane(layeredPane);

        BackgroundPanel backgroundPanel = new BackgroundPanel("/images/principal.png");
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(backgroundPanel, Integer.valueOf(1));

        btnEquipos = new JButton();
        btnJugadores = new JButton();
        btnPartidos = new JButton();
        btnReportes = new JButton();
        btnButton5 = new JButton();

        btnEquipos.setBounds(275, 13, 148, 50);
        btnJugadores.setBounds(432, 13, 166, 50);
        btnPartidos.setBounds(610, 13, 141, 50);
        btnReportes.setBounds(763, 13, 157, 50);
        btnButton5.setBounds(636, 403, 243, 64);

        layeredPane.add(btnEquipos, Integer.valueOf(2));
        layeredPane.add(btnJugadores, Integer.valueOf(2));
        layeredPane.add(btnPartidos, Integer.valueOf(2));
        layeredPane.add(btnReportes, Integer.valueOf(2));
        layeredPane.add(btnButton5, Integer.valueOf(2));
    }

    private void setupActions() {
        JButton[] botones = {btnEquipos, btnJugadores, btnPartidos, btnReportes, btnButton5};
        for (JButton b : botones) {
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            b.setFocusPainted(false);
        }

        btnEquipos.addActionListener(e -> new EquiposSubmenu().setVisible(true));
        btnJugadores.addActionListener(e -> new JugadoresSubmenu().setVisible(true));
        btnPartidos.addActionListener(e -> new PartidosSubmenu().setVisible(true));
        btnReportes.addActionListener(e -> new ReportesSubmenu().setVisible(true));
        btnButton5.addActionListener(e -> JOptionPane.showMessageDialog(this, "Button 5"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Principal().setVisible(true));
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl == null) throw new RuntimeException("Imagen no encontrada");
                backgroundImage = ImageIO.read(imageUrl);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen: " + e.getMessage());
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
