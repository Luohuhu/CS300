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
/**
 * A class to implements Inbox
 * @author Xianfu Luo
 * @version 1.0
 */
public class Inbox {
    private MessageStack readMessageBox; // stack which stores read messages
    private MessageStack unreadMessageBox; // stack which stores unread messages
    public Inbox() {
        // This no-argument constructor creates a new empty inbox
        // and initializes its instance fields.
        // Both unreadMessageBox and readMessageBox stacks of this
        // inbox must be initially empty.
        readMessageBox = new MessageStack();
        unreadMessageBox = new MessageStack();
    }
    public String readMessage() {
        // Reads the message at the top of the unreadMessageBox.
        // Once read, the message must be moved from the unreadMessageBox
        // to the readMessageBox.
        // This method returns the string representation of the message at
        // the top of the unreadMessageBox, or "Nothing in Unread"
        // if the unreadMessageBox of this inbox is empty.
        if (unreadMessageBox.isEmpty()){
            return "Nothing in Unread";
        }else {
            Message newMessage = unreadMessageBox.pop();
            readMessageBox.push(newMessage);
            return newMessage.toString();
        }
    }
    public String peekReadMessage() {
        // Returns the string representation of the message at the top of the readMessageBox.
        // This method returns the string representation of the message at the top
        // readMessageBox and "Nothing in Read" if the readMessageBox is empty.
        if (readMessageBox.isEmpty()){
            return "Nothing in Unread";
        }else{
            Message newMessage = readMessageBox.peek();
            return newMessage.toString();
        }
    }
    public int markAllMessagesAsRead() {
        // Marks all messages in the unread message box as read.
        // The unread message box must be empty after this method returns.
        // Every message marked read must be moved to the read messages box.
        // This method returns the total number of messages marked as read.
        int readNum = 0;
        while (!unreadMessageBox.isEmpty()){
            Message newMessage = unreadMessageBox.pop();
            readMessageBox.push(newMessage);
            readNum++;
        }
        return readNum;
    }
    public void receiveMessage(Message newMessage) {
        // Pushes a newMessage into the unread message box
        // newMessage represents a reference to the received message
        // Note that this method can be invoked each time a new message
        // will be received and pushed to the unreadMessageBox.
        unreadMessageBox.push(newMessage);
    }
    public int emptyReadMessageBox() {
        // Removes permanently all the messages from the readMessageBox
        // This method returns the total number of the removed messages
        int readNum = 0;
        while (!readMessageBox.isEmpty()){
            Message newMessage = readMessageBox.pop();
            readNum++;
        }
        return readNum;
    }
    public String getStatistics() {
        // Gets the statistics of this inbox
        // Returns a String formatted as follows:
        // "Unread (size1)" + "\n" + "Read (size2)",
        // where size1 and size2 represent the number of unread and read
        // messages respectively.
        return "Unread ("+unreadMessageBox.size()+")\nRead ("+readMessageBox.size()+")";
    }
    public String traverseUnreadMessages() {
        // Traverses all the unread messages and return a list of their
        // ID + " " + SUBJECT, as a string. Every string representation of a
        // message is provided in a new line.
        // This method returns a String representation of the contents of
        // the unread message box.
        // The returned output has the following format:
        // Unread(unreadMessageBox_size)\n + list of the messages in
        // unreadMessageBox (ID + " " + SUBJECT) each in a line.
        String result = "Unread("+unreadMessageBox.size()+")\n";
        for (Message iterator: unreadMessageBox){
            result += iterator.getID()+" "+iterator.getSUBJECT()+"\n";
        }
        return result;
    }

    public String traverseReadMessages() {
        // Traverses all the read messages and return a list of their string
        // representations, ID + " " + SUBJECT, each per new line, as a string
        // This method returns a String representation of the contents of
        // the read message box
        // The returned output has the following format:
        // Read(readMessageBox_size)\n + list of the messages in
        // readMessageBox (ID + " " + SUBJECT) each in a line.
        String result = "Read("+readMessageBox.size()+")\n";
        for (Message iterator: readMessageBox){
            result += iterator.getID()+" "+iterator.getSUBJECT()+"\n";
        }
        return result;
    }
}
