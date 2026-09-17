package org.example.imageprocessoops.CommandClasses;

import javafx.stage.Stage;
import org.example.imageprocessoops.CommandInterface.Command;

public class MinimizeCommand implements Command {
    private final Stage stage;

    public MinimizeCommand(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void execute(){
        stage.setIconified(true);
    }
}
