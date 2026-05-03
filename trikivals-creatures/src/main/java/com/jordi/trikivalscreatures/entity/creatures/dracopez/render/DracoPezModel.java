package com.jordi.trikivalscreatures.entity.creatures.dracopez.render;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.util.math.MathHelper;

public class DracoPezModel  extends EntityModel<DracoPezRenderState> {
    private final ModelPart tail;

    public DracoPezModel(ModelPart modelPart) {
        super(modelPart);
        this.tail = modelPart.getChild(EntityModelPartNames.TAIL);
    }

    //TODO: Currently getTexturedModelData provides data regarding the SmallTropicalFish. Replace with final DracoPez model data
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        Dilation dilation = Dilation.NONE;
        int i = 22;
        modelPartData.addChild(
                EntityModelPartNames.BODY,
                ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -1.5F, -3.0F, 2.0F, 3.0F, 6.0F, dilation),
                ModelTransform.origin(0.0F, 22.0F, 0.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.TAIL,
                ModelPartBuilder.create().uv(22, -6).cuboid(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 6.0F, dilation),
                ModelTransform.origin(0.0F, 22.0F, 3.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.RIGHT_FIN,
                ModelPartBuilder.create().uv(2, 16).cuboid(-2.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, dilation),
                ModelTransform.of(-1.0F, 22.5F, 0.0F, 0.0F, (float) (Math.PI / 4), 0.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.LEFT_FIN,
                ModelPartBuilder.create().uv(2, 12).cuboid(0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, dilation),
                ModelTransform.of(1.0F, 22.5F, 0.0F, 0.0F, (float) (-Math.PI / 4), 0.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.TOP_FIN,
                ModelPartBuilder.create().uv(10, -5).cuboid(0.0F, -3.0F, 0.0F, 0.0F, 3.0F, 6.0F, dilation),
                ModelTransform.origin(0.0F, 20.5F, -3.0F)
        );
        return TexturedModelData.of(modelData, 32, 32);
    }

    public void setAngles(DracoPezRenderState dracoPezEntityRenderState) {
        super.setAngles(dracoPezEntityRenderState);
        float f = dracoPezEntityRenderState.touchingWater ? 1.0F : 1.5F;
        this.tail.yaw = -f * 0.45F * MathHelper.sin(0.6F * dracoPezEntityRenderState.age);
    }
}
