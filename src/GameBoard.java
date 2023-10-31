public class GameBoard {

    private int size = 9;
    private int[][] board;

    public GameBoard() {
        board = new int[size][size];
    }

    public int getSize() {
        return this.size;
    }

    public int getBoard(int row, int col) {
        return board[row][col];
    }

    // função de inserir numeros no limite certo linhas e colunas
    //função reset
    // função desfazer
    // função solve
    //....

}
