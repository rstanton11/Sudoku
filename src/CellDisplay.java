import java.awt.Color;
import java.awt.Container;
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
    
    // Styling
    private Color BGCOLOR = new Color(230, 241, 245);
    private Color BORDERCOLOR = new Color(206, 217, 220);
    private Color FOCUSBGCOLOR = new Color(199, 222, 229);
    private Border basicBorder;
    
    
    public CellDisplay(String name, int value) {
        this.name = name;
        this.value = value;
        basicBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, BORDERCOLOR);
        
        // formatting
        // this.setBorderPainted(false);
        this.setBackground(BGCOLOR);
        this.setOpaque(true);
        this.setBorder(basicBorder);
        
        
        // add listeners
        this.addMouseListener(new ResponseStyler());
    }
    
    public void setFocusColors() {
        this.setBackground(FOCUSBGCOLOR);
    }
    
    public void unsetFocusColors() {
        this.setBackground(BGCOLOR);
    }
    
    // ======================================================================= \\
    // =================        Mouse Listeners       ======================== \\
    // ======================================================================= \\
    
    private class ResponseStyler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            
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
            unsetFocusColors();
        }
        
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container content = frame.getContentPane();
        content.setLayout(new GridLayout(9, 9));
        
        for(int i = 1; i < 82; i++) {
            int row = i / 9 + 1;
            int col = i % 9;
            
            String name = "R" + row + "C" + col;
            
            System.out.println(name);
            CellDisplay curCell = new CellDisplay(name, i);
            content.add(curCell);
        }
        
        
        frame.pack();
        frame.setVisible(true);
    }

}
