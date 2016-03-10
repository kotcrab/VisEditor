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

package com.kotcrab.vis.editor.ui.scene.entityproperties.components.handler;

import com.artemis.Entity;
import com.badlogic.gdx.files.FileHandle;
import com.kotcrab.vis.editor.extension.AssetType;
import com.kotcrab.vis.editor.module.project.FileAccessModule;
import com.kotcrab.vis.runtime.assets.MusicAsset;
import com.kotcrab.vis.runtime.component.AssetReference;
import com.kotcrab.vis.runtime.util.autotable.ATSelectFileHandler;

/** @author Kotcrab */
public class MusicATSelectFileHandler implements ATSelectFileHandler {
	private FileAccessModule fileAccess;

	@Override
	public void applyChanges (Entity entity, FileHandle file) {
		AssetReference asset = entity.getComponent(AssetReference.class);
		asset.asset = new MusicAsset(fileAccess.relativizeToAssetsFolder(file));
	}

	@Override
	public String getAssetDirectoryDescriptorId () {
		return AssetType.DIRECTORY_MUSIC.getId();
	}

	@Override
	public String getLabelValue (Entity entity) {
		MusicAsset asset = (MusicAsset) entity.getComponent(AssetReference.class).asset;
		return asset.getPath();
	}
}
