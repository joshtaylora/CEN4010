package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TilePopController {

// =====================================================================================================================
// ============================================ TilePopup FXML fields ==================================================
    @FXML Label tileName;
    @FXML Label tileMessage;
    @FXML Button button1;
    @FXML Button button2;


    public void tileSetup(String name) {
        tileName.setText(name);
    }
}
