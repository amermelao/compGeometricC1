package geometriC1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import geometriC1.edge.Edge;
import geometriC1.points.AbstractPoint;

public abstract class parseOutput<F extends Number,E extends AbstractPoint<F>>{
	
	public boolean hasLeft = false;
	public List<String> colores = new ArrayList<String>();
	
	public List<String> color = new ArrayList<String>();
	public int lengthColor = 4;
	public int nowColor = 0;
	
	public void parse(List<List<Edge<E,F>>> t, List<E> allPoints) throws IOException{
		
		color.add("1.0 0.0 0.0");
		color.add("0.0 1.0 0.0");
		color.add("1.0 0.0 1.0");
		color.add("0.0 0.0 1.0");
		
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
		
		//se escriben las caras
		for(int contColor = 0; contColor < triang.size(); contColor++){
			Triangulo<E> aTriangulo = triang.get(contColor);
			int t1 = allPoints.indexOf(aTriangulo.a);
			int t2 = allPoints.indexOf(aTriangulo.b);
			int t3 = allPoints.indexOf(aTriangulo.c);
			writer.write("3 " + t1 + " " + t2 + " " + t3 + " " + colores.get(contColor)); //Esta linea hay que cambiarla despues
			writer.newLine();
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
		int color1index,color2index,colorIndex;
		for(int index = 0; index < t.size(); index++){
			color1index = nowColor;
			nowColor = (nowColor+1) % lengthColor;
			color2index = nowColor;
			nowColor = (nowColor+1) % lengthColor;
			
			colorIndex = color1index;
			
			List<Edge<E,F>> unConjunto = t.get(index);
			int cont;
			for( cont = 0; cont < unConjunto.size()-1; cont+=2){
				Edge<E,F> a = unConjunto.get(cont);
				Edge<E,F> b = unConjunto.get(cont+1);
			
				E p1 = a.a;
				E p2 = a.b;
				E p3 = b.b;
				toReturn.add(newOne(p1,p2,p3));
				colores.add(color.get(colorIndex));
				colorIndex = colorIndex == color1index? color2index: color1index;
			}
			Edge<E,F> a = unConjunto.get(cont);
			Edge<E,F> b = unConjunto.get(0);
		
			if(hasLeft){
				// es el trianguulo de los ultimos lados con el primer
				E p1 = a.a;
				E p2 = a.b;
				E p3 = b.b;
				toReturn.add(newOne(p1,p2,p3));
				colores.add(color.get(colorIndex));
			}
		
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