/*
 * Copyright (c) 2020, 2025, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2020, 2025, Red Hat Inc. All rights reserved.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The contents of this file are subject to the terms of either the Universal Permissive License
 * v 1.0 as shown at https://oss.oracle.com/licenses/upl
 *
 * or the following license:
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.openjdk.jmc.joverflow.ui;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.openjdk.jmc.ui.CoreImages;

class TreemapAction extends Action {
	private final TreemapActionType actionType;
	private Runnable runnable = () -> {
	};

	TreemapAction(TreemapActionType actionType) {
		super(actionType.message, actionType.action);
		this.actionType = actionType;
		setToolTipText(actionType.message);
		setImageDescriptor(actionType.imageDescriptor);
	}

	@Override
	public void run() {
		runnable.run();
	}

	public void setRunnable(Runnable callback) {
		runnable = callback;
	}

	public TreemapActionType getType() {
		return actionType;
	}

	enum TreemapActionType {
		ZOOM_IN(Messages.TreemapAction_ZOOM_IN_DESCRIPTION, IAction.AS_PUSH_BUTTON, CoreImages.ZOOM_IN), // 
		ZOOM_OUT(Messages.TreemapAction_ZOOM_OUT_DESCRIPTION, IAction.AS_PUSH_BUTTON, CoreImages.ZOOM_OUT), // 
		ZOOM_RESET(Messages.TreemapAction_ZOOM_RESET_DESCRIPTION, IAction.AS_PUSH_BUTTON, JOverflowPlugin.getDefault()
				.getMCImageDescriptor(JOverflowPlugin.ICON_UNDO_EDIT));

		private final String message;
		private final int action;
		private final ImageDescriptor imageDescriptor;

		TreemapActionType(String message, int action, ImageDescriptor imageDescriptor) {
			this.message = message;
			this.action = action;
			this.imageDescriptor = imageDescriptor;
		}
	}
}
