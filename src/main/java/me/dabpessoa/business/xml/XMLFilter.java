package me.dabpessoa.business.xml;

import me.dabpessoa.util.Exstension;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/* ImageFilter.java is used by FileChooserDemo2.java. */
public class XMLFilter extends FileFilter {

    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Exstension.getExtension(f);
        if (extension != null) {
            if (extension.equals(Exstension.xml)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "*.xml";
    }
}
