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

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kotcrab.vis.ui.widget.VisTable;

/**
 * Table in tables. Simplified.
 * @author Kotcrab
 */
public class TableBuilder {
	public static VisTable build (String text, int labelWidth, Actor actor) {
		VisTable table = new VisTable(true);
		table.add(text).width(labelWidth);
		table.add(actor);
		return table;
	}

	public static VisTable build (Actor... actors) {
		return build(new VisTable(true), actors);
	}

	public static VisTable build (int rightSpacing, Actor... actors) {
		VisTable table = new VisTable(true);
		table.defaults().spaceRight(rightSpacing);
		return build(table, actors);
	}

	public static VisTable build (VisTable target, Actor... actors) {
		for (Actor actor : actors) target.add(actor);
		return target;
	}

}
