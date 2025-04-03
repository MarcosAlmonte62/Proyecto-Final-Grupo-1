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
        setSize(600, 400);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        cbPartido = new JComboBox<>();
        txtDetalle = new JTextArea();
        txtDetalle.setBackground(Color.DARK_GRAY);
        txtDetalle.setEditable(false);

        List<Partido> partidos = SerieNacional.getInstance().getCalendario();

        for (Partido p : partidos) {
            cbPartido.addItem(p.getEquipoLocal().getNombre() + " vs " + p.getEquipoVisitante().getNombre());
        }

        cbPartido.addActionListener(e -> mostrarDetalle(cbPartido.getSelectedIndex()));

        getContentPane().add(cbPartido, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(txtDetalle), BorderLayout.CENTER);
    }

    private void mostrarDetalle(int index) {
        if (index >= 0) {
            Partido p = SerieNacional.getInstance().getCalendario().get(index);
            String info = "Fecha: " + (p.getFecha() != null ? p.getFecha() : "No registrada") + "\n" +
                          "Ubicación: " + (p.getUbicacion() != null ? p.getUbicacion() : "Desconocida") + "\n" +
                          "Resultado: " + p.getPuntosLocal() + " - " + p.getPuntosVisitante();
            txtDetalle.setText(info);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultarPartidos().setVisible(true));
    }
}
