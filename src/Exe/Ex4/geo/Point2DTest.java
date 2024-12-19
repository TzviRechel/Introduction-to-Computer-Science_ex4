package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;

class Point2DTest {
    
      
    
       Point2D p1 = new Point2D(2.7,5);
       Point2D p2 = new Point2D(4,10.2);
       Point2D p3 = new Point2D(6.3,9);
    

    @Test
    /**
     * tests the constructor with a double type input
     */
    void testPoint2DDoubleDouble() {
        Point2D p = new Point2D(4.9,17.5);
        assertEquals(p.x(), 4.9);
        assertEquals(p.y(), 17.5);    
    }

    @Test
    /**
     * tests the constructor with a Point2D type input
     */
    void testPoint2DPoint2D() {
        Point2D p = new Point2D(p1);
        assertEquals(p, p1);
    }

    @Test
    /**
     * tests the constructor with a String type input
     */
    void testPoint2DString() {
        Point2D p = new Point2D(2.7,5.0);
        assertEquals(p,p1);    
    }

    @Test
    /**
     * tests the x-values of the points, double type
     */
    void testX() {
        assertEquals(p1.x(), 2.7);
        assertEquals(p2.x(), 4.0);
        assertEquals(p3.x(), 6.3);
    }

    @Test
    /**
     * tests the y-values of the points, double type
     */
    void testY() {
        assertEquals(p1.y(), 5.0);
        assertEquals(p2.y(), 10.2);
        assertEquals(p3.y(), 9.0);
    }
@Test
    /**
     * tests the x-values of the points, int type
     */
    void testIx() {
        assertEquals(p1.ix(), 2);
        assertEquals(p2.ix(), 4);
        assertEquals(p3.ix(), 6);
    }

    @Test
    /**
     * tests the y-values of the points, int type
     */
    void testIy() {
        assertEquals(p1.iy(), 5);
        assertEquals(p2.iy(), 10);
        assertEquals(p3.iy(), 9);
    }

    @Test
    /**
     * tests that p1+p2 = p
     */
    void testAdd() {
        Point2D p = new Point2D(6.7,15.2);
        assertEquals(p1.add(p2), p);
        
    }

    @Test
    /**
     * tests the Strings that should represent the points
     */
    void testToString() {
        assertEquals(p1.toString(), "2.7,5.0");
        assertEquals(p2.toString(), "4.0,10.2");
        assertEquals(p3.toString(), "6.3,9.0");
    }

    @Test
    /**
     * tests that the distance between p1 and the origin = 5.682429
     */
    void testDistance() {
        assertEquals(p1.distance(), 5.682429, Ex4_Const.EPS);
    }

    @Test
    /**
     * tests that the distance between p1 and p2 = 5.3600373
     */
    void testDistancePoint2D() {
        assertEquals(p1.distance(p2), 5.3600373, Ex4_Const.EPS);
    }

    @Test
    /**
     * tests that two points are equal if and only if they have the same x and y values
     * tests that the Point2D type is not equal to an object of another type
     */
    void testEqualsObject() {
        Object ob = 5;
        Point2D p = new Point2D(p1);
        boolean eq1 = p1.equals(p);
        boolean eq2 = p1.equals(p2);
        boolean eq3 = p1.equals(ob);
    
        assertTrue(eq1);
        assertFalse(eq2);
        assertFalse(eq3);
    }
@Test
    /**
     * tests that 2 points are close enough for a certain epsilon
     */
    void testClose2equalsPoint2DDouble() {
        Point2D p = new Point2D(3.7,6);
        Point2D pp = new Point2D(2.7000001,5.0000001);
        boolean eq1 = p.close2equals(p1, 1.42);
        boolean eq2 = p.close2equals(p2, 1.42);
        boolean eq3 = pp.close2equals(p1, Ex4_Const.EPS);
        assertTrue(eq1);
        assertFalse(eq2);
        assertTrue(eq3);
    }

    @Test
    /**
     * Tests that both close2equals functions return the same value when eps = Ex4_Const.EPS
     * 
     */
    void testClose2equalsPoint2D() {
        Point2D pp = new Point2D(2.7000001,5.0000001);
        boolean eq1 = pp.close2equals(p1);
        assertTrue(eq1);    
    }

    @Test
    /**
     * tests the vector between 2 points
     */
    void testVector() {
        Point2D po = new Point2D(1.3,5.2);
        boolean eq = po.close2equals(p1.vector(p2));
        Point2D po1 = new Point2D(2.3,-1.2);
        boolean eq1 = po1.close2equals(p2.vector(p3));
        assertTrue(eq);
        assertTrue(eq1);    
    }

    @Test
    /**
     * tests the value of point after it move by vector:
     * p1 should be equals to po after it moves by p2
     * p2 should be equals to po1 after it moves by p3
     */
    void testMove() {
        Point2D po = new Point2D(6.7,15.2);
        Point2D po1 = new Point2D(10.3,19.2);
        p1.move(p2);
        p2.move(p3);
        assertEquals(p1, po);
        assertEquals(p2, po1);
    }

    @Test 
    /**
     * tests scaling of points relative to the origin:
     *  p1 should be 10% smaller
     *  p2 should be 10% larger                  
     * 
     */
    void testScale() {
        p1.scale(Point2D.ORIGIN, 0.9);
        Point2D po = new Point2D(2.7*0.9,5*0.9);
        assertEquals(p1, po);
        
        p2.scale(Point2D.ORIGIN, 1.1);
        Point2D po1 = new Point2D(4*1.1,10.2*1.1);
        assertEquals(p2, po1);
    }
@Test
    /**
     * tests the rotation of points relative to the origin:
     * p1 should move by 180 degrees
     * p2 should move by 90 degrees              
     * 
     */
    void testRotate() {
        
        p1.rotate(Point2D.ORIGIN, Math.PI);
        Point2D po = new Point2D(-2.7,-5);
        boolean eq = po.close2equals(p1);
        assertTrue(eq);
        
        p2.rotate(Point2D.ORIGIN, Math.PI/2);
        Point2D po1 = new Point2D(-10.2,4);
        boolean eq1 = po1.close2equals(p2);
        assertTrue(eq1);    
    }

}