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
    private final ImageViewer encryptedViewer = new ImageViewer();

    private final JButton encodeButton = new JButton("Encode");
    private final JButton decodeButton = new JButton("Decode");
    private final JButton encryptButton = new JButton("Encrypt");
    private final JButton decryptButton = new JButton("Decrypt");
    private final JButton textClearButton = new JButton("Clear Text");
    private final JButton imageClearButton = new JButton("Clear Images");

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
        mainPanel.add(encryptedViewer);

        buttonPanel.add(encodeButton);
        buttonPanel.add(decodeButton);
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);
        buttonPanel.add(textClearButton);
        buttonPanel.add(imageClearButton);

        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        secretSplitPane.setDividerLocation(0.5);
    }

    public void buttonClicked(JButton which) {
        if (which == encodeButton) {
            int[] encodedString;
            if (baseViewer.getPic() == null) {
                alert("Missing Base Image!");
            } else {
                try {
                    encodedString = StringEncoder.convertToASCII(baseViewer.getPic(), secretTextArea.getText());
                    secretViewer.setPic(Encrypter.encode(baseViewer.getPic(), encodedString));
                } catch (IllegalArgumentException e) {
                    alert(e.toString());
                }
            }
        } else if (which == encryptButton) {
            if (baseViewer.getPic() == null) {
                alert("Missing Base Image!");
            } else if (secretViewer.getPic() == null) {
                alert("Missing Secret Image!");
            } else { 
                try {
                    encryptedViewer.setPic(Encrypter.encrypt(baseViewer.getPic(), secretViewer.getPic()));
                } catch (IllegalArgumentException e) {
                    alert(e.toString());
                }
            }
        } else if (which == decodeButton) {
            if (secretViewer.getPic() == null) {
                alert("Missing Secret Image!");
            } else {
                int[] encodedString = Encrypter.decode(secretViewer.getPic());
                secretTextArea.setText(StringEncoder.convertToString(encodedString));
            }
        } else if (which == decryptButton) {
            if (encryptedViewer.getPic() == null) {
                alert("Missing Encrypted Image!");
            } else {
                secretViewer.setPic(Encrypter.decrypt(encryptedViewer.getPic()));
            }
        } else if (which == textClearButton) {
            secretTextArea.setText("");
        } else if (which == imageClearButton) {
            baseViewer.setPic(null);
            secretViewer.setPic(null);
            encryptedViewer.setPic(null);
        }
    }

    public void alert(String text) {
        JOptionPane.showMessageDialog(this.getWindow(), text, "Error", JOptionPane.WARNING_MESSAGE);
    }
}