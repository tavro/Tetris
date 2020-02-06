import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Board implements KeyListener {
	
	private ArrayList<BoardListener> boardListeners = new ArrayList<BoardListener>();
	
	private static final Random RANDOM = new Random();
	private static final int SIZE = TetrominoMaker.getNumberOfTypes();
	
	private SquareType[][] squares;
	private int width, height;
	
	private Poly falling;
	
	private boolean gameOver = false;
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		squares = new SquareType[width][height];
		fillSquares(SquareType.EMPTY);
		placeBorder();
	}
	
	public boolean isFullRow(int row) {
		for(int x = 2; x < getWidth()-2; x++) {
			if(getSquareType(x, row) == SquareType.EMPTY) {
				return false;
			}
		}
		System.out.println("Has row at: " + row);
		return true;
	}
	
	public void removeRow(int row) {
		
		for(int rowAbove = row-1; rowAbove > 1; rowAbove--) {
			
			SquareType[] r = new SquareType[width-4];
			for(int x = 2; x < getWidth()-2; x++) {
				r[x-2] = getSquareType(x,rowAbove);
			}
			
			for(int x = 2; x < getWidth()-2; x++) {
				squares[x][rowAbove] = SquareType.EMPTY;
			}
			
			for(int x = 2; x < getWidth()-2; x++) {
				squares[x][rowAbove+1] = r[x-2];
			}
			
		}
		
	}
	
	public boolean hasCollision() {
		for(int x = 0; x < falling.getWidth()+1; x++) {
			for(int y = 0; y < falling.getHeight()+1; y++) {
				if(falling.getSquareType(x, y) != SquareType.EMPTY) {
					if(getSquareType(x+falling.getX(),y+falling.getY()) != SquareType.EMPTY) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void addPolyToBoard() {
		for(int x = 0; x < falling.getWidth()+1; x++) {
			for(int y = 0; y < falling.getHeight()+1; y++) {
				if(falling.getSquareType(x, y) != SquareType.EMPTY) {
					int boardX = x+falling.getX();
					int boardY = y+falling.getY();
					squares[boardX][boardY] = falling.getSquareType(x, y);
				}
			}
		}
	}
	
	public void tick() {
		if(!gameOver) {
			if(falling != null) {
				//falling finns
				falling.fall();
				if(hasCollision()) {
					//har landat
					falling.moveUp();
					addPolyToBoard();
					falling = null;
				}
				
				for(int row = 2; row < getHeight()-2; row++) {
					if(isFullRow(row)) {
						removeRow(row);
					}
				}
				
			}
			else {
				//falling finns inte
				falling = TetrominoMaker.getPoly(RANDOM.nextInt(SIZE));
				if(hasCollision()) {
					gameOver = true;
				}
			}
			if(hasFallingPoly()) {
				notifyListeners();
			}
		}
	}
	
	public boolean hasFallingPoly() {
		if(falling == null) {
			return false;
		}
		return true;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void addBoardListener(BoardListener bl) {
		boardListeners.add(bl);
	}
	
	private void notifyListeners() {
		for(BoardListener bl : boardListeners) {
			bl.boardChanged();
		}
	}
	
	public SquareType getSquareType(int x, int y) {
		return squares[x][y];
	}
	
	public SquareType getSquareAt(int x, int y) {
		int x1 = falling.getX();
		int y1 = falling.getY();
		
		int x2 = x1 + falling.getHeight();
		int y2 = y1 + falling.getWidth();
		
		if(x < x1 || y < y1 || x > x2 || y > y2) {
			//falling är inte en del av (x, y)
			return getSquareType(x,y);
		}
		else {
			//falling är en del av (x, y)
			int internalX = (x - x1);
			int internalY = (y - y1);
			if(falling.getSquareType(internalX, internalY) == SquareType.EMPTY) {
				//vänstra hörnet i falling är tomt
				return getSquareType(x,y);
			}
			else {
				//vänstra hörnet i falling är inte tomt
				return falling.getSquareType(internalX, internalY);
			}
		}
		
	}
	
	public SquareType getRandomSquareType() {
		return SquareType.values()[RANDOM.nextInt(SIZE)];
	}
	
	public void randomFillSquares() {
		for(int x = 0; x < getWidth(); x++) {
			for(int y = 0; y < getHeight(); y++) {
				squares[x][y] = getRandomSquareType();
			}
		}
		notifyListeners();
	}
	
	public void fillSquares(SquareType type) {
		for(int x = 0; x < getWidth(); x++) {
			for(int y = 0; y < getHeight(); y++) {
				squares[x][y] = type;
			}
		}
		notifyListeners();
	}
	
	public void placeBorder() {
		for(int x = 0; x < getWidth(); x++) {
			for(int y = 0; y < getHeight(); y++) {
				if(x < 2 || y < 2 || x > getWidth()-3 || y > getHeight()-3) {
					squares[x][y] = SquareType.OUTSIDE;
				}
			}
		}
	}
	
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();

        if(falling != null){
            if(code == KeyEvent.VK_LEFT){
            	falling.moveLeft();
            	if(hasCollision()) {
            		falling.moveRight();
            	}
            }
            else if(code == KeyEvent.VK_RIGHT){
            	falling.moveRight();
            	if(hasCollision()) {
            		falling.moveLeft();
            	}
            }
            else if(code == KeyEvent.VK_UP){
            	falling.rotateRight();
            	if(hasCollision()) {
            		falling.rotateLeft();
            	}
            }
            notifyListeners();
        }
    }    
    
    public void keyTyped(KeyEvent e){

    }

    public void keyReleased(KeyEvent e){

    }

}
