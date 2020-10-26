import squint.*;
import javax.swing.*;
import java.awt.*;

public class StegoDisplay extends GUIManager {

    private static final int WIDTH = 2000;
    private static final int HEIGHT = 1000;
    private static final String TITLE = "Steganography Tool";

    private JPanel mainPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JSplitPane secretSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private JScrollPane secretScrollPane = new JScrollPane();
    private JTextArea secretTextArea = new JTextArea();

    private ImageViewer baseViewer = new ImageViewer();
    private ImageViewer secretViewer = new ImageViewer();
    private ImageViewer encodedViewer = new ImageViewer();

    private JButton encodeButton = new JButton("Encode");
    private JButton decodeButton = new JButton("Decode");
    private JButton encryptButton = new JButton("Encrypt");
    private JButton decryptbutton = new JButton("Decrypt");
    private JButton textClearButton = new JButton("Clear Text");
    private JButton imageClearButton = new JButton("Clear Image");

    public StegoDisplay() {
        // Settup GUI
        this.createWindow(WIDTH, HEIGHT, TITLE);
        contentPane.setLayout(new BorderLayout());
        this.getWindow().setExtendedState(JFrame.MAXIMIZED_BOTH);
        JFrame.setDefaultLookAndFeelDecorated(true);

        secretTextArea.setLineWrap(true);
        secretScrollPane.setViewportView(secretTextArea);
        secretSplitPane.setTopComponent(secretScrollPane);
        secretSplitPane.setBottomComponent(secretViewer);

        mainPanel.setLayout(new GridLayout(1, 3));
        mainPanel.add(baseViewer);
        mainPanel.add(secretSplitPane);
        mainPanel.add(encodedViewer);

        buttonPanel.add(encodeButton);
        buttonPanel.add(decodeButton);
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptbutton);
        buttonPanel.add(textClearButton);
        buttonPanel.add(imageClearButton);

        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        secretSplitPane.setDividerLocation(0.5);
    }

    public void buttonClicked(JButton which) {

    }
}