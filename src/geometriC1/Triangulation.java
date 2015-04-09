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

	public List triangulate(List<PoligonoConvexo> pols){
		List triangulaciones = new ArrayList<>();

		for(int i=0; i<pols.size()-1; i++){
			PoligonoConvexo afuera = pols.get(i);
			PoligonoConvexo adentro = pols.get(i+1);
			
			List<Edge> triang = new ArrayList<Edge>();
		
			/* Comienzo uniendo los vértices 0 de cada polígono */
			Edge primer = new Edge(afuera.getVertex(0), adentro.getVertex(0));
			triang.add(primer);
		
			int actual_afuera = 0;
			Edge currEdge = primer;
			
			int verticesAdentro = adentro.points.size();
			int verticesAfuera = afuera.points.size();
			
			/* j es el indice actual del poligono "interior" */
			int j = 1;
			AbstractPoint currVertex = adentro.getVertex(0);
			
			if(verticesAdentro > 1){
				currVertex = adentro.getVertex(j);
			}
			
			while(j < verticesAdentro){
				currVertex = adentro.getVertex(j); 
				/* Si currVertex está a la derecha de currEdge */
				if(!Edge.toLeft(currEdge, currVertex)){
					Edge nuevo = new Edge(currEdge.a, currVertex);
					triang.add(nuevo);
					currEdge = nuevo;
					
					/* Agrego también a la triangulación el edge del polígono interno */
					Edge nuevo2 = new Edge(adentro.getVertex(j-1), adentro.getVertex(j));
					triang.add(nuevo2);
					
					j++;
				}else{
					actual_afuera++;
					/* Agrega el arco "intermedio" */
					AbstractPoint prevVertex = adentro.getVertex(j-1);
					Edge cierra = new Edge(afuera.getVertex(actual_afuera), prevVertex);
					triang.add(cierra);
					currEdge = cierra;
					
					/* Agrego también a la triangulación el edge del polígono externo */
					Edge nuevo2 = new Edge(afuera.getVertex(actual_afuera-1), afuera.getVertex(actual_afuera));
					triang.add(nuevo2);
									
					/* Mientras currVertex esté a la izquierda de currEdge */
					while(Edge.toLeft(currEdge, currVertex)){
						actual_afuera++;
						Edge c2 = new Edge(afuera.getVertex(actual_afuera), prevVertex);
						triang.add(c2);
						currEdge = c2;
					}
						
					/* Une los dos vertices */
					Edge nuevo = new Edge(afuera.getVertex(actual_afuera), currVertex);
					triang.add(nuevo);
					currEdge = nuevo;
					j++;
				}
			}
			
			/* En el caso que el poligono de afuera tenga más vértices que el de
			 * adentro, une los vértices que "sobran" al último vértice interno */
			while(actual_afuera < verticesAfuera){
				actual_afuera++;
				AbstractPoint currVertexAfuera = afuera.getVertex(actual_afuera);
				Edge nuevo = new Edge(currVertexAfuera, currVertex);
				triang.add(nuevo);
			}
			
			triangulaciones.add(triang);
		}
		
		return triangulaciones;
	}
	


}