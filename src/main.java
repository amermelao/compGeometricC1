import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import points.IntPoint;
import geometriC1.ConvexHull;
import geometriC1.PoligonoConvexo;
import geometriC1.Triangulation;
import geometriC1.Edge;

public class main{

	public static void main(String[] args) throws IOException{
		List<PoligonoConvexo<Integer,IntPoint>> ch = new ArrayList<PoligonoConvexo<Integer,IntPoint>>();
		ch = ConvexHull.getRingsOfConvexFigure(args[0]);
		
		List t = new ArrayList();
		t = Triangulation.triangulate(ch);
		
		//BufferedWriter out = new BufferedWriter(new FileWriter(new File("test")));
		for(int i = 0; i < t.size() ; i++){
			List<Edge> l = new ArrayList<Edge>();
			l = (List<Edge>)t.get(i);
			for(int j = 0; j < l.size() ; j++){
				System.out.println(Integer.toString(l.get(j).a));
			}
		}
		
		//out.close();
		
	}

}