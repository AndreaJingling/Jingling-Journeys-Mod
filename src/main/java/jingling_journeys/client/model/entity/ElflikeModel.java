/**
 *         Jingling Journeys - Give your Minecraft World a little Festive Cheer, Sleigh Riding Fun, and 
 *         The Spirit of the Season.<br>
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

package jingling_journeys.client.model.entity;

import jingling_journeys.Main;
import jingling_journeys.world.entity.ModEntityTypes;
import jingling_journeys.world.entity.npc.Elflike;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.model.GeoModel;

@OnlyIn(Dist.CLIENT)
public class ElflikeModel extends GeoModel<Elflike> {
    private static final ResourceLocation ELF_TEXTURE =
            ResourceLocation.tryBuild(Main.MOD_ID, "textures/entity/elflike/elf.png");

    private static final ResourceLocation ELF_MODEL =
            ResourceLocation.tryBuild(Main.MOD_ID, "geo/entity/elf.geo.json");

    private static final ResourceLocation ELF_ANIMATION =
            ResourceLocation.tryBuild(Main.MOD_ID, "animations/entity/elf.animation.json");

    @Override
    public ResourceLocation getTextureResource(Elflike animatable) {
        return getResourceLocation(animatable, ELF_TEXTURE);
    }

    @Override
    public ResourceLocation getModelResource(Elflike animatable) {
        return getResourceLocation(animatable, ELF_MODEL);
    }

    @Override
    public ResourceLocation getAnimationResource(Elflike animatable) {
        return getResourceLocation(animatable, ELF_ANIMATION);
    }

    private ResourceLocation getResourceLocation(Elflike animatable,
                                                 ResourceLocation elfResource) {
        @SuppressWarnings("unchecked") EntityType<Elflike> type =
                (EntityType<Elflike>) animatable.getType();
        if(ModEntityTypes.elfEntityType.get().equals(type))
        {
            return elfResource;
        }
        throw new IncompatibleClassChangeError();
    }
}
