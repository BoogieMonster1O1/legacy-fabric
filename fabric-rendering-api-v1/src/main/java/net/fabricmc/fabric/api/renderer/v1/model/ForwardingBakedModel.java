/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.api.renderer.v1.model;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import com.google.common.annotations.Beta;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;

@Beta
public abstract class ForwardingBakedModel implements BakedModel, FabricBakedModel {
	/** implementations must set this somehow. */
	protected BakedModel wrapped;

	@Override
	public void emitBlockQuads(WorldView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
		((FabricBakedModel) wrapped).emitBlockQuads(blockView, state, pos, randomSupplier, context);
	}

	@Override
	public boolean isVanillaAdapter() {
		return ((FabricBakedModel) wrapped).isVanillaAdapter();
	}

	@Override
	public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
		((FabricBakedModel) wrapped).emitItemQuads(stack, randomSupplier, context);
	}

	@Override
	public boolean useAmbientOcclusion() {
		return wrapped.useAmbientOcclusion();
	}

	@Override
	public List<BakedQuad> method_4453(Direction direction) {
		return wrapped.getQuads();
	}

	@Override
	public List<BakedQuad> getQuads() {
		return wrapped.getQuads();
	}

	@Override
	public boolean hasDepth() {
		return false;
	}

	@Override
	public boolean isBuiltin() {
		return wrapped.isBuiltin();
	}

	@Override
	public Sprite getSprite() {
		return wrapped.getSprite();
	}

	@Override
	public ModelTransformation getTransformation() {
		return wrapped.getTransformation();
	}
}
