package geometriC1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import geometriC1.edge.Edge;
import geometriC1.points.AbstractPoint;

public abstract class parseOutput<F extends Number,E extends AbstractPoint<F>>{
	public void parse(List<List<Edge<E,F>>> t, List<E> allPoints) throws IOException{
		BufferedWriter writer =new BufferedWriter( new PrintWriter("output.off", "UTF-8"));
		writer.write("OFF");
		writer.newLine();
		
		List<Triangulo<E>> triang = getTriangulos(t);
		/* Vertex count, Face count, Edge count */
		int vertexCount = allPoints.size();
		int faceCount = triang.size();
		int edgeCount = getNumberOfEsdges(t);
		
		writer.write("" + vertexCount + " " + faceCount + " " + edgeCount); //Esta linea hay que cambiarla despues
		writer.newLine();
		
		// se escriben lo puntos
		for(E aPoints:allPoints){
			writer.write("" + aPoints.x + " " + aPoints.y + " " + 0); //Esta linea hay que cambiarla despues
			writer.newLine();
		}
		
		String color = "1.0 0.0 0.0";
		//se escriben las caras
		for(Triangulo<E> aTriangulo: triang){
			int t1 = allPoints.indexOf(aTriangulo.a);
			int t2 = allPoints.indexOf(aTriangulo.b);
			int t3 = allPoints.indexOf(aTriangulo.c);
			writer.write("3 " + t1 + " " + t2 + " " + t3 + " " + color); //Esta linea hay que cambiarla despues
			writer.newLine();
			if(color.equals("1.0 0.0 0.0")){
				color = "0.0 1.0 0.0";
			}
			else{
				color = "1.0 0.0 0.0";
			}
		}
		
		writer.close();
	}
	
	public abstract Triangulo<E> newOne(E p1, E p2, E p3);
	
	
	/*
	 * se cuenta el numero de lados de la malla
	 */
	public int getNumberOfEsdges(List<List<Edge<E,F>>> t){
		int count = 0;
		for(List<Edge<E,F>> unConjunto : t)
			count+= unConjunto.size();
		return count;
	}
	/**
	 * Crea una lista de triangulos
	 */
	public List<Triangulo<E>> getTriangulos(List<List<Edge<E,F>>> t){
		List<Triangulo<E>> toReturn = new ArrayList<parseOutput.Triangulo<E>>();
		
		for(List<Edge<E,F>> unConjunto : t){

			int cont;
			for( cont = 0; cont < unConjunto.size()-1; cont+=2){
				Edge<E,F> a = unConjunto.get(cont);
				Edge<E,F> b = unConjunto.get(cont+1);
			
				E p1 = a.a;
				E p2 = a.b;
				E p3 = b.b;
				toReturn.add(newOne(p1,p2,p3));
			}
			Edge<E,F> a = unConjunto.get(cont);
			Edge<E,F> b = unConjunto.get(0);
		
			// es el trieanguulo de los ultimos lados con el primer
			E p1 = a.a;
			E p2 = a.b;
			E p3 = b.b;
			toReturn.add(newOne(p1,p2,p3));
		
		}
		
		return toReturn;
	}
	
	public static class Triangulo<E>{
		public E a,b,c;
		
		public Triangulo(E x, E y, E z){
			a = x;
			b = y;
			c = z;
		}

	}
}