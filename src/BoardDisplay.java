import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardDisplay extends JPanel {
    
    ArrayList<CellDisplay> cells;
    CellDisplay tgtCell;
    
    public BoardDisplay() {
        cells = new ArrayList<>();
        tgtCell = null;
        this.setLayout(new GridLayout(9, 9));
        generateCells();
        
    }
    
    public void generateCells() {
        for(int i = 1; i < 10; i++) {  // row level naming
            for(int j = 1; j < 10; j++) {  // column level naming
                CellDisplay cell = new CellDisplay(i, j);
                cell.addUpCallBack(new BoardCallBack());
                cells.add(cell);
                this.add(cell);
            }
        }
    }
    
    public void highlightNearFocusCells(int r, int col) {
        for(CellDisplay cell : cells) {
            if(cell.getRow() == r || cell.getCol() == col) {
                if(!cell.isInFocus()) {
                    cell.setNearFocusColors();
                }
            }
        }
        revalidate();
    }
    
    public void unhighlightNearFocusCells(int r, int col) {
        for(CellDisplay cell : cells) {
            if(cell.getRow() == r || cell.getCol() == col) {
                if(!cell.isInFocus()) {
                    cell.unsetFocusColors();
                }
            }
        }
        revalidate();
    }
    
    
    
    // ========================================================  \\
    // =====              CallBack Class                  =====  \\
    // ========================================================  \\
    private class BoardCallBack implements UpClassCallBack {
        
        @Override
        public void styleRelatedCells(int r, int c) {
            highlightNearFocusCells(r, c);
        }
        
        @Override
        public void unStyleRelatedCells(int r, int c) {
            unhighlightNearFocusCells(r, c);
        }
        
        @Override
        public void setTargetCell(CellDisplay target) {
            tgtCell.toggleFocus();
            tgtCell = target;
            
        }
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container content = frame.getContentPane();
        
        BoardDisplay board = new BoardDisplay();
        content.add(board, BorderLayout.CENTER);
        
        frame.pack();
        frame.setVisible(true);
    }
    
}
