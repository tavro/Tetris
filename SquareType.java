import java.awt.Color;

public enum SquareType {
	EMPTY(0,0,0), I(0,255,255), O(255,255,0), T(155,0,255), Z1(0,255,0), Z2(255,0,0), L1(0,0,255), L2(255,155,0), OUTSIDE(100,100,100);
	
    private Color color;
    
    private SquareType(int r, int g, int b) {
        color = new Color(r, g, b);
    }
    
    public Color getColor() {
    	return color;
    }
    
}
