/*
 * Copyright (c) 2018, 2025, Oracle and/or its affiliates. All rights reserved.
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
package org.openjdk.jmc.ide.launch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

import org.openjdk.jmc.ide.launch.model.JfrLaunchModel;
import org.openjdk.jmc.ui.misc.DialogToolkit;

/**
 * Helper for launch shortcut that starts with JFR enabled with some suitable default settings.
 */
public class JfrLaunchShortcutHelper {
	protected static ILaunchConfigurationWorkingCopy updateLaunchConfigurationWithJfrSettings(
		ILaunchConfigurationWorkingCopy wc, String jfrLaunchDelegateId) {

		JfrLaunchModel model = new JfrLaunchModel(true, true);
		try {
			model.updateFromJREConfiguration(wc);
			model.updateToConfiguration(wc);
			wc.setPreferredLaunchDelegate(getJfrModes(), jfrLaunchDelegateId);

			// Ensure the destination folder exists
			File parent = model.getRecordingFile().getParentFile();
			if (parent.isDirectory() || parent.mkdirs()) {
				wc.doSave();
			} else {
				displayExceptionDialog(new FileNotFoundException(
						"Could not create the folder for the flight recording file: " + parent.getAbsolutePath())); //$NON-NLS-1$
			}
		} catch (Exception e) {
			displayExceptionDialog(e);
		}
		return wc;
	}

	private static Set<String> getJfrModes() {
		Set<String> modes = new HashSet<>();
		modes.add("run"); //$NON-NLS-1$
		return modes;
	}

	protected static void displayExceptionDialog(Exception e) {
		DialogToolkit.showException(LaunchPlugin.getActiveWorkbenchShell(), Messages.JfrLaunch_JFR_OPTIONS_PROBLEM, e);
	}
}
