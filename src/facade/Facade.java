package facade;

import interfaces.MenuGame;
import interfaces.SudokuSolverGUI;
import javafx.stage.Stage;


public class Facade {
    SudokuSolverGUI sudoku;
    MenuGame menu;
    Stage primaryStage;


    public Facade(SudokuSolverGUI sudoku, MenuGame menu){
        this.sudoku = sudoku;
        this.menu = menu;
    }


    public void showMenu(){
        menu.InterfaceMenu(sudoku);
    }

}
