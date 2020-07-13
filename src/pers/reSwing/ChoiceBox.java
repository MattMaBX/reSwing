package pers.reSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * ChoiceBox is a class to create a GUI with drop-down menu,and return a String.
 * It WILL block process until any operation performed.
 * @author Matt.Ma
 */
public class ChoiceBox {
    private String result;
    private final String title;
    private final String label;
    private final String[] items;
    private final Object waitObject = new Object(); //the symbol of Sync

    private final Thread CreateGUI = new Thread("Create_GUI") {
        @Override
        public void run() {
            JFrame frame = new JFrame(title);
            // Elements:
            JPanel panel = new JPanel();
            JComboBox<String> choices = new JComboBox<>(items);
            choices.addItemListener(e -> result = items[choices.getSelectedIndex()]);
            panel.add(new JLabel(label));
            panel.add(choices);
            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            JButton button = new JButton("OK");
            button.addActionListener(e -> {
                frame.dispose();
                synchronized (waitObject) {
                    waitObject.notifyAll();
                }
            });
            frame.add(panel,BorderLayout.CENTER);
            frame.add(button,BorderLayout.SOUTH);
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
                    result = "ERROR:GOT NOTHING!";
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

    public String getResult() {
        CreateGUI.start();
        waitValue.start();
        boolean working;
        do {
            working = waitValue.isAlive();
        } while (working);
        return this.result;
    }

    public ChoiceBox(String label, String[] items, String title) {
        this.label = label;
        this.items = items;
        this.title = title;
    }

    public ChoiceBox(String label, String[] items) {
        this.label = label;
        this.items = items;
        this.title = "ChoiceBox";
    }

    public ChoiceBox() {
        this.label = "choices";
        this.items = new String[] {"choice1","choice2","choice3","..."};
        this.title = "ChoiceBox";
    }
}
