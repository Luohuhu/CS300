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

/**
 * A class to describe draggable object.
 * @author Xianfu Luo
 * @version 1.0
 */
public class DraggableObject extends VisibleObject{
    private boolean mouseWasPressed; // similar to use in ClickableObject
    private boolean isDragging; // true when this object is being dragged by the user
    private int oldMouseX; // horizontal position of mouse during last update
    private int oldMouseY; // vertical position of mouse during last update
    public DraggableObject(String name, int x, int y) { // initializes new draggable object
        super(name,x,y);
    }
    @Override
    public Action update() { // calls VisibleObject update() first,
        // then moves according to mouse drag each time isDragging changes
        // from true to false, the drop() method below will be called once and any
        // action objects returned from that method should then be 
        // returned from update() protected Action drop() { return null; }
        // this method returns null. 
        // subclass types will override this drop() method to perform more interesting behavior
        super.update();
        if (!mouseWasPressed&&getProcessing().mousePressed){ // first clicked
            if (isOver(getProcessing().mouseX, getProcessing().mouseY)){
                //System.out.print("1");
                mouseWasPressed = true;
                isDragging = true;
            }
        }else if (!getProcessing().mousePressed&&isDragging){ // release mouse
            //System.out.print("3");
            mouseWasPressed = false;
            isDragging = false;
            return drop();
        }else if(isDragging){ // keep dragging
            //System.out.print("2");
            move(getProcessing().mouseX-oldMouseX, getProcessing().mouseY-oldMouseY);
        }
        oldMouseX=getProcessing().mouseX; // for the next update()
        oldMouseY=getProcessing().mouseY;
        return null;
    }
    protected Action drop() { return null; } // this method returns null.subclass types
    // will override this drop() method to perform more interesting behavior
}
