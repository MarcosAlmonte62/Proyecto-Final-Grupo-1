package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EquiposSubmenu extends JFrame {

    private JLabel imagenLabel;
    private Point initialClick;
    private JButton agregarEquipo;
    private JButton listarEquipos;

    public EquiposSubmenu() {
        setTitle("Equipos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(522, 395);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        ImageIcon originalIcon = new ImageIcon("C:/Users/EliteBook/eclipse-workspace/probandovisual/src/images/equipos.png");
        if (originalIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            JOptionPane.showMessageDialog(this, "Error: Imagen no encontrada en la ruta especificada.");
            System.exit(1);
        }

        int availableWidth = getWidth();
        int availableHeight = getHeight();

        Image scaledImage = originalIcon.getImage().getScaledInstance(availableWidth, availableHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        imagenLabel = new JLabel(scaledIcon);
        imagenLabel.setBounds(0, 0, 504, 348);

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

        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(availableWidth, availableHeight));
        panel.add(imagenLabel, Integer.valueOf(1));  
        panel.add(agregarEquipo, Integer.valueOf(2)); 
        panel.add(listarEquipos, Integer.valueOf(2)); 

        getContentPane().add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EquiposSubmenu().setVisible(true));
    }
}