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
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.NotDirectoryException;
import java.util.NoSuchElementException;

public class FileExplorerTester {

    public static boolean testListContents(File folder) {
        try {
            // Scenario 1
            // list the basic contents of the cs300 folder
            ArrayList<String> listContent = FileExplorer.listContents(folder);
            // expected output content
            String[] contents = new String[] {"grades", "lecture notes", "programs",
                    "quizzes preparation", "reading notes", "syllabus.txt", "todo.txt"};
            List<String> expectedList = Arrays.asList(contents);
            // check the size and the contents of the output
            if (listContent.size() != 7) {
                System.out.println("Problem detected: cs300 folder must contain 7 elements.");
                return false;
            }
            for (int i = 0; i < expectedList.size(); i++) {
                if (!listContent.contains(expectedList.get(i))) {
                    System.out.println("Problem detected: " + expectedList.get(i)
                            + " is missing from the output of the list contents of cs300 folder.");
                    return false;
                }
            }
            // Scenario 2 - list the contents of the grades folder
            File f = new File(folder.getPath() + File.separator + "grades"); listContent = FileExplorer.listContents(f);
            if (listContent.size() != 0) {
                System.out.println("Problem detected: grades folder must be empty.");
                return false;
            }
            // Scenario 3 - list the contents of the p02 folder
            f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
            listContent = FileExplorer.listContents(f);
            if (listContent.size() != 1 || !listContent.contains("WisconsinPrairie.java")) {
                System.out.println("Problem detected: p02 folder must contain only one file named " + "WisconsinPrairie.java.");
                return false;
            }
            // Scenario 4 - Try to list the contents of a file
            f = new File(folder.getPath() + File.separator + "todo.txt"); try {
                listContent = FileExplorer.listContents(f);
                System.out.println("Problem detected: Your FileExplorer.listContents() must "
                        + "throw a NotDirectoryException if it is provided an input which is not"
                        + "a directory."); return false;
            } catch (NotDirectoryException e) { // catch only the expected exception // Expected behavior -- no problem detected
            }
            // Scenario 5 - Try to list the contents of not found directory/file
            f = new File(folder.getPath() + File.separator + "music.txt"); try {
                listContent = FileExplorer.listContents(f);
                System.out.println("Problem detected: Your FileExplorer.listContents() must "
                        + "throw a NotDirectoryException if the provided File does not exist."); return false;
            } catch (NotDirectoryException e) {
            // catch only the expected exception to be thrown -- no problem detected
            }
        } catch (Exception e) {
            System.out.println("Problem detected: Your FileExplorer.listContents() has thrown" + " a non expected exception."); e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean deepListContents(File folder) {
        try {
            // Scenario 1
            // list the basic contents of the programs folder
            File f = new File(folder.getPath() + File.separator + "programs");
            ArrayList<String> listContent = FileExplorer.deepListContents(f);
            // expected output content
            String[] contents = new String[] {"COVIDTracker.java", "COVIDTrackerTester.java", "WisconsinPrairie.java",
                    "Benchmarker.java", "ComparisonMethods.java", "Program01.pdf", "Program02.pdf", "Program03.pdf" };
            List<String> expectedList = Arrays.asList(contents);
            // check the size and the contents of the output
            if (listContent.size() != 8) {
                System.out.println("Problem detected: cs300 folder must contain 8 elements.");
                return false;
            }
            for (int i = 0; i < expectedList.size(); i++) {
                if (!listContent.contains(expectedList.get(i))) {
                    System.out.println("Problem detected: " + expectedList.get(i)
                            + " is missing from the output of the list contents of cs300 folder.");
                    return false;
                }
            }
            // Scenario 2 - list the contents of the grades folder
            f = new File(folder.getPath() + File.separator + "grades");
            listContent = FileExplorer.deepListContents(f);
            if (listContent.size() != 0) {
                System.out.println("Problem detected: grades folder must be empty.");
                return false;
            }
            // Scenario 3 - list the contents of the p02 folder
            f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
            listContent = FileExplorer.deepListContents(f);
            if (listContent.size() != 1 || !listContent.contains("WisconsinPrairie.java")) {
                System.out.println("Problem detected: p02 folder must contain only one file named " + "WisconsinPrairie.java.");
                return false;
            }
            // Scenario 4 - Try to list the contents of a file
            f = new File(folder.getPath() + File.separator + "todo.txt"); try {
                listContent = FileExplorer.deepListContents(f);
                System.out.println("Problem detected: Your FileExplorer.listContents() must "
                        + "throw a NotDirectoryException if it is provided an input which is not"
                        + "a directory."); return false;
            } catch (NotDirectoryException e) { // catch only the expected exception // Expected behavior -- no problem detected
            }
            // Scenario 5 - Try to list the contents of not found directory/file
            f = new File(folder.getPath() + File.separator + "music.txt"); try {
                listContent = FileExplorer.deepListContents(f);
                System.out.println("Problem detected: Your FileExplorer.listContents() must "
                        + "throw a NotDirectoryException if the provided File does not exist."); return false;
            } catch (NotDirectoryException e) {
                // catch only the expected exception to be thrown -- no problem detected
            }
        } catch (Exception e) {
            System.out.println("Problem detected: Your FileExplorer.listContents() has thrown" + " a non expected exception."); e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean testSearchByFileName(File folder) {
        try{
            // Scenario 1
            // find the path of file named zyBooksCh4.txt
            String expectedpath = "cs300/reading notes/zyBooksCh4.txt";
            String path = FileExplorer.searchByName(folder,"zyBooksCh4.txt");
            if (!path.equals(expectedpath)){
                System.out.println("Problem detected: the path of file named zyBooksCh4.txt is wrong");
                return false;
            }// Scenario 2
            // try to find file nonexistent
            try{
                path = FileExplorer.searchByName(folder,"zyBooksCh5.txt");
                System.out.println("Problem detected: Your FileExplorer.searchByName() must "
                        + "throw a NoSuchElementException if it is provided an input which is not"
                        + "exist in the folder."); return false;
            }catch (NoSuchElementException e) { // catch only the expected exception // Expected behavior -- no problem detected
            }
            // Scenario 3
            // when the provided fileName is null
            try{
                path = FileExplorer.searchByName(folder,"");
                System.out.println("Problem detected: Your FileExplorer.searchByName() must "
                        + "throw a NoSuchElementException if the provided fileName is null");
                return false;
            }catch (NoSuchElementException e) { // catch only the expected exception // Expected behavior -- no problem detected
            }
            // Scenario 4
            // try to find file when the provided currentFolder does not exist
            File f = new File(folder.getPath() + File.separator + "music.txt");
            try {
                path = FileExplorer.searchByName(f,"zyBooksCh4.txt");
                System.out.println("Problem detected: Your FileExplorer.searchByName() must "
                        + "throw a NoSuchElementException if the provided File does not exist."); return false;
            } catch (NoSuchElementException e) {
                // catch only the expected exception to be thrown -- no problem detected
            }
            // Scenario 5
            // try to find file when the provided currentFolder is not a directory
            f = new File(folder.getPath() + File.separator + "todo.txt");
            try {
                path = FileExplorer.searchByName(f,"zyBooksCh4.txt");
                System.out.println("Problem detected: Your FileExplorer.searchByName() must "
                        + "throw a NoSuchElementException if the provided File is not a directory."); return false;
            } catch (NoSuchElementException e) {
                // catch only the expected exception to be thrown -- no problem detected
            }

        } catch (Exception e) {
        System.out.println("Problem detected: Your FileExplorer.searchByName() has thrown" + " a non expected exception."); e.printStackTrace();
        return false;
        }
        return true;
    }
    public static boolean testSearchByKeyBaseCase(File folder) {
        try {
            // Scenario 1
            // list the file with the key-'.pdf' of the cs300 folder
            ArrayList<String> ResultList = FileExplorer.searchByKey(folder,".pdf");
            // expected output content
            String[] contents = new String[] {"Program01.pdf", "Program02.pdf", "Program03.pdf"};
            List<String> expectedList = Arrays.asList(contents);
            // check the size and the contents of the output
            if (ResultList.size() != 3) {
                System.out.println("Problem detected: cs300 folder must contain 3 elements with '.pdf'.");
                return false;
            }
            for (int i = 0; i < expectedList.size(); i++) {
                if (!ResultList.contains(expectedList.get(i))) {
                    System.out.println("Problem detected: " + expectedList.get(i)
                            + " is missing from the output of the list files with the key-'.pdf' of the cs300 folder.");
                    return false;
                }
            }
            // Scenario 2
            // Try to find file when no file name in the folder match the key
            ResultList = FileExplorer.searchByKey(folder,".mov");
            if (ResultList.size() !=0 ){
                System.out.println("Problem detected: cs300 folder contain no elements matched '.mov'.");
                return false;
            }
            // Scenario 3
            // Try to find file when no file name in the folder match the key
            try {
                ResultList = FileExplorer.searchByKey(folder,"");
                System.out.println("Problem detected: Your FileExplorer.searchByKey() must "
                        + "throw a NoSuchElementException if the provided key is null."); return false;
            } catch (NoSuchElementException e) {
                // catch only the expected exception to be thrown -- no problem detected
            }
            // Scenario 4
            // try to find file when the provided currentFolder does not exist
            File f = new File(folder.getPath() + File.separator + "music.txt");
            try {
                ResultList = FileExplorer.searchByKey(f,".txt");
                System.out.println("Problem detected: Your FileExplorer.searchByKey() must "
                        + "throw a NoSuchElementException if the provided File does not exist."); return false;
            } catch (NoSuchElementException e) {
                // catch only the expected exception to be thrown -- no problem detected
            }
            // Scenario 5
            // try to find file when the provided currentFolder is not a directory
            f = new File(folder.getPath() + File.separator + "todo.txt");
            try {
                ResultList = FileExplorer.searchByKey(f,".txt");
                System.out.println("Problem detected: Your FileExplorer.searchByKey() must "
                        + "throw a NoSuchElementException if the provided File is not a directory."); return false;
            } catch (NoSuchElementException e) {
                // catch only the expected exception to be thrown -- no problem detected
            }
        } catch (Exception e) {
            System.out.println("Problem detected: Your FileExplorer.searchByKey() has thrown" + " a non expected exception."); e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println("testListContents: " + testListContents(new File("cs300")));
        System.out.println("deepListContents: " + deepListContents(new File("cs300")));
        System.out.println("testSearchByFileName: " + testSearchByFileName(new File("cs300")));
        System.out.println("testSearchByKeyBaseCase: " + testSearchByKeyBaseCase(new File("cs300")));
    }
}
