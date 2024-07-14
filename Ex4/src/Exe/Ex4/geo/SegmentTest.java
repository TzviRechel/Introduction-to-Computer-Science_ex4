package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;
class SegmentTest {
	
	private Point2D p1 = new Point2D (1,1);
	private	Point2D p2 = new Point2D (4,4);
	private Point2D p3 = new Point2D (1,2);
	private Point2D p4 = new Point2D (5,6);
	private Point2D toMove = new Point2D (2.5,2.5);
	private Point2D toMove2 = new Point2D (2,2);
	private static Segment2D seg1;
	private static Segment2D seg2;
	
	@Test
    void testSegment2DConstructor() {
     Segment2D seg1 = new Segment2D(p1, p2);
     Segment2D seg2 = new Segment2D(p3, p4);
     Point2D[] po1 = seg1.getPoints();
     Point2D[] po2 = seg2.getPoints();
     assertEquals(po1[0], p1);
     assertEquals(po1[1], p2);
     assertEquals(po2[0], p3);
     assertEquals(po2[1], p4);
	}
	
	@Test
	void testContains() {
	
	Point2D doesCP1 = new Point2D (3,3);
	Point2D doesCP2 = new Point2D (2,3);
	Segment2D seg1 = new Segment2D(p1, p2);
	Segment2D seg2 = new Segment2D (p3,p4);
	boolean fx0 = seg1.contains(doesCP1);
	boolean fx1 = seg1.contains(doesCP2);
	boolean fx2 = seg2.contains(doesCP1);
	boolean fx3 = seg2.contains(doesCP2);
	assertEquals(fx0, true);
	assertEquals(fx1, false);
	assertEquals(fx2, false);
	assertEquals(fx3, true);
	}
	

	@Test
	void testToString() {
		Point2D p1 = new Point2D (1,1);
		Point2D p2 = new Point2D (4,4);
		Segment2D seg1 = new Segment2D(p1, p2);
		String st = seg1.toString();
		assertEquals(st,"1.0,1.0,4.0,4.0");
	}
	@Test
	void testArea() {
		Point2D p1 = new Point2D (1,1);
		Point2D p2 = new Point2D (4,4);
		Segment2D seg1 = new Segment2D(p2, p1);
		assertEquals(seg1.area(),0);
	}
	@Test
	void testPerimeter() {
		Point2D p1 = new Point2D (1,1);
		Point2D p2 = new Point2D (4,4);
		Point2D p3 = new Point2D (1,2);
		Point2D p4 = new Point2D (5,6);
		Segment2D seg1 = new Segment2D(p1, p2);
		Segment2D seg2 = new Segment2D (p3,p4);
		double fx0 = seg1.perimeter();
		double fx1 = seg2.perimeter();
		assertEquals(fx0, 8.48528137423857, Ex4_Const.EPS1);
		assertEquals(fx1, 11.313708498984761, Ex4_Const.EPS1);

	}
		@Test
		void testMove() {
			Point2D p1 = new Point2D (1,1);
			Point2D p2 = new Point2D (4,4);
			Segment2D seg1 = new Segment2D(p1, p2);
			seg1.move(toMove2);
			Point2D[] po = seg1.getPoints();
			assertEquals(po[0].x(), 3);
			assertEquals(po[0].y(), 3);
		    assertEquals(po[1].x(), 6);
			assertEquals(po[1].y(), 6);
		    
		
		}
		@Test
		void testCopy() {
			Segment2D seg1 = new Segment2D(p1, p2);
			GeoShapeable new_seg = seg1.copy();
		     Point2D[] po = new_seg.getPoints();
		     assertEquals(po[0], p1);
		     assertEquals(po[1], p2);
		}
		@Test
		void testScale() {
			Segment2D seg1 = new Segment2D(p1, p2);
			seg1.scale(toMove, 0.9); //The parameter of the segment should be 10% smaller (90% of the original segment)
		     double new_Perimeter = seg1.perimeter();
		     assertEquals(new_Perimeter, 8.48528137423857*0.9, Ex4_Const.EPS);
		     Segment2D seg2 = new Segment2D(p3, p4);
		     seg2.scale(toMove, 1.1); //The parameter of the segment should be 10% larger (110% of the original segment)
		     new_Perimeter = seg2.perimeter();
		     assertEquals(new_Perimeter, 11.313708498984761*1.1, Ex4_Const.EPS);
			
		}
		@Test
		void testRotate() {
			Point2D p1 = new Point2D (1,1);
			Point2D p2 = new Point2D (4,4);
			Point2D center = new Point2D (2.5,2.5);
			Segment2D seg1 = new Segment2D(p1, p2);
			 seg1.rotate(center, Math.PI/2); //The Segment should rotate 90% degrees with respect to the origin point and complete and X from bottom right to top left.
		     Point2D[] po = seg1.getPoints();
		     assertEquals(po[0].x(), 4, Ex4_Const.EPS);
		     assertEquals(po[0].y(), 1, Ex4_Const.EPS);
		     assertEquals(po[1].x(), 1, Ex4_Const.EPS);
		     assertEquals(po[1].y(), 4, Ex4_Const.EPS);
			
		}
		@Test
		void testGetPoints() {
			Point2D p1 = new Point2D (1,1);
			Point2D p2 = new Point2D (4,4);
			Point2D p3 = new Point2D (1,2);
			Point2D p4 = new Point2D (5,6);
			Segment2D seg1 = new Segment2D(p1, p2);
			Segment2D seg2 = new Segment2D (p3,p4);
			Point2D [] arr1 = new Point2D [2];
			Point2D [] arr2 = new Point2D [2];
			arr1[0] = new Point2D (1,1);
			arr1[1] = new Point2D (4,4);
			arr2[0] = new Point2D (1,2);
			arr2[1] = new Point2D (5,6);
			assertArrayEquals(seg1.getPoints(),arr1); 
			assertArrayEquals(seg2.getPoints(),arr2); 
		
			
		}

			
}
