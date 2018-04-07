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
package org.apache.struts2.xstream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XMLDeclarationXStream;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamUtils;

/**
 * <!-- START SNIPPET: description --> <p/> This result serializes an action
 * into JSON. <p/> <!-- END SNIPPET: description --> <p/> <p/> 
 * <p/> <!-- START SNIPPET: parameters --> <p/>
 * <p/> <!-- END SNIPPET: parameters --> <p/> <b>Example:</b> <p/>
 * <p/>
 * <pre>
 * &lt;!-- START SNIPPET: example --&gt;
 * &lt;result name=&quot;success&quot; type=&quot;xstream-xml&quot; /&gt;
 * &lt;!-- END SNIPPET: example --&gt;
 * </pre>
 * @see http://blog.csdn.net/zdp072/article/details/39054197
 */
@SuppressWarnings("serial")
public class XStreamXMLResult extends XMLResultSupport {

	protected static final Logger LOG = LoggerFactory.getLogger(XStreamJSONResult.class);
    protected String streamDriver;
    protected String mode;
    
    public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getStreamDriver() {
		return streamDriver;
	}

	public void setStreamDriver(String streamDriver) {
		this.streamDriver = streamDriver;
	}
	
    protected String createXMLString(HttpServletRequest request, Object rootObject) {
    	XStream xstream = new XMLDeclarationXStream(XStreamUtils.parserDriver(getStreamDriver()));
        xstream.setMode(XStreamUtils.parserMode(getMode()));
        //如果没有这句，xml中的根元素会是<包.类名>；或者说：注解根本就没生效，所以的元素名就是类的属性
        //通过注解方式的，一定要有这句话
	    xstream.processAnnotations(rootObject.getClass()); 
        return xstream.toXML(rootObject);
    }
    
}
