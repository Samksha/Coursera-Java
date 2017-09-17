
/**
 * Write a description of ImageSaver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class ImageSaver {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource (inImage.getWidth(), inImage.getHeight());
        for(Pixel p: outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(p.getX(), p.getY());
            int avg = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen());
            p.setRed(avg);
            p.setBlue(avg);
            p.setGreen(avg);
        }
        return outImage;
    }
    public void doSave() {
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource gray = makeGray(image);
            String fname = image.getFileName();
            String newName = "copy-" + fname;
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }
}
