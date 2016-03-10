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

package com.kotcrab.vis.editor.module.editor;

import com.artemis.utils.Bag;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.kotcrab.vis.editor.serializer.cloner.*;
import com.rits.cloning.Cloner;

/** @author Kotcrab */
public class ClonerModule extends EditorModule {
	private Cloner cloner;

	@Override
	public void init () {
		cloner = new Cloner();
		cloner.setNullTransient(true);
		//TODO: [plugins] plugin entry point?
		cloner.registerFastCloner(Array.class, new ArrayCloner());
		cloner.registerFastCloner(Bag.class, new BagCloner());
		cloner.registerFastCloner(IntArray.class, new IntArrayCloner());
		cloner.registerFastCloner(IntMap.class, new IntMapCloner());
		cloner.registerFastCloner(ObjectMap.class, new ObjectMapCloner());
	}

	public Cloner getCloner () {
		return cloner;
	}

	public <T> T deepClone (T o) {
		return cloner.deepClone(o);
	}
}
