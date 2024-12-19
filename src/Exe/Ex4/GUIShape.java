package Exe.Ex4;
/**
 * This class implements the GUI_shape.
 * Ex4: you should implement this class!
 * @author I2CS
 */
import java.awt.Color;
import java.util.ArrayList;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;


public class GUIShape implements GUI_Shapeable{
	private GeoShapeable _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;

	public GUIShape(GeoShapeable g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape (String lineFromString) {
		String [] line = lineFromString.split(",");
		init(line);


	}

	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}

	@Override
	public GeoShapeable getShape() {
		return _g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;

	}

	@Override
	public GUI_Shapeable copy() {
		GUI_Shapeable cp = new GUIShape(this);
		return cp;
	}
	@Override
	public String toString() {

		return "GUIShape,"+(this._color.getBlue() + this._color.getGreen()*256 + this._color.getRed()*256*256)+","+this._fill+","+this._tag+","+this._g.getClass().getSimpleName()+","+this._g.toString();

	}
	public void init(String[] ww) {
		//In the case that there there is no shape - so as to catch the exception of
		//"Out of bounds"- we will use the try-catch method. 
		try {
			this._color =  new Color(Integer.parseInt(ww[1]));
			this._fill = Boolean.parseBoolean(ww[2]);
			this._tag = Integer.parseInt(ww[3]);

			if (ww[4].contentEquals("Circle2D") ) {
				Point2D center = new Point2D (Double.parseDouble(ww[5]),Double.parseDouble(ww[6]));
				double radius = Double.parseDouble(ww[7]);
				Circle2D circ = new Circle2D(center,radius);
				this._g = circ;
			}
			else  if (ww[4].contentEquals("Polygon2D")) {

				ArrayList<Point2D> polygon = new ArrayList<>();	
				for (int i = 5; i<ww.length; i=i+2) {
					Point2D p  = new Point2D (Double.parseDouble(ww[i]),Double.parseDouble(ww[i+1]));
					polygon.add(p);

				}
				Polygon2D poly1 =new Polygon2D (polygon);
				this._g = poly1;
			}

			else if (ww[4].contentEquals("Segment2D")) {
				Point2D p1  = new Point2D (Double.parseDouble(ww[5]),Double.parseDouble(ww[6]));
				Point2D p2  = new Point2D (Double.parseDouble(ww[7]),Double.parseDouble(ww[8]));
				Segment2D seg = new Segment2D(p1, p2); 
				this._g = seg;
			}

			else if (ww[4].contentEquals("Triangle2D")) {
				Point2D p1  = new Point2D (Double.parseDouble(ww[5]),Double.parseDouble(ww[6]));
				Point2D p2  = new Point2D (Double.parseDouble(ww[7]),Double.parseDouble(ww[8]));
				Point2D p3  = new Point2D (Double.parseDouble(ww[9]),Double.parseDouble(ww[10]));
				Triangle2D tri = new Triangle2D (p1,p2,p3);
				this._g = tri;
			}
			else if (ww[4].contentEquals("Rect2D")) {
		
				Point2D p1  = new Point2D (Double.parseDouble(ww[5]),Double.parseDouble(ww[6]));
				Point2D p2  = new Point2D (Double.parseDouble(ww[9]),Double.parseDouble(ww[10]));
				Rect2D rect = new Rect2D (p1,p2);
				this._g = rect;

			}
		}			
		
		catch(Exception e) {
			System.out.println(" There is no shape ");
		}
		
	}

		

		
	


	
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
	@Override
	public void setShape(GeoShapeable g) {
		// TODO Auto-generated method stub
		this._g = g;

	}
}
