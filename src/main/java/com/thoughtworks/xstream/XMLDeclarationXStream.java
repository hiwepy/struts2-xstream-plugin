/*
 * Copyright (c) 2010-2020, vindell (https://github.com/vindell).
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
package com.thoughtworks.xstream;

import java.io.OutputStream;
import java.io.Writer;

import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XMLDeclarationXStream extends XStream {  
	
    private String version;  
    private String ecoding;  
  
    public XMLDeclarationXStream() {  
        this(new XppDriver());  
    }  
    
    public XMLDeclarationXStream(HierarchicalStreamDriver hierarchicalStreamDriver) {
    	this(hierarchicalStreamDriver, "1.0", "UTF-8");
   	}
    
    public XMLDeclarationXStream(HierarchicalStreamDriver hierarchicalStreamDriver, String version, String ecoding) {
    	super(hierarchicalStreamDriver);
        this.version = version;  
        this.ecoding = ecoding;  
    }  
    

	public String getDeclaration() {  
        return "<?xml version=\"" + this.version + "\" encoding=\"" + this.ecoding + "\"?>";  
    }
  
    @Override  
    public void toXML(Object obj, OutputStream output) {   
        try {  
            String dec = this.getDeclaration();  
            byte[] bytesOfDec = dec.getBytes(this.ecoding);  
            output.write(bytesOfDec);  
        } catch (Exception e) {  
            throw new RuntimeException("error happens", e);  
        }  
        super.toXML(obj, output);  
    }  
  
    @Override  
    public void toXML(Object obj, Writer writer) {  
        try {  
            writer.write(getDeclaration());   
        } catch (Exception e) {  
            throw new RuntimeException("error happens", e);  
        }  
        super.toXML(obj, writer);  
    }
    
}  