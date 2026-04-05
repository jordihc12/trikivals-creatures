package com.jordi.trikivalscreatures.entity.pejelagarto.anims;

import net.minecraft.client.render.entity.animation.*;

public class PejelagartoAnimations {
        public static final AnimationDefinition PEJELAGARTO_IDLE = AnimationDefinition.Builder.create(1.5F).looping()
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.9167F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.125F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.3333F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.375F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("torso", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.375F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("jaw", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.9167F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.125F, AnimationHelper.createRotationalVector(27.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.3333F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("jaw", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.9167F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.125F, AnimationHelper.createTranslationalVector(0.0F, -0.4F, 0.35F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.3333F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition PEJELAGARTO_WALK = AnimationDefinition.Builder.create(1.0F).looping()
                .addBoneAnimation("left_first", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(17.5F, 0.0F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(-17.5F, 0.0F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_second", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(-17.5F, 0.0F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(17.5F, 0.0F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_third", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(17.5F, 0.0F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(-17.5F, 0.0F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_fourth", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(-17.5F, 0.0F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(17.5F, 0.0F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_first", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(-17.5F, 0.0F, 10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(17.5F, 0.0F, 10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_second", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(17.5F, 0.0F, 10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(-17.5F, 0.0F, 10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_third", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(-17.5F, 0.0F, 10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(17.5F, 0.0F, 10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_fourth", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(17.5F, 0.0F, 10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(-17.5F, 0.0F, 10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_1", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(-12.5F, 12.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(-12.5F, -12.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_1", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -0.375F, 0.25F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.25F, -0.375F, -0.15F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.375F, 0.25F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createTranslationalVector(-0.25F, -0.375F, -0.15F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.375F, 0.25F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_2", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-12.5F, -12.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(-12.5F, 12.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-12.5F, -12.5F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_2", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.3091F, -0.3F, 0.1216F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.3F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.2929F, -0.3F, 0.0649F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.3F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-0.3091F, -0.3F, 0.1216F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_3", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(-12.5F, -12.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(-12.5F, 12.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_3", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -0.3F, 0.2F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createTranslationalVector(-0.3139F, -0.3F, 0.0994F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.3F, 0.2F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.3111F, -0.3F, 0.1244F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.3F, 0.2F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(2.2718F, 7.0825F, -1.3039F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(2.3028F, -7.3173F, -2.8867F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition PEJELAGARTO_SWIM = AnimationDefinition.Builder.create(1.0F).looping()
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -0.6F, -0.7F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.6F, -0.7F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_legs", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -2.5F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 2.5F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, -2.5F, -10.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_legs", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.2F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.2F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_first", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(47.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(47.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(47.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_second", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(52.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(47.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(52.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(47.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(52.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_third", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(50.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(50.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(50.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_fourth", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(62.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(62.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(62.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_legs", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -2.5F, 15.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 2.5F, 15.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, -2.5F, 15.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_legs", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-0.3F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-0.3F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_first", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(55.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(55.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(55.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_second", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(55.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(55.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_third", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(57.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(47.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(57.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(47.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(57.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_fourth", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(55.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(67.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(55.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(67.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(55.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("torso", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, -2.5024F, -0.1091F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 2.5F, -0.4352F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, -2.5024F, -0.1091F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_1", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-12.5F, -7.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(-12.5F, 7.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-12.5F, -7.5F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_1", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.3F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.3F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_2", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-15.1212F, 17.4828F, -0.7879F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(-15.0034F, -10.0F, 0.3262F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(-15.0881F, -14.9854F, 0.9987F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(-15.1212F, 10.0F, -0.7879F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-15.1212F, 17.4828F, -0.7879F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_2", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -0.35F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.35F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -0.35F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -0.35F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.35F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_3", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-6.9861F, 27.8984F, 3.6651F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(-6.867F, -20.3866F, -2.5978F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(-7.1809F, -27.0F, -3.1547F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createRotationalVector(-6.9861F, 20.8984F, 3.6651F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-6.9861F, 27.8984F, 3.6651F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_3", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.6F, -0.2F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createTranslationalVector(-0.6F, -0.2F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(-0.6F, -0.2F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.6F, -0.2F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.6F, -0.2F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition PEJELAGARTO_IDLE_WATER = AnimationDefinition.Builder.create(2.0F).looping()
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("mouth", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_legs", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -47.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -47.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -47.5F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_legs", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(2.3F, -1.8F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(2.3F, -0.8F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(2.3F, -1.8F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createTranslationalVector(2.3F, -2.3F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createTranslationalVector(2.3F, -1.8F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_first", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -12.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -5.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_second", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 7.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -37.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_third", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -25.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -20.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_fourth", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(27.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(19.8423F, 2.5587F, -7.0524F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(27.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(26.4032F, 7.9813F, -15.6249F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(27.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_legs", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 47.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 47.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 47.5F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_legs", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-2.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(-2.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-2.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createTranslationalVector(-2.0F, -1.5F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createTranslationalVector(-2.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_first", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -12.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 22.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_second", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 20.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -10.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_third", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 22.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 25.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_fourth", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(27.3593F, -2.2494F, 7.1566F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(34.3569F, -7.1314F, 10.2928F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("torso", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("torso", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.6F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createTranslationalVector(0.0F, -0.4F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_1", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-7.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.0F, AnimationHelper.createRotationalVector(-7.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(-2.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(-7.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_2", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_3", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(1.5F, AnimationHelper.createRotationalVector(7.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition PEJELAGARTO_FALL = AnimationDefinition.Builder.create(0.5F).looping()
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-35.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.125F, AnimationHelper.createRotationalVector(-36.6915F, 16.2701F, -11.792F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.375F, AnimationHelper.createRotationalVector(-36.6839F, -16.2753F, 11.7053F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(-35.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("jaw", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(27.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("jaw", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.5F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_legs", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, -67.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_legs", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(3.5F, -2.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_fourth", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(35.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("left_fourth", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.5F, -0.5F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_legs", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-1.9176F, 4.6185F, 67.4227F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_legs", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-3.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_fourth", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("right_fourth", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.5F, -0.5F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("torso", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_1", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .build();

        public static final AnimationDefinition PEJELAGARTO_ATTACK = AnimationDefinition.Builder.create(0.5F)
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, -0.7F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("jaw", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(32.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("jaw", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                        new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.5F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("torso", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(10.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .addBoneAnimation("tail_1", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.25F, AnimationHelper.createRotationalVector(10.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                        new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
                ))
                .build();

    public static final AnimationDefinition PEJELAGARTO_SIT = AnimationDefinition.Builder.create(1.25F)
            .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.3333F, AnimationHelper.createRotationalVector(-22.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.625F, AnimationHelper.createRotationalVector(-40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, -20.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(0.0F, -20.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("head", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.3333F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, -0.5F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.625F, AnimationHelper.createTranslationalVector(0.0F, -1.5F, 0.2F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -2.0F, -1.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -2.0F, -1.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("jaw", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.3333F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.625F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("jaw", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.3333F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.5F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.625F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.5F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("left_legs", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -42.5F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -42.5F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("left_legs", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(2.0F, -2.5F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(2.0F, -2.5F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("right_legs", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 50.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 50.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("right_legs", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(-2.0F, -1.8F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(-2.0F, -1.8F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("torso", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.3333F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.625F, AnimationHelper.createTranslationalVector(0.0F, -1.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail_1", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.3333F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.625F, AnimationHelper.createRotationalVector(-15.0881F, 14.9854F, -0.6696F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail_1", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0.3333F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.625F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail_2", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.625F, AnimationHelper.createRotationalVector(15.0F, 20.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-12.5F, 20.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-12.5F, 20.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail_2", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail_3", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.625F, AnimationHelper.createRotationalVector(12.5F, 17.5F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-23.7921F, 32.2456F, -3.0461F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createRotationalVector(-23.7921F, 32.2456F, -3.0461F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail_3", new Transformation(Transformation.Targets.MOVE_ORIGIN,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.625F, AnimationHelper.createTranslationalVector(0.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(1.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.25F, AnimationHelper.createTranslationalVector(1.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .build();
    }
