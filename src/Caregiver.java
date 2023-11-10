import java.util.ArrayList;
import java.util.List;

public class Caregiver {

    private final List<GameBoardMemento> estadosAnteriores = new ArrayList<>();

    public void addMemento(GameBoardMemento memento) {
        estadosAnteriores.add(memento);
    }

    public GameBoardMemento getLastMemento() {
        if (estadosAnteriores.isEmpty()) {
            return null;
        }
        return estadosAnteriores.remove(estadosAnteriores.size() - 1);
    }
}
