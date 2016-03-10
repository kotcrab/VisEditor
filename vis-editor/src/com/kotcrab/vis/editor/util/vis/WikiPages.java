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

package com.kotcrab.vis.editor.util.vis;

import com.badlogic.gdx.Gdx;

/** @author Kotcrab */
public enum WikiPages {
	QUICK_START("https://github.com/kotcrab/VisEditor/wiki/Quick-Start"),
	CONVERTING_FROM_VISEDITOR_02X("https://github.com/kotcrab/VisEditor/wiki/Converting-projects-from-VisEditor-0.2.x"),
	DAMAGED_ASSETS_METADATA("https://github.com/kotcrab/VisEditor/wiki/Troubleshooting:-Damaged-Assets-Metadata"),
	MARKING_DIRECTORIES("https://github.com/kotcrab/VisEditor/wiki/Marking-Assets-Directories");

	private final String url;

	WikiPages (String url) {
		this.url = url;
	}

	public String url () {
		return url;
	}

	public void open () {
		Gdx.net.openURI(url);
	}
}
