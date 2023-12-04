import facade.Facade;
import javafx.application.Application;
import javafx.stage.Stage;
import interfaces.*;



public class Main extends Application {
    
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        MenuGame menu = new MenuGame();
        SudokuSolverGUI sudoku = new SudokuSolverGUI();
        Facade fachada = new Facade(sudoku, menu);
        
        fachada.showMenu();

    }
}
