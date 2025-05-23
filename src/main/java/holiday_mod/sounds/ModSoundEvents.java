package holiday_mod.sounds;

import holiday_mod.Main;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class ModSoundEvents {
    public static final RegistryObject<SoundEvent> FEED_TRAY_MUNCH = register("block.feed_tray.munch");
    public static final RegistryObject<SoundEvent> FIREPLACE_BREAK = register("block.fireplace.break");
    public static final RegistryObject<SoundEvent> FIREPLACE_EXTINGUISH = register("block.fireplace.extinguish");
    public static final RegistryObject<SoundEvent> FIREPLACE_IGNITE = register("block.fireplace.ignite");
    public static final RegistryObject<SoundEvent> FIREPLACE_PLACE = register("block.fireplace.place");
    public static final RegistryObject<SoundEvent> REIN_ATTACH = register("entity.rein.attach");
    public static final RegistryObject<SoundEvent> REIN_ATTACH_BELLS = register("entity.rein.attach_bells");
    public static final RegistryObject<SoundEvent> REINDEER_DEATH = register("entity.reindeer.death");
    public static final RegistryObject<SoundEvent> REINDEER_HURT = register("entity.reindeer.hurt");
    public static final RegistryObject<SoundEvent> REINDEER_IDLE = register("entity.reindeer.idle");
    public static final RegistryObject<SoundEvent> REINDEER_WALKING = register("entity.reindeer.walking");
    public static final RegistryObject<SoundEvent> SLEIGH_SLED_OTHER = register("entity.sleigh.sled_other");
    public static final RegistryObject<SoundEvent> SLEIGH_SLED_SNOW = register("entity.sleigh.sled_snow");
    public static final RegistryObject<SoundEvent> TOY_TRAIN_IDLE_MUSIC = register("entity.toy_train.idle_music");
    public static final RegistryObject<SoundEvent> TOY_TRAIN_IDLE_NO_MUSIC
            = register("entity.toy_train.idle_no_music");
    public static final RegistryObject<SoundEvent> BAG_CLOSE = register("item.bag.close");
    public static final RegistryObject<SoundEvent> BAG_INSERT = register("item.bag.insert");
    public static final RegistryObject<SoundEvent> BAG_OPEN = register("item.bag.open");
    public static final RegistryObject<SoundEvent> MUSIC_DISC_CAROL_OF_THE_TIM_TAMS
            = register("music_disc.carol_of_the_tim_tams");
    public static final RegistryObject<SoundEvent> SLEIGH_BELLS_JANGLE = register("sleigh_bells.jangle");
    public static final RegistryObject<SoundEvent> SLEIGH_BELLS_PLACE = register("sleigh_bells.place");

    public static RegistryObject<SoundEvent> register(String id) {
        return Main.SOUND_EVENTS.register(id, () -> SoundEvent.createVariableRangeEvent(
                Objects.requireNonNull(ResourceLocation.tryBuild(Main.MOD_ID, id))));
    }

    public static void init() {
    }
}
