package pers.reSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * BoolBox is a class to create a GUI to show massages.
 * By default, it will block the process until the user performs the operation,
 * but you can control it with the "wait" parameter.
 * @author Matt.Ma
 */
public class MsgBox{

    private final Object waitObject = new Object();

    public MsgBox(String massage, String title, String button_text, boolean wait) {
        Thread createGUI = new Thread("Create_GUI") {
            @Override
            public void run() {
                Frame frame = new JFrame(title);
                // Elements:
                JPanel Massage = new JPanel();
                Massage.add(new JLabel(massage));
                Massage.setLayout(new FlowLayout(FlowLayout.CENTER));
                JButton Button = new JButton(button_text);
                Button.addActionListener(e -> {
                    frame.dispose();
                    synchronized (waitObject) {
                        waitObject.notifyAll();
                    }
                });
                frame.add(Massage, BorderLayout.CENTER);
                frame.add(Button, BorderLayout.SOUTH);
                // Attributes:
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frame.dispose();
                        synchronized (waitObject) {
                            waitObject.notifyAll();
                        }
                    }
                });
                frame.pack();
            }
        };
        createGUI.start();
        Thread waitValue = new Thread("wait_value") {
            @Override
            public void run() {
                synchronized (waitObject) {
                    try {
                        waitObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
        if (wait) waitValue.start();
        boolean working;
        do {
            working = waitValue.isAlive();
        } while (working);
    }

    public MsgBox(String massage, String title) {
        this(massage, title,"Get",false);
    }

    public MsgBox(String massage, String title,boolean wait) {
        this(massage, title,"Get",wait);
    }

    public MsgBox(String massage) {
        this(massage, "MsgBox","Get",false);
    }

    public MsgBox(String massage, boolean wait) {
        this(massage, "MsgBox","Get",wait);
    }

    public MsgBox() {
        this("I'm running!", "MsgBox", "Get",false);
    }
}
