package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;

class ShapeCollectionTest {

	Point2D p1 = new Point2D(2.4, 3.7);
	Point2D p2 = new Point2D(10.2, 15.3);
	Point2D p3 = new Point2D(4, 21);
	Point2D p4 = new Point2D(12, 9.2);
	Point2D p5 = new Point2D(32.4, 0);
	Point2D p6 = new Point2D(2, 4);
	ArrayList<Point2D> polygon = new ArrayList<Point2D>();

	GeoShapeable gs1 = new Rect2D(p1, p2);
	GeoShapeable gs2 = new Triangle2D(p1, p2, p3);
	GeoShapeable gs3 = new Segment2D(p1, p2);
	GeoShapeable gs4 = new Polygon2D(polygon);
	GeoShapeable gs5 = new Circle2D(p2, 12.3);

	GUIShape g1 = new GUIShape(gs1, false, Color.BLUE, 1);
	GUIShape g2 = new GUIShape(gs2, false, Color.BLACK, 2);
	GUIShape g3 = new GUIShape(gs3, false, Color.GREEN, 3);
	GUIShape g4 = new GUIShape(gs4, false, Color.RED, 4);
	GUIShape g5 = new GUIShape(gs5, false, Color.YELLOW, 5);

	ShapeCollection shapes = new ShapeCollection();


	@Test
	/**
	 * \ tests that get a reference for the i'th element in the collection: We will
	 * get the shape, then we will change it and the shape we got in the beginning
	 * has also should be changed
	 * 
	 * tests that this method return the correct GUIShape with all the correct
	 * parameters(GeoShapeable,isfill,color,tag)
	 */
	void testGet() {
		shapes.add(g1);
		shapes.add(g2);
		shapes.add(g3);
		shapes.add(g4);
		shapes.add(g5);
		GeoShapeable gs = shapes.get(1).getShape();
		shapes.get(1).getShape().move(p6);
		Point2D p = shapes.get(1).getShape().getPoints()[0];
		Point2D p1 = shapes.get(1).getShape().getPoints()[1];
		Point2D p2 = shapes.get(1).getShape().getPoints()[2];
		assertTrue(gs instanceof Triangle2D);
		assertFalse(shapes.get(1).isFilled());
		assertEquals(shapes.get(1).getColor(), Color.BLACK);
		assertEquals(shapes.get(1).getTag(), 2);
		assertEquals(p, gs.getPoints()[0]);
		assertEquals(p1, gs.getPoints()[1]);
		assertEquals(p2, gs.getPoints()[2]);
	}

	@Test
	/**
	 * tests that this method return the correct size when we add and remove
	 * elements, and return 0 when the collection is empty
	 */
	void testSize() {
		assertEquals(shapes.size(), 0);
		shapes.add(g1);
		shapes.add(g2);
		shapes.add(g3);
		shapes.add(g4);
		assertEquals(shapes.size(), 4);
		shapes.add(g5);
		assertEquals(shapes.size(), 5);
		shapes.removeElementAt(1);
		assertEquals(shapes.size(), 4);

	}

	@Test
	/**
	 * tests that this method return the GUIShape that removed with all the correct
	 * parameters(GeoShapeable,isfill,color,tag)
	 * 
	 * tests that the size of the collection decreases by 1 after removing an
	 * element
	 */
	void testRemoveElementAt() {
		shapes.add(g1);
		shapes.add(g2);
		shapes.add(g3);
		shapes.add(g4);
		shapes.add(g5);
		assertEquals(shapes.size(), 5);
		GUI_Shapeable g = shapes.removeElementAt(3);
		assertTrue(g.getShape() instanceof Polygon2D);
		assertFalse(g.isFilled());
		assertEquals(g.getColor(), Color.RED);
		assertEquals(g.getTag(), 4);

		assertEquals(shapes.size(), 4);
	}

