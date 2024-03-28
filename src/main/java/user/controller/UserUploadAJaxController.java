package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import user.bean.UserImageDTO;
import user.service.UserUploadService;

@Controller
@RequestMapping(value = "user")
public class UserUploadAJaxController {

	@Autowired
	private UserUploadService userUploadService;
	
	@GetMapping(value = "uploadFormAJax")
	public String uploadFormAJax(@ModelAttribute UserImageDTO userImageDTO,Model model){
		model.addAttribute("seq",userImageDTO.getSeq());
		model.addAttribute("imageName",userImageDTO.getImageName());
		model.addAttribute("imageContent",userImageDTO.getImageContent());
		model.addAttribute("imageFileName",userImageDTO.getImageFileName());
		return "user/uploadFormAJax";
	}
}
