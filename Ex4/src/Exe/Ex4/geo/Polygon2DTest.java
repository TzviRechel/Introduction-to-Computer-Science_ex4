package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;

class Polygon2DTest {

	
	Point2D origin = new Point2D (0,0);
	Point2D p = new Point2D (1,2);
	Point2D p1 = new Point2D (2,3.5);
	Point2D p2 = new Point2D (0.5,4);
	Point2D p3 = new Point2D (7,4);
	private static ArrayList<Point2D> arr;
	private static Polygon2D polygon;
    Point2D p11 = new Point2D (1,2);
    Point2D p12 = new Point2D (2,3);
    Point2D p13 = new Point2D (3,2);
    Point2D p14 = new Point2D (3,5);
    Point2D p15 = new Point2D (2,4);
    Point2D p16 = new Point2D (2,5);
    Point2D p17 = new Point2D (1,4);
    
    @Test
	void testPolygonConstructor() {
		arr = new ArrayList<Point2D>();
		arr.add(new Point2D(p11));
		arr.add(new Point2D(p12));
		arr.add(new Point2D(p13));
		arr.add(new Point2D(p14));
		arr.add(new Point2D(p15));
		arr.add(new Point2D(p16));
		arr.add(new Point2D(p17));
		Polygon2D poly = new Polygon2D(arr);
	      Point2D[] po = poly.getPoints();
	      assertEquals(po[0], p11);
	      assertEquals(po[1], p12);
	      assertEquals(po[2], p13);
	      assertEquals(po[3], p14);
	      assertEquals(po[4], p15);
	      assertEquals(po[5], p16);
	      assertEquals(po[6], p17);
	}
	
	@Test
	void testContains() {// Checks that the point is contained within the  of the polygon.
		p1 = new Point2D (2,3.5);
		p2 = new Point2D (0.5,4);
		p3 = new Point2D (7,4);
		arr = new ArrayList<Point2D>();
		arr.add(new Point2D(2,4));
		arr.add(new Point2D(1,3));
		arr.add(new Point2D(4,4));
		arr.add(new Point2D(6,4));
		arr.add(new Point2D(4,5));
		arr.add(new Point2D(3,4));
		arr.add(new Point2D(1,5));
		polygon = new Polygon2D(arr);
		boolean f0 = polygon.contains(p1);
		boolean f1 = polygon.contains(p2);
		boolean f2 = polygon.contains(p3);
		assertEquals(f0, true); // Should be inside
		assertEquals(f1, false); // Should be outside on left side
		assertEquals(f2, false); // Should be outside on right side
	}
	
	@Test
	void testPerimeter() { //Checks that the perimiter is calculated correctly according to the points.
		arr = new ArrayList<Point2D>();
		arr.add(new Point2D(1,2));
		arr.add(new Point2D(2,3));
		arr.add(new Point2D(3,2));
		arr.add(new Point2D(3,5));
		arr.add(new Point2D(2,4));
		arr.add(new Point2D(2,5));
		arr.add(new Point2D(1,4));
		polygon = new Polygon2D(arr);
		double f0 = polygon.perimeter();
		assertEquals(f0, 11.6568542495, Ex4_Const .EPS1); 
	}
	
	@Test
	void testFromPoints() {// Checks that the points are as should be.
		arr = new ArrayList<Point2D>();
		arr.add(new Point2D(1,2));
		arr.add(new Point2D(2,3));
		arr.add(new Point2D(3,2));
		arr.add(new Point2D(3,5));
		arr.add(new Point2D(2,4));
		arr.add(new Point2D(2,5));
		arr.add(new Point2D(1,4));
		polygon = new Polygon2D(arr);
		Point2D [] same = new Point2D[arr.size()];
		for (int i = 0; i<same.length; i++) 
			same[i] = arr.get(i);
		Assert.assertArrayEquals(polygon.getPoints(),same);
		}

	@Test
	void testArea() {// Checks that the area is calculated correctly.
		arr = new ArrayList<Point2D>();
		arr.add(new Point2D(1,2));
		arr.add(new Point2D(2,3));
		arr.add(new Point2D(3,2));
		arr.add(new Point2D(3,5));
		arr.add(new Point2D(2,4));
		arr.add(new Point2D(2,5));
		arr.add(new Point2D(1,4));
		polygon = new Polygon2D(arr);
		double f0 = polygon.area();
		assertEquals(f0, 4.0, Ex4_Const .EPS1);
	}	