	@Test
	/**
	 * tests that this method adds the element "as is" to the collection: We will
	 * add the shape, then change it, and the shape we added to the collection
	 * should also change
	 * 
	 * tests that this method adds the correct GUIShape with all the right
	 * parameters(GeoShapeable,isfill,color,tag)
	 */
	void testAddAt() {
		shapes.add(g1);
		shapes.add(g2);
		shapes.add(g3);
		shapes.add(g4);
		shapes.addAt(g5, 2);
		g5.getShape().move(p6);

		assertTrue(shapes.get(2).getShape() instanceof Circle2D);
		assertFalse(shapes.get(2).isFilled());
		assertEquals(shapes.get(2).getColor(), Color.YELLOW);
		assertEquals(shapes.get(2).getTag(), 5);

		assertEquals(shapes.get(2).getShape().getPoints()[0], g5.getShape().getPoints()[0]);
		assertEquals(shapes.get(2).getShape().getPoints()[1], g5.getShape().getPoints()[1]);

	}

	@Test
	/**
	 * tests that this method adds the element "as is" to the collection: We will
	 * add the shape, then change it, and the shape we added to the collection
	 * should also change
	 * 
	 * tests that this method adds the correct GUIShape with all the right
	 * parameters(GeoShapeable,isfill,color,tag)
	 */
	void testAdd() {

		shapes.add(g1);
		shapes.add(g2);
		shapes.add(g3);
		shapes.add(g4);
		shapes.add(g5);

		g5.getShape().move(p6);

		assertTrue(shapes.get(4).getShape() instanceof Circle2D);
		assertFalse(shapes.get(4).isFilled());
		assertEquals(shapes.get(4).getColor(), Color.YELLOW);
		assertEquals(shapes.get(4).getTag(), 5);

		assertEquals(shapes.get(4).getShape().getPoints()[0], g5.getShape().getPoints()[0]);
		assertEquals(shapes.get(4).getShape().getPoints()[1], g5.getShape().getPoints()[1]);

	}

	@Test
	/**
	 * tests that the string of each element in the new collection is equal to the
	 * string of each element in the corresponding position in the original
	 * collection
	 * 
	 * tests that the length of the collections is equal
	 * 
	 * tests that changing an element in the original collection does not affect the
	 * element in the corresponding position in the new collection
	 */
	void testCopy() {
		shapes.add(g1);
		shapes.add(g2);
		shapes.add(g3);
		shapes.add(g4);
		shapes.add(g5);
		ShapeCollectionable new_shapes = shapes.copy();

		assertEquals(shapes.size(), new_shapes.size());
		assertEquals(shapes.get(0).toString(), new_shapes.get(0).toString());
		assertEquals(shapes.get(1).toString(), new_shapes.get(1).toString());
		assertEquals(shapes.get(2).toString(), new_shapes.get(2).toString());
		assertEquals(shapes.get(3).toString(), new_shapes.get(3).toString());
		assertEquals(shapes.get(4).toString(), new_shapes.get(4).toString());

		shapes.get(1).getShape().move(p6);
		assertNotEquals(shapes.get(1).toString(), new_shapes.get(1).toString());
	}

	@Test
	/**
	 * tests that the list is sorted correctly according to each of the defined
	 * comparators
	 */
	void testSort() {

		polygon.add(p1);
		polygon.add(p2);
		polygon.add(p3);
		polygon.add(p4);
		polygon.add(p5);
		polygon.add(p6);
		gs4 = new Polygon2D(polygon);
		GUIShape g4 = new GUIShape(gs4, false, Color.RED, 4);

		shapes.add(g1);
		shapes.add(g2);
		shapes.add(g3);
		shapes.add(g4);
		shapes.add(g5);

		ShapeComp c1 = new ShapeComp(Ex4_Const.Sort_By_Area);
		ShapeComp c2 = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
		ShapeComp c3 = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
		ShapeComp c4 = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
		ShapeComp c5 = new ShapeComp(Ex4_Const.Sort_By_toString);
		ShapeComp c6 = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
		ShapeComp c7 = new ShapeComp(Ex4_Const.Sort_By_Tag);
		ShapeComp c8 = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);

