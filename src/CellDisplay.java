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
    private int value;
    private boolean inFocus;
    
    // Styling
    private Color BGCOLOR = new Color(201, 219, 222);
    private Color BORDERCOLOR = new Color(214, 227, 230);
    private Color FOCUSBGCOLOR = new Color(100, 117, 121);
    private Border basicBorder;
    private Dimension prefSize;
    
    
    public CellDisplay(String name, int value) {
        this.name = name;
        this.value = value;
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
    
    public void setFocusColors() {
        this.setBackground(FOCUSBGCOLOR);
        this.setForeground(Color.WHITE);
    }
    
    public void unsetFocusColors() {
        this.setBackground(BGCOLOR);
        this.setForeground(Color.BLACK);
    }
    
    public void setValue(int x) {
        
        this.setText("" + x);
    }
    
    public void toggleFocus() {
        inFocus = !inFocus;
    }
    
    // ======================================================================= \\
    // =================        Mouse Listeners       ======================== \\
    // ======================================================================= \\
    
    private class CellStyler implements MouseListener {
        int y = 1;
        @Override
        public void mouseClicked(MouseEvent e) {
           toggleFocus();
           repaint();
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
            setFocusColors();
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(inFocus == false) {
                unsetFocusColors();
            }
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(400, 400));
        Container content = frame.getContentPane();
        content.setLayout(new GridLayout(9, 9));
        
        
        for(int i = 1; i < 82; i++) {
            int row = i / 9;
            int col = i / 9;
            
            String name = "R" + row + "C" + col;
            
            System.out.println(name);
            CellDisplay curCell = new CellDisplay(name, i);
            content.add(curCell);
        }
        
        
        frame.pack();
        frame.setVisible(true);
    }

}
