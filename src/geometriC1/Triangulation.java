package geometriC1;

import java.util.ArrayList;
import java.util.List;

public List<Edge> triangulate(List<PoligonoConvexo> pols){
	public List<Edge> triangulacion = new ArrayList<Edge>();
	
	for(int i=0; i<pols.lenght()-1; i++){
		PoligonoConvexo afuera = pols[i];
		PoligonoConvexo adentro = pols[i+1];
		
		Edge primer = new Edge(afuera[0], adentro[0]);
		triangulacion.add(primer);
		
		int actual_afuera = 0;
		int actual_adentro = 1; 
		
		for()
		}
	}