package Exe.Ex4.geo;

import java.util.Comparator;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUI_Shapeable;

/**
 * This class represents a Comparator over GUI_Shapes - 
 * as a linear order over GUI_Shapes.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeComp implements Comparator<GUI_Shapeable>{
    
    public static final Comparator<GUI_Shapeable> CompByToString = new ShapeComp(Ex4_Const.Sort_By_toString);
    private int _flag;
    public ShapeComp(int flag) {
        _flag = flag;
        
    }
    @Override
    public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
        int ans=0;
        if(_flag == Ex4_Const.Sort_By_toString) {
            //First, compare according to the lexical order of the name of the geoshape
            ans = o1.getShape().getClass().getTypeName().compareTo(o2.getShape().getClass().getTypeName());
            //If the two geoshape are of the same type, compare according to StringComaprator
            if(ans == 0) {
                ans = o1.toString().compareTo(o2.toString());
            }    
        }
        if(_flag == Ex4_Const.Sort_By_Anti_toString) {
            ans = o1.getShape().getClass().getTypeName().compareTo(o2.getShape().getClass().getTypeName());
            if(ans == 0) {
                ans = o1.toString().compareTo(o2.toString());
            }    
            ans = ans*-1;
        }
        if(_flag == Ex4_Const.Sort_By_Area) {
            if(o1.getShape().area() > o2.getShape().area()) {
                ans = 1;    
            }
            if(o1.getShape().area() < o2.getShape().area()) {
                ans = -1;        
        }
        }
        if(_flag == Ex4_Const.Sort_By_Anti_Area) {
            if(o1.getShape().area() < o2.getShape().area()) {
                ans = 1;    
            }
            if(o1.getShape().area() > o2.getShape().area()) {
                ans = -1;   
        }
        }
        if(_flag == Ex4_Const.Sort_By_Perimeter) {
            if(o1.getShape().perimeter() > o2.getShape().perimeter()) {
                ans = 1;    
            }
            if(o1.getShape().perimeter() < o2.getShape().perimeter()) {
                ans = -1;        
        }
        }
        if(_flag == Ex4_Const.Sort_By_Anti_Perimeter) {
            if(o1.getShape().perimeter() < o2.getShape().perimeter()) {
                ans = 1;    
            }
            if(o1.getShape().perimeter() > o2.getShape().perimeter()) {
                ans = -1;        
        }
        }
        if(_flag == Ex4_Const.Sort_By_Tag) {
            if(o1.getTag()>o2.getTag()) {
                ans = 1;
        }
        if(o1.getTag()<o2.getTag()) {
                ans = -1;
        }    
        }
        
        if(_flag == Ex4_Const.Sort_By_Anti_Tag){
            if(o1.getTag()<o2.getTag()) {
                ans = 1;
        }
        if(o1.getTag()>o2.getTag()) {
                ans = -1;
        }    
        }
        return ans;
    }

}