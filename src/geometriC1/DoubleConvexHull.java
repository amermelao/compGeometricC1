package geometriC1;

import java.io.IOException;

import points.DoublePoint;

public class DoubleConvexHull extends ConvexHull<Double, DoublePoint>{


	@Override
	public long cross(DoublePoint O, DoublePoint A, DoublePoint B) {
		double cros = ((A.x - O.x) * (B.y - O.y) - (A.y - O.y) * (B.x - O.x));
		return cros > 0 ? 1 : (cros < 0?-1:0);
	}
	
	@Override
	public DoublePoint doApoint(Double x, Double y) {
		return new DoublePoint(x, y); 
	}

	@Override
	public int aCompare(Double tmp) {
		return tmp < 0? -1:(tmp > 0?1:0);
	}

	@Override
	public Double parseSide(String value) {
		return Double.parseDouble(value);
	}
	
	static public void main(String [] args) throws IOException{
		new DoubleConvexHull().doDebug(args[0]);
	}

}
