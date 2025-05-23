package holiday_mod.client.model.entity;

import holiday_mod.Main;
import holiday_mod.world.entity.ModEntityTypes;
import holiday_mod.world.entity.npc.ElflikeEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.model.GeoModel;

@OnlyIn(Dist.CLIENT)
public class ElflikeModel extends GeoModel<ElflikeEntity> {
    private static final ResourceLocation ELF_TEXTURE =
            ResourceLocation.tryBuild(Main.MOD_ID, "textures/entity/elflike/elf.png");

    private static final ResourceLocation ELF_MODEL =
            ResourceLocation.tryBuild(Main.MOD_ID, "geo/entity/elf.geo.json");

    private static final ResourceLocation ELF_ANIMATION =
            ResourceLocation.tryBuild(Main.MOD_ID, "animations/entity/elf.animation.json");

    @Override
    public ResourceLocation getTextureResource(ElflikeEntity animatable) {
        return getResourceLocation(animatable, ELF_TEXTURE);
    }

    @Override
    public ResourceLocation getModelResource(ElflikeEntity animatable) {
        return getResourceLocation(animatable, ELF_MODEL);
    }

    @Override
    public ResourceLocation getAnimationResource(ElflikeEntity animatable) {
        return getResourceLocation(animatable, ELF_ANIMATION);
    }

    private ResourceLocation getResourceLocation(ElflikeEntity animatable,
                                                 ResourceLocation elfResource) {
        @SuppressWarnings("unchecked") EntityType<ElflikeEntity> type =
                (EntityType<ElflikeEntity>) animatable.getType();
        if(ModEntityTypes.elfEntityType.get().equals(type))
        {
            return elfResource;
        }
        throw new IncompatibleClassChangeError();
    }
}
