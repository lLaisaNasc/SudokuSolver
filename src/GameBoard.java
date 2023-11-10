import java.util.Random;

public class GameBoard {

    private int size = 9;
    private int[][] board;
    Caregiver caregiver;

    public GameBoard() {
        board = new int[size][size];
        caregiver = new Caregiver();
    }

    public int getSize() {
        return this.size;
    }

    private boolean isValid(int row, int col, int num) {
        for (int i = 0; i < size; i++) {

            if (board[row][i] == num)
                return false;
            if (board[i][col] == num)
                return false;
            if (board[row - row % 3 + i / 3][col - col % 3 + i % 3] == num) // verifica se é válido nos blocos 3x3
                return false;
        }
        return true;
    }

    public boolean insertNumber(int row, int col, int num) {

        if (isValid(row, col, num)) {
            caregiver.addMemento(save()); // salva no "cuidador" antes de colocar no tabuleiro
            board[row][col] = num;
            return true;
        }
        return false;
    }

    public void resetBoard() {
        board = new int[size][size];
        caregiver = new Caregiver();
    }

    // -- padrão de projeto comportamental - Memento --

    public GameBoardMemento save() {
        return new GameBoardMemento(board);
    }

    public void undoBoard() {
        GameBoardMemento memento = caregiver.getLastMemento();
        if (memento != null) {
            this.board = memento.getSavedState();
        }
    }

    public int getNumberAt(int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < size) { // retorna a posição exata no tabuleiro para
                                                                // posteriormente atualizar a interface gráfica
            return board[row][col];
        }
        return -1;
    }

    // -- tratamento de casos sem solução ---

    public void solvePuzzle() {
        // TO DO
    }

    public void randomPuzzle() {
        Random rand = new Random();
        resetBoard();

        int numbersToFill = rand.nextInt(6) + 28;

        for (int i = 0; i < numbersToFill; i++) {
            int row, col, num;
            do {
                row = rand.nextInt(size);
                col = rand.nextInt(size);
                num = rand.nextInt(size) + 1;
            } while (!isValid(row, col, num) || board[row][col] != 0);

            board[row][col] = num;
        }

    }

    // -- para quando o usuário for no aleatório e querer resolver o puzzle dado --
    public void Winner() {

    }

}
