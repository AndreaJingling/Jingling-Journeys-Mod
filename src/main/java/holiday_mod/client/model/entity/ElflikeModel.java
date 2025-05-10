package holiday_mod.client.model.entity;

import holiday_mod.Main;
import holiday_mod.registry.ModEntityTypes;
import holiday_mod.world.entity.npc.ElflikeEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import software.bernie.geckolib.model.GeoModel;

public class ElflikeModel extends GeoModel<ElflikeEntity> {
    private static final ResourceLocation ELF_TEXTURE =
            ResourceLocation.tryBuild(Main.MOD_ID, "textures/entity/elflike/elf.png");
    private static final ResourceLocation ELF_MODEL =
            ResourceLocation.tryBuild(Main.MOD_ID, "geo/entity/elf.geo.json");
    private static final ResourceLocation ELF_ANIMATION =
            ResourceLocation.tryBuild(Main.MOD_ID, "animations/entity/elf.animation.json");

    @Override
    public ResourceLocation getTextureResource(ElflikeEntity animatable) {
        @SuppressWarnings("unchecked") EntityType<ElflikeEntity> type =
                (EntityType<ElflikeEntity>) animatable.getType();
        if(ModEntityTypes.elfEntityType.get().equals(type))
        {
            return ELF_TEXTURE;
        }
        throw new IncompatibleClassChangeError();
    }

    @Override
    public ResourceLocation getModelResource(ElflikeEntity animatable) {
        @SuppressWarnings("unchecked") EntityType<ElflikeEntity> type =
                (EntityType<ElflikeEntity>) animatable.getType();
        if(ModEntityTypes.elfEntityType.get().equals(type))
        {
            return ELF_MODEL;
        }
        throw new IncompatibleClassChangeError();
    }

    @Override
    public ResourceLocation getAnimationResource(ElflikeEntity animatable) {
        @SuppressWarnings("unchecked") EntityType<ElflikeEntity> type =
                (EntityType<ElflikeEntity>) animatable.getType();
        if(ModEntityTypes.elfEntityType.get().equals(type))
        {
            return ELF_ANIMATION;
        }
        throw new IncompatibleClassChangeError();
    }
}
