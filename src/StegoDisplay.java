import squint.*;
import javax.swing.*;
import java.awt.*;

public class StegoDisplay extends GUIManager {
    private static final int WIDTH = 2000;
    private static final int HEIGHT = 1000;
    private static final String TITLE = "Steganography Tool";

    private final JPanel mainPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();

    private final JSplitPane secretSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private final JScrollPane secretScrollPane = new JScrollPane();
    private final JTextArea secretTextArea = new JTextArea();

    private final ImageViewer baseViewer = new ImageViewer();
    private final ImageViewer secretViewer = new ImageViewer();
    private final ImageViewer encodedViewer = new ImageViewer();

    private final JButton encodeButton = new JButton("Encode");
    private final JButton decodeButton = new JButton("Decode");
    private final JButton encryptButton = new JButton("Encrypt");
    private final JButton decryptbutton = new JButton("Decrypt");
    private final JButton textClearButton = new JButton("Clear Text");
    private final JButton imageClearButton = new JButton("Clear Image");

    private int[] encodedString;

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
        if (which == encodeButton) {
            try {
                encodedString = StringEncoder.encode(baseViewer.getPic(), secretTextArea.getText());
            }
            catch (IllegalArgumentException e) {return;}
            secretViewer.setPic(Encrypter.stegImage(baseViewer.getPic(), encodedString));
        }
        else if (which == encryptButton) {
            if (encodedString == null || encodedString.length == 0)
                return;
            encodedViewer.setPic(Encrypter.encypt(baseViewer.getPic(), encodedString));
        }
    }
}