		// Sort_By_Area
		shapes.sort(c1);
		//assertEquals(shapes.get(0).toString(), g3.toString());
		assertEquals(shapes.get(1).toString(), g2.toString());
		assertEquals(shapes.get(2).toString(), g1.toString());
		assertEquals(shapes.get(3).toString(), g4.toString());
		assertEquals(shapes.get(4).toString(), g5.toString());

		// Sort_By_Anti_Area
		shapes.sort(c2);
		assertEquals(shapes.get(0).toString(), g5.toString());
		assertEquals(shapes.get(1).toString(), g4.toString());
		assertEquals(shapes.get(2).toString(), g1.toString());
		assertEquals(shapes.get(3).toString(), g2.toString());
		assertEquals(shapes.get(4).toString(), g3.toString());

		// Sort_By_Perimeter
		shapes.sort(c3);
		assertEquals(shapes.get(0).toString(), g3.toString());
		assertEquals(shapes.get(1).toString(), g1.toString());
		assertEquals(shapes.get(2).toString(), g2.toString());
		assertEquals(shapes.get(3).toString(), g5.toString());
		assertEquals(shapes.get(4).toString(), g4.toString());

		// Sort_By_Anti_Perimeter
		shapes.sort(c4);
		assertEquals(shapes.get(0).toString(), g4.toString());
		assertEquals(shapes.get(1).toString(), g5.toString());
		assertEquals(shapes.get(2).toString(), g2.toString());
		assertEquals(shapes.get(3).toString(), g1.toString());
		assertEquals(shapes.get(4).toString(), g3.toString());

		// Sort_By_toString
		shapes.sort(c5);
		assertEquals(shapes.get(0).toString(), g5.toString());
		assertEquals(shapes.get(1).toString(), g4.toString());
		assertEquals(shapes.get(2).toString(), g1.toString());
		assertEquals(shapes.get(3).toString(), g3.toString());
		assertEquals(shapes.get(4).toString(), g2.toString());

		// Sort_By_Anti_toString
		shapes.sort(c6);
		assertEquals(shapes.get(0).toString(), g2.toString());
		assertEquals(shapes.get(1).toString(), g3.toString());
		assertEquals(shapes.get(2).toString(), g1.toString());
		assertEquals(shapes.get(3).toString(), g4.toString());
		assertEquals(shapes.get(4).toString(), g5.toString());

//Sort_By_Tag
		shapes.sort(c7);
		assertEquals(shapes.get(0).toString(), g1.toString());
		assertEquals(shapes.get(1).toString(), g2.toString());
		assertEquals(shapes.get(2).toString(), g3.toString());
		assertEquals(shapes.get(3).toString(), g4.toString());
		assertEquals(shapes.get(4).toString(), g5.toString());

