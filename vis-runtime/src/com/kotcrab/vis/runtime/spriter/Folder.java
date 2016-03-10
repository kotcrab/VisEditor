/*
 * Copyright 2014 by Trixt0r
 * (https://github.com/Trixt0r, Heinrich Reich, e-mail: trixter16@web.de)
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

package com.kotcrab.vis.runtime.spriter;

/**
 * Represents a folder in a Spriter SCML file.
 * A folder has at least an {@link #id}, {@link #name} and {@link #files} may be empty.
 * An instance of this class holds an array of {@link File} instances.
 * Specific {@link File} instances can be accessed via the corresponding methods, i.e getFile().
 * @author Trixt0r
 */
public class Folder {

	final File[] files;
	private int filePointer = 0;
	public final int id;
	public final String name;

	Folder (int id, String name, int files) {
		this.id = id;
		this.name = name;
		this.files = new File[files];
	}

	/**
	 * Adds a {@link File} instance to this folder.
	 * @param file the file to add
	 */
	void addFile (File file) {
		this.files[filePointer++] = file;
	}

	/**
	 * Returns a {@link File} instance with the given index.
	 * @param index the index of the file
	 * @return the file with the given name
	 */
	public File getFile (int index) {
		return files[index];
	}

	/**
	 * Returns a {@link File} instance with the given name.
	 * @param name the name of the file
	 * @return the file with the given name or null if no file with the given name exists
	 */
	public File getFile (String name) {
		int index = getFileIndex(name);
		if (index >= 0) return getFile(index);
		else return null;
	}

	/**
	 * Returns a file index with the given name.
	 * @param name the name of the file
	 * @return the file index with the given name or -1 if no file with the given name exists
	 */
	int getFileIndex (String name) {
		for (File file : this.files)
			if (file.name.equals(name)) return file.id;
		return -1;
	}

	public String toString () {
		String toReturn = getClass().getSimpleName() + "|[id: " + id + ", name: " + name;
		for (File file : files)
			toReturn += "\n" + file;
		toReturn += "]";
		return toReturn;
	}
}
