package geometriC1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import points.AbstractPoint;
import points.IntPoint;
import geometriC1.Edge;
import geometriC1.PoligonoConvexo;
import geometriC1.ConvexHull;

public class Triangulation{

	public List<Edge> triangulate(List<PoligonoConvexo> pols){
		List<Edge> triangulacion = new ArrayList<Edge>();
		
		for(int i=0; i<pols.size()-1; i++){
			PoligonoConvexo afuera = pols.get(i);
			PoligonoConvexo adentro = pols.get(i+1);
		
			Edge primer = new Edge(afuera.getVertex(0), adentro.getVertex(0));
			triangulacion.add(primer);
		
			int actual_afuera = 0;
			Edge currEdge = primer;
			
			/* j es el indice actual del poligono "interior" */
			int j = 1;	
			
			/*TODO ojo: esto termina cuando se acaban los vertices del poligono 
			 * interior, si sobran vertices en el poli exterior quedan sin unir */
			while(j < adentro.points.size()){
				AbstractPoint currVertex = adentro.getVertex(j); 
				/* Si currVertex está a la derecha de currEdge */
				if(ConvexHull.cross(currEdge, currVertex) < 0 /*TODO revisar esto*/){
					Edge nuevo = new Edge(currEdge.a, currVertex);
					triangulacion.add(nuevo);
					currEdge = nuevo;
					j++;
				}else{
					actual_afuera++;
					/* Agrega el arco "intermedio" */
					AbstractPoint prevVertex = adentro.getVertex(j-1);
					Edge cierra = new Edge(afuera.getVertex(actual_afuera), prevVertex);
					triangulacion.add(cierra);
					currEdge = cierra;
									
					/* Mientras currVertex esté a la izquierda de currEdge */
					while(ConvexHull.cross(currEdge, currVertex) < 0 /*TODO revisar*/){
						actual_afuera++;
						Edge c2 = new Edge(afuera.getVertex(actual_afuera), prevVertex);
						triangulacion.add(c2);
						currEdge = c2;
					}
						
					/* Une los dos vertices */
					Edge nuevo = new Edge(afuera.getVertex(actual_afuera), currVertex);
					triangulacion.add(nuevo);
					currEdge = nuevo;
					j++;
				}
			}
		}
	}

}