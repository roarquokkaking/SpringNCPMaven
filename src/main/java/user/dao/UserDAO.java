package user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import user.bean.UserDTO;

@Mapper
public interface UserDAO {

	public UserDTO isExistId(String id);

	public void write(UserDTO userDTO);

	public List<UserDTO> getUserList(int startNum);

	public int getTotal();

	public UserDTO getUser(String id);

	public void update(UserDTO userDTO);

	public void delete(String id);

}
