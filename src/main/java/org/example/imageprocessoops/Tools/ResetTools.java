package org.example.imageprocessoops.Tools;

import org.example.imageprocessoops.ImageWorkSpace.ImageWorkSpace;
import org.example.imageprocessoops.NoOperationTools.NoOperationTools;

public class ResetTools extends NoOperationTools{
    @Override
    public void toolActivate(ImageWorkSpace imageWorkSpace) {
        imageWorkSpace.reset();
    }
}
