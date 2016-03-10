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

import com.google.common.eventbus.Subscribe;
import com.kotcrab.vis.editor.event.EventBusExceptionEvent;
import com.kotcrab.vis.editor.module.EventBusSubscriber;
import com.kotcrab.vis.editor.ui.toast.DetailsToast;

/** @author Kotcrab */
@EventBusSubscriber
public class EventBusExceptionMonitorModule extends EditorModule {
	private ToastModule toastModule;

	@Subscribe
	public void handleEventBusException (EventBusExceptionEvent event) {
		toastModule.show(new DetailsToast("Internal exception occurred", event.exception));
	}
}
