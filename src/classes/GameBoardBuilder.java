package classes;
import memento.Caregiver; 

public class GameBoardBuilder {
    private int size;
    private int[][] board;
    private Caregiver caregiver;

    public GameBoardBuilder() {
        board = new int[size][size];
        caregiver = new Caregiver();
    }

    public GameBoardBuilder size(int size) {
        this.size = size;
        return this;
    }

    public GameBoardBuilder board(int[][] board) {
        this.board = board;
        return this;
    }

    public GameBoardBuilder caregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
        return this;
    }

    public GameBoard build() {
        // Construa e retorne uma instância de GameBoard com os parâmetros configurados.
        GameBoard gameBoard = new GameBoard();
        gameBoard.setSize(size);
        gameBoard.setBoard(board);
        gameBoard.setCaregiver(caregiver);
        return gameBoard;
    }
}
