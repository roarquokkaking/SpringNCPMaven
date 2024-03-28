package user.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import user.bean.UserImageDTO;
import user.service.ObjectStorageService;
import user.service.UserUploadService;

@Controller
@RequestMapping(value = "user")
public class UserUploadController {
	@Autowired
	private UserUploadService userUploadService;
	
	@Autowired
	private ObjectStorageService objectStorageService;
	
	private String bucketName="bitcamp-6th-bucket-102";

	@GetMapping(value = "uploadForm")
	public String uploadForm() {
		return "user/uploadForm";
	}
	
	@PostMapping(value="upload")
	@ResponseBody
	public String upload(@ModelAttribute UserImageDTO userImageDTO, @RequestParam("img[]") List<MultipartFile> list,HttpSession session) {
		
		//실제폴더
		String filePath=session.getServletContext().getRealPath("WEB-INF/storage");
		System.out.println("실제폴더="+filePath);
		
		//이미지 이름
		String fileName="";
		String originalFileName = "";
		File file;
		String result ="";
		
		//파일명만 모아서 DB로 보내기
		List<UserImageDTO> userImageList=new ArrayList<>();
		
		for(MultipartFile img: list) {
			originalFileName= img.getOriginalFilename();
			System.out.println("originalFileName="+originalFileName);
			
			//네이버 클라우드 Object storage UUID 
			fileName = objectStorageService.uploadFile(bucketName,"storage/",img);
			
			file = new File(filePath,originalFileName);
			
			try {
				img.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				result +="<span><img src='/mini/storage/"+
				URLEncoder.encode(originalFileName,"UTF-8")+"'/></span>";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			UserImageDTO dto =new UserImageDTO();
			
			dto.setImageName(userImageDTO.getImageName());
			dto.setImageContent(userImageDTO.getImageContent());
			dto.setImageFileName(fileName);
			dto.setImageOriginalName(originalFileName);
			
			userImageList.add(dto);
		}
		
		
		
		
		userUploadService.upload(userImageList);
		return result;
		
	}
	
	@PostMapping(value="updateImage")
	@ResponseBody 
	public String updateImage(@ModelAttribute UserImageDTO userImageDTO,@RequestParam("img[]") List<MultipartFile> list,HttpSession session) {
		
		objectStorageService.deleteFile(bucketName,"storage/",userImageDTO.getImageFileName());
		
		
				String fileName="";

				for(MultipartFile img: list) {

					//네이버 클라우드 Object storage UUID 
					fileName = objectStorageService.uploadFile(bucketName,"storage/",img);

					userImageDTO.setImageFileName(fileName);
				}
				
				
				userUploadService.update(userImageDTO);
				
		  return "success";
	  
	}
	
	
	
	  @PostMapping(value="deleteImage")
	  @ResponseBody 
	  public String deleteImage(@ModelAttribute UserImageDTO userImageDTO) {
	  
		  System.out.println("userImageDTO.getImageFileName()="+userImageDTO.getImageFileName());
	  objectStorageService.deleteFile(bucketName,"storage/",userImageDTO.getImageFileName());
 
	  userUploadService.delete(userImageDTO.getSeq());
	  
	  return "success";
	  
	  }
	 
	
	
	@GetMapping(value = "uploadList")
	public String uploadList() {
		return "user/uploadList";
	}
	
	@PostMapping(value = "getUploadList")
	@ResponseBody
	public List<UserImageDTO> getUploadList() {
		return userUploadService.getUploadList();
		
	}
	
	@GetMapping(value = "uploadView")
	public String uploadView(@RequestParam String seq, Model model) {
		
		model.addAttribute("seq",seq);
		return "user/uploadView";
	}
	
	@PostMapping(value = "getUploadImage")
	@ResponseBody
	public UserImageDTO getUploadImage(@RequestParam String seq) {
		return userUploadService.getUploadImage(seq);
		
	}
	@GetMapping(value = "uploadUpdateForm")
	public String uploadUpdateForm(@RequestParam String seq, Model model) {
		model.addAttribute("seq",seq);
		return "user/uploadUpdateForm";
	}
	@PostMapping(value = "uploadUpdate", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String uploadUpdate(@ModelAttribute UserImageDTO userImageDTO, @RequestParam MultipartFile img) {
		
		System.out.println("seq="+ userImageDTO.getSeq());
		
		userUploadService.uploadUpdate(userImageDTO,img);
		return "이미지 수정 완료";
		
	}
	
	@PostMapping(value = "uploadDelete")
	@ResponseBody
	public void uploadDelete(@RequestParam String[] check) {
		for(String c : check) {
			System.out.println(c);
		}
		userUploadService.uploadDelete(check);
	}
}
