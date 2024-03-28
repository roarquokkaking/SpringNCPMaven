package user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import user.bean.UserDTO;
import user.bean.UserPaging;
import user.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserPaging userPaging;
	@Override
	public String isExistId(String id) {
		UserDTO userDTO = userDAO.isExistId(id);
		
		if(userDTO ==null)
			return "non_exist";
		else
			return "exist";
	}
	@Override
	public void write(UserDTO userDTO) {
		userDAO.write(userDTO);
		
	}
	@Override
	public Map<String,Object> getUserList(String pg) {
		//1페이지당 3개씩
		int startNum= (Integer.parseInt(pg)*3)-2;
		
		
		List<UserDTO> list = userDAO.getUserList(startNum);
		//List -> JSON 변환 
		
		
		//총글수 가져오기
		int total = userDAO.getTotal();
		
		
		userPaging.setCurrentPage(Integer.parseInt(pg));
		userPaging.setPageBlock(3);
		userPaging.setPageSize(3);
		userPaging.setTotal(total);
		userPaging.makePagingHTML();
		
		Map<String,Object> map = new HashMap<>();
		map.put("list", list);
		map.put("userPaging", userPaging.getPagingHTML().toString());
		

		return map;
	}
	@Override
	public UserDTO getUser(String id) {
		
		return userDAO.getUser(id);
	}
	@Override
	public void update(UserDTO userDTO) {
		userDAO.update(userDTO);
	}
	@Override
	public void delete(String id) {
		userDAO.delete(id);
		
	}

}
