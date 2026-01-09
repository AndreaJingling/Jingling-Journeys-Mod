/**
 *         Jingling Journeys (WIP description)<br>
 *         Copyright (C) 2025-2026  Jingling Journeys Team<br>
 *         <br>
 *         This file part of Jingling Journeys.<br>
 *         <br>
 *         This program is free software: you can redistribute it and/or modify<br>
 *         it under the terms of the GNU Lesser General Public License as published by<br>
 *         the Free Software Foundation, either version 3 of the License, or<br>
 *         (at your option) any later version.<br>
 *         <br>
 *         This program is distributed in the hope that it will be useful,<br>
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of<br>
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the<br>
 *         GNU Lesser General Public License for more details.<br>
 *         <br>
 *         You should have received a copy of the GNU Lesser General Public License<br>
 *         along with this program.  If not, see <https://www.gnu.org/licenses/>.<br>
 */

package jingling_journeys.sounds;

import jingling_journeys.Main;
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

    // Used to load this class at the needed time from main.
    public static void init() {
    }
}
