package com.jordi.trikivalscreatures.entity.dracopez.render;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

/**
 * Emissive layer (glows in the dark like spider eyes): eyes, spine tips and lateral line dots.
 */
public class DracoPezGlowFeatureRenderer extends EyesFeatureRenderer<DracoPezRenderState, DracoPezModel> {
    private static final RenderLayer GLOW = RenderLayers.eyes(
            Identifier.of(TrikiValsCreatures.MOD_ID, "textures/entity/dracopez/dracopez_glow.png"));

    public DracoPezGlowFeatureRenderer(FeatureRendererContext<DracoPezRenderState, DracoPezModel> context) {
        super(context);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return GLOW;
    }
}
