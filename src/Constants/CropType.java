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
