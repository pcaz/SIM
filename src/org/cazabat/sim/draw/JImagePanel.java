package org.cazabat.sim.draw;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
/**
 * Charger et afficher une image dans un panel
 * http://www.fobec.com/java/919/charger-afficher-une-image-dans-jpanel.html
 * @author fobec 2010
 */
public class JImagePanel extends JPanel {
 
	private static final long serialVersionUID = 8401190683972340962L;
	
	
	private Image image = null;
    private Boolean stretch = true;
 
    /**
     * Constructeur
     * @param image image à afficher
     */
    public JImagePanel(Image image) {
        this.image = image;
    }
 
    /**
     * Constructeur
     * @param file nom du fichier
     */
    public JImagePanel(String file) {
        this.image = getToolkit().getImage(file);
    }
 
   /**
    * Position de l'image sur le panel
    * @param stretch true: etirer l'image / false: centrer l'image
    */
    public void setStretch(Boolean stretch) {
        this.stretch = stretch;
    }
 
    /**
     * Surcharger le dessin du composant
     * @param g canvas
     */
    protected void paintComponent(Graphics g) {
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
 
        if (this.stretch) {
            width = this.getWidth();
            height = this.getHeight();
        } else {
            width = this.image.getWidth(this);
            height = this.image.getHeight(this);
            x=((this.getWidth()-width)/2);
            y=((this.getHeight()-height)/2);
        }  
        g.drawImage(this.image, x, y, width, height, this);
    }
 
    /**
     * Exemple : jPanelImage dans un JFrame
     * @param args
     */
    public static void main(String[] args) {
        JImagePanel imagePanel = new JImagePanel("C:\\Users\\t599741\\Downloads\\graphviz-2.38\\release\\bin\\demo.png");
        //Centrer l'image
        imagePanel.setStretch(false);
        //Etirer l'image
       // imagePanel.setStretch(true);
 
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(imagePanel);
        frame.setSize(400, 400);
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }
    

}
