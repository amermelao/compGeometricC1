package geometriC1.poligonoconvexo;

import java.util.List;

public class PoligonoConvexo <E>{
	
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
