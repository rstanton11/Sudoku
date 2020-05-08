import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class CellDisplay extends JButton{
    
    private String name;
    private int row;
    private int col;
    private boolean inFocus;
    private UpClassCallBack callback;
    
    // Styling
    private Color BGCOLOR = new Color(201, 219, 222);
    private Color BORDERCOLOR = new Color(214, 227, 230);
    private Color FOCUSBGCOLOR = new Color(100, 117, 121);
    private Color NEARFOCUSBGCOLOR = new Color(193, 204, 207);
    private Border basicBorder;
    private Dimension prefSize;
    
    
    public CellDisplay(int row, int col) {
        this.row = row;
        this.col = col;
        this.name = "R" + row + "C" + col;
        this.inFocus = false;
        
        
        // formatting
        prefSize = new Dimension(40, 40);
        basicBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, BORDERCOLOR);
        this.setBackground(BGCOLOR);
        this.setOpaque(true);
        this.setBorder(basicBorder);
        
        
        // add listeners
        this.addMouseListener(new CellStyler());
    }
    
    public void addUpCallBack(UpClassCallBack cb) {
        callback = cb;
    }
    
    public void setFocusColors() {
        this.setBackground(FOCUSBGCOLOR);
        this.setForeground(Color.WHITE);
    }
    
    public void unsetFocusColors() {
        this.setBackground(BGCOLOR);
        this.setForeground(Color.BLACK);
    }
    
    public void setNearFocusColors() {
        this.setBackground(NEARFOCUSBGCOLOR);
    }
    
    public void setValue(int x) {
        
        this.setText("" + x);
    }
    
    public void toggleFocus() {
        inFocus = !inFocus;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isInFocus() {
         return inFocus;
    }
    
    // ======================================================================= \\
    // =================        Mouse Listeners       ======================== \\
    // ======================================================================= \\
    
    private class CellStyler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
           toggleFocus();
           setFocusColors();
           revalidate();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            callback.styleRelatedCells(row, col);
            setNearFocusColors();
            revalidate();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(inFocus == false) {
                callback.unStyleRelatedCells(row, col);
                unsetFocusColors();
                revalidate();
            }
        }
    }
}
