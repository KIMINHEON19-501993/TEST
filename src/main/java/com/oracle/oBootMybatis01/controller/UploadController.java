package com.oracle.oBootMybatis01.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	//upLoadForm 시작화면
	@RequestMapping(value = "upLoadFormStart")
	public String upLoadFormStart(Model model) {
		
		System.out.println("UploadController upLoadFormStart() Start....");
		return "upLoadFormStart";
	}
	
	@RequestMapping(value = "uploadForm", method = RequestMethod.POST)
	public String uploadForm(HttpServletRequest request, 
						     MultipartFile file1,MultipartFile file2, Model model) throws Exception{
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		System.out.println("UploadController uploadForm() Start....");
		
		System.out.println("uploadForm POST Start...");
		logger.info("originaName : " + file1.getOriginalFilename());
		logger.info("size : " + file1.getSize());
		logger.info("contentType : " + file1.getContentType());
		logger.info("uploadPath : " + uploadPath);
		String savedName1 = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);
		String savedName2 = uploadFile(file2.getOriginalFilename(), file2.getBytes(), uploadPath);
		logger.info("savedName : " + savedName1);
		logger.info("savedName : " + savedName2);
		model.addAttribute("savedName",savedName1);
		return "uploadResult";
	}

	private String uploadFile(String originaname, byte[] fileData, String uploadPath)
					throws Exception{
		
		System.out.println("UploadController uploadForm() Start....");
		UUID uid = UUID.randomUUID();//UUID => universally unique identifier 국제적으로 유일한 식별자
		System.out.println("uploadPath --> " + uploadPath);
		File fileDirectory = new File(uploadPath);
		if (!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성 : " + uploadPath);
		}
		
		String saveName = uid.toString() + " " + originaname;
		logger.info("saveName : " + saveName);
		File target = new File(uploadPath, saveName);
		FileCopyUtils.copy(fileData, target);
		//Service ---> DAO 연결
		return saveName;
	}
	
	@RequestMapping(value = "uploadFileDelete")
	public String uploadFileDelete(HttpServletRequest request, 
		     MultipartFile file1,MultipartFile file2, Model model) throws Exception {
		System.out.println("UploadController uploadFileDelete() Start....");
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		String deleteFile = uploadPath + "50a087ae-638b-4f4f-ab87-328219cf8293 입사지원서_작성_참고자료_2[6].zip";
		logger.info("deleteFile --> " + deleteFile);
		System.out.println("uploadFileDelete POST Start...");
		int delResult = upFileDelete(deleteFile);
		logger.info("delResult --> " + delResult);
		model.addAttribute("deleteFile",deleteFile);
		model.addAttribute("delResult",delResult);
		
		return "uploadResult";
	}

	private int upFileDelete(String deleteFile) {
		
		int result = 0;
		logger.info("deleteFile ==> " + deleteFile);
		File file = new File(deleteFile);
		if (file.exists()) {
			if (file.delete()) {
				System.out.println("file 삭제 성공");
				result = 1;
			}else {
				System.out.println("file 삭제 성공");
				result = 1;
			}
		}else {
			System.out.println("file이 존재하지 않습니다.");
			result = -1;
		}
		
		return result;
	}
}
