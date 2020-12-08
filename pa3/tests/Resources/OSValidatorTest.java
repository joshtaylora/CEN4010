package Resources;

import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OSValidatorTest {

    OSValidator osValidator = new OSValidator();

    @Test
    void getPathToFileTest() {
        /* This module path does not work on windows, caused by using Linux/MacOS style directory notation in module path */
        String badWindowsModulePath = "\\src\\Resources/Images\\";
        String dogTokenFileName = "dog.png";
        /* Get the bad file path */
        String badFilePath1 = osValidator.getPathToFile(dogTokenFileName, badWindowsModulePath);
        /* Make the image object, will result in NullPointerException */
        Image badImage1 = new Image(badFilePath1);

    }
}