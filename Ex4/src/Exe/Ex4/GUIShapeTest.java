package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import javax.naming.InitialContext;

import org.junit.jupiter.api.Test;
import org.w3c.dom.css.RGBColor;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;


	
	

	



class GUIShapeTest {

	
	private Color _color =  Color.blue ;
	private int _tag = 0 ;

	private Point2D p1seg = new Point2D (2,2);
	private Point2D p2seg = new Point2D (4,6);
	private Point2D pCirc = new Point2D (2,4);
	private Point2D p3 = new Point2D (1,1);
	private Point2D p4 = new Point2D (5,6);
	private ArrayList<Point2D> pointsP = new ArrayList<>();
	private Triangle2D triangle = new Triangle2D(p1seg, p2seg, p3) ;
	private Rect2D rect = new Rect2D (p3, p4);
	private Segment2D seg = new Segment2D (p1seg, p2seg);
	private Circle2D circ = new Circle2D (pCirc, 2);
	
	 
	
	
	
	@Test
	void testContractors() {
		pointsP.add(p1seg);
		pointsP.add(p2seg);
		pointsP.add(p3);
		pointsP.add(p4);
		pointsP.add(pCirc);
		Polygon2D polygon = new Polygon2D (pointsP); 
		GUIShape shape1 = new GUIShape(polygon, false, _color, _tag); // constructing a polygon
		String s = shape1.toString();
		GUIShape shape2 = new GUIShape (s);
		assertEquals(shape1.toString(), shape2.toString());
		GUIShape shape3 = new GUIShape(rect, false, _color, _tag); // constructing a rectangle
		String s1 = shape3.toString();
		GUIShape shape4 = new GUIShape (s1);
		assertEquals(shape3.toString(), shape4.toString());
		GUIShape shape5 = new GUIShape(seg, false, _color, _tag); // constructing a segment
		String s2 = shape5.toString();
		GUIShape shape6 = new GUIShape (s2);
		assertEquals(shape5.toString(), shape6.toString());
		GUIShape shape7 = new GUIShape(circ, false, _color, _tag); // constructing a circle
		String s3 = shape7.toString();
		GUIShape shape8 = new GUIShape (s3);
		assertEquals(shape7.toString(), shape8.toString());
		GUIShape shape9 = new GUIShape(triangle, false, _color, _tag); // constructing a triangle
		String s4 = shape9.toString();
		GUIShape shape10 = new GUIShape (s4);
		assertEquals(shape9.toString(), shape10.toString());
	}
	
	
	@Test
	void testGetShape() {// The segment that was inserted was what we got.
		GUIShape shape = new GUIShape(seg, false, _color, _tag);
		assertEquals(shape.getShape().toString(), seg.toString());
	}
	
			
		
	
	@Test
	void testIsFilled() {
		GUIShape shape = new GUIShape(circ, false, _color, _tag);
		assertEquals(shape.isFilled(), false);
	}
	@Test
	void testSetFilled() {
		GUIShape shape = new GUIShape(circ, false, _color, _tag);
		shape.setFilled(true);
		assertEquals(shape.isFilled(), true);
	}
	@Test
	void testGetColor() {
		GUIShape shape = new GUIShape(circ, false, _color, _tag);
		assertEquals(shape.getColor(), Color.blue);
	}
	@Test
	void testSetColor() {
		GUIShape shape = new GUIShape(circ, false, _color, _tag);
		shape.setColor(Color.black);
		assertEquals(shape.getColor(), Color.black);// changed the color from blue to black.
	}
	@Test
	void testGetTag() {
		GUIShape shape = new GUIShape(circ, false, _color, _tag);
		assertEquals(shape.getTag(), 0);
		
	}
	@Test
	void testSetTag() {
		GUIShape shape = new GUIShape(circ, false, _color, _tag);
		shape.setTag(1);
		assertEquals(shape.getTag(), 1);
		
	}
	@Test
	void testCopy() {
		GUIShape shape1 = new GUIShape(circ, false, Color.black, 2);
		GUIShape shape2 = new GUIShape(circ, false, Color.black, 2);
		shape2.copy();
		assertEquals(shape2.getColor(), shape1.getColor());
		assertEquals(shape2.getTag(), shape1.getTag());
		assertEquals(shape2.getShape().toString(), shape1.getShape().toString());
		

	}
	@Test
	void testToString() {
		GUIShape shape1 = new GUIShape(circ, false, Color.black, 2);
		assertEquals(shape1.toString(), "GUIShape,0,false,2,Circle2D,2.0,4.0, 2.0");
		
	}
	@Test
	void testInit() {
		GUIShape shape1 = new GUIShape(null, false, _color, _tag);
		String st1 = "GUIShape,0,true,1,Circle2D,3.0,4.0, 2.0";
		String [] starr1 = st1.split(",");
		shape1.init(starr1);
		assertEquals(shape1.getShape().toString(),"3.0,4.0, 2.0");
		pointsP.add(p1seg);
		pointsP.add(p2seg);
		pointsP.add(p3);
		pointsP.add(p4);
		pointsP.add(pCirc);
		Polygon2D polygon = new Polygon2D (pointsP);
		GUIShape shape2 = new GUIShape(null, false, _color, _tag);
		String st2 = "GUIShape,0,true,1,Polygon2D,2.0,2.0,4.0,6.0,1.0,1.0,5.0,6.0,2.0,4.0";
		String [] starr2 = st2.split(",");
		shape2.init(starr2);
		assertEquals(shape2.toString(), "GUIShape,0,true,1,Polygon2D,2.0,2.0,4.0,6.0,1.0,1.0,5.0,6.0,2.0,4.0");
	}
	@Test
	void testIsSelected() {
		GUIShape shape1 = new GUIShape(circ, false, Color.black, 2);
		assertEquals(shape1.isSelected(), false);
	}
	@Test
	void testSetSelected() {
		GUIShape shape1 = new GUIShape(circ, false, Color.black, 2);
		shape1.setSelected(true);
		assertEquals(shape1.isSelected(), true);
	}
	@Test
	void testSetShape() {
		GUIShape shape1 = new GUIShape(circ, false, Color.black, 2);
		shape1.setShape(seg);
		assertEquals(shape1.getShape().toString(), seg.toString()); // Only the shape changed- all the other elements stayed.
		assertEquals(shape1.getTag(), 2);
		assertEquals(shape1.getColor(),Color.black ); //
	}

}

