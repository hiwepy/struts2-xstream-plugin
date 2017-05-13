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

import java.io.Writer;

import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.binary.BinaryStreamDriver;
import com.thoughtworks.xstream.io.json.FastJsonStreamDriver;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;
import com.thoughtworks.xstream.io.xml.BEAStaxDriver;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.JDom2Driver;
import com.thoughtworks.xstream.io.xml.JDomDriver;
import com.thoughtworks.xstream.io.xml.KXml2DomDriver;
import com.thoughtworks.xstream.io.xml.KXml2Driver;
import com.thoughtworks.xstream.io.xml.SjsxpDriver;
import com.thoughtworks.xstream.io.xml.StandardStaxDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.WstxDriver;
import com.thoughtworks.xstream.io.xml.XomDriver;
import com.thoughtworks.xstream.io.xml.Xpp3DomDriver;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import com.thoughtworks.xstream.io.xml.XppDomDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;

@SuppressWarnings("deprecation")
public class XStreamUtils {

	public static HierarchicalStreamDriver parserDriver(String driver) {
		HierarchicalStreamDriver streamDriver = null;
		/*JSON Stream Driver*/
		if(Drivers.JSON_DRIVER.equalsIgnoreCase(driver)){
			/*
			 * 使用JsonHierarchicalStreamDriver转换默认会给转换后的对象添加一个根节点，
			 * 但是在构建JsonHierarchicalStreamDriver驱动的时候，可以重写createWriter方法，删掉根节点。
			 */
			streamDriver = new JsonHierarchicalStreamDriver() {  
	            
				public HierarchicalStreamWriter createWriter(Writer out) { 
	            	//删除根节点
	                return new JsonWriter(out, JsonWriter.DROP_ROOT_MODE);  
	            }
	            
	        };
		}
		else if(Drivers.JETTISON_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new JettisonMappedXmlDriver();
		}
		else if(Drivers.FASTJSON_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new FastJsonStreamDriver();
		}
		/*XML Stream Driver*/
		else if (Drivers.XPP_DRIVER.equalsIgnoreCase(driver)) {
			streamDriver = new XppDriver();
		}
		else if(Drivers.XPP_DOM_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new XppDomDriver();
		}
		else if(Drivers.XPP3_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new Xpp3Driver();
		}
		else if(Drivers.XPP3_DOM_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new Xpp3DomDriver();
		}
		else if(Drivers.KXML2_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new KXml2Driver();
		}
		else if(Drivers.KXML2_DOM_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new KXml2DomDriver();
		}
		else if(Drivers.JDOM_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new JDomDriver();
		}
		else if(Drivers.JDOM2_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new JDom2Driver();
		}
		else if(Drivers.DOM_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new DomDriver();
		}
		else if(Drivers.DOM4J_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new Dom4JDriver();
		}
		else if(Drivers.STAX_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new StaxDriver();
		}
		else if(Drivers.BEA_STAX_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new BEAStaxDriver();
		}
		else if(Drivers.SJSXP_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new SjsxpDriver();
		}
		else if(Drivers.STANDARD_STAX_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new StandardStaxDriver();
		}
		else if(Drivers.WSTX_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new WstxDriver();
		}
		else if(Drivers.XOM_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new XomDriver();
		}
		else if(Drivers.BINARY_DRIVER.equalsIgnoreCase(driver)){
			streamDriver = new BinaryStreamDriver();
		}
		
		return streamDriver;
	}
	
	public static int parserMode(String mode) {
    	if("NO_REFERENCES".equals(mode)){
    		return XStream.NO_REFERENCES;
    	}else if("ID_REFERENCES".equals(mode)){
    		return XStream.ID_REFERENCES;
    	}else if("XPATH_RELATIVE_REFERENCES".equals(mode)){
    		return XStream.XPATH_RELATIVE_REFERENCES;
    	}else if("XPATH_ABSOLUTE_REFERENCES".equals(mode)){
    		return XStream.XPATH_ABSOLUTE_REFERENCES;
    	}else if("SINGLE_NODE_XPATH_RELATIVE_REFERENCES".equals(mode)){
    		return XStream.SINGLE_NODE_XPATH_RELATIVE_REFERENCES;
    	}else if("SINGLE_NODE_XPATH_ABSOLUTE_REFERENCES".equals(mode)){
    		return XStream.SINGLE_NODE_XPATH_ABSOLUTE_REFERENCES;
    	}
		return XStream.NO_REFERENCES;
	}
	
}
