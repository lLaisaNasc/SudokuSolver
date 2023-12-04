package memento;
// A classe GameBoardMemento é responsável por armazenar um estado do tabuleiro do jogo.
public class GameBoardMemento {

    // Esta matriz bidimensional armazena o estado do tabuleiro do jogo.
    private int[][] boardState;

    // O construtor da classe recebe o estado do tabuleiro do jogo e faz uma cópia dele.
    public GameBoardMemento(int[][] state) {
        this.boardState = new int[state.length][];
        for (int i = 0; i < state.length; i++) {
            this.boardState[i] = state[i].clone();
        }
    }

    // Este método retorna o estado salvo do tabuleiro do jogo.
    public int[][] getSavedState() {
        return boardState;
    }
}
