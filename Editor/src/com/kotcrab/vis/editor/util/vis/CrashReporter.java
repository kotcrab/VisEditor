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

package com.kotcrab.vis.editor.util.vis;

import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.editor.App;
import com.kotcrab.vis.editor.Log;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Saves VisEditor crashes to file.
 * @author Kotcrab
 */
public class CrashReporter {
	private static final String TAG = "CrashReporter";

	private StringBuilder crashReport;
	private File logFile;
	private String report;

	public CrashReporter (File logFile) throws IOException {
		this.logFile = logFile;
		this.crashReport = new StringBuilder();

		printHeader();
		printThreadInfo();
		printLog();

		report = crashReport.toString();
	}

	public void processReport () throws IOException {
		File crashReportFile = new File(logFile.getParent(), "viseditor-crash " + new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date()) + ".txt");
		FileUtils.writeStringToFile(crashReportFile, report);
		Log.info(TAG, "Crash saved to file: " + crashReportFile.getAbsolutePath());
	}

	private void printHeader () {
		println("--- VisEditor Crash Report ---");
		println("VisEditor " + App.VERSION);
		println("VersionCode: " + App.VERSION_CODE + " Snapshot: " + App.SNAPSHOT);
		println();

		println("Java: " + System.getProperty("java.version") + " " + System.getProperty("java.vendor"));
		println("Java VM: " + System.getProperty("java.vm.name"));
		println("OS: " + System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch"));
		println();

	}

	private void printThreadInfo () {
		println("--- Threads ---");

		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();

		for (Thread t : threadSet) {
			println("Thread: " + t.getName() + " Daemon: " + t.isDaemon() + " State: " + t.getState());
			for (StackTraceElement e : t.getStackTrace()) {
				crashReport.append("\t");
				println(e.toString());
			}

			println();
		}

		println("---------------");
		println();
	}

	private void printLog () throws IOException {
		println("--- Log file (last 200 lines) ---");

		ReversedLinesFileReader reader = new ReversedLinesFileReader(logFile);
		Array<String> logLines = new Array<>();

		for (int i = 0; i < 200; i++) {
			String line = reader.readLine();
			if (line == null) break;
			logLines.add(line);
		}

		logLines.reverse();

		for (String s : logLines)
			println(s);

		println("---------------------------------");
		println();
		reader.close();
	}

	private void println () {
		crashReport.append(System.lineSeparator());
	}

	private void println (String s) {
		crashReport.append(s);
		crashReport.append(System.lineSeparator());
	}
}
