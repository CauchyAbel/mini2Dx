/**
 * Copyright (c) 2015 See AUTHORS file
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of the mini2Dx nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.mini2Dx.core;

import org.mini2Dx.core.assets.Assets;
import org.mini2Dx.core.controller.ControllerMapping;
import org.mini2Dx.core.di.DependencyInjection;
import org.mini2Dx.core.files.ExternalGameData;
import org.mini2Dx.core.files.PlayerData;
import org.mini2Dx.core.graphics.Texture;
import org.mini2Dx.core.serialization.JsonSerializer;
import org.mini2Dx.core.serialization.XmlSerializer;
import org.mini2Dx.natives.Os;
import org.mini2Dx.natives.OsInformation;

/**
 * Mini2Dx utility classes
 */
public class Mdx {
	/**
	 * Interface for loading and getting assets such as {@link Texture}s
	 */
	public static Assets assets;
	
	/**
	 * Provides access to controller mappings
	 */
	public static ControllerMapping controllers = new ControllerMapping();
	
	/**
	 * Interface for reading/writing player data, e.g. save games, preferences
	 */
	public static PlayerData playerData;
	
	/**
	 * Interface for reading/writing external game data, e.g. DLC, mods, etc.
	 */
	public static ExternalGameData externalGameData;
	
	/**
	 * Dependency injection
	 */
	public static DependencyInjection di;
	
	/**
	 * Returns the current operating system. See <a href="https://github.com/mini2Dx/natives-loader">natives-loader project</a> for more javadocs
	 */
	public static Os os = OsInformation.getOs();
	
	/**
	 * Returns the current runtime implementation
	 */
	public static MdxRuntime runtime;
	
	/**
	 * JSON serialization
	 */
	public static JsonSerializer json;
	
	/**
	 * XML serialization
	 */
	public static XmlSerializer xml;
	
	/**
	 * This game's unique identifier for app stores
	 */
	public static String gameIdentifier;
}
