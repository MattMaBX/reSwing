package pers.reSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * PasswordBox is a class to create a GUI with a password box,and return a String[].
 * It WILL block process until any operation performed.
 * @author Matt.Ma
 */
public class PasswordBox {

    private String password;
    private String text;
    private final String title;
    private final String password_label;
    private final String text_label;
    private final Object waitObject = new Object(); //the symbol of Sync

    private final Thread CreateGUI = new Thread("Create_GUI") {
        @Override
        public void run() {
            JFrame frame = new JFrame(title);
            // Elements:
            JPanel totalPanel = new JPanel();
            JPanel textPanel = new JPanel();
            JTextField Text = new JTextField(25);
            textPanel.add(new JLabel(text_label));
            textPanel.add(Text);
            JPanel passwordPanel = new JPanel();
            JPasswordField Password = new JPasswordField(25);
            passwordPanel.add(new JLabel(password_label));
            passwordPanel.add(Password);
            frame.add(textPanel);
            frame.add(passwordPanel);
            totalPanel.add(textPanel);
            totalPanel.add(passwordPanel);
            JPanel buttons = new JPanel();
            JButton OK = new JButton("OK");
            OK.addActionListener(e -> {
                text = Text.getText();
                password = new String(Password.getPassword());
                frame.dispose();
                synchronized (waitObject) {
                    waitObject.notifyAll();
                }
            });
            JButton Cancel = new JButton("Cancel");
            Cancel.addActionListener(e -> {
                text = "ERROR:GOT NOTHING!";
                password = "ERROR:GOT NOTHING!";
                frame.dispose();
                synchronized (waitObject) {
                    waitObject.notifyAll();
                }
            });
            buttons.add(OK);
            buttons.add(Cancel);
            frame.add(totalPanel,BorderLayout.CENTER);
            frame.add(buttons,BorderLayout.SOUTH);
            // Attributes:
            frame.setSize(420,150);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    text = "ERROR:GOT NOTHING!";
                    password = "ERROR:GOT NOTHING!";
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

    public String[] getResult() {
        CreateGUI.start();
        waitValue.start();
        boolean working;
        do {
            working = waitValue.isAlive();
        } while (working);
        return (new String[]{this.text,this.password});
    }

    public PasswordBox(String text_label, String password_label, String title) {
        this.text_label = text_label;
        this.password_label = password_label;
        this.title = title;
    }

    public PasswordBox(String text_label, String password_label) {
        this.text_label = text_label;
        this.password_label = password_label;
        this.title = "PasswordBox";
    }

    public PasswordBox() {
        this.text_label = "User Name";
        this.password_label = "Password";
        this.title = "PasswordBox";
    }
}