		// Sort_By_Anti_Tag
		shapes.sort(c8);
		assertEquals(shapes.get(0).toString(), g5.toString());
		assertEquals(shapes.get(1).toString(), g4.toString());
		assertEquals(shapes.get(2).toString(), g3.toString());
		assertEquals(shapes.get(3).toString(), g2.toString());
		assertEquals(shapes.get(4).toString(), g1.toString());

	}

	@Test
	/**
	 * tests that the size of the collection == 0 after remove the all elements
	 */
	void testRemoveAll() {

		shapes.add(g1);
		shapes.add(g2);
		shapes.add(g3);
		shapes.add(g4);
		shapes.add(g5);

		assertEquals(shapes.size(), 5);
		shapes.removeAll();
		assertEquals(shapes.size(), 0);
	}

	@Test
	void testSave() {
		GUIShape[] arr = new GUIShape[4];
		arr[0] = new GUIShape(new Circle2D(new Point2D(3.0, 4), 2), true, Color.black, 1);
		arr[1] =  new GUIShape(new Rect2D(new Point2D(3.0,4.0),new Point2D(6.0, 8.0)), true, Color.yellow, 2);
		arr[2] = new GUIShape(new Circle2D(new Point2D(1.078125, 9.234375), 1.7050591082129676), false, Color.blue, 0);
		arr[3] = new GUIShape(new Circle2D(new Point2D(1.96875,7.3125), 0.940749576468148), false,Color.blue,0);
		ShapeCollection sc1 = new ShapeCollection();
		for (int i =0; i<arr.length;i++) {
			sc1.add(arr[i]);
		}
		sc1.save("drawing2.txt");
		ShapeCollection sc2 = new ShapeCollection();
		sc2.load("drawing2.txt");
		for (int j = 0; j<sc1.size(); j++) {
			assertEquals(sc1.get(j).toString(), sc2.get(j).toString());
			
		}
			
		
	}

	@Test
	void testLoad() {
		/* This was the documentation of the file:
		 * GUIShape,0,true,1,Circle2D,3.0,4.0, 2.0
		 * GUIShape,16776960,true,2,Rect2D,3.0,4.0,6.0,4.0,6.0,8.0,3.0,8.0
		 * GUIShape,255,false,0,Circle2D,1.078125,9.234375, 1.7050591082129676
		 * GUIShape,255,false,0,Circle2D,1.96875,7.3125, 0.940749576468148
		 */
		GUIShape[] arr = new GUIShape[4];
		arr[0] = new GUIShape(new Circle2D(new Point2D(3.0, 4), 2), true, Color.black, 1);
		arr[1] = new GUIShape(new Rect2D(new Point2D(3.0,4.0),new Point2D(6.0, 8.0)), true, Color.yellow, 2);
		arr[2] = new GUIShape(new Circle2D(new Point2D(1.078125, 9.234375), 1.7050591082129676), false, Color.blue, 0);
		arr[3]= new GUIShape(new Circle2D(new Point2D(1.96875,7.3125), 0.940749576468148), false,Color.blue,0);
		/////////////////
		
		String file = "drawing.txt";
		ShapeCollection sc = new ShapeCollection();
		sc.load(file);
		GUIShape expected;
		GUIShape result;
		
		for (int i = 0; i < arr.length; i++) {
			expected = arr[i];
			result = (GUIShape) sc.get(i);
			assertEquals(expected.toString(),result.toString());
		}
	}

	@Test
	/**
	 * tests that This methods returns empty rectangle when the collection is empty
	 * 
	 * tests that This methods returns the correct rectangle after After adding the
	 * GUIshapes to the collection
	 */
        void testGetBoundingBox() {
        
        Rect2D r = shapes.getBoundingBox();
        assertEquals(r, null);    
        shapes.add(g1);
        shapes.add(g2);
        shapes.add(g3);
        shapes.add(g5);
        
        Rect2D r2 = shapes.getBoundingBox();
        Point2D p = new Point2D(-2.1,3);
        Point2D pp = new Point2D(22.5,27.6);    
        assertTrue(r2.getPoints()[0].close2equals(p));
        assertTrue(r2.getPoints()[1].close2equals(pp));    
        
        
    }
	@Test
    /***
    * tests that This methods returns empty string when the collection is empty
     * 
     * tests that This methods returns the correct string after adding the GUIshapes to the collection
     */
    void testToString() {
        polygon.add(p1);polygon.add(p2);polygon.add(p3);polygon.add(p4);polygon.add(p5);polygon.add(p6);
        gs4 = new Polygon2D(polygon);
        g4 = new GUIShape(gs4,false,Color.RED,4);

        assertEquals(shapes.toString(), "");
        
        shapes.add(g1);
        shapes.add(g2);
        shapes.add(g3);
        shapes.add(g4);
        shapes.add(g5);
        
        String st = shapes.toString();
        assertEquals(st, "GUIShape,255,false,1,Rect2D,2.4,3.7,10.2,3.7,10.2,15.3,2.4,15.3"+
                "GUIShape,0,false,2,Triangle2D,2.4,3.7,10.2,15.3,4.0,21.0"+
                "GUIShape,65280,false,3,Segment2D,2.4,3.7,10.2,15.3"+
                "GUIShape,16711680,false,4,Polygon2D,2.4,3.7,10.2,15.3,4.0,21.0,12.0,9.2,32.4,0.0,2.0,4.0"+
                "GUIShape,16776960,false,5,Circle2D,10.2,15.3, 12.3");

    }
}