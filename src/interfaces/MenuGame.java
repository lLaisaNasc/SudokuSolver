package interfaces;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuGame {
    public void InterfaceMenu(SudokuSolverGUI sudoku) {
        // Cria uma nova janela de diálogo.
        Stage menu = new Stage();
        // Create the main stage
        menu.setTitle("Sudoku Solver Menu");
        

        // Create buttons for the menu
        Button sudokuSolverButton = createMenuButton("Sudoku Solver");
        Button randomGameButton = createMenuButton("Random Game");
        Button exitButton = createMenuButton("Exit");
        

        // Add actions to the menu buttons
        sudokuSolverButton.setOnAction(e -> {
            int opcao = 1;
            sudoku.Jogar(opcao);
        });

        randomGameButton.setOnAction(e -> {
            int opcao = 2;
            sudoku.Jogar(opcao);
        });

        exitButton.setOnAction(e -> menu.close());


        Label mainMenuLabel = new Label("MAIN MENU");
        mainMenuLabel.setStyle("-fx-text-fill: #fff; -fx-font-weight: bold; -fx-font-size: 24px;");

        // Create a layout for the menu
        VBox menuLayout = new VBox(40);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getChildren().addAll(mainMenuLabel ,sudokuSolverButton, randomGameButton, exitButton);

        // Create the scene for the main stage
        Scene menuScene = new Scene(menuLayout, 400, 450);
        menuScene.getRoot().setStyle("-fx-background-color: #1c1d27;");

        // Set the scene for the main stage
        menu.setScene(menuScene);

        // Show the main stage
        menu.show();
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(160);
        button.setPrefHeight(40);
        button.setStyle(
                "-fx-background-color: #000; -fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #455ab0;");
        return button;
    }
}
