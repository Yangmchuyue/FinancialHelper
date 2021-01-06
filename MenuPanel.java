import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel implements ActionListener
{
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 30;
    private JButton startButton;
    private JButton exitButton;
    public MenuPanel() {

        // Add Buttons to main menu Panel
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        startButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.add(startButton);

        // Add exit button to main menu panel
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        exitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.add(exitButton);

    }

    public void actionPerformed(ActionEvent actionEvent) {
        // Exit program if the exit button is pressed and go to app panel if start button is pressed
        if (actionEvent.getSource() == exitButton)
        {
            System.exit(0);
        }
        else
        {
            MyFrame.getCardLayout().next(MyFrame.getContainer());
        }
    }
}
