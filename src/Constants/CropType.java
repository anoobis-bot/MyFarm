/*
    This enum contains the type of crops available in the game.
    They are distinguished mainly from their required free space
    (padding) when planting the seeds.
 */

package Constants;

public enum CropType
{
    ROOT_CROP("Root Crop", 0),
    FLOWER("Flower", 0),
    FRUIT_TREE("Fruit Tree", 1);

    public final String typeName;
    public final int paddingRequired;

    CropType(String typeName, int paddingRequired)
    {
        this.typeName = typeName;
        this.paddingRequired = paddingRequired;
    }
}
