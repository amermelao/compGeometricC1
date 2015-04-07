package geometriC1;

import java.util.ArrayList;
import java.util.List;

import points.AbstractPoint;

public class PoligonoConvexo <E extends Number>{
	
	public List<AbstractPoint<E>> points = new ArrayList<AbstractPoint<E>>();
	
	public AbstractPoint<E> getVertex(int i){
		AbstractPoint<E> vertex = this.points.get(i);
		return vertex;
	}

}
