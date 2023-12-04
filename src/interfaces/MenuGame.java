package interfaces;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuGame {

    public void InterfaceMenu(SudokuSolverGUI sudoku) {
        Stage menu = new Stage();
        // Create the main stage
        menu.setTitle("Sudoku Solver Menu");

        Image icon = new Image("/img/icon.png");
        menu.getIcons().add(icon);


        // Create buttons for the menu
        Button sudokuSolverButton = createMenuButton("");
        Button randomGameButton = createMenuButton("");
        Button exitButton = createMenuButton("");

        // Set the background of the buttons and their text to be transparent
        sudokuSolverButton.setStyle("-fx-background-color: transparent;");
        randomGameButton.setStyle("-fx-background-color: transparent;");
        exitButton.setStyle("-fx-background-color: transparent;");

        // Set the size of each button
        sudokuSolverButton.setMinSize(200, 50); // Width, Height
        randomGameButton.setMinSize(200, 50);
        exitButton.setMinSize(200, 50);



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

        // Create a layout for the menu
        VBox menuLayout = new VBox(40);
        menuLayout.setAlignment(Pos.CENTER);

        menuLayout.setPadding(new Insets(100, 0, 0, 0)); 

        menuLayout.getChildren().addAll( sudokuSolverButton, randomGameButton, exitButton);

        // Set the background image for the menu
        Image bg = new Image("/img/menu.png");
        BackgroundImage backgroundImage = new BackgroundImage(
                bg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        Background background = new Background(backgroundImage);
        menuLayout.setBackground(background);

        // Create the scene for the main stage
        Scene menuScene = new Scene(menuLayout, 600, 600);

        // Set the scene for the main stage
        menu.setScene(menuScene);

        // Show the main stage
        menu.show();
    }

    // Helper method to create menu buttons
    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
        return button;
    }
}
