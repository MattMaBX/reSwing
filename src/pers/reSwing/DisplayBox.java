package pers.reSwing;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;

/**
 * DisplayBox is a class to create a GUI to display a lot of text.
 * By default, it will block the process until the user performs the operation,
 * but you can control it with the "wait" parameter.
 * @author Matt.Ma
 */
public class DisplayBox {

    private final String source;
    private final String title;
    private final int rows;
    private final int columns;
    private final boolean wait;
    private final Object waitObject = new Object();

    private final Thread CreateGUI = new Thread("Create_GUI") {
        @Override
        public void run() {
            JFrame frame = new JFrame(title);
            // Elements:
            JTextArea text = new JTextArea(source,rows,columns);
            text.setLineWrap(true);
            text.setEditable(false);
            JButton OK = new JButton("OK");
            OK.addActionListener(e -> {
                frame.dispose();
                synchronized (waitObject) {
                    waitObject.notifyAll();
                }
            });
            frame.add(text,BorderLayout.CENTER);
            frame.add(OK,BorderLayout.SOUTH);
            // Attributes:
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.pack();
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    frame.dispose();
                    synchronized (waitObject) {
                        waitObject.notifyAll();
                    }
                }
            });
        }
    };

    private final Thread waitValue = new Thread("wait_value") {
        @Override
        public void run() {
            synchronized (waitObject) {
                try {
                    waitObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void show() {
        CreateGUI.start();
        if (wait) waitValue.start();
        boolean done;
        do {
            done = waitValue.isAlive();
        } while (done);
    }

    public DisplayBox(String source, String title, int rows, int columns, boolean wait) {
        this.source = source;
        this.title = title;
        this.rows = rows;
        this.columns = columns;
        this.wait = wait;
    }

    public DisplayBox(String source, String title, int rows, int columns) {
        this.source = source;
        this.title = title;
        this.rows = rows;
        this.columns = columns;
        this.wait = true;
    }

    public DisplayBox(String source, String title) {
        this.source = source;
        this.title = title;
        this.rows = 10;
        this.columns = 30;
        this.wait = true;
    }

    public DisplayBox(String source, String title, boolean wait) {
        this.source = source;
        this.title = title;
        this.rows = 10;
        this.columns = 30;
        this.wait = wait;
    }

    public DisplayBox(String source) {
        this.source = source;
        this.title = "DisplayBox";
        this.rows = 10;
        this.columns = 30;
        this.wait = true;
    }

    public DisplayBox(String source, boolean wait) {
        this.source = source;
        this.title = "DisplayBox";
        this.rows = 10;
        this.columns = 30;
        this.wait = wait;
    }

    public DisplayBox() {
        this.source = "This is a DisplayBox.\nIt can display a lot of text.\nFirst: ...\nSecond: ...\nThird: ...";
        this.title = "DisplayBox";
        this.rows = 10;
        this.columns = 30;
        this.wait = true;
    }
}
