package pers.reSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * TextBox is a class to create a GUI with multi-textfield,and return a String[].
 * It WILL block process until any operation performed.
 * @author Matt.Ma
 */
public class TextBox {

    private String[] Text;
    private final String title;
    private final String[] label;
    final Object waitObject = new Object(); //the symbol of Sync

    private final Thread createGUI = new Thread("create_GUI") {
        @Override
        public void run() {
            JFrame frame = new JFrame(title);
            // Elements:
            int number = label.length;
            Text = new String[number];
            JTextField[] textFields = new JTextField[number];
            JPanel[] panels = new JPanel[number];
            for (int i = 0; i < number; i++) {
                textFields[i] = new JTextField(25);
                panels[i] = new JPanel();
                panels[i].add(new JLabel(label[i]));
                panels[i].add(textFields[i]);
                panels[i].setLayout(new FlowLayout(FlowLayout.CENTER));
                frame.add(panels[i]);
            }

            JPanel Buttons = new JPanel();
            Buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
            JButton submit = new JButton("Submit");
            submit.addActionListener(e -> {
                for (int i = 0; i < number; i++) {
                    Text[i] = textFields[i].getText();
                }
                frame.dispose();
                synchronized (waitObject) {
                    waitObject.notifyAll();
                }
            });
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(e -> {
                frame.dispose();
                synchronized (waitObject) {
                    for (int i = 0; i < number; i++) {
                        Text[i] = "ERROR:GOT NOTHING!";
                    }
                    waitObject.notifyAll();
                }
            });
            Buttons.add(submit);
            Buttons.add(cancel);
            frame.add(Buttons, BorderLayout.SOUTH);
            // Attributes:
            frame.setLocationRelativeTo(null);
            frame.setSize(350, (80 + 35 * number));
            frame.setLayout(new FlowLayout(FlowLayout.CENTER));
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    frame.dispose();
                    synchronized (waitObject) {
                        for (int i = 0; i < number; i++) {
                            Text[i] = "ERROR:GOT NOTHING!";
                        }
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
        createGUI.start();
        waitValue.start();
        boolean working;
            do {
                working = waitValue.isAlive();
            } while (working);
        return this.Text;
    }

    public TextBox(String[] Label, String Title) {
        this.title = Title;
        this.label = Label;
    }

    public TextBox(String[] Label) {
        this.title = "TextBox";
        this.label = Label;
    }

    public TextBox() {
        this.title = "TextBox";
        this.label = new String[] {"label:"};
    }
}
