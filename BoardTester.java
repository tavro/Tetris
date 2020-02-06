
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

public class BoardTester {
	
	public static void main(String[] args) {
		
		Board board = new Board(14, 24);
		
		TetrisViewer tetrisViewer = new TetrisViewer(board);
		tetrisViewer.show();
		
		board.addBoardListener(tetrisViewer.getTetrisComponent());
		
	    final Action tick = new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	        	board.tick();
	        }
	    };
	    
	    final Timer clockTimer = new Timer(500, tick);
	    clockTimer.setCoalesce(true);
	    clockTimer.start();
	    
	}
	
}
