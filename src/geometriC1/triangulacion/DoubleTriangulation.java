package geometriC1.triangulacion;

import geometriC1.edge.DoubleEdge;
import geometriC1.edge.Edge;
import geometriC1.points.DoublePoint;

public class DoubleTriangulation extends Triangulation<DoublePoint, Double>{

	@Override
	protected Edge<DoublePoint, Double> newEdge(DoublePoint a, DoublePoint b) {
		return new DoubleEdge(a, b);
	}

}
