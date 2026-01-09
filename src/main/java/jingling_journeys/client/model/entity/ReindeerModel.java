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
import jingling_journeys.world.entity.animal.Reindeer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.model.GeoModel;

@OnlyIn(Dist.CLIENT)
public class ReindeerModel extends GeoModel<Reindeer> {
    private static final ResourceLocation REINDEER_TEXTURE =
            ResourceLocation.tryBuild(Main.MOD_ID, "textures/entity/reindeer/reindeer_normal.png");

    private static final ResourceLocation REINDEER_MODEL =
            ResourceLocation.tryBuild(Main.MOD_ID, "geo/entity/reindeer.geo.json");

    private static final ResourceLocation REINDEER_ANIMATION =
            ResourceLocation.tryBuild(Main.MOD_ID, "animations/entity/reindeer.animation.json");

    @Override
    public ResourceLocation getTextureResource(Reindeer animatable) {
        return REINDEER_TEXTURE; // TODO Implement Textures
    }

    @Override
    public ResourceLocation getModelResource(Reindeer animatable) {
        return REINDEER_MODEL;
    }

    @Override
    public ResourceLocation getAnimationResource(Reindeer animatable) {
        return REINDEER_ANIMATION;
    }
}
