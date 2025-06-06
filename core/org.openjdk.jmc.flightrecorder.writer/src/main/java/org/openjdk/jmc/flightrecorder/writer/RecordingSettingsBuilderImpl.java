/*
 * Copyright (c) 2021, 2025, Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2021, 2025, Datadog, Inc. All rights reserved.
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
package org.openjdk.jmc.flightrecorder.writer;

import org.openjdk.jmc.flightrecorder.writer.api.RecordingSettings;
import org.openjdk.jmc.flightrecorder.writer.api.RecordingSettingsBuilder;

public final class RecordingSettingsBuilderImpl implements RecordingSettingsBuilder {
	private long timestamp = -1;
	private long startTicks = -1;
	private long duration = -1;
	private boolean initializeJdkTypes = false;

	@Override
	public RecordingSettingsBuilder withTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	@Override
	public RecordingSettingsBuilder withStartTicks(long ticks) {
		this.startTicks = ticks;
		return this;
	}

	@Override
	public RecordingSettingsBuilder withDuration(long duration) {
		this.duration = duration;
		return this;
	}

	@Override
	public RecordingSettingsBuilder withJdkTypeInitialization() {
		initializeJdkTypes = true;
		return this;
	}

	@Override
	public RecordingSettings build() {
		return new RecordingSettings(timestamp > 0 ? timestamp : System.currentTimeMillis() * 1_000_000L,
				startTicks > 0 ? startTicks : System.nanoTime(), duration, initializeJdkTypes);
	}
}
