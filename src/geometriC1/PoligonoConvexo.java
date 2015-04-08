package geometriC1;

import java.util.ArrayList;
import java.util.List;

import points.AbstractPoint;

public class PoligonoConvexo <F extends Number,E extends AbstractPoint<F>>{
	
	public List<E> points;
	
	public PoligonoConvexo(List<E> aPoints){
		points = aPoints;
	}

	@Override
	public String toString() {
		String toReturn = "|";
		for(E aPoint: points){
			toReturn = toReturn + " " + aPoint.toString() + " | ";
		}
		return toReturn;
	}
	
	public E getVertex(int i){
		return this.points.get(i);
	}
}
