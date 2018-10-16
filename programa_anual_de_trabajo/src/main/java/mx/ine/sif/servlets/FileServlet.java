/*
 * net/balusc/webapp/FileServlet.java
 *
 * Copyright (C) 2009 BalusC
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with this library.
 * If not, see <http://www.gnu.org/licenses/>.
 */
package mx.ine.sif.servlets;



import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.EnumMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.ine.sif.bsd.BSDFileManager;
import mx.ine.sif.bsd.BSDFileManager.ReturnParamKeys;
import mx.ine.sif.file.ZipperStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * A file servlet supporting resume of downloads and client-side caching and GZIP of text content.
 * This servlet can also be used for images, client-side caching would become more efficient.
 * This servlet can also be used for text files, GZIP would decrease network bandwidth.
 *
 * @author BalusC
 * @link http://balusc.blogspot.com/2009/02/fileservlet-supporting-resume-and.html
 */

@WebServlet(value="/media/*" 
//		,initParams = {
//        			@WebInitParam(name="basePath", value="\\GlusterFS\\sifv2\\sif_admin\\")
//        			} 
)
public class FileServlet extends HttpServlet implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4273923709250190365L;

	private static final Logger logger = Logger.getLogger(FileServlet.class);

    // Properties ---------------------------------------------------------------------------------

    @Autowired
    private transient BSDFileManager bsdFileManager;

    private WebApplicationContext springContext;
    
    // Actions ------------------------------------------------------------------------------------

    /**
     * Initialize the servlet.
     * @see HttpServlet#init().
     */
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }

    /**
     * Process HEAD request. This returns the same headers as GET request, but without content.
     * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse).
     */
    protected void doHead(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        // Process request without content.
        processRequest(request, response, false);
    }

    /**
     * Process GET request.
     * @see HttpServlet#doGet(HttpServletRequest, HttpServletResponse).
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        // Process request with content.
        processRequest(request, response, true);
    }

    /**
     * Process the actual request.
     * @param request The request to be processed.
     * @param response The response to be created.
     * @param content Whether the request body should be written (GET) or not (HEAD).
     * @throws IOException If something fails at I/O level.
     */
    private void processRequest
        (HttpServletRequest request, HttpServletResponse response, boolean content)
            throws IOException
    {
        // Validate the requested file ------------------------------------------------------------
        // Get requested file by path info.
        String requestedFile = request.getPathInfo();
        String forceDownloadParam = request.getParameter("forceDownload");
        boolean forceDownload = false;
        
        if ( forceDownloadParam != null )
        	forceDownload = true;
        
        // Check if file is actually supplied to the request URL.
        if (requestedFile == null) {
        	// Do your thing if the file is not supplied to the request URL.
        	// Throw an exception, or send 404, or show default/warning page, or just ignore it.
        	response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED);
        	return;
        }

        EnumMap<ReturnParamKeys, Object> enumMap;
        
        try {
        	enumMap = bsdFileManager.analizeUrl(requestedFile);
		} catch (Exception e) {
			e.printStackTrace();
			
			response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED); 
            return;
		}

//        logger.error(""+basePath);

        File file = (File) enumMap.get( ReturnParamKeys.FILE ); 
        String fileName = (String) enumMap.get( ReturnParamKeys.FILENAME );
        Boolean insideZip = (Boolean) enumMap.get( ReturnParamKeys.INSIDE_ZIP );

        // URL-decode the file name (might contain spaces and on) and prepare file object.
        		//new File("", URLDecoder.decode(requestedFile, "UTF-8"));

        // Check if file actually exists in filesystem.
        if (!file.exists()) {
            // Do your thing if the file appears to be non-existing.
            // Throw an exception, or send 404, or show default/warning page, or just ignore it.
            response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED);
            
            return;
        }
        
        ServletContext context = getServletContext();
         
        String mimeType = context.getMimeType( fileName );
        if ( mimeType == null || forceDownload )      
            mimeType = "application/octet-stream";
         
        response.setContentType(mimeType);
        response.setHeader("Accept-Ranges", "none");
//        response.setContentLength((int) downloadFile.length());
         
        String headerKey = "Content-Disposition";
        String headerValue = String.format("inline; filename=\"%s\"", fileName );
        response.setHeader( headerKey , headerValue);
         
        OutputStream outStream = response.getOutputStream();
         
        try {
        	if( insideZip == true )
        		ZipperStream.unzipToStream(file, fileName ,outStream);
        } catch (Exception e) {
        		logger.error(e.getStackTrace(), e);
        		response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED);
        } finally {
            if (outStream != null) {
                try {
                	outStream.close();
                } catch (IOException ignore) {}
            }
        }
         
    }
}