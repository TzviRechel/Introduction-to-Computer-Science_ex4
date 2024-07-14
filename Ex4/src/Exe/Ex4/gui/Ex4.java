package Exe.Ex4.gui;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFileChooser;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 *ID1: 208846642
 *ID2: 316289495
 */
public class Ex4 implements Ex4_GUI{
	private  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private  Point2D _p1, _p2;
	private  static Ex4 _winEx4 = null;
	private static int _tag = 1; // We chose that every time  we create a new shape independently - we will get a new tag. A shape created as a copy - will not get a new tag but will have an equal to the one created from.
	private Ex4() {
			init(null);
	}
	
	public void init(ShapeCollectionable s) {
		if(s==null) {_shapes = new ShapeCollection();}
		else {_shapes = s.copy();}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
	}
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}
	// This is the main function that gathers all the details on the major list - shape collection, and draws.
	public void drawShapes() {
		StdDraw_Ex4.clear();
		for(int i=0;i<_shapes.size();i++) { 
			GUI_Shapeable sh = _shapes.get(i); //		
				drawShape(sh);
			}
			if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}
	// The instance of every shape- how to draw it. We receive from the 
	// Gui Shapable all the aspects of the the shape- which shape it is,
	// which color, and if it meant to be filled. - And with that we will go
	// to make the drawing happen according to the Std_draw rules for that shape
	// The GeoShapable class - is the actual shape- without the drawing aspects.
	private static void drawShape(GUI_Shapeable g) {				 
		if (g == null ||g.getShape()==null)// In case there was no drawing. (In the class Shapecollection - there will be a print -"You didn't draw a shape" 
			return;
		StdDraw_Ex4.setPenColor(g.getColor());						
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}	
		GeoShapeable gs = g.getShape();								
		boolean isFill = g.isFilled();								
		if(gs instanceof Circle2D) { 
			Circle2D c = (Circle2D)gs; 
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			else { 
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
				}
			}		
		if(gs instanceof Segment2D) {
			Segment2D seg = (Segment2D)gs;
			Point2D firstP = seg.getPoints()[0];
			Point2D secondP = seg.getPoints()[1];
			StdDraw_Ex4.line(firstP.x(), firstP.y(), secondP.x(), secondP.y() );
			}
		if(gs instanceof Rect2D) {
            Rect2D r = (Rect2D)gs;
            double[] x = r.getx();
            double[] y = r.gety();
            if(isFill) {
                StdDraw_Ex4.filledPolygon(x, y);
            }
            else { 
                StdDraw_Ex4.polygon(x, y);
                }    
        }	
		if(gs instanceof Triangle2D) {
			Triangle2D t = (Triangle2D)gs;
			Point2D a = t.getPoints()[0];
			Point2D b = t.getPoints()[1];
			Point2D c = t.getPoints()[2];
			double[] x = {a.x(), b.x(), c.x()}; 
			double[] y = {a.y(), b.y(), c.y()};
			if(isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			}
			else { 
				StdDraw_Ex4.polygon(x, y);
			}
		}
		if(gs instanceof Polygon2D) {
			Polygon2D poly = (Polygon2D)gs;
			double [] x =new double[poly.getPoints().length];	// We convert the points from and ArrayList, and into "regular arrays", required by the class StdDraw. 
			double [] y =new double[poly.getPoints().length];
			for (int i= 0;i<poly.getPoints().length; i++) {
				x[i]= poly.getPoints()[i].x();
				y[i]= poly.getPoints()[i].y();
			}
			if(isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			}
			else { 
				StdDraw_Ex4.polygon(x, y);
			}
		}
	}
		
	// Extracts out of the main list (shape collection) - which color should the shape be drawn.
	private void setColor(Color c) { 
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}
	// Extracts out of the main list (shape collection) - if the shape should be drawn filled or not- and applies that to the drawing.
	private void setFill() { 
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}
	// This is a major function, on the basis of what we clicked on the screen.
	public void actionPerformed(String p) { 
        _mode = p;
        if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
        if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
        if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
        if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
        if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
        if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
        if(p.equals("Fill")) {_fill = true; setFill();}
        if(p.equals("Empty")) {_fill = false; setFill();}
        if(p.equals("Clear")) {_shapes.removeAll();}
        if(p.equals("Info")) {System.out.println(getInfo());}
        if(p.equals("All")) {
            for(int i=0;i<_shapes.size();i++) {
                 _shapes.get(i).setSelected(true);
            }    
            }
      //credit: https://www.codejava.net/java-se/swing/show-simple-open-file-dialog-using-jfilechooser
     	if(p.equals("Save")) {
     		
     		JFileChooser fileChooser = new JFileChooser();
     		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
     		int result = fileChooser.showSaveDialog(StdDraw_Ex4.getFrame());
     			if (result == JFileChooser.APPROVE_OPTION) {

     				try {
     					fileChooser.getSelectedFile().getAbsoluteFile().createNewFile();
     				} 
     				catch (IOException e) {
     					e.printStackTrace();
     				}
     				_shapes.save(fileChooser.getSelectedFile().getPath());
     			}

     		}
     	if(p.equals("Load")) {
     		
			JFileChooser fileChooser = new JFileChooser(); // Building a file that will read the content.
			int result = fileChooser.showOpenDialog(StdDraw_Ex4.getFrame());
			if (result == JFileChooser.APPROVE_OPTION) {
				try {
					fileChooser.getSelectedFile().getAbsoluteFile().createNewFile(); // need to be fixed
				} catch (IOException e) {

					e.printStackTrace();
				}
				_shapes.removeAll();// Removing whatever there is before we show the new loaded drawing.
				_shapes.load(fileChooser.getSelectedFile().getPath());
			}
		}
        if(p.equals("Anti")) { // Switches the selection between the chosen and not chosen shapes of the screen.
            for(int i=0;i<_shapes.size();i++) {
                boolean selected = _shapes.get(i).isSelected();
                 _shapes.get(i).setSelected(!selected);
            }    
            }
        if(p.equals("None")) { // Makes the selected - unselected.
            for(int i=0;i<_shapes.size();i++) {
                 _shapes.get(i).setSelected(false);
            }    
            }
        
     // The shape/s that were selected on screen - will be removed.
        if(p.equals("Remove")) { 
            for(int i=0;i<_shapes.size();i++) {
                GUI_Shapeable s = _shapes.get(i);
                if(s!=null && s.isSelected()) {
                    _shapes.removeElementAt(i);
                    i--;				// Every time we remove an element - all the following will move back one. 
                    					// So that we don't miss a shape - we will set back the 'i' one.
                }
            }  
        }
            

        
        // This is all the way of sorting - so that if one shape is blocking the other - it will show upon the choosing of the parameter: and display it in order.
        if(p.equals("ByArea")) {// According to the largest area.
            ShapeComp c = new ShapeComp(Ex4_Const.Sort_By_Area);
            _shapes.sort(c);
        }
        if(p.equals("ByAntiArea")) {// According to the smallest area.
            ShapeComp c = new ShapeComp(Ex4_Const.Sort_By_Anti_Area);
            _shapes.sort(c);
            }
        if(p.equals("ByPerimeter")) {// According to the largest parimeter.
            ShapeComp c = new ShapeComp(Ex4_Const.Sort_By_Perimeter);
            _shapes.sort(c);
            }
        if(p.equals("ByAntiPerimeter")) {// According to the smallest parimeter.
            ShapeComp c = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
            _shapes.sort(c);
            }
        if(p.equals("ByToString")) {// According to the first in alphabetical order.
            ShapeComp c = new ShapeComp(Ex4_Const.Sort_By_toString);
            _shapes.sort(c);
            }
        if(p.equals("ByAntiToString")) {// According to the opposite of alphabetical order.
            ShapeComp c = new ShapeComp(Ex4_Const.Sort_By_Anti_toString);
            _shapes.sort(c);
            }
        if(p.equals("ByTag")) {// NEED TO CHANGE
            ShapeComp c = new ShapeComp(Ex4_Const.Sort_By_Tag);
            _shapes.sort(c);
            }
        if(p.equals("ByAntiTag")) {// AASSS WEEELLL
            ShapeComp c = new ShapeComp(Ex4_Const.Sort_By_Anti_Tag);
            _shapes.sort(c);
            }
        
        
        drawShapes();
        
    }
    
	// This ArrayList will represent our points of a polygon.
	ArrayList<Point2D> pol = new ArrayList<Point2D>(); 
	public void mouseClicked(Point2D p) {
		System.out.println("Mode: "+_mode+"  "+p);
		if(_mode.equals("Circle")|| _mode.equals("Segment") || _mode.equals("Rect")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(_tag);
				_tag++;
				_shapes.add(_gs);
				_gs = null; // once we have finished with the choosing of points for the shape -
				_p1 = null; // We want the assisting parameters for holding the points - to start over for the next shape.
				_p2 = null;	// That is so- in all the building of shapes.
				pol.clear();// And we start from scratch to make things clear.
			}				
		}
		
		// Triangle
		if(_mode.equals("Triangle")) {
			if(_gs ==null) {
				_p1 = new Point2D(p);
				
			}
			else {
				if(_p2 == null) {
				    _p2 = new Point2D(p);
				}	
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_gs.setTag(_tag);
				_tag++;
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_p2 = null;
				pol.clear(); 
				}
			}
			}
		// Polygon
			if(_mode.equals("Polygon")) {
				if (_gs == null) {	// Has not received any points yet, and will automatically 
				_p1 = new Point2D(p);
				pol.add(p);
				}
				pol.add(p); // We need to add the first time twice the same point (and then only one will be added)- Explanation bellow:
				
			}
			if(_mode.equals("Rotate")) {
                if(this._p1 != null) {
                for(int i=0;i<_shapes.size();i++) {
                    GUI_Shapeable s = _shapes.get(i);
                    GeoShapeable g = s.getShape();
                    if(g!=null && s.isSelected()) {
                        double degree = Math.atan2(p.y()-this._p1.y() ,p.x()-this._p1.x());
                        g.rotate(this._p1, degree);    
                    }
                }
                this._p1 = null;
                _gs = null;
                }
                else {
                   this._p1 = new Point2D(p);
                }
            } 
			//The scale is implemented in each shape's class, changes the the points of each shapes class.
			// Basiclly - we are just trying to get to that shape's class and scale the points.
			if(_mode.equals("Scale_90%")) {  
                for(int i=0;i<_shapes.size();i++) { 
                    GUI_Shapeable s = _shapes.get(i);
                    GeoShapeable g = s.getShape();
                    if(g!=null && s.isSelected()) {
                        g.scale(p, 0.9);
                        
                    }
                }
            }
            
            if(_mode.equals("Scale_110%")) {
                for(int i=0;i<_shapes.size();i++) {
                    GUI_Shapeable s = _shapes.get(i);
                    GeoShapeable g = s.getShape();
                    if(g!=null && s.isSelected()) {
                        g.scale(p, 1.1);
                        
                    }
                }
            }
    
			
		if(_mode.equals("Move")) {
			if(_p1==null) {_p1 = new Point2D(p);} // we choose a certain point
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
				_gs = null;
			}
			}
		if(_mode.equals("Copy")) {
            if(_p1 == null) {
                _p1 = new Point2D(p);
            }
            else {
                _p1 = _p1.vector(p);
                copy();
                _p1 = null;
                _gs = null;
            }
            
            
        }
		if (_mode.equals("Clear")) {
			_shapes.removeAll();
	
		}
		if(_mode.equals("Point")) {
			select(p);
		}
		
		drawShapes();
	}
	// Copies the same shape.
	private void copy() {
        for(int i=0;i<_shapes.size();i++) {
            GUI_Shapeable s = _shapes.get(i);
            if(s!=null && s.isSelected()) {
                GUI_Shapeable new_s = s.copy();
                _shapes.add(new_s);    
                new_s.setSelected(false);
                new_s.getShape().move(_p1);
            }
        }
    }
	// This function helps us with the choosing of each shape on the screen.
	private void select(Point2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null && g.contains(p)) { // It checks if the point that was clicked is within the shape.
				s.setSelected(!s.isSelected());
			}
		}
	}
	
	private void move() {// 
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.move(_p1);
			}
		}
		
	}
	
	// This function is to help us with the creation of the polygon. The right click finishes the building of the polygon.
	public void mouseRightClicked(Point2D p) {
			
		if(_mode.equals("Polygon" )&& (_gs!= null)) {
			pol.remove(pol.size()-1);
			GeoShapeable gs = new Polygon2D(pol);
			_gs = new GUIShape(gs,false, Color.pink, 0);
			_gs.setColor(_color); 
			_gs.setTag(_tag);
			_tag++;
			_gs.setFilled(_fill);
			_shapes.add(_gs);
			drawShapes();
			_gs = null; // The null in all the bellow - is to start afresh for the new shape.
			_p1 =null;
			_p2 = null;
			pol.clear();	
		}
		// If we right clicked while drawing a shape other than a polygon - it will erase the shape in build.
		if((_mode.equals("Segment" )&& (_gs!= null)) || (_mode.equals("Circle" )&& (_gs!= null)) || (_mode.equals("Triangle" )&& (_gs!= null)) || (_mode.equals("Rect" )&& (_gs!= null))) {
			_p1 =null;				
			_p2 = null;
			_gs =null;
			pol.clear();
			drawShapes();
			

		}
	
	}
	// Until choosing the right size of the shape to draw - 
	// this function will draw the expected shape in the color pink- 
	// until officially deciding the actual shape.
	public void mouseMoved(MouseEvent e) { 
		if(_p1!=null) {						
			double x1 = StdDraw_Ex4.mouseX(); 
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
	
			Point2D p = new Point2D(x1,y1);
			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle2D(_p1,r);
			}
			
			
			if(_mode.equals("Segment")) {
				gs = new Segment2D(_p1,p);
			}
			
			if(_mode.equals("Rect")) {
				gs = new Rect2D(_p1, p);
			}
			if(_mode.equals("Triangle")) {
				if(_p2 == null) 
				gs = new Segment2D(_p1,p);
				else {
					gs = new Triangle2D(_p1,_p2,p);
				}
			}
			//Polygon
			//// In order not to draw many 'p's, but to move as it should, we want the last point 
			//to always be dynamic(until it is clicked), so each 'p' will replace the same 
			//last 'p'(the last point on the ArrayList - until the click! 
			if(_mode.equals("Polygon")) {
                if(pol.size()>0) {
                pol.set(pol.size()-1, p);
                }
                else { // In the case of an empty array.
                    pol.add(_p1);
                    if(_p2!=null) {pol.add(_p2);} 
                    pol.add(_p1);
                    
                    pol.set(pol.size()-1, p);    
                }
                gs = new Polygon2D(pol);
                
            }
			
			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}
	@Override
	public ShapeCollectionable getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}
