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

package com.kotcrab.vis.editor.module.project;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.kotcrab.vis.editor.scheme.SpriterAssetData;

/** @author Kotcrab */
public class SpriterDataIOModule extends ProjectModule {
	private Json json;

	@Override
	public void init () {
		json = getNewJson();
	}

	public Json getJson () {
		return json;
	}

	public static Json getNewJson () {
		Json json = new Json();
		json.addClassTag("SpriterAssetData", SpriterAssetData.class);
		return json;
	}

	public SpriterAssetData loadData (FileHandle file) {
		return json.fromJson(SpriterAssetData.class, file);
	}
}
