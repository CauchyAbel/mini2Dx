/**
 * Copyright 2015 Thomas Cashman
 */
package org.mini2Dx.ui.render;

import java.util.Map;

import org.mini2Dx.core.controller.button.ControllerButton;
import org.mini2Dx.ui.element.Actionable;
import org.mini2Dx.ui.element.Column;
import org.mini2Dx.ui.element.Modal;
import org.mini2Dx.ui.layout.LayoutState;
import org.mini2Dx.ui.style.ContainerStyleRule;

/**
 *
 */
public class ModalRenderNode extends ContainerRenderNode {
	private Map<Integer, ActionableRenderNode> keyboardHotkeys;
	private Map<String, ActionableRenderNode> controllerHotkeys;

	public ModalRenderNode(ParentRenderNode<?, ?> parent, Column column) {
		super(parent, column);
	}
	
	@Override
	public void layout(LayoutState layoutState) {
		((Modal) element).getNavigation().layout(layoutState.getScreenSize());
		super.layout(layoutState);
	}
	
	public ActionableRenderNode hotkey(int keycode) {
		if(keyboardHotkeys == null) {
			return null;
		}
		return keyboardHotkeys.get(keycode);
	}
	
	public ActionableRenderNode hotkey(ControllerButton controllerButton) {
		if(controllerHotkeys == null) {
			return null;
		}
		return controllerHotkeys.get(controllerButton.getAbsoluteValue());
	}
	
	public ActionableRenderNode navigate(int keycode) {
		Actionable actionable = ((Modal) element).getNavigation().navigate(keycode);
		return (ActionableRenderNode) getElementById(actionable.getId());
	}

	@Override
	protected ContainerStyleRule determineStyleRule(LayoutState layoutState) {
		System.out.println(layoutState);
		ContainerStyleRule rule = layoutState.getTheme().getStyleRule(((Modal) element), layoutState.getScreenSize());
		System.out.println(rule);
		return rule;
	}

}
