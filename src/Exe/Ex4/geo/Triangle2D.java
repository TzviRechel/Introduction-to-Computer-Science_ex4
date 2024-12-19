package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{

	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;
	
	
	// Constructor
	public Triangle2D(Point2D p1, Point2D p2, Point2D p3) {
        this._p1 = new Point2D(p1);
        this._p2 = new Point2D(p2);
        this._p3 = new Point2D(p3);
    }
	@Override
	public boolean contains(Point2D ot) {//      
		// We check that the sum of the area of the three triangles created by the 
		//given point- with the other points) - is equal to the area of created by the initial triangle. If it is equal
		// so the point is in the triangle. Otherwise the area would have been bigger than the initial triangle.
		
	        // TODO Auto-generated method stub
	        Triangle2D a = new Triangle2D(this._p1, this._p2, ot);
	        Triangle2D b = new Triangle2D(this._p2, this._p3, ot);
	        Triangle2D c = new Triangle2D(this._p3, this._p1, ot);
	        double s1 = a.area();
	        double s2 = b.area();
	        double s3 = c.area();
	        
	        return (s1+s2+s3) - this.area() <= Ex4_Const.EPS;
	    }
	

	@Override
	public double area() {//Hero's Formula for finding the area of a triangle (credit https://he.wikipedia.org/wiki/%D7%A0%D7%95%D7%A1%D7%97%D7%AA_%D7%94%D7%A8%D7%95%D7%9F)
		// TODO Auto-generated method stub
		double a = this._p1.distance(this._p2);
		double b = this._p1.distance(this._p3);
		double c = this._p3.distance(this._p2);
		double s = (a+b+c)/2;
		return  Math.sqrt(s*(s-a)*(s-b)*(s-c));
		
				
	}

	@Override
	public double perimeter() {// Adding together the three sides of a triangle.
		// TODO Auto-generated method stub
		double a = this._p1.distance(this._p2);
		double b = this._p1.distance(this._p3);
		double c = this._p3.distance(this._p2);
		return a+b+c;
		
		
	}
	@Override
    public String toString() {
        // TODO Auto-generated method stub
     return _p1.toString()+","+_p2.toString()+","+_p3.toString();
    }
	
	
	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Triangle2D(_p1,_p2,_p3);
	}

	@Override
    public void scale(Point2D center, double ratio) {
        // TODO Auto-generated method stub
        this._p1.scale(center, ratio);
        this._p2.scale(center, ratio);
        this._p3.scale(center, ratio);
    }

    @Override
    public void rotate(Point2D center, double angleDegrees) {
        // TODO Auto-generated method stub
        this._p1.rotate(center, angleDegrees);
        this._p2.rotate(center, angleDegrees);
        this._p3.rotate(center, angleDegrees);
    }

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		Point2D[] ans = new Point2D[3];
		ans[0] =new Point2D(this._p1);
		ans[1] = new Point2D(this._p2);
		ans[2] = new Point2D(this._p3);
		return ans;
	}
	
}
