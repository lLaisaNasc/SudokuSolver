public class GameBoardMemento {

    private int[][] boardState;

    public GameBoardMemento(int[][] state) {
        this.boardState = new int[state.length][];
        for (int i = 0; i < state.length; i++) {
            this.boardState[i] = state[i].clone();
        }
    }

    public int[][] getSavedState() {
        return boardState;
    }

}
