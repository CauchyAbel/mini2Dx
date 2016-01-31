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
package org.mini2Dx.ui.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.mini2Dx.ui.element.Actionable;
import org.mini2Dx.ui.layout.ScreenSize;

import com.badlogic.gdx.Input.Keys;

/**
 *
 */
public class GridUiNavigation implements UiNavigation {
	private final List<Actionable> navigation = new ArrayList<Actionable>();
	private final Map<ScreenSize, Integer> widths = new HashMap<ScreenSize, Integer>();
	
	private int width;
	private int cursorX, cursorY;
	
	public GridUiNavigation(int xsWidth) {
		widths.put(ScreenSize.XS, xsWidth);
	}
	
	@Override
	public void layout(ScreenSize screenSize) {
		Iterator<ScreenSize> screenSizes = ScreenSize.largestToSmallest();
		while(screenSizes.hasNext()) {
			ScreenSize nextSize = screenSizes.next();
			if(nextSize.getMinSize() > screenSize.getMinSize()) {
				continue;
			}
			if(!widths.containsKey(nextSize)) {
				continue;
			}
			width = widths.get(nextSize);
			break;
		}
		resetCursor();
	}

	@Override
	public void set(int index, Actionable actionable) {
		if(navigation.size() > index) {
			navigation.set(index, actionable);
		} else {
			navigation.add(index, actionable);
		}
	}
	
	public void set(int x, int y, Actionable actionable) {
		set((y * width) + x, actionable);
	}

	@Override
	public Actionable navigate(int keycode) {
		switch(keycode) {
		case Keys.UP:
			if(cursorY > 0) {
				cursorY--;
			}
			break;
		case Keys.DOWN:
			if(cursorY < getTotalRows() - 1) {
				cursorY++;
			} else {
				cursorY = getTotalRows() - 1;
			}
			break;
		case Keys.LEFT:
			if(cursorX > 0) {
				cursorX--;
			}
			break;
		case Keys.RIGHT:
			if(cursorX < width - 1) {
				cursorX++;
			} else {
				cursorX = width - 1;
			}
			break;
		}
		return navigation.get((cursorY * width) + cursorX);
	}

	@Override
	public void resetCursor() {
		cursorX = 0;
		cursorY = 0;
	}
	
	public void setWidth(ScreenSize screenSize, int width) {
		if(screenSize == null) {
			return;
		}
		widths.put(screenSize, width);
	}
	
	public int getTotalColumns() {
		return width;
	}
	
	public int getTotalRows() {
		int rows = navigation.size() / width;
		if(navigation.size() % width != 0) {
			rows++;
		}
		return rows;
	}
}