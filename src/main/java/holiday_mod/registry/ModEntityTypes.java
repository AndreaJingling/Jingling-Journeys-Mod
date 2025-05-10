package holiday_mod.registry;

import holiday_mod.world.entity.npc.ElfEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static holiday_mod.Main.ENTITY_TYPES;

public class ModEntityTypes {
    public static final RegistryObject<EntityType<ElfEntity>> elfEntityType = registerEntity("elf",
            () -> EntityType.Builder.of(ElfEntity::new, MobCategory.MISC)
                    .sized(0.6F, 1.95F)
                    .clientTrackingRange(10)
                    .build("elf"));

    public static void init() {
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends EntityType<?>> RegistryObject<T> registerEntity(String id, Supplier<T> block) {
        return ENTITY_TYPES.register(id, block);
    }
}
