package geometriC1.triangulacion;

import geometriC1.edge.Edge;
import geometriC1.edge.IntEdge;
import geometriC1.points.IntPoint;

public class IntTriangulation extends Triangulation<IntPoint, Integer>{

	@Override
	protected Edge<IntPoint, Integer> newEdge(IntPoint a, IntPoint b) {
		return new IntEdge(a, b);
	}

}
