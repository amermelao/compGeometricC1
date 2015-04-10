package geometriC1.edge;

import geometriC1.points.DoublePoint;

public class DoubleEdge extends Edge<DoublePoint, Double>{

	public DoubleEdge(DoublePoint _a, DoublePoint _b) {
		super(_a, _b);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Double area2(DoublePoint a, DoublePoint b, DoublePoint c) {
		return (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
	}

	@Override
	public boolean toLeft(Edge<DoublePoint, Double> e, DoublePoint p) {
		DoublePoint a = e.a;
		DoublePoint b = e.b;
		return area2(a, b, p) > 0;
	}

}
