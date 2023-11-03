public class GameBoard {

    private int size = 9;
    private int[][] board;

    public GameBoard() {
        board = new int[size][size];
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
            if (board[row - row % 3 + i / 3][col - col % 3 + i % 3] == num)
                return false;
        }
        return true;
    }

    public boolean insertNumber(int row, int col, int num) {
        if (isValid(row, col, num)) {
            board[row][col] = num;
            return true;
        }
        return false;
    }

    public void resetBoard(){
        board = new int[size][size];
    }

    public void undoBoard(){
        //padrão de projeto comportamental - Memento
    }

    public void solvePuzzle(){
        //TO DO
    }

    //tratamento de casos sem solução
    //gerar gameboard aleatório para resolução do usuário


}
