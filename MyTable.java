import javax.swing.*;

public class MyTable extends JTable {
    private static final String[] DEFAULT_TITLES = {"A", "B", "C"};
    private static final String[][] DEFAULT_ROW_DATA = {{"1", "2", "3"}};
    private boolean isSaved;
    public MyTable() {
        // Create a new table with default values
        super(DEFAULT_ROW_DATA, DEFAULT_TITLES);
        isSaved = false;
    }
    public MyTable(String[][] rowData, String[] columnTitles){
        // Create table with given values
        super(rowData, columnTitles);
        isSaved = false;
    }
    public boolean getIsSaved(){
        // Return whether this table is saved
        return isSaved;
    }
    public void setIsSaved(boolean saved)
    {
        isSaved = saved;
    }
}

