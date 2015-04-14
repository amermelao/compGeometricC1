package geometriC1.triangulacion;

import java.util.ArrayList;
import java.util.List;

import geometriC1.edge.Edge;
import geometriC1.poligonoconvexo.PoligonoConvexo;

public abstract class Triangulation<E,F>{//E point,F type point

	protected abstract Edge<E,F> newEdge(E a, E b);
	public boolean hasLast = false;
	
	public List<List<Edge<E,F>>> triangulate(List<PoligonoConvexo<E>> pols){
		List<List<Edge<E,F>>> triangulaciones = new ArrayList<>();

		for(int i=0; i<pols.size()-1; i++){
			PoligonoConvexo< E> afuera = pols.get(i);
			PoligonoConvexo< E> adentro = pols.get(i+1);
			
			List<Edge<E,F>> triang = new ArrayList<Edge<E,F>>();
		
			/* Comienzo uniendo los vértices 0 de cada polígono */
			Edge<E,F> primer = newEdge(afuera.getVertex(0), adentro.getVertex(0));
			//new Edge<E,F>(afuera.getVertex(0), adentro.getVertex(0));
			triang.add(primer);
		
			int actual_afuera = 0;
			Edge<E,F> currEdge = primer;
			
			int verticesAdentro = adentro.points.size();
			int verticesAfuera = afuera.points.size();
			
			/* j es el indice actual del poligono "interior" */
			int j = 1;
			E currVertex = adentro.getVertex(0);
			
			if(verticesAdentro > 1){
				currVertex = adentro.getVertex(j);
			}
			
			E lastVertex = afuera.getVertex(afuera.points.size()-1);
			
			while(j < verticesAdentro){
				currVertex = adentro.getVertex(j); 
				/* Si currVertex está a la derecha de currEdge */
				if(!currEdge.toLeft(currEdge, currVertex)){
					/* Agrego también a la triangulación el edge del polígono interno */
					Edge<E,F> nuevo2 = newEdge(adentro.getVertex(j-1), adentro.getVertex(j));
					//new Edge(adentro.getVertex(j-1), adentro.getVertex(j));
					triang.add(nuevo2);
					
					E currVertexAfuera = afuera.getVertex(actual_afuera);
					Edge<E,F> nuevo = newEdge(currVertexAfuera, currVertex);//new Edge(currEdge.a, currVertex);
					triang.add(nuevo);
					currEdge = nuevo;
					
					j++;
				}else{
					actual_afuera++;
					if(actual_afuera >= verticesAfuera){
						break;
					}
					
					/* Agrego también a la triangulación el edge del polígono externo */
					Edge<E,F> nuevo2 = newEdge(afuera.getVertex(actual_afuera-1), afuera.getVertex(actual_afuera));
					//new Edge(afuera.getVertex(actual_afuera-1), afuera.getVertex(actual_afuera));
					triang.add(nuevo2);
					
					/* Agrega el arco "intermedio" */
					E prevVertex = adentro.getVertex(j-1);
					Edge<E,F> cierra = newEdge(afuera.getVertex(actual_afuera), prevVertex);
					//new Edge(afuera.getVertex(actual_afuera), prevVertex);
					triang.add(cierra);
					currEdge = cierra;
					
					/* Mientras currVertex esté a la izquierda de currEdge */
					while(currEdge.toLeft(currEdge, currVertex)){
						actual_afuera++;
						if(actual_afuera == afuera.points.size()){
							break;
						}else{
							Edge<E,F> cl = newEdge(afuera.getVertex(actual_afuera-1), afuera.getVertex(actual_afuera));
							triang.add(cl);
						}
						Edge<E,F> cl2 = newEdge(afuera.getVertex(actual_afuera), prevVertex);
						triang.add(cl2);
						currEdge = cl2;
						if(actual_afuera >= verticesAfuera){
							break;
						}
						// se cierra el triangulo
						Edge<E,F> c3 = newEdge(afuera.getVertex(actual_afuera-1), afuera.getVertex(actual_afuera));
						Edge<E,F> c2 = newEdge(afuera.getVertex(actual_afuera), prevVertex);
						//new Edge(afuera.getVertex(actual_afuera), prevVertex);
						
						triang.add(c3);
						triang.add(c2);
						currEdge = c2;
					}
					
					if(actual_afuera >= verticesAfuera){
						break;
					}
					/* Une los dos vertices */
					Edge<E,F> cerrar = newEdge(prevVertex, currVertex);
					Edge<E,F> nuevo = newEdge(afuera.getVertex(actual_afuera), currVertex);
					//new Edge(afuera.getVertex(actual_afuera), currVertex);
					triang.add(cerrar);
					triang.add(nuevo);
					currEdge = nuevo;
					j++;
				}
			}
			
			/* En el caso que el poligono de afuera tenga más vértices que el de
			 * adentro, une los vértices que "sobran" al último vértice interno */

			while(actual_afuera < verticesAfuera-1){
				E currVertexAfuera = afuera.getVertex(actual_afuera+1);
				Edge<E,F> nuevo0 = newEdge(afuera.getVertex(actual_afuera), currVertexAfuera);//new Edge(currVertexAfuera, currVertex);
				Edge<E,F> nuevo = newEdge(currVertexAfuera, currVertex);//new Edge(currVertexAfuera, currVertex);
				triang.add(nuevo0);
				triang.add(nuevo);
				actual_afuera++;
			}
			Edge<E,F> ultimoadentro = newEdge(adentro.getVertex(adentro.points.size()-1), adentro.getVertex(0));
			triang.add(ultimoadentro);

			// se agrega el edge entre el ultimo y primert vertice de afuera
			
			//caso cuado se acaban los puntos de afuera

			while(j < verticesAdentro){
				Edge<E,F> c4 = newEdge(adentro.getVertex(j-1), adentro.getVertex(j));
				Edge<E,F> c5 = newEdge(afuera.getVertex(0), adentro.getVertex(j));
				triang.add(c4);
				triang.add(c5);
				j++;
			}
			
			if(adentro.points.size() < 1){
				Edge<E,F> nuevo2 = newEdge(adentro.getVertex(j-1), adentro.getVertex(0));
				triang.add(nuevo2);
			}
			Edge<E,F> nuevo2 = newEdge(afuera.getVertex(afuera.points.size()-1), adentro.getVertex(0));
			triang.add(nuevo2);
			
			Edge<E,F> nuevo = newEdge(afuera.getVertex(afuera.points.size()-1), afuera.getVertex(0));
			triang.add(nuevo);
			
			if(actual_afuera == afuera.points.size()){
				Edge<E,F> ultimo = newEdge(lastVertex, afuera.getVertex(actual_afuera-1));
				triang.add(ultimo);
			}else{
				Edge<E,F> ultimo = newEdge(lastVertex, afuera.getVertex(actual_afuera));
				triang.add(ultimo);
			}
			
			Edge<E,F> ultimoafuera = newEdge(afuera.getVertex(0), afuera.getVertex(afuera.points.size()-1));
			triang.add(ultimoafuera);
			
			triangulaciones.add(triang);
		}
		
		PoligonoConvexo< E> ultimo = pols.get(pols.size()-1);
		
		if(ultimo.points.size() > 2){
			hasLast = true;
			List<Edge<E,F>> triang = new ArrayList<Edge<E,F>>();
			
			// se hace un lado con el primer y se gundo vertice
			
			Edge<E,F> nuevo = newEdge(ultimo.getVertex(0), ultimo.getVertex(1));
			triang.add(nuevo);
			
			for(int cont = 2; cont < ultimo.points.size(); cont++){
				nuevo = newEdge(ultimo.getVertex(cont-1), ultimo.getVertex(cont));
				triang.add(nuevo);
				nuevo = newEdge(ultimo.getVertex(0), ultimo.getVertex(cont));
				triang.add(nuevo);
			}
			
			triangulaciones.add(triang);
		}
		return triangulaciones;
	}
	


}