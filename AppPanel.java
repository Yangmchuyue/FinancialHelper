import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AppPanel extends JPanel
{
    private MyMenuBar menuBar;
    private JMenuItem menuItem;
    private KeyStroke keyStroke;
    private MyTable table;
    private JScrollPane tablePane;

    public AppPanel() {
        // Set up Layout and Menu bar of app panel
        this.setLayout(new BorderLayout());
        menuBar = new MyMenuBar();
        this.add(BorderLayout.NORTH, menuBar);

        // Create a default table and scroll pane
        table = new MyTable();
        tablePane = new JScrollPane(table);
        this.add(tablePane, BorderLayout.CENTER);

        // Add menu items with keyboard shortcuts to the File Menu in menu bar
        menuItem = new JMenuItem("New");
        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        menuItem.setAccelerator(keyStroke);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent)
            {
                if (!table.getIsSaved())
                {
                    int confirmExit = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to create new file without saving?",
                            "Unsaved Work!",JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if (confirmExit == JOptionPane.OK_OPTION)
                    {
                        createTable();
                    }
                }
                else
                {
                    createTable();
                }
            }
        });
        menuBar.getFileMenu().add(menuItem);
        menuItem = new JMenuItem("Open...");
        keyStroke = KeyStroke.getKeyStroke( KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        menuItem.setAccelerator(keyStroke);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                // Check if the current table is saved
                if (!table.getIsSaved())
                {
                    // Show a warning for unsaved work
                    int confirmExit = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to open new file without saving?",
                            "Unsaved Work!",JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if (confirmExit == JOptionPane.OK_OPTION)
                    {
                        // Create file chooser.
                        JFileChooser fileChooser = new JFileChooser();
                        int returnValue = fileChooser.showOpenDialog(null);
                        if (returnValue == JFileChooser.APPROVE_OPTION)
                        {
                            // Check if the chosen file is a valid text file
                            if (fileChooser.getSelectedFile().getName().contains(".txt")){
                                createTable(fileChooser.getSelectedFile());
                            }
                            else
                            {
                                // Show message for invalid file chosen
                                confirmExit = JOptionPane.showConfirmDialog(null,
                                        "Sorry, that is not a valid text file. Please choose a different file.",
                                        "Not Valid File", JOptionPane.CANCEL_OPTION,
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                }
                else
                {
                    // Create table with chosen text file.
                    JFileChooser fileChooser = new JFileChooser();
                    int returnValue = fileChooser.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION)
                    {
                        createTable(fileChooser.getSelectedFile());
                    }
                }

            }
        });
        menuBar.getFileMenu().add(menuItem);
        menuItem = new JMenuItem("Save");
        keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        menuItem.setAccelerator(keyStroke);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Save table as a text file
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION)
                {
                    try {
                        PrintWriter out = new PrintWriter(fileChooser.getSelectedFile());
                        InputReaderWriter.saveFileAs(out, table);
                        out.close();
                        table.setIsSaved(true);
                    }
                    catch (FileNotFoundException e) {
                        int confirmExit = JOptionPane.showConfirmDialog(null,
                                "Error has occurred. Unable to Save work.",
                                "Error!",JOptionPane.OK_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        });
        menuBar.getFileMenu().add(menuItem);
        menuBar.getFileMenu().addSeparator();
        menuItem = new JMenuItem("Main Menu");
        menuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event)
                    {
                        // Check if the current table is saved
                        if (!table.getIsSaved())
                        {
                            // Show warning for unsaved work
                            int confirmExit = JOptionPane.showConfirmDialog(null,
                                    "Are you sure you want to leave without saving?",
                                    "Unsaved Work!", JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.WARNING_MESSAGE);
                            if (confirmExit == JOptionPane.OK_OPTION) {
                                // Return to main menu
                                MyFrame.getCardLayout().first(MyFrame.getContainer());
                            }
                        }
                        else
                        {
                            // Return to main menu
                            MyFrame.getCardLayout().first(MyFrame.getContainer());
                        }
                    }
                });
        menuBar.getFileMenu().add(menuItem);

        // Add Menu Items to Edit Menu in menu bar
        menuItem = new JMenuItem("Undo", KeyEvent.VK_Z);
        keyStroke = KeyStroke.getKeyStroke( KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
        menuItem.setAccelerator(keyStroke);
        menuBar.getEditMenu().add(menuItem);

        // Add Menu Items to Calculate Menu in menu bar
        menuItem = new JMenuItem("Graph");
        menuBar.getCalculateMenu().add(menuItem);
        menuItem = new JMenuItem("Calculate Row Sums");
        menuBar.getCalculateMenu().add(menuItem);
    }
    public void createTable(File file) {
        // Remove the current scroll pane and table and add new ones with chosen file
        this.remove(tablePane);
        this.remove(table);
        table = new MyTable(InputReaderWriter.getRowDataValues(file),
                InputReaderWriter.getColumnNames(file));
        tablePane = new JScrollPane(table);
        this.add(tablePane, BorderLayout.CENTER);
        this.revalidate();
    }
    public void createTable() {
        // Remove current scroll pane and table and create new ones with default values
        this.remove(tablePane);
        this.remove(table);
        table = new MyTable();
        tablePane = new JScrollPane(table);
        this.add(tablePane, BorderLayout.CENTER);
        this.revalidate();
    }
}
