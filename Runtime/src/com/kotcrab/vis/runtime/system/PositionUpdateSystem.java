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

package com.kotcrab.vis.runtime.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.kotcrab.vis.runtime.component.PositionChanged;
import com.kotcrab.vis.runtime.component.PositionComponent;
import com.kotcrab.vis.runtime.component.VisSprite;

/** @author Kotcrab */
public class PositionUpdateSystem extends IteratingSystem {
	private ComponentMapper<PositionComponent> posCm;
	private ComponentMapper<VisSprite> spriteCm;

	public PositionUpdateSystem () {
		super(Aspect.all(PositionComponent.class, PositionChanged.class).one(VisSprite.class));
	}

	@Override
	protected void process (int entityId) {
		PositionComponent pos = posCm.get(entityId);
		VisSprite sprite = spriteCm.getSafe(entityId);

		if (sprite != null) sprite.setPosition(pos.x, pos.y);
	}

}