//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Treasure Hunt
// Course:   CS 300 Fall 2020
//
// Author:   Xianfu Luo
// Email:    xluo96@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import processing.core.PImage;
import java.io.File;

/**
 * A class to describe visible object.
 * @author Xianfu Luo
 * @version 1.0
 */
public class VisibleObject extends InteractiveObject {
    private PImage image; // the graphical representation of this object
    private int x; // the horizontal position (in pixels of this object’s left side)
    private int y; // the vertical position (in pixels of this object’s top side)
    public VisibleObject(String name, int x, int y) {
        // initialize this new VisibleObject the image for this visible object should be
        // loaded from : "images"+File.separator+ name +".png" 
        super(name);
        image=getProcessing().loadImage("images"+ File.separator+ name +".png");
        this.x=x;this.y=y;
    }
    @Override
    public Action update() { // draws image at its position before returning null
        getProcessing().image(image,x,y);
        return null;
    }
    public void move(int dx, int dy) { // changes x by adding dx to it (and y by dy)
        x+=dx;
        y+=dy;
    }
    public boolean isOver(int x, int y) { // return true only when point x,y is over image
        if(x>=this.x&&x<=this.x+image.width&&y>=this.y&&y<=this.y+image.height) {
            // x,y is over the image if x,y is on the area of the four points of image
            return true;
        }else{
            return false;
        }
    }
    public boolean isOver(VisibleObject other) { // return true only when other’s image overlaps
        // this one’s
        if ((other.x<=this.x+image.width&&other.y<=this.y+image.height)
                ||(other.x+other.image.width>=this.x&&other.y+other.image.height>=this.y)){
            // compare the top left point and bottom right point to make sure if
            // one image is overlaps another
            return true;
        }else {
            return false;
        }
    }
}
