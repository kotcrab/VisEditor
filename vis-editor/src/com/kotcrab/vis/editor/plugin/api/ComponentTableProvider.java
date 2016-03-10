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

package com.kotcrab.vis.editor.plugin.api;

import com.artemis.Component;
import com.kotcrab.vis.editor.module.scene.SceneModuleContainer;
import com.kotcrab.vis.editor.ui.scene.entityproperties.ComponentTable;
import com.kotcrab.vis.editor.ui.scene.entityproperties.autotable.AutoComponentTable;
import com.kotcrab.vis.editor.util.PublicApi;

/**
 * Entry point for plugins providing custom UI for given component.
 * @author Kotcrab
 */
@PublicApi
public interface ComponentTableProvider {
	/**
	 * Called when plugin should provide it's {@link ComponentTable} object. Consider using {@link AutoComponentTable}
	 * for simple components.
	 * @param sceneMC scene module container invoking this plugin, usually used to create {@link AutoComponentTable}
	 * @return component table instance.
	 */
	ComponentTable<? extends Component> provide (SceneModuleContainer sceneMC);
}
