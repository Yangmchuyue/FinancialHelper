import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame
{
    private static CardLayout cardLayout;
    private static Container flipPane;
    private JPanel menuPanel;
    private JPanel appPanel;
    public MyFrame() {
        // Set up CardLayout with a main menu panel and app panel
        cardLayout = new CardLayout();
        flipPane = this.getContentPane();
        flipPane.setLayout(cardLayout);
        menuPanel = new MenuPanel();
        appPanel = new AppPanel();
        flipPane.add("Menu", menuPanel);
        flipPane.add("Application", appPanel);
    }
    public static CardLayout getCardLayout() { return cardLayout; }
    public static Container getContainer()
    {
        return flipPane;
    }
}
