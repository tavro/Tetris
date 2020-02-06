import java.awt.BorderLayout;

import javax.swing.JFrame;

public class TetrisViewer {
	
	private TetrisComponent tetrisComponent;
	private Board board;
	
	public TetrisViewer(Board board) {
		this.board = board;
		tetrisComponent = new TetrisComponent(board);
	}
	
	public TetrisComponent getTetrisComponent() {
		return tetrisComponent;
	}
	
	public void show() {
		JFrame frame = new JFrame("Tetris");
		frame.setLayout(new BorderLayout());
		frame.setSize(tetrisComponent.getPreferredSize());
		frame.add(tetrisComponent);
		frame.addKeyListener(board);
		frame.pack();
		frame.setVisible(true);
	}
	
}
