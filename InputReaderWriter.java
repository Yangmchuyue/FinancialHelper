import javax.swing.*;
import java.io.*;

public class InputReaderWriter {
    private static BufferedReader bufferedReader;

    public static int getNumberOfColumns(File file){
        int numColumns = 0;
        // Read the first line in the data file and split the string into the column titles
        try
        {
            bufferedReader = new BufferedReader(new FileReader(file));
            String[] columnTitles = bufferedReader.readLine().trim().split(" ");
            bufferedReader.close();
            numColumns =  columnTitles.length;
        }
        catch (FileNotFoundException exception)
        {
            // Show warning message
            int confirmExit = JOptionPane.showConfirmDialog(null,
                    "Error has occurred. File could not be found.",
                    "Error!",JOptionPane.OK_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            bufferedReader.close();
        }
        catch (Exception exception)
        {
            // Show warning message
            int confirmExit = JOptionPane.showConfirmDialog(null,
                    "Error has occurred. The file could not be read correctly.",
                    "Error!",JOptionPane.OK_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            bufferedReader.close();
        }
        finally {
            return numColumns;
        }


    }
    public static int getMaxNumberRows(File file){
        // Row Data does not include the first line of the text file.
        int maxRow = 0;
        try
        {
            bufferedReader = new BufferedReader(new FileReader(file));
            // Row data does not include the first line of the text file so go to second line
            bufferedReader.readLine();
            String line = bufferedReader.readLine();
            // Count number of lines in the file, excluding first line
            while (line!= null)
            {
                line = bufferedReader.readLine();
                maxRow = maxRow + 1;
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException exception)
        {
            // Show warning message
            int confirmExit = JOptionPane.showConfirmDialog(null,
                    "Error has occurred. The file could not be found.",
                    "Error!",JOptionPane.OK_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            bufferedReader.close();
        }
        catch (Exception exception)
        {
            // Show warning message
            int confirmExit = JOptionPane.showConfirmDialog(null,
                    "Error has occurred. The file could not be read correctly.",
                    "Error!",JOptionPane.OK_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            bufferedReader.close();
        }
        finally
        {
            return maxRow;
        }

    }
    public static String[] getColumnNames (File file) {
        // Columns Names cannot contain numbers
        String[] columnTitles = new String[getNumberOfColumns(file)];
        try
        {
            // Get the column titles from the first line in the file
            bufferedReader = new BufferedReader(new FileReader(file));
            columnTitles = bufferedReader.readLine().trim().split(" "); // Splits the first line in the data file with white spaces
            bufferedReader.close();
        }
        catch (FileNotFoundException exception)
        {
            // Show warning message
            int confirmExit = JOptionPane.showConfirmDialog(null,
                    "Error has occurred. The file could not be read.",
                    "Error!",JOptionPane.OK_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            bufferedReader.close();
        }
        catch (Exception exception)
        {
            // Show warning message
            int confirmExit = JOptionPane.showConfirmDialog(null,
                    "Error has occurred. The file could not be read correctly.",
                    "Error!",JOptionPane.OK_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            bufferedReader.close();
            return null;
        }
        finally
        {
            return columnTitles;
        }

    }
    public static String[][] getRowDataValues(File file){
        String[][] data = new String[getMaxNumberRows(file)][getNumberOfColumns(file)];
        try
        {
            // Start reading file from second line
            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedReader.readLine();
            String line = bufferedReader.readLine();
            int currentRowNumber = 0;
            // Read file line by line and get data row by row
            while (line!= null)
            {
                data[currentRowNumber] = line.trim().split(" ");
                currentRowNumber = currentRowNumber + 1;
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException exception)
        {
            // Show warning message
            int confirmExit = JOptionPane.showConfirmDialog(null,
                    "Error has occurred. The file could not be read.",
                    "Error!",JOptionPane.OK_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            bufferedReader.close();
        }
        catch (Exception exception)
        {
            // Show warning message
            int confirmExit = JOptionPane.showConfirmDialog(null,
                    "Error has occurred. The file could not be read correctly.",
                    "Error!",JOptionPane.OK_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            bufferedReader.close();
            return null;
        }
        finally
        {
            return data;
        }


    }
    public static void saveFileAs(PrintWriter writer, MyTable table){
        int numberColumns = table.getColumnCount();
        int numberRows = table.getRowCount();
        // Get the column titles
        for (int i = 0; i < numberColumns; i++) {
            // Write the column title to first line of file
            writer.print(table.getColumnName(i) + " ");
        }
        writer.print("\n");

        // Go through table row by row
        for (int i = 0; i < numberRows; i++) {
            // Go through current row by column
            for (int j = 0; j < numberColumns; j++) {
                // Check if the current cell has null
                if (table.getValueAt(i, j) != null) {
                    // Write value in current cell in current line of file
                    writer.print(table.getValueAt(i, j).toString() + " ");
                }
                else {
                    writer.print("NO VALUE ");
                }
            }
            // Start next line of file
            writer.print("\n");
        }
        writer.close();
    }
}
