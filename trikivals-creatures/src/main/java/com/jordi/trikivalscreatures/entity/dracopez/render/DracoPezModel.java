package com.jordi.trikivalscreatures.entity.dracopez.render;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.dracopez.anims.DracoPezAnimations;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

/**
 * Model generated from external_res/blockbench/DracoPez.bbmodel.
 * Texture: 128x128 image with 64x64 UV units (2 pixels per model pixel, same as the Pejelagarto).
 * Cubes with their own rotation in Blockbench are exported as child parts named "<group>_c<n>".
 */
public class DracoPezModel extends EntityModel<DracoPezRenderState> {
    public static final EntityModelLayer DRACOPEZ = new EntityModelLayer(Identifier.of(TrikiValsCreatures.MOD_ID, "dracopez"), "main");

    private final ModelPart dracopez;
    private final ModelPart head;

    //--- ANIMATIONS ---
    private final Animation swimAnimation;
    private final Animation idleWaterAnimation;
    private final Animation flopAnimation;

    public DracoPezModel(ModelPart root) {
        super(root);
        this.dracopez = root.getChild("dracopez");
        this.head = this.dracopez.getChild("head");

        this.swimAnimation = DracoPezAnimations.DRACOPEZ_SWIM.createAnimation(root);
        this.idleWaterAnimation = DracoPezAnimations.DRACOPEZ_IDLE_WATER.createAnimation(root);
        this.flopAnimation = DracoPezAnimations.DRACOPEZ_FLOP.createAnimation(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData dracopez = modelPartData.addChild("dracopez", ModelPartBuilder.create(), ModelTransform.origin(-0.5000F, 20.7000F, -4.5745F));
        ModelPartData head = dracopez.addChild("head", ModelPartBuilder.create(), ModelTransform.origin(0.5000F, -0.7000F, -0.4255F));
        head.addChild("head_c0", ModelPartBuilder.create().uv(48, 0).cuboid(-1.0000F, -1.7636F, -3.9922F, 3.0000F, 3.0000F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(-0.5000F, 0.7000F, 0.4255F, 0.0873F, 0.0000F, 0.0000F));
        head.addChild("head_c7", ModelPartBuilder.create().uv(24, 42).cuboid(-0.2500F, -0.1000F, -2.4255F, 1.5000F, 1.0000F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(-0.5000F, 0.3000F, -3.1745F, 0.0873F, 0.0000F, 0.0000F));
        head.addChild("head_c9", ModelPartBuilder.create().uv(32, 42).cuboid(-0.2500F, 0.0000F, -2.4255F, 0.5000F, 0.5000F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(1.5000F, -0.7000F, -1.1745F, 0.0873F, 0.0000F, 0.0000F));
        head.addChild("head_c10", ModelPartBuilder.create().uv(38, 42).cuboid(-0.2500F, 0.0000F, -2.4255F, 0.5000F, 0.5000F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(-1.5000F, -0.7000F, -1.1745F, 0.0873F, 0.0000F, 0.0000F));
        ModelPartData aleta_1 = head.addChild("aleta_1", ModelPartBuilder.create(), ModelTransform.origin(0.0000F, -0.7000F, -2.8745F));
        aleta_1.addChild("aleta_1_c11", ModelPartBuilder.create().uv(29, 19).cuboid(-0.2500F, -0.4000F, -0.2255F, 0.5000F, 0.5000F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(0.0000F, 0.0000F, 0.0000F, 1.2217F, 0.0000F, 0.0000F));
        aleta_1.addChild("aleta_1_c12", ModelPartBuilder.create().uv(44, 42).cuboid(-0.0250F, -0.1500F, -0.2255F, 0.2500F, 0.2500F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(-0.1000F, 0.0000F, 2.5000F, 0.8727F, 0.0000F, 0.0000F));
        aleta_1.addChild("aleta_1_c13", ModelPartBuilder.create().uv(39, 19).cuboid(-0.0250F, -0.1500F, -3.2255F, 0.2500F, 0.2500F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(-0.1000F, -2.0000F, 2.5000F, 1.0472F, 0.0000F, 0.0000F));
        aleta_1.addChild("aleta_1_c31", ModelPartBuilder.create().uv(0, 19).cuboid(-0.1600F, -1.9000F, -3.2255F, 0.0000F, 2.0000F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(0.2000F, -1.8000F, 2.4000F, 1.2217F, 0.0000F, 0.0000F));
        aleta_1.addChild("aleta_1_c32", ModelPartBuilder.create().uv(35, 37).cuboid(-0.1600F, -0.5000F, -3.2255F, 0.0000F, 1.6000F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(0.2000F, -3.0000F, 3.6000F, 0.8290F, 0.0000F, 0.0000F));
        ModelPartData aleta_2 = head.addChild("aleta_2", ModelPartBuilder.create(), ModelTransform.of(-1.3000F, 1.8000F, -2.0745F, 0.0000F, 0.0000F, -2.1380F));
        aleta_2.addChild("aleta_2_c14", ModelPartBuilder.create().uv(49, 19).cuboid(-0.2500F, -0.4000F, -0.2255F, 0.5000F, 0.5000F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(0.0000F, 0.0000F, 0.0000F, 1.2217F, 0.0000F, 0.0000F));
        aleta_2.addChild("aleta_2_c15", ModelPartBuilder.create().uv(50, 42).cuboid(-0.0250F, -0.1500F, -0.2255F, 0.2500F, 0.2500F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(-0.1000F, 0.0000F, 2.5000F, 0.8727F, 0.0000F, 0.0000F));
        aleta_2.addChild("aleta_2_c16", ModelPartBuilder.create().uv(0, 26).cuboid(-0.0250F, -0.1500F, -3.2255F, 0.2500F, 0.2500F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(-0.1000F, -2.0000F, 2.5000F, 1.0472F, 0.0000F, 0.0000F));
        aleta_2.addChild("aleta_2_c17", ModelPartBuilder.create().uv(50, 11).cuboid(0.1600F, -1.9000F, -3.2255F, 0.0000F, 2.0000F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(-0.1000F, -1.8000F, 2.4000F, 1.2217F, 0.0000F, 0.0000F));
        aleta_2.addChild("aleta_2_c18", ModelPartBuilder.create().uv(25, 37).cuboid(0.1600F, -0.5000F, -3.2255F, 0.0000F, 1.6000F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(-0.1000F, -3.0000F, 3.6000F, 0.8290F, 0.0000F, 0.0000F));
        ModelPartData aleta_3 = head.addChild("aleta_3", ModelPartBuilder.create(), ModelTransform.of(1.3000F, 1.8000F, -2.0745F, 0.0000F, 0.0000F, 2.1380F));
        aleta_3.addChild("aleta_3_c38", ModelPartBuilder.create().uv(10, 26).cuboid(-0.2500F, -0.4000F, -0.2255F, 0.5000F, 0.5000F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(0.0000F, 0.0000F, 0.0000F, 1.2217F, 0.0000F, 0.0000F));
        aleta_3.addChild("aleta_3_c39", ModelPartBuilder.create().uv(24, 46).cuboid(-0.2250F, -0.1500F, -0.2255F, 0.2500F, 0.2500F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(0.1000F, 0.0000F, 2.5000F, 0.8727F, 0.0000F, 0.0000F));
        aleta_3.addChild("aleta_3_c40", ModelPartBuilder.create().uv(20, 26).cuboid(-0.2250F, -0.1500F, -3.2255F, 0.2500F, 0.2500F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(0.1000F, -2.0000F, 2.5000F, 1.0472F, 0.0000F, 0.0000F));
        aleta_3.addChild("aleta_3_c41", ModelPartBuilder.create().uv(9, 19).cuboid(-0.1600F, -1.9000F, -3.2255F, 0.0000F, 2.0000F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(0.1000F, -1.8000F, 2.4000F, 1.2217F, 0.0000F, 0.0000F));
        aleta_3.addChild("aleta_3_c42", ModelPartBuilder.create().uv(55, 37).cuboid(-0.1600F, -0.5000F, -3.2255F, 0.0000F, 1.6000F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(0.1000F, -3.0000F, 3.6000F, 0.8290F, 0.0000F, 0.0000F));
        ModelPartData jaw = head.addChild("jaw", ModelPartBuilder.create(), ModelTransform.origin(-0.5000F, 1.3000F, -3.1745F));
        jaw.addChild("jaw_c8", ModelPartBuilder.create().uv(45, 56).cuboid(-1.0000F, -0.1000F, -1.8255F, 1.0000F, 0.5000F, 1.5000F, new Dilation(0.0F)), ModelTransform.of(1.0000F, 0.0000F, 0.0000F, 0.0873F, 0.0000F, 0.0000F));
        ModelPartData body = dracopez.addChild("body", ModelPartBuilder.create(), ModelTransform.origin(0.5000F, 3.3000F, 4.5745F));
        ModelPartData parte_1 = body.addChild("parte_1", ModelPartBuilder.create(), ModelTransform.origin(0.0000F, -3.3000F, -5.0000F));
        parte_1.addChild("parte_1_c1", ModelPartBuilder.create().uv(0, 11).cuboid(-1.0000F, -1.6924F, -0.1545F, 2.0000F, 3.0000F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(0.0000F, -0.1583F, 0.0538F, 0.3491F, 0.0000F, 0.0000F));
        parte_1.addChild("parte_1_c19", ModelPartBuilder.create().uv(56, 42).cuboid(-0.0250F, -0.1500F, -3.2255F, 0.2500F, 0.2500F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(-0.1000F, -4.8000F, 2.2255F, 1.2217F, 0.0000F, 0.0000F));
        parte_1.addChild("parte_1_c20", ModelPartBuilder.create().uv(30, 37).cuboid(0.2000F, -0.2000F, -3.2255F, 0.0000F, 1.3000F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(-0.2000F, -4.5000F, 2.3255F, 1.2217F, 0.0000F, 0.0000F));
        ModelPartData ala_6 = parte_1.addChild("ala_6", ModelPartBuilder.create(), ModelTransform.origin(-1.0000F, -2.1583F, 5.0538F));
        ala_6.addChild("ala_6_c49", ModelPartBuilder.create().uv(49, 53).cuboid(-2.9000F, -0.1152F, -0.3507F, 3.0000F, 0.5000F, 0.7500F, new Dilation(0.0F)), ModelTransform.of(-0.7000F, 0.0000F, -2.8000F, 0.3491F, 0.0000F, 0.2182F));
        ala_6.addChild("ala_6_c50", ModelPartBuilder.create().uv(56, 56).cuboid(-0.9000F, -0.1152F, -0.3507F, 1.0000F, 0.5000F, 0.7500F, new Dilation(0.0F)), ModelTransform.of(0.2000F, 0.0000F, -2.8000F, 0.3491F, 0.0000F, 0.0000F));
        ala_6.addChild("ala_6_c51", ModelPartBuilder.create().uv(35, 50).cuboid(-2.9000F, 0.3848F, -0.3507F, 3.0000F, 0.0000F, 1.7500F, new Dilation(0.0F)), ModelTransform.of(-0.4000F, -0.5000F, -2.3000F, 0.3491F, 0.0000F, 0.2182F));
        ala_6.addChild("ala_6_c52", ModelPartBuilder.create().uv(0, 56).cuboid(-2.9000F, -0.1152F, -0.3507F, 3.0000F, 0.5000F, 0.7500F, new Dilation(0.0F)), ModelTransform.of(-3.4225F, -0.5748F, -2.8054F, 0.2182F, 0.5672F, 0.0436F));
        ala_6.addChild("ala_6_c53", ModelPartBuilder.create().uv(42, 46).cuboid(-3.9000F, 0.2348F, -0.3507F, 4.0000F, 0.1500F, 1.2500F, new Dilation(0.0F)), ModelTransform.of(-3.4225F, -0.7748F, -2.4054F, 0.2182F, 0.5672F, 0.0436F));
        ala_6.addChild("ala_6_c54", ModelPartBuilder.create().uv(0, 32).cuboid(-5.9000F, 0.3848F, -0.3507F, 6.0000F, 0.0000F, 3.2500F, new Dilation(0.0F)), ModelTransform.of(-3.4225F, -0.8748F, -2.4054F, 0.2182F, 0.5672F, 0.0436F));
        ModelPartData ala_5 = parte_1.addChild("ala_5", ModelPartBuilder.create(), ModelTransform.origin(1.0000F, -2.1583F, 5.0538F));
        ala_5.addChild("ala_5_c43", ModelPartBuilder.create().uv(51, 56).cuboid(-0.1000F, -0.1152F, -0.3507F, 1.0000F, 0.5000F, 0.7500F, new Dilation(0.0F)), ModelTransform.of(-0.2000F, 0.0000F, -2.8000F, 0.3491F, 0.0000F, 0.0000F));
        ala_5.addChild("ala_5_c44", ModelPartBuilder.create().uv(31, 53).cuboid(-0.1000F, -0.1152F, -0.3507F, 3.0000F, 0.5000F, 0.7500F, new Dilation(0.0F)), ModelTransform.of(0.7000F, 0.0000F, -2.8000F, 0.3491F, 0.0000F, -0.2182F));
        ala_5.addChild("ala_5_c45", ModelPartBuilder.create().uv(24, 50).cuboid(-0.1000F, 0.3848F, -0.3507F, 3.0000F, 0.0000F, 1.7500F, new Dilation(0.0F)), ModelTransform.of(0.4000F, -0.5000F, -2.3000F, 0.3491F, 0.0000F, -0.2182F));
        ala_5.addChild("ala_5_c46", ModelPartBuilder.create().uv(40, 53).cuboid(-0.1000F, -0.1152F, -0.3507F, 3.0000F, 0.5000F, 0.7500F, new Dilation(0.0F)), ModelTransform.of(3.4225F, -0.5748F, -2.8054F, 0.2182F, -0.5672F, -0.0436F));
        ala_5.addChild("ala_5_c47", ModelPartBuilder.create().uv(30, 46).cuboid(-0.1000F, 0.2348F, -0.3507F, 4.0000F, 0.1500F, 1.2500F, new Dilation(0.0F)), ModelTransform.of(3.4225F, -0.7748F, -2.4054F, 0.2182F, -0.5672F, -0.0436F));
        ala_5.addChild("ala_5_c48", ModelPartBuilder.create().uv(30, 26).cuboid(-0.1000F, 0.3848F, -0.3507F, 6.0000F, 0.0000F, 3.2500F, new Dilation(0.0F)), ModelTransform.of(3.4225F, -0.8748F, -2.4054F, 0.2182F, -0.5672F, -0.0436F));
        ModelPartData parte_2 = parte_1.addChild("parte_2", ModelPartBuilder.create().uv(13, 11).cuboid(-1.0000F, -1.1583F, -0.3000F, 2.0000F, 2.0000F, 4.0000F, new Dilation(0.0F)), ModelTransform.origin(0.0000F, -1.3000F, 3.3000F));
        ModelPartData ala_13 = parte_2.addChild("ala_13", ModelPartBuilder.create(), ModelTransform.origin(-1.0000F, 0.0583F, 4.7538F));
        ala_13.addChild("ala_13_c55", ModelPartBuilder.create().uv(9, 56).cuboid(-2.9000F, -0.3848F, 0.2462F, 3.0000F, 0.5000F, 0.7500F, new Dilation(0.0F)), ModelTransform.of(-0.7000F, 0.0000F, -2.8000F, -0.3491F, 0.0000F, -0.2182F));
        ala_13.addChild("ala_13_c56", ModelPartBuilder.create().uv(0, 59).cuboid(-0.9000F, -0.3848F, 0.2462F, 1.0000F, 0.5000F, 0.7500F, new Dilation(0.0F)), ModelTransform.of(0.2000F, 0.0000F, -2.8000F, -0.3491F, 0.0000F, 0.0000F));
        ala_13.addChild("ala_13_c57", ModelPartBuilder.create().uv(46, 50).cuboid(-2.9000F, -0.3848F, -0.2538F, 3.0000F, 0.0000F, 1.7500F, new Dilation(0.0F)), ModelTransform.of(-0.4000F, 0.5000F, -2.3000F, -0.3491F, 0.0000F, -0.2182F));
        ala_13.addChild("ala_13_c58", ModelPartBuilder.create().uv(18, 56).cuboid(-2.9000F, -0.3848F, 0.2516F, 3.0000F, 0.5000F, 0.7500F, new Dilation(0.0F)), ModelTransform.of(-3.4225F, 0.5748F, -2.8054F, -0.2182F, 0.5672F, -0.0436F));
        ala_13.addChild("ala_13_c59", ModelPartBuilder.create().uv(0, 50).cuboid(-3.9000F, -0.3848F, -0.1484F, 4.0000F, 0.1500F, 1.2500F, new Dilation(0.0F)), ModelTransform.of(-3.4225F, 0.7748F, -2.4054F, -0.2182F, 0.5672F, -0.0436F));
        ala_13.addChild("ala_13_c60", ModelPartBuilder.create().uv(20, 32).cuboid(-5.9000F, -0.3848F, -0.1484F, 6.0000F, 0.0000F, 3.2500F, new Dilation(0.0F)), ModelTransform.of(-3.4225F, 0.8748F, -2.4054F, -0.2182F, 0.5672F, -0.0436F));
        ModelPartData ala_14 = parte_2.addChild("ala_14", ModelPartBuilder.create(), ModelTransform.origin(1.0000F, 0.0583F, 4.7538F));
        ala_14.addChild("ala_14_c61", ModelPartBuilder.create().uv(5, 59).cuboid(-0.1000F, -0.3848F, 0.2462F, 1.0000F, 0.5000F, 0.7500F, new Dilation(0.0F)), ModelTransform.of(-0.2000F, 0.0000F, -2.8000F, -0.3491F, 0.0000F, 0.0000F));
        ala_14.addChild("ala_14_c62", ModelPartBuilder.create().uv(27, 56).cuboid(-0.1000F, -0.3848F, 0.2462F, 3.0000F, 0.5000F, 0.7500F, new Dilation(0.0F)), ModelTransform.of(0.7000F, 0.0000F, -2.8000F, -0.3491F, 0.0000F, 0.2182F));
        ala_14.addChild("ala_14_c63", ModelPartBuilder.create().uv(0, 53).cuboid(-0.1000F, -0.3848F, -0.2538F, 3.0000F, 0.0000F, 1.7500F, new Dilation(0.0F)), ModelTransform.of(0.4000F, 0.5000F, -2.3000F, -0.3491F, 0.0000F, 0.2182F));
        ala_14.addChild("ala_14_c64", ModelPartBuilder.create().uv(36, 56).cuboid(-0.1000F, -0.3848F, 0.2516F, 3.0000F, 0.5000F, 0.7500F, new Dilation(0.0F)), ModelTransform.of(3.4225F, 0.5748F, -2.8054F, -0.2182F, -0.5672F, 0.0436F));
        ala_14.addChild("ala_14_c65", ModelPartBuilder.create().uv(12, 50).cuboid(-0.1000F, -0.3848F, -0.1484F, 4.0000F, 0.1500F, 1.2500F, new Dilation(0.0F)), ModelTransform.of(3.4225F, 0.7748F, -2.4054F, -0.2182F, -0.5672F, 0.0436F));
        ala_14.addChild("ala_14_c66", ModelPartBuilder.create().uv(40, 32).cuboid(-0.1000F, -0.3848F, -0.1484F, 6.0000F, 0.0000F, 3.2500F, new Dilation(0.0F)), ModelTransform.of(3.4225F, 0.8748F, -2.4054F, -0.2182F, -0.5672F, 0.0436F));
        parte_2.addChild("parte_2_c21", ModelPartBuilder.create().uv(0, 46).cuboid(-0.0250F, -0.1500F, -3.2255F, 0.2500F, 0.2500F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(-0.1000F, -3.0000F, 2.8255F, 0.9163F, 0.0000F, 0.0000F));
        parte_2.addChild("parte_2_c33", ModelPartBuilder.create().uv(40, 37).cuboid(-0.1600F, 1.0175F, -4.8122F, 0.0000F, 1.3000F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(0.2000F, -4.7000F, 2.9255F, 0.9163F, 0.0000F, 0.0000F));
        ModelPartData parte_3 = parte_2.addChild("parte_3", ModelPartBuilder.create(), ModelTransform.origin(0.0000F, -0.5000F, 3.6000F));
        parte_3.addChild("parte_3_c22", ModelPartBuilder.create().uv(6, 46).cuboid(-0.0250F, -0.1500F, -3.2255F, 0.2500F, 0.2500F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(-0.1000F, -1.4023F, 2.9732F, 0.6109F, 0.0000F, 0.0000F));
        parte_3.addChild("parte_3_c3", ModelPartBuilder.create().uv(26, 11).cuboid(-1.0000F, -1.1735F, -0.1740F, 2.0000F, 2.0000F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(0.0000F, 0.6394F, -0.0984F, -0.2618F, 0.0000F, 0.0000F));
        parte_3.addChild("parte_3_c34", ModelPartBuilder.create().uv(45, 37).cuboid(-0.1600F, -0.2000F, -3.2255F, 0.0000F, 1.3000F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(0.2000F, -1.1023F, 3.0732F, 0.6109F, 0.0000F, 0.0000F));
        ModelPartData parte_4 = parte_3.addChild("parte_4", ModelPartBuilder.create(), ModelTransform.origin(-0.5000F, -0.3606F, 1.7016F));
        parte_4.addChild("parte_4_c4", ModelPartBuilder.create().uv(39, 11).cuboid(-0.5000F, -1.0572F, -0.4073F, 1.0000F, 2.0000F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(0.5000F, 1.7000F, 1.6000F, -0.0873F, 0.0000F, 0.0000F));
        parte_4.addChild("parte_4_c35", ModelPartBuilder.create().uv(50, 37).cuboid(-0.1600F, -0.2000F, -3.2255F, 0.0000F, 1.3000F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(0.7000F, -0.3417F, 4.7717F, 0.6109F, 0.0000F, 0.0000F));
        ModelPartData aleta_4 = parte_4.addChild("aleta_4", ModelPartBuilder.create(), ModelTransform.origin(0.5000F, 2.0000F, 2.5000F));
        aleta_4.addChild("aleta_4_c23", ModelPartBuilder.create().uv(9, 37).cuboid(-0.1500F, -0.1303F, -0.1782F, 0.2500F, 0.2500F, 3.0000F, new Dilation(0.0F)), ModelTransform.of(0.4000F, 0.0000F, -0.1000F, -0.0873F, 1.2217F, 0.0000F));
        aleta_4.addChild("aleta_4_c24", ModelPartBuilder.create().uv(12, 46).cuboid(-0.0004F, -0.0175F, -0.0395F, 0.1250F, 0.1250F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(3.0000F, 0.2000F, 0.9000F, -0.0873F, 0.6109F, 0.0000F));
        aleta_4.addChild("aleta_4_c25", ModelPartBuilder.create().uv(11, 53).cuboid(-2.0004F, 0.1075F, -0.0395F, 2.1250F, 0.0000F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(2.9000F, 0.1000F, 0.9000F, -0.0873F, 0.6109F, 0.0000F));
        aleta_4.addChild("aleta_4_c26", ModelPartBuilder.create().uv(0, 42).cuboid(-2.0004F, 0.1075F, -0.0395F, 2.1250F, 0.0000F, 3.0000F, new Dilation(0.0F)), ModelTransform.of(0.3000F, -0.1000F, 0.1000F, -0.0873F, 1.2217F, 0.0000F));
        ModelPartData aleta_5 = parte_4.addChild("aleta_5", ModelPartBuilder.create(), ModelTransform.origin(0.5000F, 2.0000F, 2.5000F));
        aleta_5.addChild("aleta_5_c27", ModelPartBuilder.create().uv(17, 37).cuboid(-0.1000F, -0.1303F, -0.1782F, 0.2500F, 0.2500F, 3.0000F, new Dilation(0.0F)), ModelTransform.of(-0.4000F, 0.0000F, -0.1000F, -0.0873F, -1.2217F, 0.0000F));
        aleta_5.addChild("aleta_5_c28", ModelPartBuilder.create().uv(18, 46).cuboid(-0.1246F, -0.0175F, -0.0395F, 0.1250F, 0.1250F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(-3.0000F, 0.2000F, 0.9000F, -0.0873F, -0.6109F, 0.0000F));
        aleta_5.addChild("aleta_5_c29", ModelPartBuilder.create().uv(21, 53).cuboid(-0.1246F, 0.1075F, -0.0395F, 2.1250F, 0.0000F, 2.0000F, new Dilation(0.0F)), ModelTransform.of(-2.9000F, 0.1000F, 0.9000F, -0.0873F, -0.6109F, 0.0000F));
        aleta_5.addChild("aleta_5_c30", ModelPartBuilder.create().uv(12, 42).cuboid(-0.1246F, 0.1075F, -0.0395F, 2.1250F, 0.0000F, 3.0000F, new Dilation(0.0F)), ModelTransform.of(-0.3000F, -0.1000F, 0.1000F, -0.0873F, -1.2217F, 0.0000F));
        ModelPartData parte_5 = parte_4.addChild("parte_5", ModelPartBuilder.create(), ModelTransform.origin(0.0000F, 2.0000F, 2.0000F));
        parte_5.addChild("parte_5_c5", ModelPartBuilder.create().uv(18, 19).cuboid(-0.5000F, -0.5417F, -0.1538F, 1.0000F, 1.0000F, 4.0000F, new Dilation(0.0F)), ModelTransform.of(0.5000F, 0.0000F, 3.0000F, 0.0873F, 0.0000F, 0.0000F));
        parte_5.addChild("parte_5_c36", ModelPartBuilder.create().uv(10, 59).cuboid(-0.1600F, 0.1000F, -3.2255F, 0.0000F, 1.0000F, 1.0000F, new Dilation(0.0F)), ModelTransform.of(0.7000F, -3.0417F, 6.5717F, 0.8290F, 0.0000F, 0.0000F));
        ModelPartData parte_6 = parte_4.addChild("parte_6", ModelPartBuilder.create(), ModelTransform.origin(0.5000F, 1.9606F, 8.3462F));
        parte_6.addChild("parte_6_c6", ModelPartBuilder.create().uv(0, 37).cuboid(-0.3500F, -0.3421F, 0.0468F, 0.7000F, 0.7000F, 3.0000F, new Dilation(0.0F)), ModelTransform.of(0.0000F, -0.2520F, 0.3451F, 0.3927F, 0.0000F, 0.0000F));
        ModelPartData parte_9 = parte_6.addChild("parte_9", ModelPartBuilder.create(), ModelTransform.origin(0.0000F, 3.4977F, 1.0000F));
        parte_9.addChild("parte_9_c37", ModelPartBuilder.create().uv(0, 0).cuboid(-13.7500F, 0.3579F, 0.0468F, 13.3000F, 0.0000F, 10.0000F, new Dilation(0.0F)), ModelTransform.of(7.1000F, -4.1497F, -0.5549F, 0.3927F, 0.0000F, 0.0000F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(DracoPezRenderState state) {
        super.setAngles(state);

        if (state.touchingWater) {
            // Follow the swimming direction (up / down)
            this.dracopez.pitch += state.pitch * (float) (Math.PI / 180.0);
            float headYaw = MathHelper.clamp(state.relativeHeadYaw, -25.0F, 25.0F);
            this.head.yaw += headYaw * (float) (Math.PI / 180.0);
        }

        if (state.activeAnimation == null) {
            return;
        }

        switch (state.activeAnimation) {
            case SWIM -> this.swimAnimation.apply(state.activeAnimationState, state.age, 1f);
            case SWIM_FAST -> this.swimAnimation.apply(state.activeAnimationState, state.age, 2f);
            case IDLE_WATER -> this.idleWaterAnimation.apply(state.activeAnimationState, state.age, 1f);
            case FLOP -> this.flopAnimation.apply(state.activeAnimationState, state.age, 1f);
        }
    }
}
