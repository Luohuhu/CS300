//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    File Explorer
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
import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A class to output the different running time of different methods.
 * @author Xianfu Luo
 * @version 1.0
 */

public class FileExplorer {

    /**
     * Find all files and directories in the the given folder.
     * @param currentFolder the provided currentFolder.
     * @return a list all files and directories in he the given folder.
     */
    private static ArrayList<File> RecursiveHelper (File currentFolder) {
        ArrayList<File> Contents = new ArrayList<File>();
        File[] fileContents = currentFolder.listFiles();
        for (int i=0; i<fileContents.length; i++){
            if (fileContents[i].isDirectory()){ // recursive case
                Contents.addAll(RecursiveHelper(fileContents[i]));
            }
            Contents.add(fileContents[i]);// base case
        }
        return Contents;
    }

    /**
     * Find all files and directories in the the given folder.
     * @param currentFolder the provided currentFolder.
     * @return a list of the names of all files and directories in
     * the the given folder.
     * @throws NotDirectoryException if the provided currentFolder
     * does not exist or if it is not a directory.
     */
    public static ArrayList<String> listContents (File currentFolder) throws NotDirectoryException {
        ArrayList<String> Contents = new ArrayList<String>();
        if (!currentFolder.exists()){
            // throws NotDirectoryException as the provided currentFolder does not exist
            throw new NotDirectoryException("the provided currentFolder does not exist");
        }
        if (!currentFolder.isDirectory()){
            // throws NotDirectoryException as the provided currentFolder is not a directory
            throw new NotDirectoryException("the provided currentFolder is not a directory");
        }
        File[] fileContents = currentFolder.listFiles();
        for (int i=0; i<fileContents.length; i++){
            Contents.add(fileContents[i].getName());
        }
        return Contents;
    }


    /**
     * Recursive method that lists the names of all the files (not directories).
     * @param currentFolder the provided currentFolder.
     * @return a list of the names of all files in the the given folder.
     * @throws NotDirectoryException if the provided currentFolder
     * does not exist or if it is not a directory.
     */
    public static ArrayList<String> deepListContents (File currentFolder) throws NotDirectoryException {
        if (!currentFolder.exists()){
            // throws NotDirectoryException as the provided currentFolder does not exist
            throw new NotDirectoryException("the provided currentFolder does not exist");
        }
        if (!currentFolder.isDirectory()){
            // throws NotDirectoryException as the provided currentFolder is not a directory
            throw new NotDirectoryException("the provided currentFolder is not a directory");
        }
        ArrayList<File> fileContents = RecursiveHelper(currentFolder);  // get all file in the folder
        ArrayList<String> Contents = new ArrayList<String>();
        for (int i=0; i<fileContents.size(); i++){
            if (!fileContents.get(i).isDirectory()) {
                Contents.add(fileContents.get(i).getName());    // add to the list
            }
        }
        return Contents;
    }


    /**
     * Searches the given folder and all of its subfolders for an exact match
     * to the provided fileName.
     * @param currentFolder the provided currentFolder.
     * @param fileName the name of file you want to search.
     * @throws NoSuchElementException If fileName is null or the provided file
     * is not a directory or does not exist, or if the search operation returns with no results found.
     * @return a path to the file, if it exists.
     * found (including the case if fileName is null or currentFolder does not
     * exist, or was not a directory).
     */
    public static String searchByName (File currentFolder, String fileName) throws NoSuchElementException {
        if (!currentFolder.exists()){
            // throws NoSuchElementException as the provided currentFolder does not exist
            throw new NoSuchElementException("the provided currentFolder does not exist");
        }
        if (!currentFolder.isDirectory()){
            // throws NoSuchElementException as the provided currentFolder is not a directory
            throw new NoSuchElementException("the provided currentFolder is not a directory");
        }
        if (fileName==null){
            throw new NoSuchElementException("the provided fileName is null");
        }
        String path = "File Not Found";
        if (!currentFolder.exists()||!currentFolder.isDirectory()){
            return "";
        }
        ArrayList<File> Contents = RecursiveHelper(currentFolder);
        for (int i=0; i<Contents.size(); i++){  // find filename in all Contents
            if (Contents.get(i).getName().equals(fileName)){    // find the file name
                path=Contents.get(i).getPath();
                break;
            }
        }
        if (path.equals("File Not Found")){
            // throws NoSuchElementException as the search operation returns with no results found
            throw new NoSuchElementException("the search operation returns with no results found");
        }
        return path;
    }

    /**
     * Recursive method that searches the given folder and its subfolders
     * for ALL files that contain the given key in part of their name.
     * @param currentFolder the provided currentFolder.
     * @param key the given key.
     * @return An arraylist of all the names of files that match (empty
     * arraylist for no results found (including the case where currentFolder
     * is not a directory)).
     */
    public static ArrayList<String> searchByKey (File currentFolder, String key) {
        if (key.equals("")){
            // throws NoSuchElementException as the key is null
            throw new NoSuchElementException("the key is null");
        }
        if (!currentFolder.exists()){
            // throws NoSuchElementException as the provided currentFolder does not exist
            throw new NoSuchElementException("the provided currentFolder does not exist");
        }
        if (!currentFolder.isDirectory()){
            // throws NoSuchElementException as the provided currentFolder is not a directory
            throw new NoSuchElementException("the provided currentFolder is not a directory");
        }
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<File> Contents = RecursiveHelper(currentFolder);
        for (int i=0; i<Contents.size(); i++){
            if (Contents.get(i).getName().contains(key)){ // match the key
                result.add(Contents.get(i).getName()); // add to the list
            }
        }
        return result;
    }

    /**
     * Recursive method that searches the given folder and its subfolders for
     * ALL files whose size is within the given max and min values, inclusive.
     * @param currentFolder the provided currentFolder.
     * @param sizeMin the given min values.
     * @param sizeMax the given max values
     * @return An arraylist of all the names of all files whose size are within
     * the boundaries (empty arraylist for no results found (including the case
     * where currentFolder is not a directory)).
     */
    public  static  ArrayList<String> searchBySize (File currentFolder, long sizeMin, long sizeMax ){
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<File> Contents = RecursiveHelper(currentFolder);
        for (int i=0; i<Contents.size(); i++){
            if (Contents.get(i).length()>sizeMin&&Contents.get(i).length()<sizeMax&&!Contents.get(i).isDirectory()){
                //range between sizeMin and sizeMax
                // it can't be a directory
                result.add(Contents.get(i).getName());  // add to the list
            }
        }
        return result;
    }
}
