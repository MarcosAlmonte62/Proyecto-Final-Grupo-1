package Visual;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.net.URL;

@SuppressWarnings("serial")
public class Principal extends JFrame {

    private JLayeredPane layeredPane;
    private JButton btnEquipos;
    private JButton btnJugadores;
    private JButton btnPartidos;
    private JButton btnReportes;
    private JButton btnButton5;

    public Principal() {
        initComponents();
        setupBackground();
        setupActions();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("StatHoops");
        setResizable(false);
        setSize(952, 565);
        setLocationRelativeTo(null);

        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        setContentPane(layeredPane);

        btnEquipos = new JButton();
        btnJugadores = new JButton();
        btnPartidos = new JButton();
        btnReportes = new JButton();
        btnButton5 = new JButton();

        btnEquipos.setBounds(275, 13, 148, 50);
        btnJugadores.setBounds(432, 13, 166, 50);
        btnPartidos.setBounds(610, 13, 141, 50);
        btnReportes.setBounds(763, 13, 157, 50);
        btnButton5.setBounds(629, 399, 250, 61);

        layeredPane.add(btnEquipos, Integer.valueOf(2));
        layeredPane.add(btnJugadores, Integer.valueOf(2));
        layeredPane.add(btnPartidos, Integer.valueOf(2));
        layeredPane.add(btnReportes, Integer.valueOf(2));
        layeredPane.add(btnButton5, Integer.valueOf(2));
    }

    private void setupBackground() {
        try {
            URL imageUrl = getClass().getResource("/images/principal.png");
            if (imageUrl == null) {
                throw new RuntimeException("Image not found in resources!");
            }

            // Leer y escalar la imagen al tamaño de la ventana
            Image originalImage = ImageIO.read(imageUrl);
            Image scaledImage = originalImage.getScaledInstance(946, 529, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Configurar el JLabel con la imagen escalada
            JLabel backgroundLabel = new JLabel(new ImageIcon(Principal.class.getResource("/images/principalv.png")));
            backgroundLabel.setBounds(0, 0, 946, 529);

            // Añadirlo al fondo
            layeredPane.add(backgroundLabel, Integer.valueOf(1));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage());
            System.exit(1);
        }
    }

    private void setupActions() {
        for (JButton button : new JButton[]{btnEquipos, btnJugadores, btnPartidos, btnReportes, btnButton5}) {
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
        }

        btnEquipos.addActionListener(e -> new EquiposSubmenu().setVisible(true));
        btnJugadores.addActionListener(e -> new JugadoresSubmenu().setVisible(true));
        btnPartidos.addActionListener(e -> new PartidosSubmenu().setVisible(true));
        btnReportes.addActionListener(e -> new ReportesSubmenu().setVisible(true));
        btnButton5.addActionListener(e -> JOptionPane.showMessageDialog(this, "Button 5 clicked!"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Principal().setVisible(true));
    }
}
