import squint.GUIManager;

import javax.swing.*;
import java.util.Arrays;

public class QuicksortTester extends GUIManager {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private static final String TITLE = "QuicksortWizard";

    private final JPanel mainPanel = new JPanel();
    private final JTextArea numStringsRequest = new JTextArea("How long should the array be?");
    private final JTextArea numStringsDialog = new JTextArea(" ");
    private final JTextArea stringLenRequest = new JTextArea("How long should the strings be?");
    private final JTextArea stringLenDialog = new JTextArea(" ");
    private final JButton runButton = new JButton("Run");

    private final JPanel resultPanel = new JPanel();
    private final JTextArea randomArrDisplay = new JTextArea();
    private final JTextArea sortedArrDisplay = new JTextArea();

    private QuicksortTester() {
        createWindow(WIDTH, HEIGHT, TITLE);
        mainPanel.add(numStringsRequest);
        mainPanel.add(numStringsDialog);
        mainPanel.add(stringLenRequest);
        mainPanel.add(stringLenDialog);
        mainPanel.add(runButton);
        resultPanel.add(randomArrDisplay);
        resultPanel.add(sortedArrDisplay);
        contentPane.add(mainPanel);
    }

    public void buttonClicked(JButton which) {
        if (which == runButton) {
            int numStrings = Integer.parseInt(numStringsDialog.getText());
            int lenStrings = Integer.parseInt(stringLenDialog.getText());
            String[] arr = Quicksort.generateRandomStringArray(numStrings, lenStrings);
            randomArrDisplay.setText(Arrays.toString(arr));
            Quicksort.quicksort(arr);
            sortedArrDisplay.setText(Arrays.toString(arr));
            contentPane.add(resultPanel);
        }
    }

    public static void main(String[] args) {
        QuicksortTester quicksortTester = new QuicksortTester();
    }
}