	@Test
	void testCopy() {// Checking that all the points are .
		arr = new ArrayList<Point2D>();
		arr.add(new Point2D(1,2));
		arr.add(new Point2D(2,3));
		arr.add(new Point2D(3,2));
		arr.add(new Point2D(3,5));
		arr.add(new Point2D(2,4));
		arr.add(new Point2D(2,5));
		arr.add(new Point2D(1,4));
		polygon = new Polygon2D(arr);
		GeoShapeable new_poly = polygon.copy();
	    Point2D[] po = new_poly.getPoints();
	     assertEquals(po[0], p11);
	     assertEquals(po[1], p12);
	     assertEquals(po[2], p13);
	     assertEquals(po[3], p14);
	     assertEquals(po[4], p15);
	     assertEquals(po[5], p16);
	     assertEquals(po[6], p17);
	}
	 @Test
	 void testMove() { // checking in this test that every point moves by (1,2).
		arr = new ArrayList<Point2D>();
		arr.add(new Point2D(1,2));
		arr.add(new Point2D(2,3));
		arr.add(new Point2D(3,2));
		arr.add(new Point2D(3,5));
		arr.add(new Point2D(2,4));
		arr.add(new Point2D(2,5));
		arr.add(new Point2D(1,4));
		polygon = new Polygon2D(arr);
		polygon.move(p); // moves every point  by (1,2)
	     Point2D[] po = polygon.getPoints();
	     assertEquals(po[0].x(), 2);
	     assertEquals(po[0].y(), 4);
	     assertEquals(po[1].x(), 3);
	     assertEquals(po[1].y(), 5);
	     assertEquals(po[2].x(), 4);
	     assertEquals(po[2].y(), 4);
	     assertEquals(po[3].x(), 4);
	     assertEquals(po[3].y(), 7);
	     assertEquals(po[4].x(), 3);
	     assertEquals(po[4].y(), 6);
	     assertEquals(po[5].x(), 3);
	     assertEquals(po[5].y(), 7);
	     assertEquals(po[6].x(), 2);
	     assertEquals(po[6].y(), 6);
	 
	 }
	@Test
	 void testScale() {// Tests that the polygon scales bigger by 1.1 or smaller by 0.9.
		arr = new ArrayList<Point2D>();
		arr.add(new Point2D(1,2));
		arr.add(new Point2D(2,3));
		arr.add(new Point2D(3,2));
		arr.add(new Point2D(3,5));
		arr.add(new Point2D(2,4));
		arr.add(new Point2D(2,5));
		arr.add(new Point2D(1,4));
		polygon = new Polygon2D(arr);
	    polygon.scale(p11, 0.9); //The parameter of the polygon should be 90% smaller
	    double new_Perimeter = polygon.perimeter();
	    assertEquals(new_Perimeter,11.6568542495 *0.9, Ex4_Const.EPS);
	    arr = new ArrayList<Point2D>();
		arr.add(new Point2D(1,2));
		arr.add(new Point2D(2,3));
		arr.add(new Point2D(3,2));
		arr.add(new Point2D(3,5));
		arr.add(new Point2D(2,4));
		arr.add(new Point2D(2,5));
		arr.add(new Point2D(1,4));
		polygon = new Polygon2D(arr);
	    polygon.scale(p11, 1.1); //The perimeter of the polygon should be 110% larger
	    new_Perimeter = polygon.perimeter();
	    assertEquals(new_Perimeter, 11.6568542495*1.1, Ex4_Const.EPS);
	     
	 }

	 @Test
	 void testRotate() {// Checks that the points have been changed and rotated according to the angles degree of the rotation.
		arr = new ArrayList<Point2D>();
		arr.add(new Point2D(1,2));
		arr.add(new Point2D(2,3));
		arr.add(new Point2D(3,2));
		arr.add(new Point2D(3,5));
		arr.add(new Point2D(2,4));
		arr.add(new Point2D(2,5));
		arr.add(new Point2D(1,4));
		polygon = new Polygon2D(arr);
	    polygon.rotate(p1, Math.PI/2); //The polygon should rotate 90% degrees with respect to the origin point
	    Point2D[] po = polygon.getPoints();
	    assertEquals(po[0].x(), 3.5, Ex4_Const.EPS);
	    assertEquals(po[0].y(), 2.5, Ex4_Const.EPS);
	    assertEquals(po[1].x(), 2.5, Ex4_Const.EPS);
	    assertEquals(po[1].y(), 3.5, Ex4_Const.EPS);
	    assertEquals(po[2].x(), 3.5, Ex4_Const.EPS);
	    assertEquals(po[2].y(), 4.5, Ex4_Const.EPS);
	    assertEquals(po[3].x(), 0.5, Ex4_Const.EPS);
	    assertEquals(po[3].y(), 4.5, Ex4_Const.EPS);
	    assertEquals(po[4].x(), 1.5, Ex4_Const.EPS);
	    assertEquals(po[4].y(), 3.5, Ex4_Const.EPS);
	    assertEquals(po[5].x(), 0.5, Ex4_Const.EPS);
	    assertEquals(po[5].y(), 3.5, Ex4_Const.EPS);
	    assertEquals(po[6].x(), 1.5, Ex4_Const.EPS);
	    assertEquals(po[6].y(), 2.5, Ex4_Const.EPS);
	 }
	 
	
	     
	 
	 @Test
	 void testtoString() {// Tests if the string comes out correctly.
		 arr = new ArrayList<Point2D>();
			arr.add(new Point2D(1,2));
			arr.add(new Point2D(2,3));
			arr.add(new Point2D(3,2));
			arr.add(new Point2D(3,5));
			arr.add(new Point2D(2,4));
			arr.add(new Point2D(2,5));
			arr.add(new Point2D(1,4));
			polygon = new Polygon2D(arr);
	     String str = polygon.toString();
	     assertEquals(str, "1.0,2.0,2.0,3.0,3.0,2.0,3.0,5.0,2.0,4.0,2.0,5.0,1.0,4.0");
	     
	 }
	     


	}





