package user.service;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import user.bean.UserImageDTO;
import user.dao.UserUploadDAO;


@Service
public class UserUploadServiceImpl implements UserUploadService {
	
	
	@Autowired
	private UserUploadDAO userUploadDAO;
	@Autowired
	private ObjectStorageService objectStorageService;
	@Autowired
	private HttpSession session;
	private String bucketName="bitcamp-6th-bucket-102";
	
	
	@Override
	public void upload(List<UserImageDTO> userImageList) {
		userUploadDAO.upload(userImageList);
	}

	@Override
	public List<UserImageDTO> getUploadList() {
		
		return userUploadDAO.getUploadList();
	}

	@Override
	public void delete(int seq ) {
		userUploadDAO.delete(seq);
	}

	@Override
	public void update(UserImageDTO userImageDTO) {
		userUploadDAO.update(userImageDTO);
		
	}

	@Override
	public UserImageDTO getUploadImage(String seq) {
		return userUploadDAO.getUploadImage(seq);
	}

	@Override
	public void uploadUpdate(UserImageDTO userImageDTO, MultipartFile img) {
		//실제폴더
		String filePath=session.getServletContext().getRealPath("WEB-INF/storage");
		System.out.println("실제폴더="+filePath);
		
		String imageFileNmae = userUploadDAO.getImageFileName(userImageDTO.getSeq());
		System.out.println("imageFileNmae="+imageFileNmae);
		
		objectStorageService.deleteFile(bucketName, "storage/", imageFileNmae);
		
		imageFileNmae=objectStorageService.uploadFile(bucketName, "storage/", img);
		File file = new File(filePath,imageFileNmae);
		
		try {
			img.transferTo(file);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		userImageDTO.setImageFileName(imageFileNmae);
		userImageDTO.setImageOriginalName(img.getOriginalFilename());
		
		userUploadDAO.update(userImageDTO);
		
	}

	@Override
	public void uploadDelete(String[] check) {
		List<String> list = new ArrayList<>();
		
		for(String c: check) {
			String imageFileNmae = userUploadDAO.getImageFileName(Integer.parseInt(c));
			list.add(imageFileNmae);
		}
		
		objectStorageService.deleteFile(bucketName, "storage/", list);
		userUploadDAO.uploadDelete(list);
		
		
		
	}

}
