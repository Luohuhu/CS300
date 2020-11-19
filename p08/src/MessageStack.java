//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    LIFO Inbox Reader
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
import java.util.EmptyStackException;
import java.util.Iterator;
/**
 * A class to implements Message a Stack and make it Iterable
 * @author Xianfu Luo
 * @version 1.0
 */
public class MessageStack implements StackADT<Message>, Iterable<Message>{
    private LinkedNode <Message> top; // top of the stack
    private int size; // keeps track of the total number of
                        // Message objects stored in the stack
    @Override public void push(Message element) {
        LinkedNode <Message> newData = new LinkedNode(element);
        newData.setNext(top);
        top = newData;
        size++;
    }

    @Override public Message pop() {
        if (top==null){
            throw new EmptyStackException();
        }
        Message element = top.getData();
        top = top.getNext();
        size--;
        return element;
    }

    @Override public Message peek() {
        if (top==null){
            throw new EmptyStackException();
        }
        Message element = top.getData();
        return element;
    }

    @Override public boolean isEmpty() {
        if(top==null){
            return true;
        }else {
            return false;
        }

    }

    @Override public int size() {
        return size;
    }

    @Override public Iterator<Message> iterator() {
        return new MessageStackIterator(top);
    }
}
