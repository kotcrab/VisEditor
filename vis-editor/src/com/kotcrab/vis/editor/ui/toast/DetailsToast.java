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

package com.kotcrab.vis.editor.ui.toast;

import com.kotcrab.vis.editor.module.editor.ToastModule;
import com.kotcrab.vis.editor.ui.dialog.DetailsDialog;
import com.kotcrab.vis.editor.util.ExceptionUtils;
import com.kotcrab.vis.ui.widget.LinkLabel;
import com.kotcrab.vis.ui.widget.VisTable;

/**
 * Used to display toast with short message and additional details available in separate dialog after pressing "Details" button. For example exception with stacktrace as details.
 * @author Kotcrab
 * @see ToastModule
 */
public class DetailsToast extends VisTable {
	public DetailsToast (String text, Throwable cause) {
		this(text, "Exception Details", ExceptionUtils.getStackTrace(cause));
	}

	public DetailsToast (String text, String details) {
		this(text, "Details", details);
	}

	public DetailsToast (String text, String detailsDialogTitle, String details) {
		LinkLabel label = new LinkLabel("Details");
		label.setListener(url -> getStage().addActor(new DetailsDialog(text, detailsDialogTitle, details).fadeIn()));

		add(text).expand().fill().row();
		add(label).right();
	}
}
