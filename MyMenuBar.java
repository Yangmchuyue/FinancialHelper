import javax.swing.*;

public class MyMenuBar extends JMenuBar {
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu calculateMenu;
    public MyMenuBar(){
        // Create a new menu bar with a file menu, edit menu and calculate menu
        fileMenu = new JMenu("File");
        this.add(fileMenu);
        editMenu = new JMenu("Edit");
        this.add(editMenu);
        calculateMenu = new JMenu("Calculate");
        this.add(calculateMenu);

    }
    public JMenu getFileMenu()
    {
        return fileMenu;
    }
    public JMenu getEditMenu()
    {
        return editMenu;
    }
    public JMenu getCalculateMenu()
    {
        return calculateMenu;
    }
}
