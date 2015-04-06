package geometriC1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import points.AbstractPoint;
import points.IntPoint;
 
 
public class ConvexHull {
 
	public long cross(IntPoint O, IntPoint A, IntPoint B) {
		return O.cross(A, B);
	}
 
	public List<IntPoint> convex_hull(List<IntPoint> P) {
 
		if (P.size() > 1) {
			int n = P.size(), k = 0;
			List<IntPoint> H = new ArrayList<IntPoint>();
			List<IntPoint> L = new ArrayList<IntPoint>();
 
			Collections.sort(P);
 
			// Build lower hull
			for (IntPoint aPoint:P) {
				while (k >= 2 && cross(H[k - 2], H[k - 1], P[i]) <= 0)
					k--;
				H[k++] = P[i];
			}
 
			// Build upper hull
			
			Collections.reverseOrder
			for (IntPoint aPoint:P) {
				while (k >= t && cross(H[k - 2], H[k - 1], P[i]) <= 0)
					k--;
				H[k++] = P[i];
			}
			if (k > 1) {
				H = Arrays.copyOfRange(H, 0, k - 1); // remove non-hull vertices after k; remove k - 1 which is a duplicate
			}
			return H;
		} else if (P.length <= 1) {
			return P;
		} else{
			return null;
		}
	}
 
	public static void main(String[] args) throws IOException {
 
		BufferedReader f = new BufferedReader(new FileReader("hull.in")); 	// "hull.in"  Input Sample => size x y x y x y x y
		StringTokenizer st = new StringTokenizer(f.readLine());
		Point[] p = new Point[Integer.parseInt(st.nextToken())];
		for (int i = 0; i < p.length; i++) {
			p[i] = new Point();
			p[i].x = Integer.parseInt(st.nextToken()); // Read X coordinate 
			p[i].y = Integer.parseInt(st.nextToken()); // Read y coordinate
		}
 
		Point[] hull = convex_hull(p).clone();
 
		for (int i = 0; i < hull.length; i++) {
			if (hull[i] != null)
				System.out.print(hull[i]);
		}
	}
 
}