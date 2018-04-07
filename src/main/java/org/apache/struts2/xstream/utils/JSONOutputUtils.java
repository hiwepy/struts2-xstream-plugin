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
package org.apache.struts2.xstream.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wrapper for JSONWriter with some utility methods.
 */
public class JSONOutputUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(JSONOutputUtils.class);

    public static void writeJSONToResponse(SerializationParams serializationParams) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(serializationParams.getSerialized())){
            stringBuilder.append(serializationParams.getSerialized());
        }
        if (StringUtils.isNotBlank(serializationParams.getWrapPrefix())){
            stringBuilder.insert(0, serializationParams.getWrapPrefix());
        } else if (serializationParams.isWrapWithComments()) {
            stringBuilder.insert(0, "/* ");
            stringBuilder.append(" */");
        } else if (serializationParams.isPrefix()){
            stringBuilder.insert(0, "{}&& ");
        }
        if (StringUtils.isNotBlank(serializationParams.getWrapSuffix())){
            stringBuilder.append(serializationParams.getWrapSuffix());
        }
        String json = stringBuilder.toString();

        LOG.debug("[JSON] {}", json);

        HttpServletResponse response = serializationParams.getResponse();

        // status or error code
        if (serializationParams.getStatusCode() > 0){
            response.setStatus(serializationParams.getStatusCode());
        } else if (serializationParams.getErrorCode() > 0){
            response.sendError(serializationParams.getErrorCode());
        }
        // content type
        response.setContentType(serializationParams.getContentType() + ";charset=" + serializationParams.getEncoding());

        if (serializationParams.isNoCache()) {
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "No-cache");
        }

        if (serializationParams.isGzip()) {
            response.addHeader("Content-Encoding", "gzip");
            GZIPOutputStream out = null;
            InputStream in = null;
            try {
                out = new GZIPOutputStream(response.getOutputStream());
                in = new ByteArrayInputStream(json.getBytes(serializationParams.getEncoding()));
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                if (in != null)
                    in.close();
                if (out != null) {
                    out.finish();
                    out.close();
                }
            }

        } else {
            response.setContentLength(json.getBytes(serializationParams.getEncoding()).length);
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    public static boolean isGzipInRequest(HttpServletRequest request) {
        return StringUtils.contains(request.getHeader("Accept-Encoding"), "gzip");
    }
    
}
