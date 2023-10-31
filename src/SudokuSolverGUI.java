import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SudokuSolverGUI extends Application {

    GameBoard gameBoard = new GameBoard();
    private String selectedNumber = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vbox = new VBox(40);
        vbox.setAlignment(Pos.CENTER);
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(4);
        root.setVgap(4);

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

                btn.setOnAction(e -> {
                    if (selectedNumber != null) {
                        btn.setText(selectedNumber);
                        String oldStyle = btn.getStyle();
                        btn.setStyle(oldStyle + "-fx-text-fill: #fff; -fx-font-weight: bold; -fx-font-size: 20px;");
                    }
                });
                root.add(btn, j, i);
            }
        }

        GridPane numberPane = new GridPane();
        numberPane.setAlignment(Pos.CENTER);
        numberPane.setHgap(5);

        GridPane buttonPane = new GridPane();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setHgap(5);

        ImageView solveIcon = new ImageView(new Image("/img/solve.png"));
        solveIcon.setFitWidth(40);
        solveIcon.setFitHeight(40);
        Button solveButton = new Button("Solve Puzzle", solveIcon);
        solveButton.setStyle("-fx-background-color: #000");

        ImageView undoIcon = new ImageView(new Image("/img/undo.png"));
        undoIcon.setFitWidth(40);
        undoIcon.setFitHeight(40);
        Button undoButton = new Button("Undo", undoIcon);
        undoButton.setStyle("-fx-background-color: #000");

        ImageView resetIcon = new ImageView(new Image("/img/reset.png"));
        resetIcon.setFitWidth(40);
        resetIcon.setFitHeight(40);
        Button resetButton = new Button("Reset", resetIcon);
        resetButton.setStyle("-fx-background-color: #000");

        buttonPane.add(solveButton, 0, 0);
        buttonPane.add(undoButton, 1, 0);
        buttonPane.add(resetButton, 2, 0);

        for (int i = 1; i <= 9; i++) {
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

        vbox.getChildren().addAll(root, buttonPane, numberPane);
        Scene scene = new Scene(vbox, 600, 700);
        scene.getRoot().setStyle("-fx-background-color: #000;");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sudoku Solver");
        primaryStage.show();
    }
}
