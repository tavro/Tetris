
public class TetrominoMaker {
	
	public static int getNumberOfTypes() {
		//-1 because we want to remove OUTSIDE
		//return SquareType.values().length-1;
		return 7;
	}
	
    public static Poly getPoly(int n) {
    	if(n < 0 || n > getNumberOfTypes()) {
    		throw new IllegalArgumentException("Invalid index: " + n);
    	}
    	switch(n) {
    		default:
    			//O
    			SquareType[][] o = {{SquareType.O, SquareType.O},
    								{SquareType.O, SquareType.O}};
    			return new Poly(o);
    		case 0:
    			//L1
    			SquareType[][] l1 = {{SquareType.L1, SquareType.EMPTY, SquareType.EMPTY},
    								 {SquareType.L1, SquareType.L1, SquareType.L1},
    								 {SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}};
    			return new Poly(l1);
    		case 1:
    			//L2
    			SquareType[][] l2 = {{SquareType.EMPTY, SquareType.EMPTY, SquareType.L2},
    								 {SquareType.L2, SquareType.L2, SquareType.L2},
    								 {SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}};
    			return new Poly(l2);
    		case 2:
    			//Z1
    			SquareType[][] z1 = {{SquareType.EMPTY, SquareType.Z1, SquareType.Z1},
    								 {SquareType.Z1, SquareType.Z1, SquareType.EMPTY},
    								 {SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}};
    			return new Poly(z1);
    		case 3:
    			//Z2
    			SquareType[][] z2 = {{SquareType.Z2, SquareType.Z2, SquareType.EMPTY},
    								 {SquareType.EMPTY, SquareType.Z2, SquareType.Z2},
    								 {SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}};
    			return new Poly(z2);
    		case 4:
    			//T
    			SquareType[][] t = {{SquareType.EMPTY, SquareType.T, SquareType.EMPTY},
    								 {SquareType.T, SquareType.T, SquareType.T},
    								 {SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}};
    			return new Poly(t);
    		case 5:
    			//I
    			SquareType[][] i = {{SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY},
    								 {SquareType.I, SquareType.I, SquareType.I, SquareType.I},
    								 {SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY},
    								 {SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}};
    			return new Poly(i);
    	}
    }
}
