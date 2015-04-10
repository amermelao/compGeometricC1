package geometriC1.convexhull;

import geometriC1.points.IntPoint;

public class IntConvexHull extends ConvexHull<Integer, IntPoint>
{

	@Override
	public long cross(IntPoint O, IntPoint A, IntPoint B) {
		return(long) ((A.x - O.x) * (B.y - O.y) - (A.y - O.y) * (B.x - O.x));
	}
	
	@Override
	public IntPoint doApoint(Integer x, Integer y) {
		return new IntPoint(x, y); 
	}

	@Override
	public int aCompare(Integer tmp) {
		return tmp < 0? -1:(tmp > 0?1:0);
	}

	@Override
	public Integer parseSide(String value) {
		return Integer.parseInt(value);
	}

}
