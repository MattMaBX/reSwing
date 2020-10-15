package pers.reSwing;
import java.io.*;

/**
 * DisplayBox is a class to create a GUI to display a lot of text from files.
 * By default, it will block the process until the user performs the operation,
 * but you can control it with the "wait" parameter.
 * @author Matt.Ma
 */
public class FileDisplayBox extends DisplayBox{

    private static String fileToString(String fileName) {
        String result;
        File file = new File(fileName);
        if (!file.exists()) {
            result = "ERROR:NO SUCH FILE!";
            return result;
        }
        else {
            try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder buffer = new StringBuilder();
                String s;
                while ((s = reader.readLine()) != null) {
                    buffer.append(s.trim());
                }
                result = buffer.toString();
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                result = "ERROR:CAN NOT OPEN THIS FILE.";
                return result;
            }
        }
    }

    public FileDisplayBox(String source, String title, int rows, int columns, boolean wait) {
        super(fileToString(source),title,rows,columns,wait);
    }
    public FileDisplayBox(String source, String title, int rows, int columns) {
        super(fileToString(source),title,rows,columns,true);
    }
    public FileDisplayBox(String source, String title) {
        super(fileToString(source),title,10,30,true);
    }
    public FileDisplayBox(String source, String title, boolean wait) {
        super(fileToString(source),title,10,30,wait);
    }
    public FileDisplayBox(String source) {
        super(fileToString(source),"FileDisplayBox",10,30,true);
    }
    public FileDisplayBox(String source, boolean wait) {
        super(fileToString(source),"FileDisplayBox",10,30,wait);
    }
    public FileDisplayBox() {
        super("This is a FileDisplayBox.\nIt can display a lot of text from files.","FileDisplayBox",10,30,true);
    }
}