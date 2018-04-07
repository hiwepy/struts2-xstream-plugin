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

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;

import com.thoughtworks.xstream.io.AbstractDriver;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.naming.NameCoder;

public class FastJsonStreamDriver extends AbstractDriver {

    /**
     * Construct a FastJsonStreamDriver.
     */
    public FastJsonStreamDriver() {
        super();
    }
    
    /**
     * Construct a FastJsonStreamDriver with name coding.
     * @param nameCoder the coder to encode and decode the JSON labels.
     * @since 1.4.2
     */
    public FastJsonStreamDriver(NameCoder nameCoder) {
        super(nameCoder);
    }
    
	@Override
	public HierarchicalStreamReader createReader(Reader in) {
		throw new UnsupportedOperationException("The JsonHierarchicalStreamDriver can only write JSON");
	}

	@Override
	public HierarchicalStreamReader createReader(InputStream in) {
		throw new UnsupportedOperationException("The JsonHierarchicalStreamDriver can only write JSON");
	}
	
	@Override
    public HierarchicalStreamReader createReader(URL in) {
        throw new UnsupportedOperationException("The JsonHierarchicalStreamDriver can only write JSON");
    }
	
	@Override
    public HierarchicalStreamReader createReader(File in) {
        throw new UnsupportedOperationException("The JsonHierarchicalStreamDriver can only write JSON");
    }
	
	/**
     * Create a HierarchicalStreamWriter that writes JSON.
     */
	@Override
    public HierarchicalStreamWriter createWriter(Writer out) {
        return new FastJsonWriter(out);
    }
	
    @Override
    public HierarchicalStreamWriter createWriter(OutputStream out) {
        try {
            // JSON spec requires UTF-8
            return createWriter(new OutputStreamWriter(out, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new StreamException(e);
        }
    }

}
