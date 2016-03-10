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

package com.kotcrab.vis.editor.util.scene2d;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Stops parent actor from receiving child events
 * @author Kotcrab
 */
public class EventStopper extends InputListener {
	@Override
	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		event.stop();
		return true;
	}

	@Override
	public boolean keyDown (InputEvent event, int keycode) {
		event.stop();
		return true;
	}

	@Override
	public boolean scrolled (InputEvent event, float x, float y, int amount) {
		event.stop();
		return true;
	}
}
