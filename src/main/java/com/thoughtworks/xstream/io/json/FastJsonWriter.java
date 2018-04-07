/*
 * Copyright (c) 2018 (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.thoughtworks.xstream.io.json;

import java.io.IOException;
import java.io.Writer;

import com.alibaba.fastjson.JSONWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.thoughtworks.xstream.io.json.AbstractJsonWriter;

public class FastJsonWriter extends AbstractJsonWriter {

	private JSONWriter writer;
	
	public FastJsonWriter(Writer out) {
		writer = new JSONWriter(out);
	}
	
	public FastJsonWriter(Writer out,SerializerFeature feature, boolean state) {
		writer = new JSONWriter(out);
		writer.config(feature, state);
	}

	@Override
	protected void addLabel(String name) {
		writer.writeKey(name);
	}

	@Override
	protected void addValue(String value, Type type) {
		writer.writeValue(value);
	}

	@Override
	protected void endArray() {
		writer.endArray();
	}
	
	@Override
	protected void endObject() {
		writer.endObject();
	}

	@Override
	protected void nextElement() {
		
	}

	@Override
	protected void startArray() {
		writer.startArray();
	}

	@Override
	protected void startObject() {
		writer.startObject();
	}

	@Override
	public void close() {
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void flush() {
		try {
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
