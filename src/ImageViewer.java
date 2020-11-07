import java.awt.*;
import squint.*;
import javax.swing.*;
import java.io.File;

/**
 * ImageViewer --- a GUIManager to display a single image
 * @author Williams College
 */
public class ImageViewer extends GUIManager {
    // The image to display
    private SImage picture;
    
    // Label used to display the image
    private JLabel displayImage = new JLabel( "", SwingConstants.CENTER );
    
    // Buttons and dialog box used to load and save images
    private JFileChooser chooser = new JFileChooser( new File( System.getProperty("user.dir")) );
    private JButton load = new JButton("Load Image");
    private JButton save = new JButton("Save Image");
    
    // Place all the GUI components in the GUIManager's pane
    public ImageViewer() {
      contentPane.setLayout( new BorderLayout() );
      contentPane.add( new JScrollPane( displayImage ), BorderLayout.CENTER );
      JPanel buttons = new JPanel();
      buttons.add(load);
      buttons.add(save);
      contentPane.add(buttons, BorderLayout.SOUTH);
    }
    
    // Load or save an image
    public void buttonClicked( JButton which ) {
        if (which == load) {
            if ( chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                setPic(new SImage( chooser.getSelectedFile().getAbsolutePath() ) );
            }
        } else if (which == save  && picture != null ) {
           if ( chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION ) {
               String name = chooser.getSelectedFile().toString();
               if ( ! name.endsWith(".png" ) ) {
                   name = name + ".png";
                }
                picture.saveAs(name);
            }            
        }
    }
    
    // Set the image displayed within the viewer
    public void setPic( SImage newPic ) {
        picture = newPic;
        displayImage.setIcon( picture );
    }
    
    // Get the image displayed within the viewer
    public SImage getPic( ) {
        return picture;
    }

}