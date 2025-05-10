package holiday_mod.client.renderer.entity;

import holiday_mod.client.model.entity.ElflikeModel;
import holiday_mod.world.entity.npc.ElflikeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class ElflikeRenderer
extends GeoEntityRenderer<ElflikeEntity> {
    public ElflikeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ElflikeModel());
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ElflikeEntity entity) {
        return this.model.getTextureResource(entity);
    }
}
