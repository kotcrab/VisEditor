/*
 * Copyright 2014-2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kotcrab.vis.editor.module.scene.system.inflater;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.kotcrab.vis.editor.entity.SpriterPropertiesComponent;
import com.kotcrab.vis.editor.module.project.SpriterCacheModule;
import com.kotcrab.vis.runtime.assets.SpriterAsset;
import com.kotcrab.vis.runtime.component.AssetComponent;
import com.kotcrab.vis.runtime.component.SpriterComponent;
import com.kotcrab.vis.runtime.component.SpriterProtoComponent;
import com.kotcrab.vis.runtime.system.inflater.InflaterSystem;

/** @author Kotcrab */
public class EditorSpriterInflater extends InflaterSystem {
	private ComponentMapper<SpriterProtoComponent> protoCm;
	private ComponentMapper<SpriterPropertiesComponent> propertiesCm;
	private ComponentMapper<AssetComponent> assetCm;
	private SpriterCacheModule cache;

	public EditorSpriterInflater () {
		super(Aspect.all(SpriterProtoComponent.class, AssetComponent.class));
	}

	@Override
	public void inserted (int entityId) {
		AssetComponent assetComponent = assetCm.get(entityId);
		SpriterProtoComponent protoComponent = protoCm.get(entityId);
		SpriterPropertiesComponent propsComponent = propertiesCm.get(entityId);

		SpriterAsset asset = (SpriterAsset) assetComponent.asset;

		SpriterComponent component = cache.createComponent(asset, protoComponent.scale);

		protoComponent.fill(component);
		world.getEntity(entityId).edit().add(component);

		if (propsComponent.previewInEditor == false) component.animationPlaying = false;

		protoCm.remove(entityId);
	}
}