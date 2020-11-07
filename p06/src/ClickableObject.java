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
 * A class to describe clickable object.
 * @author Xianfu Luo
 * @version 1.0
 */

public class ClickableObject extends VisibleObject {
    private Action action; // action returned from update when this object is clicked
    private boolean mouseWasPressed; // tracks whether the mouse was pressed during the last update()
    // initializes this new object
    public ClickableObject(String name, int x, int y, Action action){
        super(name,x,y);
        this.action=action;
    }
    @Override
    public Action update(){
        // calls VisibleObject update, then returns action
        // only when mouse is first clicked on this object
        super.update();
        if (!mouseWasPressed&&getProcessing().mousePressed){ // first clicked
            mouseWasPressed = true;
            if (isOver(getProcessing().mouseX, getProcessing().mouseY)){
                return action;
            }
        }if (!getProcessing().mousePressed){ // release mouse
            mouseWasPressed = false;
        }
        return null;
    }
}
