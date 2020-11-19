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
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * A class to implements MessageStackIterator
 * @author Xianfu Luo
 * @version 1.0
 */
public class MessageStackIterator implements Iterator<Message> {
    private LinkedNode <Message> Next;
    //private LinkedNode <Message> top;
    public MessageStackIterator(LinkedNode <Message> message){
        Next = message;
    }
    @Override public boolean hasNext() {
        return Next!=null;
    }

    @Override public Message next() throws NoSuchElementException {
        if (!this.hasNext()){
            throw new NoSuchElementException("The stack is exhausted");
        }
        Message current = Next.getData();
        Next = Next.getNext();
        return current;
    }
}
