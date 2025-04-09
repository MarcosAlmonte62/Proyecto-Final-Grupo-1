package Visual;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

import Logico.*;

public class SimulacionVisual extends JFrame {

    private JTextArea consola;

    public SimulacionVisual(Partido partido) {
        setTitle("Simulación del Partido");
        setSize(920, 574);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 920, 600);
        panel.setOpaque(false);
        getContentPane().add(panel);

        consola = new JTextArea();
        consola.setEditable(false);
        consola.setFont(new Font("Courier New", Font.PLAIN, 14));
        consola.setForeground(Color.GREEN);
        consola.setBackground(Color.BLACK);

        JScrollPane scroll = new JScrollPane(consola);
        scroll.setBounds(50, 50, 800, 450);
        panel.add(scroll);

        JLabel fondo = new JLabel(new ImageIcon(SimulacionVisual.class.getResource("/images/fondo900.png")));
        fondo.setBounds(0, -37, 908, 595);
        panel.add(fondo);
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1);

        PrintStream printStream = new PrintStream(new ConsoleOutputStream());
        System.setOut(printStream);
        System.setErr(printStream);

        new Thread(() -> partido.simularPartido()).start();
    }

    private class ConsoleOutputStream extends OutputStream {
        @Override
        public void write(int b) {
            consola.append(String.valueOf((char) b));
            consola.setCaretPosition(consola.getDocument().getLength());
        }
    }

    public static void main(String[] args) {
        if (SerieNacional.getInstance().getClasificacion().size() >= 2) {
            Equipo local = SerieNacional.getInstance().getClasificacion().get(0);
            Equipo visitante = SerieNacional.getInstance().getClasificacion().get(1);
            Partido partido = new Partido(local, visitante);
            partido.setDuracion(15000);
            SwingUtilities.invokeLater(() -> new SimulacionVisual(partido).setVisible(true));
        } else {
            System.out.println("No hay suficientes equipos registrados.");
        }
    }
}
