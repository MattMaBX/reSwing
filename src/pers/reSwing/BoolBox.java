package pers.reSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * BoolBox is a class to create a GUI with Yes/No buttons,and return a boolean.
 * It WILL block process until any operation performed.
 * @author Matt.Ma
 */
public class BoolBox {

    private boolean result;
    private final String title, massage, yes_text, no_text;
    final Object waitObject = new Object(); //the symbol of Sync

    private final Thread createGUI = new Thread("create_GUI") {
        @Override
        public void run() {
            JFrame frame = new JFrame(title);
            // Elements:
            JPanel Massage = new JPanel();
            Massage.add(new JLabel(massage));
            Massage.setLayout(new FlowLayout(FlowLayout.CENTER));
            JButton Yes = new JButton(yes_text);
            Yes.addActionListener(e -> {
                result = true;
                frame.dispose();
                synchronized (waitObject) {
                    waitObject.notifyAll();
                }
            });
            JButton No = new JButton(no_text);
            No.addActionListener(e -> {
                result = false;
                frame.dispose();
                synchronized (waitObject) {
                    waitObject.notifyAll();
                }
            });
            JPanel Buttons = new JPanel();
            Buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
            Buttons.add(Yes); Buttons.add(No);
            frame.add(Massage,BorderLayout.CENTER);
            frame.add(Buttons,BorderLayout.SOUTH);
            // Attributes:
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.pack();
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    frame.dispose();
                    synchronized (waitObject) {
                        result = false;
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

    public boolean getResult() {
        createGUI.start();
        waitValue.start();
        boolean working;
        do {
            working = waitValue.isAlive();
        } while (working);
        return this.result;
    }

    public BoolBox(String massage, String title, String yes_text, String no_text) {
        this.title = title;
        this.massage = massage;
        this.yes_text = yes_text;
        this.no_text = no_text;
    }

    public BoolBox(String massage, String title) {
        this.title = title;
        this.massage = massage;
        this.yes_text = "Yes";
        this.no_text = "No";
    }

    public BoolBox(String massage) {
        this.title = "Sure?";
        this.massage = massage;
        this.yes_text = "Yes";
        this.no_text = "No";
    }

    public BoolBox() {
        this.title = "Sure?";
        this.massage = "Shell I continue?";
        this.yes_text = "Yes";
        this.no_text = "No";
    }
}
