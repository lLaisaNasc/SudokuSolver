import java.util.ArrayList;
import java.util.List;

// A classe Caregiver é responsável por armazenar os estados anteriores do tabuleiro do jogo.
public class Caregiver {

    // Esta lista armazena todos os estados anteriores do tabuleiro do jogo.
    private final List<GameBoardMemento> estadosAnteriores = new ArrayList<>();

    // Este método adiciona um estado do tabuleiro do jogo à lista de estados anteriores.
    public void addMemento(GameBoardMemento memento) {
        estadosAnteriores.add(memento);
    }

    // Este método retorna o último estado do tabuleiro do jogo que foi salvo e o remove da lista de estados anteriores.
    public GameBoardMemento getLastMemento() {
        if (estadosAnteriores.isEmpty()) {
            return null;
        }
        return estadosAnteriores.remove(estadosAnteriores.size() - 1);
    }
}
