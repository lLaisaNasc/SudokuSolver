import javafx.application.Application;
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

public class SudokuSolverGUI extends Application {

    GameBoard gameBoard = new GameBoard();
    private String selectedNumber = null;

    private void updateBoard(GridPane root) { // -- "reseta" a interface gráfica ---
        for (Node child : root.getChildren()) {
            if (child instanceof Button) {
                Button button = (Button) child;
                int row = GridPane.getRowIndex(button);
                int col = GridPane.getColumnIndex(button);
                int num = gameBoard.getNumberAt(row, col);
                button.setText(num == 0 ? "" : Integer.toString(num));
            }
        }
    }

    public void winnerBoard() {
        Stage winnerStage = new Stage();
        winnerStage.initModality(Modality.APPLICATION_MODAL); // Para tornar a janela modal

        Label winnerLabel = new Label("Congratulations, The Puzzle has been solved!");
        winnerLabel.setStyle("-fx-text-fill: #399; -fx-font-weight: bold; -fx-font-size: 20px;");

        VBox layout = new VBox(10);
        layout.getChildren().add(winnerLabel);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 100);
        winnerStage.setScene(scene);
        winnerStage.setTitle("Winner!");
        winnerStage.showAndWait();

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vbox = new VBox(40);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(4);
        root.setVgap(4);

        for (int i = 0; i < gameBoard.getSize(); i++) { // -- Faz com que cada posição no tabuleiro seja um botão e
                                                        // define o layout --
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

                btn.setOnAction(e -> { // -- Insere o número do botao selecionado no tabuleiro --
                    if (selectedNumber != null) {
                        int num = Integer.parseInt(selectedNumber);
                        int row = GridPane.getRowIndex(btn);
                        int col = GridPane.getColumnIndex(btn);

                        if (gameBoard.insertNumber(row, col, num)) {
                            btn.setText(selectedNumber);
                            btn.setStyle(btn.getStyle()
                                    + "-fx-text-fill: #fff; -fx-font-weight: bold; -fx-font-size: 20px;");
                        } else {
                            System.out.println("Número em uma posição inválida!");
                        }
                    }
                });

                root.add(btn, j, i);
            }
        }

        GridPane numberPane = new GridPane();
        numberPane.setAlignment(Pos.CENTER);
        numberPane.setHgap(5);

        // --- Botões do menu com imagens sendo os ícone e define o layout

        GridPane menuPane = new GridPane();
        menuPane.setAlignment(Pos.CENTER);
        menuPane.setHgap(5);

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
                }
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
        });

        menuPane.add(solveButton, 0, 0);
        menuPane.add(undoButton, 1, 0);
        menuPane.add(randomButton, 2, 0);
        menuPane.add(resetButton, 3, 0);

        for (int i = 1; i <= 9; i++) { // -- cria os botões dos números e define o layout --
            Button numberButton = new Button(Integer.toString(i));
            numberButton.setPrefWidth(40);
            numberButton.setPrefHeight(40);
            numberButton.setStyle(
                    "-fx-background-color: #1c1d27; -fx-text-fill: #455ab0; -fx-font-weight: bold; -fx-border-color: #3a3a3a; -fx-border-width: 1px; -fx-border-radius: 4px; -fx-font-size: 24px");
            numberPane.add(numberButton, i + 2, 0);

            numberButton.setOnAction(e -> {
                selectedNumber = numberButton.getText();
            });
        }

        vbox.getChildren().addAll(root, numberPane, menuPane);

        Scene scene = new Scene(vbox, 630, 700);
        scene.getRoot().setStyle("-fx-background-color: #000;");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Sudoku Solver");
        primaryStage.show();
    }
}
