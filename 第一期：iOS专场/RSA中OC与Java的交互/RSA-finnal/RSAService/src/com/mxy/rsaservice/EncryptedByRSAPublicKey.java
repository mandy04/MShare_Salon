package com.mxy.rsaservice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EncryptedByRSAPublicKey
 */
@WebServlet("/EncryptedByRSAPublicKey")
public class EncryptedByRSAPublicKey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		String path = this.getClass().getResource("").getPath();
		String webInfPath = path.substring(0, path.indexOf("WEB-INF/") + "WEB-INF/".length());
		String privateKeyPath = webInfPath + "rsakey/rsa_public_key.pem";        // replace your public key path here
        String publicKeyPath =  webInfPath + "rsakey/pkcs8_private_key.pem";     // replace your private path here
        String data = request.getParameter("data").replaceAll(" ", "");
        String encryptedData = "";
        PrintWriter out = response.getWriter();  
        try {
			RSAEncryptor rsaEncryptor = new RSAEncryptor(privateKeyPath, publicKeyPath);
			encryptedData = rsaEncryptor.encryptedByPublicKey(data);
		} catch (Exception e) {
			e.printStackTrace();
			out.write(encryptedData);
		}
        out.write(encryptedData);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
