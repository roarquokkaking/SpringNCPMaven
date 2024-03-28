package user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import user.bean.UserImageDTO;

@Repository
@Transactional
public class UserUploadDAOMyBatis implements UserUploadDAO {
	@Autowired
	private SqlSession sqlSession;
//	@Override
//	public void upload(List<UserImageDTO> userImageList) {
//		for(UserImageDTO userImageDTO : userImageList) {
//			sqlSession.insert("userUploadSQL.upload",userImageDTO);
//		}
//	}
	
	//XML에서 <forEach>  사용할 때
	@Override
	public void upload(List<UserImageDTO> userImageList) {
			sqlSession.insert("userUploadSQL.upload",userImageList);
	}
	
	@Override
	public List<UserImageDTO> getUploadList() {
		return sqlSession.selectList("userUploadSQL.getUploadList");
	}

	@Override
	public void delete(int seq) {
		sqlSession.delete("userUploadSQL.upload",seq);
	}

	@Override
	public void update(UserImageDTO userImageDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserImageDTO getUploadImage(String seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getImageFileName(int seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void uploadDelete(List<String> list) {
		// TODO Auto-generated method stub
		
	}
	

}
