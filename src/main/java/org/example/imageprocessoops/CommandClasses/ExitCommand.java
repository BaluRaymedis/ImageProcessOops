package org.example.imageprocessoops.CommandClasses;

import javafx.application.Platform;
import org.example.imageprocessoops.CommandInterface.Command;

public class ExitCommand implements Command {
    @Override
    public void execute(){
        Platform.exit();
    }
}
