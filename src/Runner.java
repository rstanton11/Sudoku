import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Runner {
    
    
    public static void main(String[] args) {
        
        GameController controller = new GameController();
        controller.loadNewGame(Level.EASY);
        controller.display();
    }
}
