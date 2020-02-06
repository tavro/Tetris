import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class TetrisComponent extends JComponent implements BoardListener {
	
	private Board board;
	private final static int SQUARE_SIZE = 30;
	
	public TetrisComponent(Board board) {
		this.board = board;
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(board.getWidth()*SQUARE_SIZE, board.getHeight()*SQUARE_SIZE);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		
		if(board.hasFallingPoly()) {
	        for(int x = 0; x < board.getWidth(); x++) {
	        	for(int y = 0; y < board.getHeight(); y++) {
	        		SquareType squareType = board.getSquareAt(x,y);
	        		g2d.setColor(squareType.getColor());
	        		g2d.fillRect(x*SQUARE_SIZE, y*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
	        	}
	        }
		}
        
    }
	
	public void boardChanged() {
		repaint();
	}
	
}
