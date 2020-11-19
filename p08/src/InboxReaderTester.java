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
import java.util.NoSuchElementException;

/**
 * This class implements unit test methods to check the correctness of the implementation of the
 * MessageStack, Inbox, and MessageStackIterator classes defined in the CS300 Fall 2020 - P08 LIFO
 * Inbox Reader programming assignment.
 *
 */
public class InboxReaderTester {

    /**
     * Calls your tester methods
     *
     * @param args input arguments if any
     */
    public static void main(String[] args) {
        System.out.println(runInboxReaderTestSuite());
    }

    // add the runInboxReaderTestSuite() method and your additional tester methods
    public static boolean runInboxReaderTestSuite(){
        try {
            if(!testStackConstructorIsEmptyPushPeek()){
                System.out.println("testStackConstructorIsEmptyPushPeek() fails");
                return false;
            }
            if(!testStackPop()){
                System.out.println("testStackPop() fails");
                return false;
            }
            if(!testInboxReceiveMessage()){
                System.out.println("testInboxReceiveMessage() fails");
                return false;
            }
            if(!testInboxReadMessage()){
                System.out.println("testInboxReadMessage() fails");
                return false;
            }
            if(!testInboxMarkAllMessagesAsRead()){
                System.out.println("testInboxMarkAllMessagesAsRead() fails");
                return false;
            }
            if(!testMessageStackIterator()){
                System.out.println("testMessageStackIterator() fails");
                return false;
            }
        }catch (Exception e){
            System.out.println("unexpected exception");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * Checks for the correctness of the constructor of the MessageStack, MessageStack.push(),
     * MessageStack.peek(), MessageStack.isEmpty(), and MessageStack.size() methods. This method must
     * consider at least the test scenarios provided in the detailed description of these javadocs.
     * (1) Create a new MessageStack object. The new created stack must be empty and its size must be
     * zero. (2) You can consider calling peek method on the empty stack. An EmptyStackException is
     * expected to be thrown by the peek method call. (3) Then, push a Message onto the stack and then
     * use peek to verify that the correct item is at the top of the stack. Make sure also to check
     * that isEmpty() must return false and the size of the stack is one. The peek() method call
     * should not make any change to the contents of the stack. (4) You can further consider pushing
     * other elements onto the stack. Then, each call of peek() method should return the correct
     * Message object that should be at the top of the stack. After pushing a new message to the stack
     * double check that the size of the stack was incremented by one.
     *
     * @return true when this test verifies a correct functionality, and false otherwise.
     */
    public static boolean testStackConstructorIsEmptyPushPeek() {
        try{
            MessageStack messageSrack = new MessageStack();
            if (!messageSrack.isEmpty()){ // isEmpty
                System.out.println("New MessageStack should be empty");
                return false;
            }
            if (messageSrack.size()!=0){ // Size = 0
                System.out.println("New MessageStack's size should be 0");
                return false;
            }
            try{
                Message test = messageSrack.peek();
                System.out.println("Peek method on the empty stack should throw an "
                    + "EmptyStackException ");
                return false;
            }catch (EmptyStackException e){
                // catch only the expected exception to be thrown -- no problem detected
            }
            Message test1 = new Message("Food", "I like apple");
            messageSrack.push(test1);
            if (messageSrack.isEmpty()){ // isEmpty
                System.out.println("MessageStack should not be empty");
                return false;
            }
            if (messageSrack.size()!=1){
                System.out.println("MessageStack's size should be 1");
                return false;
            }Message test = messageSrack.peek();
            if (!test1.equals(test)){
                System.out.println("MessageStack's top is not correct");
                return false;
            }Message test2 = new Message("Food", "I like banana");
            messageSrack.push(test2);
            if (messageSrack.isEmpty()){ // isEmpty
                System.out.println("MessageStack should not be empty");
                return false;
            }
            if (messageSrack.size()!=2){
                System.out.println("MessageStack's size should be 2");
                return false;
            }test = messageSrack.peek();
            if (!test.equals(test2)){
                System.out.println("MessageStack's top is not correct");
                return false;
            }
        }catch (Exception e){
            System.out.println("Problem detected: Your StackConstructorIsEmptyPushPeek() has thrown"
                + " a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    } //


    /**
     * Checks for the correctness of MessageStack.pop(). It calls MessageStack.push() and
     * MessageStack.peek() methods. This method must consider at least the test scenarios provided in
     * the detailed description of these javadocs. (1) Try to create a new empty MessageStack. Then,
     * try calling pop method on the empty stack. An EmptyStackException is expected to be thrown as a
     * result. (2) Try to push a message to the stack. Then, try to call the pop method on the stack
     * which contains only one element. Make sure that the pop() operation returned the expected
     * message, and that the stack is empty and its size is zero. (3) Then, try to push at least three
     * messages, then call pop method on the stack which contains 3 elements, the element at the top
     * of the stack must be returned and removed from the stack and its size must be decremented by
     * one. You can further keep popping the elements of the stack one by one until it becomes empty
     * and check each time that the pop operation performs appropriately.This test method must return
     * false if it detects at least one defect.
     *
     * @return true when this test verifies a correct functionality, and false otherwise.
     */
    public static boolean testStackPop() {
        try{
            MessageStack messageSrack = new MessageStack();
            try{
                messageSrack.pop();
                System.out.println("Pop method on the empty stack should throw an "
                    + "EmptyStackException ");
                return false;
            }catch (EmptyStackException e){
                // catch only the expected exception to be thrown -- no problem detected
            }
            Message test1 = new Message("Food", "I like apple");
            messageSrack.push(test1);
            Message test = messageSrack.pop();
            if (!test.equals(test1)){
                System.out.println("MessageStack pop()'s return is not correct");
                return false;
            }
            if (messageSrack.size()!=0){
                System.out.println("MessageStack's size decremented by 1");
                return false;
            }
            if (!messageSrack.isEmpty()){
                System.out.println("MessageStack after pop() return empty");
                return false;
            }
            Message test2 = new Message("Food", "I like banana");
            Message test3 = new Message("Food", "I like peach");
            messageSrack.push(test1);messageSrack.push(test2);messageSrack.push(test3);
            test = messageSrack.pop();
            if (!test.equals(test3)){
                System.out.println("MessageStack pop()'s return is not correct");
                return false;
            }
            if (messageSrack.size()!=2){
                System.out.println("MessageStack's size decremented by 1 to 2");
                return false;
            }
        }catch (Exception e){
            System.out.println("Problem detected: Your testStackPop() has thrown"
                + " a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Checks for the correctness of the Inbox.ReadMessage() method.
     *
     * @return true when this test verifies a correct functionality, and false otherwise.
     */
    public static boolean testInboxReadMessage() {
        // Define your own test scenarios to check the correctness of Inbox.ReadMessage()
        // Your test method must return false if it detects at least one defect
        // Vary your test scenarios. Make sure to consider at least two test scenarios:
        // (1) when Inbox.unreadMessageBox is empty
        // (2) when Inbox.unreadMessageBox is not empty. You have to make sure the read message
        // has been popped off the Inbox.unreadMessageBox and pushed into the Inbox.readMessageBox
        // You can rely on Inbox.peekReadMessage() and Inbox.getStatistics() to check the method
        // behavior was as expected.
        try{
            Inbox box = new Inbox();
            String readMessage = box.readMessage();
            String statisticsMessage = box.getStatistics();
            if (!readMessage.equals("Nothing in Unread")){
                System.out.println("Nothing in Unread should be readMessaged when unreadMessage "
                    + "is empty");
                return false;
            }
            if (!statisticsMessage.equals("Unread (0)\nRead (0)")){
                System.out.println("Both unread and read's sizes are 0 when unreadMessage "
                    + "is empty");
                return false;
            }
            Message test1 = new Message("Food", "I like apple");
            box.receiveMessage(test1);
            readMessage = box.readMessage();
            statisticsMessage = box.getStatistics();
            if (!readMessage.equals("[9] Food: I like apple")){
                System.out.println("readMessage() is incorrect in readMessage()");
                return false;
            }if (!statisticsMessage.equals("Unread (0)\nRead (1)")){
                System.out.println("unread's size should be 0 and read's sizes should be 1");
                return false;
            }
            readMessage = box.peekReadMessage();
            if (!readMessage.equals("[9] Food: I like apple")){
                System.out.println("readMessage() is incorrect in peekReadMessage()");
                return false;
            }

        }catch (Exception e){
            System.out.println("Problem detected: Your testInboxReadMessage() has thrown"
                + " a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * Checks for the correctness of the Inbox.ReceiveMessage() method
     *
     * @return true when this test verifies a correct functionality, and false otherwise.
     */
    public static boolean testInboxReceiveMessage() {
        // Define your own test scenarios to check the correctness of Inbox.receiveMessage()
        // Your test method must return false if it detects at least one defect
        try{
            Inbox box = new Inbox();
            Message test1 = new Message("Food", "I like apple");
            box.receiveMessage(test1);
            String statisticsMessage = box.getStatistics();
            if (!statisticsMessage.equals("Unread (1)\nRead (0)")){
                System.out.println("unread's size should be 1 and read's sizes should be 0");
                return false;
            }
            Message test2 = new Message("Food", "I like banana");
            Message test3 = new Message("Food", "I like peach");
            box.receiveMessage(test2);box.receiveMessage(test3);
            statisticsMessage = box.getStatistics();
            if (!statisticsMessage.equals("Unread (3)\nRead (0)")){
                System.out.println("unread's size should be 3 and read's sizes should be 0");
                return false;
            }
        }catch (Exception e){
            System.out.println("Problem detected: Your testInboxReceiveMessage() has thrown"
                + " a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Checks for the correctness of the Inbox.markAllMessagesAsRead() method
     *
     * @return true when this test verifies a correct functionality, and false otherwise.
     */
    public static boolean testInboxMarkAllMessagesAsRead() {
        // Define your own test scenarios to check the correctness of Inbox.markAllMessagesAsRead()
        // Your test method must return false if it detects at least one defect
        try{
            Inbox box = new Inbox();
            Message test1 = new Message("Food", "I like apple");
            Message test2 = new Message("Food", "I like banana");
            Message test3 = new Message("Food", "I like peach");
            box.receiveMessage(test1);box.receiveMessage(test2);box.receiveMessage(test3);
            int num = box.markAllMessagesAsRead();
            if (num!=3){
                System.out.println("Mark number should be 3");
                return false;
            }
            String statisticsMessage = box.getStatistics();
            if (!statisticsMessage.equals("Unread (0)\nRead (3)")){
                System.out.println("unread's size should be 0 and read's sizes should be 3");
                return false;
            }
        }catch (Exception e){
            System.out.println("Problem detected: Your testInboxMarkAllMessagesAsRead() has thrown"
                + " a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Checks for the correctness of MessageStackIterator.hasNext() and MessageStackIterator.next()
     * methods. You can rely on the constructor of the LinkedNode class which takes two input
     * parameters (setting both data and next instance fields) to create a chain of linked nodes (at
     * least 3 linked nodes) which carry messages as data fields. Then, create a new
     * MessageStackIterator() and pass it the head of the chain of linked nodes that you created. We
     * recommend that you consider at least the following test scenarios in this tester method. (1)
     * Try to call next() on the iterator. The first call of next() must return the message at the
     * head of your chain of linked nodes. (2) Try to call hasNext() on your iterator, it must return
     * true. (3) The second call of next() must return the message which has been initially at index 1
     * of your chain of linked nodes. (4) The third call of next() on your iterator must return the
     * message initially at index 2 of your chain of linked nodes. (4) If you defined a chain of 3
     * linked nodes in this scenario, hasNext() should return false, and the fourth call of next() on
     * the iterator must throw a NoSuchElementException.
     *
     * @return true when this test verifies a correct functionality, and false otherwise.
     */
    public static boolean testMessageStackIterator() {
        try{
            Message test1 = new Message("Food", "I like apple");
            Message test2 = new Message("Food", "I like banana");
            Message test3 = new Message("Food", "I like peach");
            LinkedNode<Message> testLinkedNode1 = new LinkedNode<Message>(test3);
            LinkedNode<Message> testLinkedNode2 = new LinkedNode<Message>(test2,testLinkedNode1);
            LinkedNode<Message> testLinkedNode3 = new LinkedNode<Message>(test1,testLinkedNode2);
            MessageStackIterator test = new MessageStackIterator(testLinkedNode3);
            Message next = test.next();
            if (!next.equals(test1)){
                System.out.println("he first call of next() must return the message at the head");
                return false;
            }
            if (!test.hasNext()){
                System.out.println("must return true");
                return false;
            }
            next = test.next();
            if (!next.equals(test2)){
                System.out.println("he first call of next() must return the message at index 1");
                return false;
            }
            next = test.next();
            if (!next.equals(test3)){
                System.out.println("he first call of next() must return the message at index 2");
                return false;
            }
            if (test.hasNext()){
                System.out.println("must return false");
                return false;
            }
            try{
                next = test.next();
                System.out.println("the iterator must throw a NoSuchElementException");
                return false;
            }catch (NoSuchElementException e){

            }
        }catch (Exception e){
            System.out.println("Problem detected: Your testMessageStackIterator() has thrown"
                + " a non expected exception.");
            e.printStackTrace();
            return false;
        }
        return true;
    }



}
