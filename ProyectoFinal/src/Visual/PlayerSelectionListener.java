package Visual;

import java.util.List;
import Logico.Jugador;

@FunctionalInterface
public interface PlayerSelectionListener {
    void onPlayersSelected(List<Jugador> seleccionados);
}
