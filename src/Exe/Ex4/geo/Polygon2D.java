package Exe.Ex4.geo;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.geom.Line2D;
/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
	public class Polygon2D implements GeoShapeable{
	
	private ArrayList<Point2D> pointsP = new ArrayList<>();
	private Point2D [] polyP;
	
	//Constructor
	// The polygon is dynamic, so to represent it's points- we will use a dynamic array.
	 public Polygon2D (ArrayList<Point2D> arr) {
	        for (int i =0; i<arr.size();i++) {
	            Point2D p = new Point2D(arr.get(i));
	            this.pointsP.add(p);
	        }
	    }
	
	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		// Ultimately the purpose of the function will be to see how many times dose the imaginative 
		// line of  "ot" (to the right ) cross the the sides of the shape. If it is an even number
		// that means the point is outside the shape. If uneven - it's in.
		// We will start of in checking every two points which are attached by a line, if the height(y) of "ot" is between their heights.
		// If so, we find the equation of that line and ultimately find if the x point on that line is smaller (and therefore before- to the left )
		// of the x of "ot". If it is bigger (to the right of ot) we will and 1 to the count.
		// Ultimately checking the evenness or unevenness. Deciding if the point is contained within the polygon or not.
		int count =0;
		double x = 0;
		double yFirstP;
		double ySecondP;
		int size = this.pointsP.size();
		for	(int i=0; i<size -1; i++ ) {
			yFirstP = pointsP.get(i).y();
			ySecondP = pointsP.get(i+1).y();
			if (((yFirstP <=  ot.y()) && (ot.y() <=  ySecondP)) || ((yFirstP >=  ot.y()) && (ot.y() >=  ySecondP))){// If the height of "ot" is between their height.
				x = findingXInLine(pointsP.get(i),pointsP.get(i+1) , ot);
				if (x >= ot.x()) {// Checking the x point of the and and x point of "ot".
					count++;
				}
			}
		}
		yFirstP = pointsP.get(pointsP.size()-1).y();// Last correlation - first and last points. 
		ySecondP = pointsP.get(0).y();
		if (((yFirstP <=  ot.y()) && (ot.y() <=  ySecondP)) || ((yFirstP >=  ot.y()) && (ot.y() >=  ySecondP))){// If the height of "ot" is between their height.
		if (findingXInLine(pointsP.get(pointsP.size()-1),pointsP.get(0) , ot) >= ot.x())// the last line connecting the last point to the first.
			count++;
		}
		return count%2 == 1; 
	}		
			
		
		
		
	// An assisting function that will return the X of the  straight line equation, that is at the same f(x) as ot f(x).
	public double findingXInLine(Point2D p1, Point2D p2, Point2D ot) {
		double m= (p2.y()- p1.y())/(p2.x()- p1.x()); 
		double b = (p1.y())-(m*p1.x()); //
		double x = (ot.y()-b)/m;
	return x;
	}
	public String toString() {
		String st= "";
		for (int i= 0; i< this.pointsP.size(); i++) { 
			st += this.pointsP.get(i).toString() + ",";
		}
		if (st.length()>0) {
			st = st.substring(0, st.length()-1); // To delete the last ","
		}
			return st;
	}

	@Override
	// Gauss's shoe lace method.  https://he.wikipedia.org/wiki/%D7%A0%D7%95%D7%A1%D7%97%D7%AA_%D7%94%D7%A9%D7%A8%D7%95%D7%9A נוסחת השרוך
	public double area() {
		// TODO Auto-generated method stub
	if (pointsP.size()<2) return 0;	// In case the it's just a segment, so as not to go out of bounds. 
	
	double sum =0;
		for (int i = 0; i < pointsP.size(); i++) {
			Point2D p1 = pointsP.get(i);
			Point2D p2 = pointsP.get((i + 1) % pointsP.size()); //
			sum += (p1.x() * p2.y()) - (p1.y() * p2.x());
		}
		return Math.abs(sum / 2);

	
	}
		

	@Override
	public double perimeter() { 
		// The sum of the distances between all two adjacent points.
		// TODO Auto-generated method stub
		double sum= 0;
		for (int i=1; i< pointsP.size(); i++)
		sum += pointsP.get(i).distance(pointsP.get(i-1));
		sum += pointsP.get(0).distance(pointsP.get(pointsP.size()-1));
		return sum;
	}
	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		 this.polyP = new  Point2D[this.pointsP.size()];
		for (int i=0; i<this.pointsP.size();i++) {
			polyP[i] = pointsP.get(i);
			
		}
		return polyP;
	
	}
	@Override
    // moving each point
    public void move(Point2D vec) {
        // TODO Auto-generated method stub
        for (int i= 0;i<pointsP.size();i++) {
            this.pointsP.get(i).move(vec);
        }
    }
	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Polygon2D( this.pointsP);
	
	}

	 @Override
	    public void scale(Point2D center, double ratio) {
	        // TODO Auto-generated method stub
	         for (int i= 0;i<pointsP.size();i++) {
	             this.pointsP.get(i).scale(center,ratio);
	         }
	        
	        
	    }
	 @Override
	    public void rotate(Point2D center, double angleDegrees) {
	        // TODO Auto-generated method stub
	         for (int i= 0;i<pointsP.size();i++) {
	             this.pointsP.get(i).rotate(center,angleDegrees);
	         }

	
	 }
}
