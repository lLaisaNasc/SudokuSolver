package interfaces;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import memento.Caregiver;
import classes.*;

// A classe SudokuSolverGUI é responsável por criar a interface gráfica do usuário para o jogo de Sudoku.
public class SudokuSolverGUI {

     // Usa o GameBoardBuilder para construir uma instância de GameBoard(O tabuleiro do jogo).
        GameBoard gameBoard = new GameBoardBuilder()
        .size(9)  // Define o tamanho conforme necessário.
        .board(new int[9][9])  // Passa um tabuleiro inicial, se necessário.
        .caregiver(new Caregiver())  // Passa um cuidador inicial, se necessário.
        .build();

    // O número selecionado pelo usuário.
    private String selectedNumber = null;
    // A linha e coluna selecionadas pelo usuário.
    private int selectedRow = -1;
    private int selectedColumn = -1;

    // Este método atualiza o tabuleiro do jogo na interface gráfica.
    private void updateBoard(GridPane root) {
        // Percorre todos os filhos do GridPane.
        for (Node child : root.getChildren()) {
            // Verifica se o filho é um botão.
            if (child instanceof Button) {
                Button button = (Button) child;
                // Obtém a linha e coluna do botão.
                int row = GridPane.getRowIndex(button);
                int col = GridPane.getColumnIndex(button);
                // Obtém o número no tabuleiro do jogo na posição correspondente.
                int num = gameBoard.getNumberAt(row, col);
                // Define o texto do botão para o número obtido.
                button.setText(num == 0 ? "" : Integer.toString(num));

                // Se o botão corresponder à célula selecionada, destaca o botão.
                if (row == selectedRow && col == selectedColumn) {
                    button.setStyle(button.getStyle() + "-fx-background-color: #ffcc00;");
                } else {
                    // Caso contrário, remove o destaque do botão.
                    button.setStyle(button.getStyle().replace("-fx-background-color: #ffcc00;", ""));
                }
            }
        }
    }

    // Este método limpa o destaque de uma célula no tabuleiro do jogo na interface
    // gráfica.
    private void clearCellHighlight(GridPane root, int row, int col) {
        // Obtém o botão na posição especificada.
        Button button = (Button) getNodeByRowColumnIndex(row, col, root);
        // Remove o destaque do botão.
        button.setStyle(button.getStyle().replace("-fx-background-color: #ffcc00;", ""));
    }

