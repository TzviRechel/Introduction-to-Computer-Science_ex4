package Exe.Ex4.gui;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

class Ex4Test {
    
    Point2D p1 = new Point2D(2.4,3.7);
    Point2D p2 = new Point2D(10.2,15.3);
    Point2D p3 = new Point2D(4,21);
    Point2D p4 = new Point2D(12,9.2);
    Point2D p5 = new Point2D(32.4,0);
    Point2D p6 = new Point2D(2,4);
    ArrayList<Point2D> polygon = new ArrayList<Point2D>();
    
    GeoShapeable gs1 = new Rect2D(p1,p2);
    GeoShapeable gs2 = new Triangle2D(p1,p2,p3);
    GeoShapeable gs3 = new Segment2D(p1,p2);
    GeoShapeable gs4 = new Polygon2D(polygon);
    GeoShapeable gs5 = new Circle2D(p2,12.3);
    
    GUIShape g1 = new GUIShape(gs1,false,Color.BLUE,1);
    GUIShape g2 = new GUIShape(gs2,false,Color.BLACK,2);
    GUIShape g3 = new GUIShape(gs3,false,Color.GREEN,3);
    GUIShape g4 = new GUIShape(gs4,false,Color.RED,4);
    GUIShape g5 = new GUIShape(gs5,false,Color.YELLOW,5);
    
    ShapeCollection shapes = new ShapeCollection();

    Ex4 Ex4_test = Ex4.getInstance();
    
@Test
    /**
     * tests that the collection in EX4 is equal has been updated to the correct collection
     * 
     * tests that when the collection is null, a new empty collection is created in EX4
     */
    void testInit() {
        polygon.add(p1);polygon.add(p2);polygon.add(p3);polygon.add(p4);polygon.add(p5);polygon.add(p6);
        gs4 = new Polygon2D(polygon);
        g4 = new GUIShape(gs4,false,Color.RED,4);
        
        shapes.add(g1);
        shapes.add(g2);
        shapes.add(g3);
        shapes.add(g4);
        shapes.add(g5);
        
        Ex4_test.init(shapes);
        assertEquals(Ex4_test.getShape_Collection().size(), 5);
        assertEquals(Ex4_test.getShape_Collection().get(0).toString(), shapes.get(0).toString());
        assertEquals(Ex4_test.getShape_Collection().get(1).toString(), shapes.get(1).toString());
        assertEquals(Ex4_test.getShape_Collection().get(2).toString(), shapes.get(2).toString());
        assertEquals(Ex4_test.getShape_Collection().get(3).toString(), shapes.get(3).toString());
        assertEquals(Ex4_test.getShape_Collection().get(4).toString(), shapes.get(4).toString());    
        
        Ex4_test.init(null);
        assertEquals(Ex4_test.getShape_Collection().size(), 0);
        
    }
@Test
    /**
     * tests that the function returns the correct collection
     */
    void testGetShape_Collection() {
        
        polygon.add(p1);polygon.add(p2);polygon.add(p3);polygon.add(p4);polygon.add(p5);polygon.add(p6);
        gs4 = new Polygon2D(polygon);
        g4 = new GUIShape(gs4,false,Color.RED,4);
        
        
        shapes.add(g5);
        shapes.add(g4);
        shapes.add(g3);
        shapes.add(g2);
        
        
        Ex4_test.init(shapes);
        
        assertEquals(Ex4_test.getShape_Collection().size(), 4);
        assertEquals(Ex4_test.getShape_Collection().get(0).toString(), shapes.get(0).toString());
        assertEquals(Ex4_test.getShape_Collection().get(1).toString(), shapes.get(1).toString());
        assertEquals(Ex4_test.getShape_Collection().get(2).toString(), shapes.get(2).toString());
        assertEquals(Ex4_test.getShape_Collection().get(3).toString(), shapes.get(3).toString());    
    }


    @Test
    /**
     * tests that the function returns the correct string that represents the collection in Ex4
     */
    void testGetInfo() {
        
        polygon.add(p1);polygon.add(p2);polygon.add(p3);polygon.add(p4);polygon.add(p5);polygon.add(p6);
        gs4 = new Polygon2D(polygon);
        g4 = new GUIShape(gs4,false,Color.RED,4);
        
        shapes.add(g1);
        shapes.add(g2);
        shapes.add(g3);
        shapes.add(g4);
        shapes.add(g5);
        
        Ex4_test.init(shapes);
        
        String st = Ex4_test.getInfo();
        assertEquals(st, shapes.get(0)+"\n"+ shapes.get(1)+"\n"+ shapes.get(2)+"\n"+ shapes.get(3)+"\n"+ shapes.get(4)+"\n");
    }
}