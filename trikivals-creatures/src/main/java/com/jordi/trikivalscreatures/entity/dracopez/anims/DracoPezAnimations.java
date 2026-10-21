package com.jordi.trikivalscreatures.entity.dracopez.anims;

import net.minecraft.client.render.entity.animation.*;

/**
 * Generated from DracoPez.bbmodel (animations dracopez_swim, dracopez_idle_water, dracopez_flop).
 * The swim animation is an undulating wave that travels from the head to the tail fan,
 * with the two pairs of wings flapping out of phase.
 */
public class DracoPezAnimations {
    public static final AnimationDefinition DRACOPEZ_SWIM = AnimationDefinition.Builder.create(1.0000F).looping()
                .addBoneAnimation("ala_13", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -28.519F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -9.902F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 14.515F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 30.429F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 28.519F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 9.902F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -14.515F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -30.429F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -28.519F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("ala_14", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 28.519F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 9.902F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -14.515F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -30.429F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -28.519F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -9.902F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 14.515F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 30.429F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 28.519F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("ala_5", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -22.627F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -32.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -22.627F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 22.627F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 32.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 22.627F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("ala_6", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 22.627F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 32.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 22.627F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -22.627F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -32.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -22.627F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("aleta_1", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(2.364F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(7.076F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(7.643F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(3.732F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(-2.364F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(-7.076F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(-7.643F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(-3.732F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(2.364F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("aleta_2", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 10.307F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 15.942F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 12.237F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 1.365F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -10.307F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -15.942F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -12.237F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -1.365F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 10.307F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("aleta_3", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -10.307F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -15.942F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -12.237F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -1.365F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 10.307F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 15.942F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 12.237F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 1.365F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -10.307F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("aleta_4", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, -15.523F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(0.000F, -18.966F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(0.000F, -11.299F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 2.987F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(0.000F, 15.523F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(0.000F, 18.966F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 11.299F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(0.000F, -2.987F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(0.000F, -15.523F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("aleta_5", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 15.523F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(0.000F, 18.966F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(0.000F, 11.299F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, -2.987F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(0.000F, -15.523F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(0.000F, -18.966F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, -11.299F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(0.000F, 2.987F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(0.000F, 15.523F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("dracopez", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.876F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(2.085F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(2.072F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.846F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(-0.876F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(-2.085F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(-2.072F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(-0.846F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(0.876F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(-4.230F, -1.381F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(-5.365F, -0.405F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(-3.357F, 0.808F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.617F, 1.548F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(4.230F, 1.381F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(5.365F, 0.405F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(3.357F, -0.808F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(-0.617F, -1.548F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(-4.230F, -1.381F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_1", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 3.728F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(6.364F, 3.661F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(9.000F, 1.449F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(6.364F, -1.611F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(0.000F, -3.728F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(-6.364F, -3.661F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(-9.000F, -1.449F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(-6.364F, 1.611F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(-0.000F, 3.728F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_2", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(-7.050F, 1.182F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(-1.029F, 3.538F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(5.594F, 3.821F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(8.941F, 1.866F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(7.050F, -1.182F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(1.029F, -3.538F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(-5.594F, -3.821F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(-8.941F, -1.866F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(-7.050F, 1.182F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_3", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(-8.765F, -2.259F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(-7.643F, 0.737F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(-2.045F, 3.301F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(4.752F, 3.931F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(8.765F, 2.259F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(7.643F, -0.737F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(2.045F, -3.301F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(-4.752F, -3.931F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(-8.765F, -2.259F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_4", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(-3.846F, -3.990F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(-8.473F, -2.621F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(-8.137F, 0.283F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(-3.034F, 3.021F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(3.846F, 3.990F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(8.473F, 2.621F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(8.137F, -0.283F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(3.034F, -3.021F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(-3.846F, -3.990F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_6", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(3.983F, -2.702F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(-2.891F, -3.996F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(-8.071F, -2.950F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(-8.523F, -0.175F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(-3.983F, 2.702F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(2.891F, 3.996F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(8.071F, 2.950F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(8.523F, 0.175F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(3.983F, -2.702F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_9", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(14.076F, 0.631F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1250F, AnimationHelper.createRotationalVector(7.807F, -2.347F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2500F, AnimationHelper.createRotationalVector(-3.035F, -3.950F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(-12.100F, -3.239F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5000F, AnimationHelper.createRotationalVector(-14.076F, -0.631F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6250F, AnimationHelper.createRotationalVector(-7.807F, 2.347F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(3.035F, 3.950F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.8750F, AnimationHelper.createRotationalVector(12.100F, 3.239F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.0000F, AnimationHelper.createRotationalVector(14.076F, 0.631F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .build();

    public static final AnimationDefinition DRACOPEZ_IDLE_WATER = AnimationDefinition.Builder.create(3.0000F).looping()
                .addBoneAnimation("ala_13", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -10.694F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -3.713F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 5.443F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 11.411F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 10.694F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 3.713F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -5.443F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -11.411F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -10.694F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("ala_14", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 10.694F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 3.713F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -5.443F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -11.411F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -10.694F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -3.713F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 5.443F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 11.411F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 10.694F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("ala_5", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -8.485F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -12.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -8.485F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 8.485F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 12.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 8.485F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("ala_6", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 8.485F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 12.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 8.485F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -8.485F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -12.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -8.485F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("aleta_1", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.887F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(2.653F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(2.866F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(1.400F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(-0.887F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(-2.653F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(-2.866F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(-1.400F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(0.887F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("aleta_2", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 3.865F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 5.978F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 4.589F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 0.512F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -3.865F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -5.978F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -4.589F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -0.512F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 3.865F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("aleta_3", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -3.865F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -5.978F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -4.589F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -0.512F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 3.865F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 5.978F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 4.589F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 0.512F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -3.865F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("aleta_4", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, -5.821F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, -7.112F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, -4.237F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(0.000F, 1.120F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(0.000F, 5.821F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(0.000F, 7.112F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(0.000F, 4.237F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(0.000F, -1.120F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(0.000F, -5.821F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("aleta_5", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 5.821F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 7.112F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.000F, 4.237F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(0.000F, -1.120F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(0.000F, -5.821F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(0.000F, -7.112F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(0.000F, -4.237F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(0.000F, 1.120F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(0.000F, 5.821F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("dracopez", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.389F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.927F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(0.921F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(0.376F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(-0.389F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(-0.927F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(-0.921F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(-0.376F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(0.389F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(-1.880F, -0.863F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(-2.384F, -0.253F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(-1.492F, 0.505F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(0.274F, 0.967F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(1.880F, 0.863F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(2.384F, 0.253F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(1.492F, -0.505F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(-0.274F, -0.967F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(-1.880F, -0.863F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_1", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 2.330F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(2.828F, 2.288F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(4.000F, 0.906F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(2.828F, -1.007F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(0.000F, -2.330F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(-2.828F, -2.288F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(-4.000F, -0.906F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(-2.828F, 1.007F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(-0.000F, 2.330F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_2", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(-3.133F, 0.739F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(-0.457F, 2.211F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(2.486F, 2.388F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(3.974F, 1.166F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(3.133F, -0.739F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(0.457F, -2.211F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(-2.486F, -2.388F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(-3.974F, -1.166F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(-3.133F, 0.739F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_3", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(-3.895F, -1.412F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(-3.397F, 0.461F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(-0.909F, 2.063F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(2.112F, 2.457F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(3.895F, 1.412F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(3.397F, -0.461F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(0.909F, -2.063F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(-2.112F, -2.457F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(-3.895F, -1.412F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_4", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(-1.710F, -2.494F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(-3.766F, -1.638F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(-3.616F, 0.177F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(-1.348F, 1.888F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(1.710F, 2.494F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(3.766F, 1.638F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(3.616F, -0.177F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(1.348F, -1.888F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(-1.710F, -2.494F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_6", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(1.770F, -1.689F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(-1.285F, -2.498F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(-3.587F, -1.843F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(-3.788F, -0.109F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(-1.770F, 1.689F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(1.285F, 2.498F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(3.587F, 1.843F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(3.788F, 0.109F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(1.770F, -1.689F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_9", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(6.256F, 0.394F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(3.470F, -1.467F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.7500F, AnimationHelper.createRotationalVector(-1.349F, -2.469F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.1250F, AnimationHelper.createRotationalVector(-5.378F, -2.024F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.5000F, AnimationHelper.createRotationalVector(-6.256F, -0.394F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(1.8750F, AnimationHelper.createRotationalVector(-3.470F, 1.467F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.2500F, AnimationHelper.createRotationalVector(1.349F, 2.469F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(2.6250F, AnimationHelper.createRotationalVector(5.378F, 2.024F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(3.0000F, AnimationHelper.createRotationalVector(6.256F, 0.394F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .build();

    public static final AnimationDefinition DRACOPEZ_FLOP = AnimationDefinition.Builder.create(0.6000F).looping()
                .addBoneAnimation("ala_13", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 6.585F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.0750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 9.597F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 23.415F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 20.403F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 6.585F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 9.597F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.4500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 23.415F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 20.403F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 6.585F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("ala_14", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -6.585F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.0750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -9.597F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -23.415F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -20.403F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -6.585F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -9.597F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.4500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -23.415F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -20.403F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -6.585F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("ala_5", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -20.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.0750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -8.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -20.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -32.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -20.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -8.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.4500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -20.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -32.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, -20.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("ala_6", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 20.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.0750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 8.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 20.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 32.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 20.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 8.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.4500F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 20.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5250F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 32.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 20.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, -11.596F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.0750F, AnimationHelper.createRotationalVector(0.000F, -17.934F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1500F, AnimationHelper.createRotationalVector(0.000F, -13.767F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2250F, AnimationHelper.createRotationalVector(0.000F, -1.535F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3000F, AnimationHelper.createRotationalVector(0.000F, 11.596F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(0.000F, 17.934F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.4500F, AnimationHelper.createRotationalVector(0.000F, 13.767F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5250F, AnimationHelper.createRotationalVector(0.000F, 1.535F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6000F, AnimationHelper.createRotationalVector(0.000F, -11.596F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("jaw", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(20.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.0750F, AnimationHelper.createRotationalVector(35.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1500F, AnimationHelper.createRotationalVector(20.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2250F, AnimationHelper.createRotationalVector(5.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3000F, AnimationHelper.createRotationalVector(20.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(35.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.4500F, AnimationHelper.createRotationalVector(20.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5250F, AnimationHelper.createRotationalVector(5.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6000F, AnimationHelper.createRotationalVector(20.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_1", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(0.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.0750F, AnimationHelper.createRotationalVector(4.000F, 15.556F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1500F, AnimationHelper.createRotationalVector(0.000F, 22.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2250F, AnimationHelper.createRotationalVector(-4.000F, 15.556F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3000F, AnimationHelper.createRotationalVector(-0.000F, 0.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(4.000F, -15.556F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.4500F, AnimationHelper.createRotationalVector(0.000F, -22.000F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5250F, AnimationHelper.createRotationalVector(-4.000F, -15.556F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6000F, AnimationHelper.createRotationalVector(-0.000F, -0.000F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_2", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(-3.366F, -14.173F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.0750F, AnimationHelper.createRotationalVector(2.161F, 1.876F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1500F, AnimationHelper.createRotationalVector(3.366F, 16.827F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2250F, AnimationHelper.createRotationalVector(-2.161F, 21.920F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3000F, AnimationHelper.createRotationalVector(-3.366F, 14.173F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(2.161F, -1.876F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.4500F, AnimationHelper.createRotationalVector(3.366F, -16.827F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5250F, AnimationHelper.createRotationalVector(-2.161F, -21.920F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6000F, AnimationHelper.createRotationalVector(-3.366F, -14.173F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_3", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(-3.637F, -21.680F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.0750F, AnimationHelper.createRotationalVector(-1.665F, -12.686F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1500F, AnimationHelper.createRotationalVector(3.637F, 3.739F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2250F, AnimationHelper.createRotationalVector(1.665F, 17.974F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3000F, AnimationHelper.createRotationalVector(-3.637F, 21.680F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(-1.665F, 12.686F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.4500F, AnimationHelper.createRotationalVector(3.637F, -3.739F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5250F, AnimationHelper.createRotationalVector(1.665F, -17.974F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6000F, AnimationHelper.createRotationalVector(-3.637F, -21.680F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_4", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(-0.564F, -18.991F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.0750F, AnimationHelper.createRotationalVector(-3.960F, -21.282F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1500F, AnimationHelper.createRotationalVector(0.564F, -11.107F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2250F, AnimationHelper.createRotationalVector(3.960F, 5.575F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3000F, AnimationHelper.createRotationalVector(-0.564F, 18.991F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(-3.960F, 21.282F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.4500F, AnimationHelper.createRotationalVector(0.564F, 11.107F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5250F, AnimationHelper.createRotationalVector(3.960F, -5.575F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6000F, AnimationHelper.createRotationalVector(-0.564F, -18.991F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_6", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(3.027F, -7.370F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.0750F, AnimationHelper.createRotationalVector(-2.615F, -19.869F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1500F, AnimationHelper.createRotationalVector(-3.027F, -20.729F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2250F, AnimationHelper.createRotationalVector(2.615F, -9.446F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3000F, AnimationHelper.createRotationalVector(3.027F, 7.370F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(-2.615F, 19.869F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.4500F, AnimationHelper.createRotationalVector(-3.027F, 20.729F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5250F, AnimationHelper.createRotationalVector(2.615F, 9.446F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6000F, AnimationHelper.createRotationalVector(3.027F, -7.370F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .addBoneAnimation("parte_9", new Transformation(Transformation.Targets.ROTATE,
                        new Keyframe(0.0000F, AnimationHelper.createRotationalVector(3.836F, 7.717F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.0750F, AnimationHelper.createRotationalVector(1.135F, -9.111F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.1500F, AnimationHelper.createRotationalVector(-3.836F, -20.602F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.2250F, AnimationHelper.createRotationalVector(-1.135F, -20.025F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3000F, AnimationHelper.createRotationalVector(3.836F, -7.717F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.3750F, AnimationHelper.createRotationalVector(1.135F, 9.111F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.4500F, AnimationHelper.createRotationalVector(-3.836F, 20.602F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.5250F, AnimationHelper.createRotationalVector(-1.135F, 20.025F, 0.000F), Transformation.Interpolations.CUBIC),
                        new Keyframe(0.6000F, AnimationHelper.createRotationalVector(3.836F, 7.717F, 0.000F), Transformation.Interpolations.CUBIC)
                ))
                .build();
}
