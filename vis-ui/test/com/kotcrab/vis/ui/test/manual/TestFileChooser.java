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

package com.kotcrab.vis.ui.test.manual;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooser.Mode;
import com.kotcrab.vis.ui.widget.file.StreamingFileChooserListener;

public class TestFileChooser extends VisWindow {

	public TestFileChooser () {
		super("filechooser");

		FileChooser.setFavoritesPrefsName("com.kotcrab.vis.ui.test.manual");
		final FileChooser chooser = new FileChooser(Mode.OPEN);
		chooser.setListener(new StreamingFileChooserListener() {
			@Override
			public void selected (FileHandle file) {
				System.out.println(file.path());
			}
		});

		VisTextButton show = new VisTextButton("show");

		show.addListener(new ChangeListener() {
			@Override
			public void changed (ChangeEvent event, Actor actor) {
				getStage().addActor(chooser.fadeIn());
			}
		});

		add(show);

		pack();
		setPosition(1038, 66);
	}

}
