package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;

class Triangle2DTest {
    
    Point2D origin = new Point2D(0,0);
 
    Point2D p1 = origin;
    Point2D p2 = new Point2D(0,6);
    Point2D p3 = new Point2D(6,0);
 
    Point2D p11 = new Point2D(5,9);
    Point2D p22 = new Point2D(2,49);
    Point2D p33 = new Point2D(7,33);
    
    Triangle2D t1 = new Triangle2D(p1,p2,p3);
    Triangle2D t2 = new Triangle2D(p11,p22,p33);

    @Test
     void testTriangle2D() {
      Triangle2D t1 = new Triangle2D(p1,p2,p3);
      Point2D[] po = t1.getPoints();
      assertEquals(po[0], p1);
      assertEquals(po[1], p2);
      assertEquals(po[2], p3);
     }
    
 @Test
 void testContains() {
     
     Point2D p4 = new Point2D(0,6); //this point should be in the first triangle and not in the second triangle
     Point2D p5 = new Point2D(4,25); //this point should be in the second triangle and not in the first triangle
     boolean ans1 = t1.contains(p4);
     boolean ans2 = t2.contains(p5);
     boolean ans3 = t1.contains(p5);
     boolean ans4 = t2.contains(p4);
     assertTrue(ans1);
     assertTrue(ans2);
     assertFalse(ans3);
     assertFalse(ans4);
 }

 @Test
 void testArea() {
     double ans1 = t1.area();
     double ans2 = t2.area();
     assertEquals(ans1, 18, Ex4_Const.EPS);
     assertEquals(ans2, 76, Ex4_Const.EPS);    
 }
 @Test
 void testPerimeter() {
     double ans1 = t1.perimeter();
     double ans2 = t2.perimeter();
     assertEquals(ans1, 20.485281, Ex4_Const.EPS);
     assertEquals(ans2, 80.958586, Ex4_Const.EPS);    
 }

 @Test
 void testMove() {
     t1.move(p11);
     Point2D[] po = t1.getPoints();
     assertEquals(po[0], p11);
     assertEquals(po[1].x(), 5);
     assertEquals(po[1].y(), 15);
     assertEquals(po[2].x(), 11);
     assertEquals(po[2].y(), 9);
 }

 @Test
 void testCopy() {
     GeoShapeable new_t = t1.copy();
     Point2D[] po = new_t.getPoints();
     assertEquals(po[0], p1);
     assertEquals(po[1], p2);
     assertEquals(po[2], p3);
     
 }

 @Test
 void testScale() {
     
     t1.scale(p11, 0.9); //The parameter of the triangle should be 90% smaller
     double new_Perimeter = t1.perimeter();
     assertEquals(new_Perimeter, 20.485281*0.9, Ex4_Const.EPS);
     
     t2.scale(p11, 1.1); //The perimeter of the triangle should be 110% larger
     new_Perimeter = t2.perimeter();
     assertEquals(new_Perimeter, 80.958586*1.1, Ex4_Const.EPS);
     
 }

 @Test
 void testRotate() {
     
     t1.rotate(origin, Math.PI/2); //The triangle should rotate 90% degrees with respect to the origin point
     Point2D[] po = t1.getPoints();
     assertEquals(po[0].x(), 0, Ex4_Const.EPS);
     assertEquals(po[0].y(), 0, Ex4_Const.EPS);
     assertEquals(po[1].x(), -6, Ex4_Const.EPS);
     assertEquals(po[1].y(), 0, Ex4_Const.EPS);
     assertEquals(po[2].x(), 0, Ex4_Const.EPS);
     assertEquals(po[2].y(), 6, Ex4_Const.EPS);
 }
 @Test
 void testGetPoints() {
     Point2D[] po = t2.getPoints();
     assertEquals(po[0], p11);
     assertEquals(po[1], p22);
     assertEquals(po[2], p33);
     
 }
 @Test
 void testtoString() {
     String str = t2.toString();
     assertEquals(str, "5.0,9.0,2.0,49.0,7.0,33.0");
     
 }
     


}



