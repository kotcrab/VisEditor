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

package com.kotcrab.vis.editor.proxy;

import com.artemis.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.kotcrab.vis.editor.Assets;
import com.kotcrab.vis.runtime.component.Transform;
import com.kotcrab.vis.runtime.properties.BoundsOwner;
import com.kotcrab.vis.runtime.properties.SizeOwner;

/** @author Kotcrab */
public class AudioProxy extends EntityProxy {
	private Transform transform;
	private Accessor accessor;

	private boolean music;
	private float renderSize;

	public AudioProxy (Entity entity, boolean music, float pixelsPerUnit) {
		super(entity);
		this.music = music;
		renderSize = Assets.BIG_ICON_SIZE / pixelsPerUnit;
	}

	@Override
	protected void createAccessors () {
		accessor = new Accessor();
	}

	@Override
	protected void reloadAccessors () {
		transform = getComponent(Transform.class);
		enableBasicProperties(transform, accessor, accessor);
	}

	@Override
	public String getEntityName () {
		return music ? "Music" : "Sound";
	}

	private class Accessor implements SizeOwner, BoundsOwner {
		private Rectangle bounds = new Rectangle();

		public Accessor () {
			bounds = new Rectangle();
		}

		@Override
		public float getWidth () {
			return renderSize;
		}

		@Override
		public float getHeight () {
			return renderSize;
		}

		@Override
		public Rectangle getBoundingRectangle () {
			return bounds.set(transform.getX() - renderSize / 2, transform.getY() - renderSize / 2, renderSize, renderSize);
		}
	}
}
