package com.jordi.trikivalscreatures.entity.dracopez.render;

import com.jordi.trikivalscreatures.TrikiValsCreatures;
import com.jordi.trikivalscreatures.entity.dracopez.DracoPezEntity;
import com.jordi.trikivalscreatures.entity.dracopez.anims.DracoPezAnimatorManager;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

public class DracoPezRenderer extends MobEntityRenderer<DracoPezEntity, DracoPezRenderState, DracoPezModel> {
    private static final Identifier TEXTURE = Identifier.of(TrikiValsCreatures.MOD_ID, "textures/entity/dracopez/dracopez.png");

    public DracoPezRenderer(EntityRendererFactory.Context context) {
        super(context, new DracoPezModel(context.getPart(DracoPezModel.DRACOPEZ)), 0.35f);

        // Fins: white texture tinted with the dye color (like sheep wool)
        this.addFeature(new DracoPezFinsFeatureRenderer(this));
        // Bioluminescent eyes, spines and lateral line
        this.addFeature(new DracoPezGlowFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(DracoPezRenderState state) {
        return TEXTURE;
    }

    @Override
    public DracoPezRenderState createRenderState() {
        return new DracoPezRenderState();
    }

    @Override
    public void updateRenderState(DracoPezEntity entity, DracoPezRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);

        DracoPezAnimatorManager manager = entity.getAnimationManager();
        var activeAnimation = manager.getActiveAnim();
        state.activeAnimation = activeAnimation;
        state.activeAnimationState.copyFrom(manager.getAnimationStateFor(activeAnimation));

        state.finColor = ColorHelper.fullAlpha(entity.getFinColor().getEntityColor());
    }
}
