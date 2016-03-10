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

package com.kotcrab.vis.runtime;

import com.artemis.Entity;
import com.kotcrab.vis.runtime.component.AssetReference;
import com.kotcrab.vis.runtime.component.PhysicsBody;
import com.kotcrab.vis.runtime.util.EntityEngine;

/**
 * Holds runtime configurations values
 * @author Kotcrab
 */
public class RuntimeConfiguration {
	/**
	 * Controls whether to store {@link AssetReference} in {@link Entity} after inflating it. Set this to false if you
	 * need to access {@link AssetReference} during runtime. Default is true. Certain inflaters may ignore this setting
	 * if asset is still always required later for example to render entity.
	 */
	public boolean removeAssetsComponentAfterInflating = true;

	/**
	 * If true body stored in {@link PhysicsBody} will be automatically disposed when entity was removed from entity
	 * engine (for example after calling {@link EntityEngine#deleteEntity(Entity)} or {@link Entity#deleteFromWorld()}).
	 * Note that actual body will be disposed during next {@link EntityEngine} update.
	 */
	public boolean autoDisposeBox2dBodyOnEntityRemove = true;
}
