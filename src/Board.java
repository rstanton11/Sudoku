import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Board {
    
    ArrayList<Cell> cellList;
    ArrayList<ArrayList<Cell>> groups;
    
    public Board() {
        generateBoard();
    }
    
    public void generateBoard() {
        createCells();
        createGroups();
        assignValues();
    }
    
    public void createCells() {
        cellList = new ArrayList<>();
        for(int i = 1; i < 10; i++) {
            for(int j = 1; j < 10; j++) {
                String cellID = "R" + i + "C" + j;
                Cell cell = new Cell(cellID);
                cell.setRowNum(i);
                cell.setColNum(j);
                cell.setBoxNum((int) (Math.ceil((float) j / 3) + (3 * (Math.ceil((float) i / 3) - 1))));
                cellList.add(cell);
            }     
        }
    }
    
    public void createGroups() {
        groups = new ArrayList<>();
        Map<Integer, ArrayList<Cell>> boxes = this.cellList.stream().collect(Collectors.groupingBy(Cell::getBoxNum, Collectors.toCollection(ArrayList::new)));
        Map<Integer, ArrayList<Cell>> rows = this.cellList.stream().collect(Collectors.groupingBy(Cell::getRowNum, Collectors.toCollection(ArrayList::new)));
        Map<Integer, ArrayList<Cell>> cols = this.cellList.stream().collect(Collectors.groupingBy(Cell::getColNum, Collectors.toCollection(ArrayList::new)));
        groups.addAll(boxes.values());
        groups.addAll(rows.values());
        groups.addAll(cols.values());
    }
    
    
    public void assignValueToCell(Cell tgtCell, Integer val) {
        if(tgtCell.getTrueVal() == 0) {
            tgtCell.setTrueVal(val);
            
            for(ArrayList<Cell> group : groups) {
                if(group.contains(tgtCell)) {
                    for(Cell c : group) {
                        c.removeNum(val);
                        if(c.getAvailableNums().size() == 1) {
                            assignValueToCell(c, c.getAvailableNums().get(0));
                        }
                    }
                }
            }
        }
    }
    
    public void assignValues() {
        for(Cell cell : cellList) {
            if(cell.getAvailableNums().size() > 1) {
                Collections.shuffle(cell.getAvailableNums());
                Integer val = cell.getAvailableNums().get(0);
                assignValueToCell(cell, val);
            } else if(cell.getAvailableNums().size() == 1) {
                assignValueToCell(cell, cell.getAvailableNums().get(0));
            }
        }
    }
    
    public void clearBoard() {
        cellList = new ArrayList<>();
        groups = new ArrayList<>();
        
    }
    
    public void displayBoard() {
        for(int i = 1; i < 82; i++) {
            System.out.print(cellList.get(i-1).getTrueVal());
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
    
    public ArrayList<Cell> getCellList() {
        return cellList;
    }

    public Cell getCell(String cellName) {
        return cellList.stream()
                .filter(c -> c.getName().equals(cellName))
                .findFirst()
                .get();
    }
}
