package holiday_mod.client.model.entity;

import holiday_mod.Main;
import holiday_mod.registry.ModEntityTypes;
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
    private static final ResourceLocation KRINGLE_ELF_TEXTURE =
            ResourceLocation.tryBuild(Main.MOD_ID, "textures/entity/elflike/kringle_elf_temp.png");
    private static final ResourceLocation RANKIN_BASS_ELF_TEXTURE =
            ResourceLocation.tryBuild(Main.MOD_ID, "textures/entity/elflike/rankin_bass_elf_temp.png");

    private static final ResourceLocation ELF_MODEL =
            ResourceLocation.tryBuild(Main.MOD_ID, "geo/entity/elf.geo.json");
    private static final ResourceLocation KRINGLE_ELF_MODEL =
            ResourceLocation.tryBuild(Main.MOD_ID, "geo/entity/kringle_elf_temp.geo.json");
    private static final ResourceLocation RANKIN_BASS_ELF_MODEL =
            ResourceLocation.tryBuild(Main.MOD_ID, "geo/entity/rankin_bass_elf_temp.geo.json");

    private static final ResourceLocation ELF_ANIMATION =
            ResourceLocation.tryBuild(Main.MOD_ID, "animations/entity/elf.animation.json");
    private static final ResourceLocation KRINGLE_ELF_ANIMATION =
            ResourceLocation.tryBuild(Main.MOD_ID, "animations/entity/kringle_elf_temp.animation.json");
    private static final ResourceLocation RANKIN_BASS_ELF_ANIMATION =
            ResourceLocation.tryBuild(Main.MOD_ID, "animations/entity/rankin_bass_elf_temp.animation.json");

    @Override
    public ResourceLocation getTextureResource(ElflikeEntity animatable) {
        return getResourceLocation(animatable, ELF_TEXTURE, KRINGLE_ELF_TEXTURE, RANKIN_BASS_ELF_TEXTURE);
    }

    @Override
    public ResourceLocation getModelResource(ElflikeEntity animatable) {
        return getResourceLocation(animatable, ELF_MODEL, KRINGLE_ELF_MODEL, RANKIN_BASS_ELF_MODEL);
    }

    @Override
    public ResourceLocation getAnimationResource(ElflikeEntity animatable) {
        return getResourceLocation(animatable, ELF_ANIMATION, KRINGLE_ELF_ANIMATION, RANKIN_BASS_ELF_ANIMATION);
    }

    private ResourceLocation getResourceLocation(ElflikeEntity animatable,
                                                 ResourceLocation elfResource,
                                                 ResourceLocation kringleElfResource,
                                                 ResourceLocation rankinBassElfResource) {
        @SuppressWarnings("unchecked") EntityType<ElflikeEntity> type =
                (EntityType<ElflikeEntity>) animatable.getType();
        if(ModEntityTypes.elfEntityType.get().equals(type))
        {
            return elfResource;
        } else if (ModEntityTypes.kringleElfEntityType.get().equals(type)) {
            return kringleElfResource;
        } else if (ModEntityTypes.rankinBassElfEntityType.get().equals(type)) {
            return rankinBassElfResource;
        }
        throw new IncompatibleClassChangeError();
    }
}
