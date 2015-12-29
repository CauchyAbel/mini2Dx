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
package org.mini2Dx.ui.layout;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class LayoutRuleset {
	public static final LayoutRuleset DEFAULT_RULESET = new LayoutRuleset("xs-12");
	
	private final Map<ScreenSize, SizeRule> sizeRules = new HashMap<ScreenSize, SizeRule>();
	private final Map<ScreenSize, OffsetRule> offsetRules = new HashMap<ScreenSize, OffsetRule>();

	public LayoutRuleset(String rules) {
		String[] rule = rules.split(" ");
		for (int i = 0; i < rule.length; i++) {
			String[] ruleDetails = rule[i].split("-");
			switch (ruleDetails.length) {
			case 1:
				break;
			case 2:
				storeWidthRule(ruleDetails);
				break;
			case 3:
				storeOffsetRule(ruleDetails);
				break;
			}
		}
	}

	private void storeWidthRule(String[] ruleDetails) {
		ScreenSize screenSize = ScreenSize.fromString(ruleDetails[0]);
		sizeRules.put(screenSize, new SizeRule(Integer.parseInt(ruleDetails[1])));
	}

	private void storeOffsetRule(String[] ruleDetails) {
		ScreenSize screenSize = ScreenSize.fromString(ruleDetails[0]);
		offsetRules.put(screenSize, new OffsetRule(Integer.parseInt(ruleDetails[2])));
	}

	public float getPreferredWidth(LayoutState layoutState) {
		return layoutState.getColumnWidth() * sizeRules.get(layoutState.getScreenSize()).getColumns();
	}
	
	public float getXOffset(LayoutState layoutState) {
		return layoutState.getColumnWidth() * offsetRules.get(layoutState.getScreenSize()).getColumns();
	}
}
