package com.jordi.trikivalscreatures.blocks.pejelagarto;

import net.minecraft.client.model.*;

public class PejelagartoEggBlockModel {
    private final ModelPart egg1;
    private final ModelPart egg2;
    private final ModelPart egg3;
    private final ModelPart egg4;

    public PejelagartoEggBlockModel(ModelPart root) {
        // Retrieve the geometry parts defined in the static method
        this.egg1 = root.getChild("Egg_1");
        this.egg2 = root.getChild("Egg_2");
        this.egg3 = root.getChild("Egg_3");
        this.egg4 = root.getChild("Egg_4");
    }

    public static ModelPart createFourEggGeometry() {
        // 1. Create the container for the entire model structure
        ModelData modelData = new ModelData();

        // 2. Get the root ModelPartData builder
        ModelPartData root = modelData.getRoot();

        // Define the texture dimensions (e.g., 16x16)
        final int TEXTURE_WIDTH = 16;
        final int TEXTURE_HEIGHT = 16;

        // --- Egg 1 ---
        // addChild is called on the ModelPartData, and it takes a ModelPartBuilder.
        root.addChild("Egg_1",
                ModelPartBuilder.create().uv(1, 4).cuboid(5.0F, 0.0F, 4.0F, 4.0F, 7.0F, 4.0F),
                ModelTransform.NONE);

        // --- Egg 2 ---
        root.addChild("Egg_2",
                ModelPartBuilder.create().uv(4, 11).cuboid(6.0F, 0.0F, 9.0F, 4.0F, 4.0F, 4.0F),
                ModelTransform.NONE);

        // --- Egg 3 ---
        root.addChild("Egg_3",
                ModelPartBuilder.create().uv(8, 3).cuboid(11.0F, 0.0F, 7.0F, 3.0F, 4.0F, 3.0F),
                ModelTransform.NONE);

        // --- Egg 4 ---
        root.addChild("Egg_4",
                ModelPartBuilder.create().uv(10, 10).cuboid(1.0F, 0.0F, 7.0F, 4.0F, 5.0F, 4.0F),
                ModelTransform.NONE);

        // 3. Bake the ModelData into a ModelPart
        // This generates the final, renderable ModelPart structure.
        return modelData.getRoot().createPart(TEXTURE_WIDTH, TEXTURE_HEIGHT);
    }

    public ModelPart getEggPart(int index) {
        return switch (index) {
            case 1 -> egg1;
            case 2 -> egg2;
            case 3 -> egg3;
            case 4 -> egg4;
            default -> throw new IllegalArgumentException("Invalid egg index: " + index);
        };
    }
}
