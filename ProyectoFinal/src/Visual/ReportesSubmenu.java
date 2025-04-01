package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReportesSubmenu extends JFrame {

    private JLabel imagenLabel;
    private Point initialClick;

    public ReportesSubmenu() {
        setTitle("Mover Imagen Libremente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(522, 395);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        ImageIcon originalIcon = new ImageIcon("C:/Users/EliteBook/eclipse-workspace/probandovisual/src/images/reportes.png");
        if (originalIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            JOptionPane.showMessageDialog(this, "Error: Imagen no encontrada en la ruta especificada.");
            System.exit(1);
        }

        int availableWidth = getWidth();
        int availableHeight = getHeight();

        Image scaledImage = originalIcon.getImage()
                .getScaledInstance(availableWidth, availableHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        imagenLabel = new JLabel(scaledIcon);
        imagenLabel.setLocation(0, 0);
        imagenLabel.setSize(504, 348);

        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(availableWidth, availableHeight));
        panel.add(imagenLabel);

        imagenLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });
        
        imagenLabel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point currentPoint = e.getPoint();
                int newX = imagenLabel.getX() + (currentPoint.x - initialClick.x);
                int newY = imagenLabel.getY() + (currentPoint.y - initialClick.y);
                
                int maxX = panel.getWidth() - imagenLabel.getWidth();
                int maxY = panel.getHeight() - imagenLabel.getHeight();
                
                newX = Math.max(0, Math.min(newX, maxX));
                newY = Math.max(0, Math.min(newY, maxY));
                
                imagenLabel.setLocation(newX, newY);
            }
        });

        getContentPane().add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ReportesSubmenu().setVisible(true);
        });
    }
}