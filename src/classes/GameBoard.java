package classes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import memento.*;


// A classe GameBoard é responsável por gerenciar o tabuleiro do jogo.
public class GameBoard {

    // O tamanho do tabuleiro do jogo.
    private int size;
    // O tabuleiro do jogo.
    private int[][] board;
    // O objeto Caregiver que armazena os estados anteriores do tabuleiro do jogo.
    Caregiver caregiver;

    // O construtor da classe inicializa o tabuleiro do jogo e o objeto Caregiver.
    public GameBoard() {
        board = new int[size][size];
        caregiver = new Caregiver();
    }
    // Adicione métodos setters para permitir a configuração do tamanho, tabuleiro e cuidador.
    public void setSize(int size) {
        this.size = size;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    // Este método retorna o tamanho do tabuleiro do jogo.
    public int getSize() {
        return this.size;
    }

    // Este método verifica se um número pode ser inserido em uma posição específica no tabuleiro do jogo.
    public boolean isValid(int row, int col, int num) {
        for (int i = 0; i < size; i++) {
            // Verifica se o número já existe na linha ou coluna.
            if (board[row][i] == num)
                return false;
            if (board[i][col] == num)
                return false;
            // Verifica se o número já existe no bloco 3x3.
            if (board[row - row % 3 + i / 3][col - col % 3 + i % 3] == num)
                return false;
        }
        return true;
    }

    // Este método insere um número em uma posição específica no tabuleiro do jogo.
    public boolean insertNumber(int row, int col, int num) {
        // Verifica se o número pode ser inserido na posição.
        if (isValid(row, col, num)) {
            // Salva o estado atual do tabuleiro do jogo antes de inserir o número.
            caregiver.addMemento(save());
            // Insere o número no tabuleiro do jogo.
            board[row][col] = num;
            return true;
        }
        return false;
    }

    // Este método reseta o tabuleiro do jogo.
    public void resetBoard() {
        board = new int[size][size];
        caregiver = new Caregiver();
    }

    // Este método salva o estado atual do tabuleiro do jogo.
    public GameBoardMemento save() {
        return new GameBoardMemento(board);
    }

    // Este método desfaz a última ação no tabuleiro do jogo.
    public void undoBoard() {
        GameBoardMemento memento = caregiver.getLastMemento();
        if (memento != null) {
            this.board = memento.getSavedState();
        }
    }

    // Este método retorna o número em uma posição específica no tabuleiro do jogo.
    public int getNumberAt(int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            return board[row][col];
        }
        return -1;
    }

    // Este método resolve o quebra-cabeça.
    public boolean solvePuzzle() {
        // Lista de números a serem tentados em cada posição vazia.
        List<Integer> numbersToTry = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(numbersToTry);

        // Lista de células vazias no tabuleiro do jogo.
        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (board[row][column] == 0) {
                    emptyCells.add(new int[] { row, column });
                }
            }
        }

        // Tenta preencher cada célula vazia com um número válido.
        for (int[] cell : emptyCells) {
            int row = cell[0];
            int column = cell[1];
            for (int numberToTry : numbersToTry) {
                if (isValid(row, column, numberToTry)) {
                    board[row][column] = numberToTry;
                    if (solvePuzzle()) {
                        return true;
                    } else {
                        board[row][column] = 0;
                    }
                }
            }
            return false;
        }

        return true;
    }

    // Este método gera um quebra-cabeça aleatório.
    public void randomPuzzle() {
        // Reseta o tabuleiro do jogo.
        resetBoard();
        // Resolve o quebra-cabeça.
        solvePuzzle();

        // Remove alguns números do tabuleiro do jogo para criar o quebra-cabeça.
        Random rand = new Random();
        int numbersToRemove = rand.nextInt(6) + 28;
        for (int i = 0; i < numbersToRemove; i++) {
            int row, col;
            do {
                row = rand.nextInt(size);
                col = rand.nextInt(size);
            } while (board[row][col] == 0);

            board[row][col] = 0;
        }
    }

    
}
