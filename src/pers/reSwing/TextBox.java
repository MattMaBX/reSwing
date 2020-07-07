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
    final Object waitObject = new Object();

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
                //System.out.println("解锁");
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
                //System.out.println("解锁");
            });
            Buttons.add(submit);
            Buttons.add(cancel);
            // Attributes:
            frame.setLocationRelativeTo(null);
            frame.add(Buttons, BorderLayout.SOUTH);
            frame.setSize(350, (80 + 35 * number));
            frame.setLayout(new FlowLayout(FlowLayout.CENTER));
            //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

    public String[] getText() {
        createGUI.start();
        waitValue.start();
        boolean done;
        //System.out.println(waitValue.isAlive());
            do {
                done = waitValue.isAlive();
            } while (done);
        return this.Text;
    }

    public TextBox(String Title, String[] Label) {
        this.title = Title;
        this.label = Label;
    }

    public TextBox() {
        this.title = "TextBox";
        this.label = new String[] {"label:"};
    }
}
