/*
 * Copyright 2014-2016 See AUTHORS file.
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
import com.kotcrab.vis.editor.module.project.ShaderCacheModule;
import com.kotcrab.vis.runtime.component.AssetReference;
import com.kotcrab.vis.runtime.component.Shader;
import com.kotcrab.vis.runtime.component.proto.ProtoShader;
import com.kotcrab.vis.runtime.system.inflater.InflaterSystem;

/** @author Kotcrab */
public class EditorShaderInflater extends InflaterSystem {
	private ComponentMapper<Shader> shaderCm;
	private ComponentMapper<ProtoShader> protoCm;
	private ShaderCacheModule shaderCache;

	public EditorShaderInflater () {
		super(Aspect.all(ProtoShader.class, AssetReference.class));
	}

	@Override
	public void inserted (int entityId) {
		ProtoShader protoComponent = protoCm.get(entityId);

		Shader shader = shaderCm.create(entityId);
		shader.asset = protoComponent.asset;
		if (shader.asset != null) {
			shader.shader = shaderCache.get(shader.asset);
		}

		protoCm.remove(entityId);
	}
}
