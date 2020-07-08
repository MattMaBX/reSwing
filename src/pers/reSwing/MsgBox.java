package pers.reSwing;
import javax.swing.*;
import java.awt.*;

public class MsgBox{
    /**
     * BoolBox is a class to create a GUI to show massages.
     * It WILL NOT block process.
     * @author Matt.Ma
     */
    public MsgBox(String massage, String title, String button_text) {
        JFrame frame = new JFrame(title);
        // Elements:
        JPanel Massage = new JPanel();
        Massage.add(new JLabel(massage));
        Massage.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton Button = new JButton(button_text);
            Button.addActionListener(e -> frame.dispose());
        frame.add(Massage,BorderLayout.CENTER);
        frame.add(Button,BorderLayout.SOUTH);
        // Attributes:
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
    }

    public MsgBox(String massage, String title) {
        this(massage, title, "Get");
    }

    public MsgBox(String massage) {
        this(massage, "MsgBox","Get");
    }

    public MsgBox() {
        this("I'm running!", "MsgBox", "Get");
    }
}
