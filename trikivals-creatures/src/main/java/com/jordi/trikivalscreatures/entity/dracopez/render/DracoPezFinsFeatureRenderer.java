package com.jordi.trikivalscreatures.entity.dracopez.render;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

/**
 * Renders the fins layer (white/grey texture) tinted with the DracoPez fin color.
 * The base texture leaves the fin pixels transparent, so both layers never overlap.
 */
public class DracoPezFinsFeatureRenderer extends FeatureRenderer<DracoPezRenderState, DracoPezModel> {
    private static final Identifier FINS_TEXTURE = Identifier.of(TrikiValsCreatures.MOD_ID, "textures/entity/dracopez/dracopez_fins.png");

    public DracoPezFinsFeatureRenderer(FeatureRendererContext<DracoPezRenderState, DracoPezModel> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, DracoPezRenderState state,
                       float limbAngle, float limbDistance) {
        if (state.invisible) {
            return;
        }

        renderModel(this.getContextModel(), FINS_TEXTURE, matrices, queue, light, state, state.finColor, 1);
    }
}
