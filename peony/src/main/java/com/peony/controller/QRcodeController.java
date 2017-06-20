package com.peony.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.peony.util.QrUtil;

@Controller
@RequestMapping("QRcode")
public class QRcodeController {
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(Model model){
		return "qrcode/home";
	}
	
	@RequestMapping(value = "homeImg", method = RequestMethod.POST)
	public String homeImg(Model model, HttpServletResponse response, 
			HttpServletRequest request, 
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "file", required = false) MultipartFile logo){
		try {
			if(logo != null){
				File f = new File("");
				inputstreamToFile(logo.getInputStream(), f);
				BufferedImage bi = QrUtil.encodeIII(url, f, "H", null, 
						response.getOutputStream(), 
						300, 300, 6);
				model.addAttribute("bi", bi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qrcode/home";
	}
	
	/*@RequestMapping(value = "create", method = RequestMethod.GET)
	public void create(Model model, HttpServletResponse response, 
			HttpServletRequest request){
		try {
			QrUtil.encode(request.getParameter("url"), null, "H", null, 
					response.getOutputStream(), 500, 500, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	public void inputstreamToFile(InputStream ins, File file) throws Exception{
		OutputStream os = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = ins.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		ins.close();
	}
	
	/*public static void main(String[] args) throws Exception {
		FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\1.jpg"));
		QrUtil.encode("https://www.baidu.com/", 
				"C:\\Users\\Administrator\\Desktop\\11111.jpg", 
				"H", null, out, 300, 300, 5);
	}*/
	
}
