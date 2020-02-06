
public class Poly {
	
	private SquareType[][] shape;
	
	private int x, y;
	private int width, height;
	
	public Poly(SquareType[][] shape) {
		this.shape = shape;
		x = 2;
		y = 2;
		width = shape.length-1;
		height = shape.length-1;
	}
	
	public void rotateRight() {

		SquareType[][] newShape = new SquareType[width+1][height+1];

	    for (int r = 0; r < width+1; r++) {
	        for (int c = 0; c < height+1; c++){
	        	newShape[c][height+1-1-r] = shape[r][c];
	        }
	    }

	    shape = newShape;
	    
	}
	
	public void rotateLeft() {
		for(int i = 0; i < 3; i++) {
			rotateRight();
		}
	}
	
	public void fall() {
		y++;
	}
	
	public void moveUp() {
		y--;
	}
	
	public void moveLeft() {
		x--;
	}
	
	public void moveRight() {
		x++;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public SquareType getSquareType(int x, int y) {
		return shape[x][y];
	}
	
}
