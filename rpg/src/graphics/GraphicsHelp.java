package graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class GraphicsHelp {

	public static BufferedImage MirrorImage(BufferedImage img) {
	    if (img != null) {
	        int width = img.getWidth();
	        int height = img.getHeight();
	        BufferedImage mirroredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        AffineTransform transform = AffineTransform.getScaleInstance(-1, 1);
	        transform.translate(-width, 0);
	        Graphics2D g = mirroredImage.createGraphics();
	        g.drawImage(img, transform, null);
	        g.dispose();
	        return mirroredImage;
	    }
	    return null;
	}
}
