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
import processing.core.PApplet;

/**
 * A class to describe interaction.
 * @author Xianfu Luo
 * @version 1.0
 */

public class InteractiveObject {
    private static PApplet processing = null;
    public static void setProcessing(PApplet processing) { // initializes processing field
        InteractiveObject.processing=processing;
    }
    protected static PApplet getProcessing() { // accessor method to retrieve this static field
        return processing;
    }

    private final String NAME; // the constant name identifying this interactive object
    private boolean isActive; // active means this interactive object is visible and
    // can be interacted with
    public InteractiveObject(String name) { // initializes the name of this object, and sets isActive to true
        NAME = name;
        isActive=true;
    }
    public boolean hasName(String name) { // returns true only when contents of name equal NAME
        if (name.equals(NAME)){
            return true;
        }else {
            return false;
        }
    }
    public boolean isActive() { // returns true only when isActive is true
        return isActive;
    }
    public void activate() { // changes isActive to true
        isActive = true;
    }
    public void deactivate() { // changes isActive to false
        isActive = false;
    }
    public Action update() { return null; } // this method returns null
    // subclass types will override this update() method to do more interesting things
}
