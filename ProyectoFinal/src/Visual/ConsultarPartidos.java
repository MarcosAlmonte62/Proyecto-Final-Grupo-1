package Visual;

import javax.swing.*;
import java.awt.*;
import Logico.*;
import java.util.List;

public class ConsultarPartidos extends JFrame {

    private JComboBox<String> cbPartido;
    private JTextArea txtDetalle;

    public ConsultarPartidos() {
        setTitle("Consultar Partidos");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(606, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        setContentPane(layeredPane);

        JLabel fondo = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon(getClass().getResource("/images/fondo500.png"));
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        fondo.setBounds(0, 0, 606, 400);
        layeredPane.add(fondo, Integer.valueOf(0));

        cbPartido = new JComboBox<>();
        cbPartido.setBounds(20, 20, 550, 30);
        layeredPane.add(cbPartido, Integer.valueOf(1));

        txtDetalle = new JTextArea();
        txtDetalle.setEditable(false);
        txtDetalle.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtDetalle.setForeground(Color.WHITE);
        txtDetalle.setBackground(new Color(0, 0, 0, 150));

        JScrollPane scrollPane = new JScrollPane(txtDetalle);
        scrollPane.setBounds(20, 70, 550, 270);
        layeredPane.add(scrollPane, Integer.valueOf(1));

        List<Partido> partidos = SerieNacional.getInstance().getCalendario();
        for (Partido p : partidos) {
            cbPartido.addItem(p.getEquipoLocal().getNombre() + " vs " + p.getEquipoVisitante().getNombre());
        }

        cbPartido.addActionListener(e -> mostrarDetalle(cbPartido.getSelectedIndex()));
    }

    private void mostrarDetalle(int index) {
        if (index >= 0) {
            Partido p = SerieNacional.getInstance().getCalendario().get(index);
            String info = "Fecha:     " + (p.getFecha() != null ? p.getFecha() : "No registrada") + "\n" +
                          "Ubicacion: " + (p.getUbicacion() != null ? p.getUbicacion() : "Desconocida") + "\n" +
                          "Resultado: " + p.getPuntosLocal() + " - " + p.getPuntosVisitante();
            txtDetalle.setText(info);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultarPartidos().setVisible(true));
    }
}
