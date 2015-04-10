package geometriC1.edge;

import geometriC1.points.IntPoint;

public class IntEdge extends Edge<IntPoint, Integer>{

	public IntEdge(IntPoint _a, IntPoint _b) {
		super(_a, _b);
	}

	@Override
	protected Integer area2(IntPoint a, IntPoint b, IntPoint c) {
		return (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
	}

	@Override
	public boolean toLeft(Edge<IntPoint, Integer> e, IntPoint p) {
		IntPoint a = e.a;
		IntPoint b = e.b;
		return area2(a, b, p) > 0;
	}

}