    // Este método retorna um nó em uma posição específica no GridPane.
    private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        // Percorre todos os nós no GridPane.
        for (Node node : gridPane.getChildren()) {
            // Se o nó estiver na posição especificada, retorna o nó.
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return node;
            }
        }
        // Se nenhum nó for encontrado na posição especificada, retorna null.
        return null;
    }

    // Este método limpa todos os destaques de células no tabuleiro do jogo na
    // interface gráfica.
    private void clearAllCellHighlights(GridPane root) {
        // Percorre todos os filhos do GridPane.
        for (Node child : root.getChildren()) {
            // Verifica se o filho é um botão.
            if (child instanceof Button) {
                Button button = (Button) child;
                // Remove o destaque do botão.
                button.setStyle(button.getStyle().replace("-fx-background-color: #ff0000;", ""));
            }
        }
    }

    // Este método exibe uma janela de diálogo quando o usuário resolve o
    // quebra-cabeça.
    public void winnerBoard() {
        // Cria uma nova janela de diálogo.
        Stage winnerStage = new Stage();
        // Define a janela de diálogo como modal.
        winnerStage.initModality(Modality.APPLICATION_MODAL);

        // Cria um rótulo para a mensagem de vitória.
        Label winnerLabel = new Label("Congratulations, The Puzzle has been solved!");
        // Define o estilo do rótulo.
        winnerLabel.setStyle("-fx-text-fill: #399; -fx-font-weight: bold; -fx-font-size: 20px;");

        // Cria um layout vertical para a janela de diálogo.
        VBox layout = new VBox(10);
        // Adiciona o rótulo ao layout.
        layout.getChildren().add(winnerLabel);
        // Centraliza os elementos no layout.
        layout.setAlignment(Pos.CENTER);

        // Cria uma cena para a janela de diálogo.
        Scene scene = new Scene(layout, 500, 100);
        // Define a cena para a janela de diálogo.
        winnerStage.setScene(scene);
        // Define o título da janela de diálogo.
        winnerStage.setTitle("Winner!");
        // Exibe a janela de diálogo e aguarda até que seja fechada.
        winnerStage.showAndWait();
    }

    

    
    public void Jogar(int opcao) {
        Stage primaryStage = new Stage();

        // Cria um VBox para conter todos os elementos da interface gráfica.
        VBox vbox = new VBox(40);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        // Cria um GridPane para o tabuleiro do jogo.
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(4);
        root.setVgap(4);

        // Cria os botões para o tabuleiro do jogo.
        for (int i = 0; i < gameBoard.getSize(); i++) {
            for (int j = 0; j < gameBoard.getSize(); j++) {

                Button btn = new Button();
                btn.setPrefWidth(50);
                btn.setPrefHeight(50);

                if ((i / 3 + j / 3) % 2 == 0) {
                    btn.setStyle("-fx-background-color: #2c2e43;");
                } else {
                    btn.setStyle("-fx-background-color:  #1e202e;");
                }

                btn.setStyle(btn.getStyle() + "-fx-text-fill: #399; -fx-font-weight: bold; -fx-font-size: 20px;");

                // Define a ação a ser executada quando o botão é clicado.
                btn.setOnAction(e -> {
                    int row = GridPane.getRowIndex(btn);
                    int col = GridPane.getColumnIndex(btn);

                    // Limpar todas as células destacadas em vermelho ao selecionar uma nova célula
                    clearAllCellHighlights(root);

                    if (selectedNumber != null) {
                        if (gameBoard.insertNumber(row, col, Integer.parseInt(selectedNumber))) {
                            btn.setText(selectedNumber);
                            btn.setStyle(
                                    btn.getStyle()
                                            + "-fx-text-fill: #fff; -fx-font-weight: bold; -fx-font-size: 20px;");
                            selectedRow = row;
                            selectedColumn = col;
                        } else {
                            System.out.println("Número em uma posição inválida!");

                            // Destacar a célula em vermelho
                            btn.setStyle(
                                    btn.getStyle()
                                            + "-fx-background-color: #ff0000; -fx-text-fill: #fff; -fx-font-weight: bold; -fx-font-size: 20px;");
                        }
                    }
                });

                root.add(btn, j, i);
            }
        }

        // Cria um GridPane para os números que podem ser selecionados.
        GridPane numberPane = new GridPane();
        numberPane.setAlignment(Pos.CENTER);
        numberPane.setHgap(5);

        // Cria um GridPane para o menu.
        GridPane menuPane = new GridPane();
        menuPane.setAlignment(Pos.CENTER);
        menuPane.setHgap(5);

        // Cria os botões para o menu.
        ImageView solveIcon = new ImageView(new Image("/img/solve.png"));
        solveIcon.setFitWidth(40);
        solveIcon.setFitHeight(40);

        Button solveButton = new Button(" Solve Puzzle ", solveIcon);
        solveButton.setStyle(
                "-fx-background-color: #000; -fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #455ab0;");
        solveButton.setOnAction(e -> {
            gameBoard.solvePuzzle();
            if (gameBoard.solvePuzzle()) {
                updateBoard(root);
                winnerBoard();
            } else {
                System.out.println("Puzzle sem solução");
            }
        });

        ImageView undoIcon = new ImageView(new Image("/img/undo.png"));
        undoIcon.setFitWidth(40);
        undoIcon.setFitHeight(40);

        Button undoButton = new Button(" Undo ", undoIcon);
        undoButton.setStyle(
                "-fx-background-color: #000; -fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #455ab0;");
        undoButton.setOnAction(e -> {
            gameBoard.undoBoard();
            updateBoard(root);
        });

        ImageView resetIcon = new ImageView(new Image("/img/reset.png"));
        resetIcon.setFitWidth(40);
        resetIcon.setFitHeight(40);
        Button resetButton = new Button(" Reset ", resetIcon);
        resetButton.setStyle(
                "-fx-background-color: #000; -fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #455ab0;");
        resetButton.setOnAction(e -> {
            gameBoard.resetBoard();
            for (Node child : root.getChildren()) {
                if (child instanceof Button) {
                    Button button = (Button) child;
                    button.setText("");
                    button.setStyle(button.getStyle().replace("-fx-background-color: #ff0000;", ""));
                }
            }

            // Limpar o destaque da célula selecionada
            if (selectedRow != -1 && selectedColumn != -1) {
                clearCellHighlight(root, selectedRow, selectedColumn);
                selectedRow = -1;
                selectedColumn = -1;
            }
        });

        ImageView randomIcon = new ImageView(new Image("/img/random.png"));
        resetIcon.setFitWidth(40);
        resetIcon.setFitHeight(40);

        Button randomButton = new Button(" Random ", randomIcon);
        randomButton.setStyle(
                "-fx-background-color: #000; -fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #455ab0;");
        randomButton.setOnAction(e -> {
            gameBoard.randomPuzzle();
            updateBoard(root);

            // Limpar o destaque da célula selecionada
            if (selectedRow != -1 && selectedColumn != -1) {
                clearCellHighlight(root, selectedRow, selectedColumn);
                selectedRow = -1;
                selectedColumn = -1;
            }
        });

        if(opcao == 1){
           menuPane.add(solveButton, 0, 0);
            menuPane.add(undoButton, 1, 0);
            menuPane.add(resetButton, 2, 0); 
            // Define o título do palco principal.
            primaryStage.setTitle("Sudoku Solver");
        }else if(opcao == 2){
            menuPane.add(undoButton, 1, 0);
            menuPane.add(randomButton, 0, 0);
            menuPane.add(resetButton, 2, 0);
            // Define o título do palco principal.
            primaryStage.setTitle("Random");
            gameBoard.randomPuzzle();
            updateBoard(root);

            // Limpar o destaque da célula selecionada
            if (selectedRow != -1 && selectedColumn != -1) {
                clearCellHighlight(root, selectedRow, selectedColumn);
                selectedRow = -1;
                selectedColumn = -1;
            }
        }
        

        for (int i = 1; i <= 9; i++) {
            Button numberButton = new Button(Integer.toString(i));
            numberButton.setPrefWidth(40);
            numberButton.setPrefHeight(40);
            numberButton.setStyle(
                    "-fx-background-color: #1c1d27; -fx-text-fill: #455ab0; -fx-font-weight: bold; -fx-border-color: #3a3a3a; -fx-border-width: 1px; -fx-border-radius: 4px; -fx-font-size: 24px");
            numberPane.add(numberButton, i + 2, 0);

            numberButton.setOnAction(e -> {
                selectedNumber = numberButton.getText();

                // Limpar todas as células destacadas em vermelho ao selecionar um novo número
                clearAllCellHighlights(root);

                // Destacar o número selecionado no menu
                for (Node child : numberPane.getChildren()) {
                    if (child instanceof Button) {
                        Button button = (Button) child;
                        button.setStyle(
                                "-fx-background-color: #1c1d27; -fx-text-fill: #455ab0; -fx-font-weight: bold; -fx-border-color: #3a3a3a; -fx-border-width: 1px; -fx-border-radius: 4px; -fx-font-size: 24px");
                    }
                }
                numberButton.setStyle(
                        "-fx-background-color: #ffcc00; -fx-text-fill: #455ab0; -fx-font-weight: bold; -fx-border-color: #3a3a3a; -fx-border-width: 1px; -fx-border-radius: 4px; -fx-font-size: 24px");
            });
        }

        // Adiciona os GridPanes ao VBox.
        vbox.getChildren().addAll(root, numberPane, menuPane);

        // Cria a cena e adiciona o VBox a ela.
        Scene scene = new Scene(vbox, 630, 700);
        scene.getRoot().setStyle("-fx-background-color: #000;");

        // Define a cena para o palco principal.
        primaryStage.setScene(scene);
        
        // Exibe o palco principal.
        primaryStage.show();
    }
}
