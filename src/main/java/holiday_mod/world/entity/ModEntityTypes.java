package holiday_mod.world.entity;

import holiday_mod.world.entity.npc.ElfEntity;
import holiday_mod.world.entity.npc.KringleElfEntity;
import holiday_mod.world.entity.npc.RankinBassElfEntity;
import holiday_mod.world.entity.vehicle.GenericSmallSledEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static holiday_mod.Main.ENTITY_TYPES;

public class ModEntityTypes {
    public static final RegistryObject<EntityType<ElfEntity>> elfEntityType
            = registerEntity("elf",
            () -> EntityType.Builder.of(ElfEntity::new, MobCategory.MISC)
                    .sized(0.625F, 0.9375F)
                    .clientTrackingRange(10)
                    .build("elf"));
    public static final RegistryObject<EntityType<KringleElfEntity>> kringleElfEntityType
            = registerEntity("kringle_elf",
            () -> EntityType.Builder.of(KringleElfEntity::new, MobCategory.MISC)
                    .sized(0.625F, 0.9375F)
                    .clientTrackingRange(10)
                    .build("kringle_elf"));
    public static final RegistryObject<EntityType<RankinBassElfEntity>> rankinBassElfEntityType
            = registerEntity("rankin_bass_elf",
            () -> EntityType.Builder.of(RankinBassElfEntity::new, MobCategory.MISC)
                    .sized(0.625F, 0.9375F)
                    .clientTrackingRange(10)
                    .build("rankin_bass_elf"));
    public static final RegistryObject<EntityType<GenericSmallSledEntity>> genericSmallSledEntityType
            = registerEntity("generic_small_sled",
            () -> EntityType.Builder.
                            <GenericSmallSledEntity>of(GenericSmallSledEntity::new, MobCategory.MISC)
                    .sized(1.0F, 0.5F)
                    .clientTrackingRange(10)
                    .build("generic_small_sled"));

    public static void init() {
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends EntityType<?>> RegistryObject<T> registerEntity(String id, Supplier<T> block) {
        return ENTITY_TYPES.register(id, block);
    }
}
