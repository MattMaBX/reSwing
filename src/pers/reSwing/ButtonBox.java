package pers.reSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * ButtonBox is a class to create a GUI with buttons,and return a String.
 * It WILL block process until any operation performed.
 * @author Matt.Ma
 */
public class ButtonBox {

    private final String[] label;
    private final String title;
    private final String massage;
    private String result;
    private final Object waitObject = new Object(); //the symbol of Sync

    private final Thread createGUI = new Thread("create_GUI") {
        @Override
        public void run() {
            JFrame frame = new JFrame(title);
            // Elements:
            JPanel panel1 = new JPanel();
            panel1.add(new JLabel(massage));
            JButton[] Buttons = new JButton[label.length];
            for (int i = 0; i < Buttons.length; i++) {
                Buttons[i] = new JButton(label[i]);
                int I = i;
                Buttons[i].addActionListener(e -> {
                    result = label[I];
                    frame.dispose();
                    synchronized (waitObject) {
                        waitObject.notifyAll();
                    }
                });
            }
            JPanel panel2 = new JPanel();
            panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
            for (JButton button : Buttons) {
                panel2.add(button);
            }
            frame.add(panel1,BorderLayout.CENTER);
            frame.add(panel2,BorderLayout.SOUTH);
            // Attributes:
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.pack();
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    frame.dispose();
                    synchronized (waitObject) {
                        result = "ERROR:GOT NOTHING!";
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
                    Thread.currentThread().interrupt();
                }
            }
        }
    };

    public String getResult() {
        createGUI.start();
        waitValue.start();
        boolean working;
        do {
            working = waitValue.isAlive();
        } while (working);
        return result;
    }

    public ButtonBox(String massage, String[] text, String title) {
        this.title = title;
        this.massage = massage;
        this.label = text;
    }

    public ButtonBox(String massage, String[] text) {
        this.title = "ButtonBox";
        this.massage = massage;
        this.label = text;
    }
    public ButtonBox() {
        this.title = "ButtonBox";
        this.massage = "This is a ButtonBox.";
        this.label = new String[]{"Button1","Button2","Button3"};
    }
}
