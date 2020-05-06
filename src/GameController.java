import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class GameController {
    
    private Board gameBoard;
    public int guesses;
    public int badGuesses;
    private Random generator;
    
    
    public GameController() {
        gameBoard = new Board();
        generator = new Random();
        guesses = 0;
        badGuesses = 0;
    }
    
    public void loadNewGame(Level level) {
        gameBoard.generateBoard();
        setVisibleCells(level);
    }
    
    public void processGuess(int guess, String cellName) {
        Cell cellRef = gameBoard.getCell(cellName);
        if(cellRef.getTrueVal() == guess) {
            cellRef.setTrueVal(guess);
            cellRef.setVisible();
        } else {
            badGuesses++;
        }
        guesses++;
    }
    
    public void setVisibleCells(Level level) {
        int numCells = gameBoard.getCellList().size();
        switch (level) {
        case HARD :
            generator.ints(28, 0, numCells - 1).forEach(x -> gameBoard.getCellList().get(x).setVisible());
        case MEDIUM :
            generator.ints(32, 0, numCells - 1).forEach(x -> gameBoard.getCellList().get(x).setVisible());
        case EASY :
            generator.ints(38, 0, numCells - 1).forEach(x -> gameBoard.getCellList().get(x).setVisible());
        }
    }

    public void display() {
        for(int i = 1; i < 82; i++) {
            Cell tgtCell = gameBoard.getCellList().get(i-1);
            System.out.print(tgtCell.isVisible ? tgtCell.getTrueVal() : ".");
            if(i % 3 == 0) {
                System.out.print("\t");
            }
            if(i % 9 == 0) {
                System.out.println("");
            }
            if(i % 27 == 0) {
                System.out.println("");
            }
        }
        
    }
}
