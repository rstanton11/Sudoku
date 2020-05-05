import java.util.ArrayList;

/**
 * Data structure for the cell of a sudoku board. 
 * @author robertstanton
 *
 */
public class Cell {
    
    int trueVal;
    String name; // of type row column i.e. R1C1 = row 1 column 1
    ArrayList<Integer> availableNums;
    boolean isVisible;
    int rowNum;
    int colNum;
    int boxNum;
    
    
    /**
     * Construct default cell with isVisible false.  Must assign values to the trueVal and name instance variables.
     */
    public Cell(String name) {
        isVisible = false;
        availableNums = new ArrayList<>();
        this.name = name;
        this.trueVal = 0;
        for(int i = 1; i < 10; i++) {
            availableNums.add(i);
        }
    }
    
    /**
     * Assign cell value for the game.  Must be an integer between 1 and 9 inclusive.
     * @param tv
     */
    public void setTrueVal(int tv ) {
        trueVal = tv;
        
        if(tv < 1 || tv > 9) {
            trueVal = 0;
        }
        
        if(trueVal == 0 ) {
            availableNums.clear();
        }
     }
    
    /**
     * get the cell value for this cell.
     * @return
     */
    public int getTrueVal() {
        return trueVal;
    }
    
    public ArrayList<Integer> getAvailableNums() {
        return availableNums;
    }
    
    public void removeNum(int x) {
        availableNums.remove((Integer) x);
        
    }
    
    /**
     * Assign name of the cell.  Should be of the form R1C1 for row 1 column 1.
     * @param rowCol
     */
    public void setName(String rowCol) {
        name = rowCol;
    }
    
    /**
     * Get this cells name.
     * @return
     */
    public String getName() {
        return name;
    }
    
    public int getRowNum() {
        return rowNum;
    }
    
    public void setRowNum(int num) {
        rowNum = num;
    }
    
    public int getColNum() {
        return colNum;
    }
    
    public void setColNum(int num) {
        colNum = num;
    }
    
    public int getBoxNum() {
        return boxNum;
    }
    
    public void setBoxNum(int num) {
        boxNum = num;
    }
    
    // ============================================================================== \\
    // ======================   OVERRIDEN METHODS   ================================= \\
    // ============================================================================== \\
    
    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o.getClass() != this.getClass()) {
            return false;
        }
        
        if(this.name.equals(((Cell) o).getName())) {
            return true;
        }
        return false;
    }
}
