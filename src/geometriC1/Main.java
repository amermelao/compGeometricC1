package geometriC1;

import geometriC1.convexhull.DoubleConvexHull;
import geometriC1.convexhull.IntConvexHull;
import geometriC1.edge.Edge;
import geometriC1.points.DoublePoint;
import geometriC1.points.IntPoint;
import geometriC1.poligonoconvexo.PoligonoConvexo;
import geometriC1.triangulacion.DoubleTriangulation;
import geometriC1.triangulacion.IntTriangulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {

	public static void doInt(String location) throws IOException{
		System.err.println("\nProcesiong Int traingulation");
		IntConvexHull convexHull =new IntConvexHull();
		List<IntPoint> points = convexHull.readDate(location);
		List<PoligonoConvexo<IntPoint>> poligonos = convexHull.getRingsOfConvexFigure();
		
		convexHull.doDebug(points, poligonos);
		
		IntTriangulation tri = new IntTriangulation();
		
		List<List<Edge<IntPoint,Integer>>> triAng = tri.triangulate(poligonos);
		
		int selected = (triAng.size()-1)/2;
		List<Edge<IntPoint,Integer>> aux = triAng.get(selected);

		System.out.println(selected );
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("triang.dat")));
		int cont;
		for( cont = 0; cont < aux.size()-1; cont+=2){
			Edge<IntPoint,Integer> a = aux.get(cont);
			Edge<IntPoint,Integer> b = aux.get(cont+1);
			
			IntPoint p1 = a.a;
			IntPoint p2 = a.b;
			IntPoint p3 = b.b;
			out.write("" + p1.x + " " + p1.y + " " + p2.x + " " + p2.y + " " + p3.x + " " + p3.y );
			out.newLine();
		}
		Edge<IntPoint,Integer> a = aux.get(cont);
		Edge<IntPoint,Integer> b = aux.get(0);
		
		IntPoint p1 = a.a;
		IntPoint p2 = a.b;
		IntPoint p3 = b.b;
		out.write("" + p1.x + " " + p1.y + " " + p2.x + " " + p2.y + " " + p3.x + " " + p3.y );
		out.newLine();
		
		out.close();
	}
	
	public static void doDouble(String location) throws IOException{
		System.err.println("\nProcesiong Double traingulation");
		DoubleConvexHull convexHull =new DoubleConvexHull();
		List<DoublePoint> points = convexHull.readDate(location);
		List<PoligonoConvexo<DoublePoint>> poligonos = convexHull.getRingsOfConvexFigure();
		
		convexHull.doDebug(points, poligonos);
		
		DoubleTriangulation tri = new DoubleTriangulation();
		
		List<List<Edge<DoublePoint,Double>>> triAng = tri.triangulate(poligonos);
		
		int selected = (triAng.size()-1)/2;
		List<Edge<DoublePoint,Double>> aux = triAng.get(selected);

		System.out.println(selected );
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("triang.dat")));
		int cont;
		for( cont = 0; cont < aux.size()-1; cont+=2){
			Edge<DoublePoint,Double> a = aux.get(cont);
			Edge<DoublePoint,Double> b = aux.get(cont+1);
			
			DoublePoint p1 = a.a;
			DoublePoint p2 = a.b;
			DoublePoint p3 = b.b;
			out.write("" + p1.x + " " + p1.y + " " + p2.x + " " + p2.y + " " + p3.x + " " + p3.y );
			out.newLine();
		}
		Edge<DoublePoint,Double> a = aux.get(cont);
		Edge<DoublePoint,Double> b = aux.get(0);
		
		DoublePoint p1 = a.a;
		DoublePoint p2 = a.b;
		DoublePoint p3 = b.b;
		out.write("" + p1.x + " " + p1.y + " " + p2.x + " " + p2.y + " " + p3.x + " " + p3.y );
		out.newLine();
		
		out.close();
	}
	static public void main(String [] args) throws IOException{
		
		if(args.length > 0){
			if(args[0].equals("-i")){
				doInt(args[1]);
				return;
			}
			if(args[0].equals("-d")){
				doDouble(args[1]);
				return;
			}
			
		}
		
		System.err.println(" use arguments <option> <file>\n\noption:\n-i for integer triangulation\n-d for double triangulation");

		
	}
}
