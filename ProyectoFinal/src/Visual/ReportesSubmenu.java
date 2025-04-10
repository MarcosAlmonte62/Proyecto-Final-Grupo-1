package Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class ReportesSubmenu extends JFrame {

    private JButton btnReporteGlobal;

    public ReportesSubmenu() {
        setTitle("Reportes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(541, 431);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null); // <- importante para diseño absoluto

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 541, 431);
        panel.setOpaque(false);
        getContentPane().add(panel);

        // Boton invisible sobre la imagen
        btnReporteGlobal = new JButton();
        btnReporteGlobal.setBounds(147, 190, 233, 35); 
        hacerInvisible(btnReporteGlobal);
        panel.add(btnReporteGlobal);

        btnReporteGlobal.addActionListener(e -> new ReportesGlobales().setVisible(true));

        // Imagen como JLabel para que sea visible en el WindowBuilder
        JLabel fondo = new JLabel(new ImageIcon(ReportesSubmenu.class.getResource("/images/reportessubmenu - Copy.png")));
        fondo.setBounds(0, 0, 529, 387);
        panel.add(fondo);
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1); // Mandar al fondo
    }

    private void hacerInvisible(JButton b) {
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReportesSubmenu().setVisible(true));
    }
}
