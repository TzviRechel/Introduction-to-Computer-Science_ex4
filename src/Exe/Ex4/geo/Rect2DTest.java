package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;

class Rect2DTest {

	 Point2D origin = new Point2D(0,0);
	 
	    Point2D p1 = new Point2D(1,1);
	    Point2D p2 = new Point2D(8,8);
	    Point2D p3 = new Point2D(1,8);
	    Point2D p4 = new Point2D(8,1);
	    Rect2D rect1;
	    Point2D pt1 = new Point2D(2,8);
	    Point2D pt2 = new Point2D(3,3);
	    
	    Point2D p5 = new Point2D(9,9);
	    Point2D p6= new Point2D(7,4);
	    Point2D p7 = new Point2D(9,4);
	    Point2D p8= new Point2D(7,9);
	    
	    Rect2D rect2;
	    Point2D p11= new Point2D(2,2);
	    Point2D p12= new Point2D(4.5,4.5);
	    

	    @Test
	     void testRectle2D() {
	      Rect2D rect1 = new Rect2D(p1,p2);
	      Point2D[] po1 = rect1.getPoints();
	      assertEquals(po1[0], p1);
	      assertEquals(po1[1], p2);
	      
	      Rect2D rect2 = new Rect2D(p5,p6);
	      Point2D[] po2 = rect2.getPoints();// Testing that it the constructor does the "min" "max" correctly and switches the points.
	      assertEquals(po2[0], p6);
	      assertEquals(po2[1], p5);
	      
	    }
	    
	 @Test
	 void testContains() {
	     
	     Point2D p7 = new Point2D(1,7); //This point is on the line of the first rectangle - therefore it should be in.
	     Point2D p8 = new Point2D(7,9); //This point is on the line of the second rectangle and not in the first.
	     Rect2D rect1 = new Rect2D(p1,p2);
	     Rect2D rect2 = new Rect2D(p5,p6);
	     boolean ans1 = rect1.contains(p7);
	     boolean ans2 = rect2.contains(p8);
	     boolean ans3 = rect1.contains(p8);
	     boolean ans4 = rect2.contains(p7);
	     assertTrue(ans1);// Each point does not appear in the other rectangle.
	     assertTrue(ans2);
	     assertFalse(ans3);
	     assertFalse(ans4);
	 }

	 @Test
	 void testArea() {
		 Rect2D rect1 = new Rect2D(p1,p2);
	     Rect2D rect2 = new Rect2D(p5,p6);
		 double ans1 = rect1.area();
	     double ans2 = rect2.area();
	     assertEquals(ans1, 49, Ex4_Const.EPS);
	     assertEquals(ans2, 10, Ex4_Const.EPS);    
	 }
	 @Test
	 void testPerimeter() {
		 Rect2D rect1 = new Rect2D(p1,p2);
	     Rect2D rect2 = new Rect2D(p5,p6);
	     double ans1 = rect1.perimeter();
	     double ans2 = rect2.perimeter();
	     assertEquals(ans1, 28, Ex4_Const.EPS);
	     assertEquals(ans2, 14, Ex4_Const.EPS);    
	 }

	 @Test
	 void testMove() {
		 Rect2D rect1 = new Rect2D(p1,p2);
	     rect1.move(p11);// Each point should move by (2,2).
	     Point2D[] po = rect1.getPoints();
	     assertEquals(po[0].x(), 3);
	     assertEquals(po[0].y(), 3);
	     assertEquals(po[1].x(), 10);
	     assertEquals(po[1].y(), 10);
	     
	     
	 }

	 @Test
	 void testCopy() {
		 Rect2D rect1 = new Rect2D(p1,p2);
	     GeoShapeable new_t = rect1.copy();
	     Point2D[] po = new_t.getPoints();
	     assertEquals(po[0], p1);
	     assertEquals(po[1], p2);
	     
	 }

	 @Test
	 void testScale() {
		 Rect2D rect1 = new Rect2D(p1,p2);
	     rect1.scale(origin, 0.9); //The parameter of the rectangle should be 90% smaller
	     double new_Perimeter = rect1.perimeter();
	     assertEquals(new_Perimeter, 28*0.9, Ex4_Const.EPS);
	     Rect2D rect2 = new Rect2D(p5,p6);
	     rect2.scale(origin, 1.1); //The perimeter of the rectangle should be 110% larger
	     new_Perimeter = rect2.perimeter();
	     assertEquals(new_Perimeter, 14*1.1, Ex4_Const.EPS);
	     
	 }

	 @Test
	 void testRotate() {//  
		 Rect2D rect1 = new Rect2D(p1,p2);
	     rect1.rotate(p12, Math.PI/2); //The triangle should rotate 90% degrees with respect to the center of rectangle. So the points should change. (because it's a square- the points should just replace with each other). 
	     Point2D[] po = rect1.getPoints();
	     assertEquals(po[0], p4);
	     assertEquals(po[1], p3);
	     
	     
	 }
	 @Test
	 void testGetPoints() {
	     Rect2D rect1 = new Rect2D(p1,p2);
	     Point2D[] po = rect1.getPoints();
	     assertEquals(po[0], p1);
	     assertEquals(po[1], p2);
	     
	     
	 }
	 @Test
	 void testtoString() {// Returns in string the four points that make the rectangle - in the order of  - low left, low right, top right, low left.
		 Rect2D rect1 = new Rect2D(p1,p2);
	     String str = rect1.toString();
	     assertEquals(str, "1.0,1.0,8.0,1.0,8.0,8.0,1.0,8.0");
	     
	 }
	     


	}



