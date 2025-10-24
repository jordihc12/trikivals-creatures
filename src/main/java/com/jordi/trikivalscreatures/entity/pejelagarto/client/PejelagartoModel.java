// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.jordi.trikivalscreatures.entity.pejelagarto.client;

import com.jordi.trikivalscreatures.entity.pejelagarto.animation.PejelagartoAnims;
import com.jordi.trikivalscreatures.entity.pejelagarto.custom.PejelagartoEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class PejelagartoModel<T extends PejelagartoEntity> extends SinglePartEntityModel<T> {
	private final ModelPart pejelagarto;
    private final ModelPart head;

	public PejelagartoModel(ModelPart root) {
		this.pejelagarto = root.getChild("pejelagarto");
        this.head = pejelagarto.getChild("body").getChild("head");
	}

	public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData pejelagarto = modelPartData.addChild("pejelagarto", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body = pejelagarto.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(11, 0).cuboid(-1.5F, -1.0F, -0.6667F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(11, 0).mirrored().cuboid(0.5F, -1.0F, -0.6667F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.5F, -4.0F, -5.3133F));

        ModelPartData mouth = head.addChild("mouth", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -0.75F, 0.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.75F, -0.6667F));

        ModelPartData cube_r1 = mouth.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.25F, -1.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData jaw = mouth.addChild("jaw", ModelPartBuilder.create().uv(0, 4).cuboid(-1.5F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.75F, -1.0F));

        ModelPartData left_legs = body.addChild("left_legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData left_first = left_legs.addChild("left_first", ModelPartBuilder.create().uv(4, 7).cuboid(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(3.5F, -1.0F, -3.4577F));

        ModelPartData left_second = left_legs.addChild("left_second", ModelPartBuilder.create().uv(0, 10).cuboid(-0.5F, -1.1425F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(3.5F, -0.8575F, -1.4843F));

        ModelPartData left_third = left_legs.addChild("left_third", ModelPartBuilder.create().uv(4, 10).cuboid(-0.5F, -1.2435F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(3.5F, -0.7565F, 0.4685F));

        ModelPartData left_fourth = left_legs.addChild("left_fourth", ModelPartBuilder.create().uv(0, 7).cuboid(0.0F, -0.9328F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(2.5F, -1.0672F, 2.5002F));

        ModelPartData right_legs = body.addChild("right_legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData right_first = right_legs.addChild("right_first", ModelPartBuilder.create().uv(0, 10).cuboid(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, -1.0F, -3.5F));

        ModelPartData right_second = right_legs.addChild("right_second", ModelPartBuilder.create().uv(0, 10).cuboid(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, -1.0F, -1.5F));

        ModelPartData right_third = right_legs.addChild("right_third", ModelPartBuilder.create().uv(4, 7).cuboid(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, -1.0F, 0.5F));

        ModelPartData right_fourth = right_legs.addChild("right_fourth", ModelPartBuilder.create().uv(4, 10).cuboid(-1.0F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, -1.0F, 2.5F));

        ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(8, 4).cuboid(-2.5F, 0.25F, -3.5833F, 5.0F, 2.0F, 7.0F, new Dilation(0.0F))
                .uv(2, 24).cuboid(-1.5F, -0.75F, -1.5833F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(1, 21).cuboid(-0.5F, -0.75F, -2.5833F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
                .uv(2, 24).cuboid(0.5F, -0.75F, -1.5833F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 24).cuboid(-1.5F, -0.75F, 0.4167F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(2, 24).cuboid(0.5F, -0.75F, 0.4167F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, -3.25F, -1.4167F));

        ModelPartData tail_1 = torso.addChild("tail_1", ModelPartBuilder.create().uv(17, 14).cuboid(-1.4895F, -0.8249F, -1.7818F, 3.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.0105F, 0.6999F, 4.8985F, 0.2182F, 0.0F, 0.0F));

        ModelPartData tail_2 = tail_1.addChild("tail_2", ModelPartBuilder.create().uv(8, 15).cuboid(-0.5F, -1.0F, -1.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0105F, -0.1341F, 3.4619F, 0.2182F, 0.0F, 0.0F));

        ModelPartData tail_3 = tail_2.addChild("tail_3", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, -1.0F, -1.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.2971F, 2.7661F, 0.2182F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

	@Override
	public void setAngles(PejelagartoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	    this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(PejelagartoAnims.PEJELAGARTO_WALK, limbSwing, limbSwingAmount, 2, 2.5f);
        this.updateAnimation(entity.idleAnimationState, PejelagartoAnims.PEJELAGARTO_IDLE, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.head.yaw = headYaw * (float) (Math.PI / 180.0);
        this.head.pitch = headPitch * (float) (Math.PI / 180.0);
    }

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		pejelagarto.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

    @Override
    public ModelPart getPart() {
        return pejelagarto;
    }
}