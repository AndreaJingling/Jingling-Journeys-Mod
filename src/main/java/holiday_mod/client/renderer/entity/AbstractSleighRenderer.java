package holiday_mod.client.renderer.entity;

import holiday_mod.client.model.entity.AbstractSleighModel;
import holiday_mod.client.model.entity.ElflikeModel;
import holiday_mod.world.entity.npc.ElflikeEntity;
import holiday_mod.world.entity.vehicle.AbstractSleighEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class AbstractSleighRenderer
extends GeoEntityRenderer<AbstractSleighEntity> {
    public AbstractSleighRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AbstractSleighModel());
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AbstractSleighEntity entity) {
        return this.model.getTextureResource(entity);
    }
}
