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
 * A class to describe object after dropped.
 * @author Xianfu Luo
 * @version 1.0
 */
public class DroppableObject extends DraggableObject {
    private VisibleObject target; // object over which this object can be dropped
    private Action action; // action that results from dropping this object
    // over target initialize new object
    public DroppableObject(String name, int x, int y, VisibleObject target, Action action) {
        super(name,x,y);
        this.target=target;
        this.action=action;
    }
    @Override
    protected Action drop() { // returns action and deactivates objects in response to successful
        // drop When this object is over its target and its target is active: deactivate both this
        // object and the target object, and return action, otherwise return null
        if (this.isOver(target)&&target.isActive()){
            target.deactivate();
            this.deactivate();
            return action;
        }else {
            return null;
        }
    }
}
