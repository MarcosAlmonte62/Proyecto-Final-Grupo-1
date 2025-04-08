package Visual;

import Logico.Partido;
import Logico.SerieNacional;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class SimulacionVisual extends JFrame {

    private JTextArea consola;

    public SimulacionVisual(final Partido partido) {
        setTitle("Simulación del Partido");
        setSize(920, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

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

        JLabel fondo = new JLabel(new ImageIcon(getClass().getResource("/images/fondo_partido.png")));
        fondo.setBounds(0, 0, 920, 600);
        panel.add(fondo);
        panel.setComponentZOrder(fondo, panel.getComponentCount() - 1);

        // Redirigir System.out y System.err a la consola
        PrintStream printStream = new PrintStream(new ConsoleOutputStream());
        System.setOut(printStream);
        System.setErr(printStream);

        // Ejecutar simulación en un hilo separado
        new Thread(new Runnable() {
            @Override
            public void run() {
                partido.simularPartido();
            }
        }).start();
    }

    // Clase interna para capturar la salida de consola
    private class ConsoleOutputStream extends OutputStream {
        @Override
        public void write(int b) {
            consola.append(String.valueOf((char) b));
            consola.setCaretPosition(consola.getDocument().getLength());
        }
    }

    // Método main para pruebas
    public static void main(String[] args) {
        // Asegúrate de tener al menos 2 equipos creados
        SerieNacional liga = SerieNacional.getInstance();

        if (liga.getEquipos().size() >= 2) {
            Partido partido = new Partido(
                    liga.getEquipos().get(0),
                    liga.getEquipos().get(1)
            );
            partido.setDuracion(15000); // 15 segundos

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new SimulacionVisual(partido).setVisible(true);
                }
            });
        } else {
            System.out.println("Debes tener al menos 2 equipos en SerieNacional para simular un partido.");
        }
    }
}
