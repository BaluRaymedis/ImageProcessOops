package org.example.imageprocessoops.Tools;

import org.example.imageprocessoops.NoOperationTools.NoOperationTools;

public class FitTools extends NoOperationTools {
    @Override
    public void toolActivate(org.example.imageprocessoops.ImageWorkSpace.ImageWorkSpace imageWorkSpace) {
        imageWorkSpace.fitToContainer();
    }
}
