/**
 * 
 */
package net.prolancer.validation.mapper;

import net.prolancer.validation.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author jaechulhan
 *
 */
@Mapper
public interface UserMapper {
	
	/**
	 * @param user
	 * @return
	 */
	User selectUserById(User user);

	/**
	 * Create a user
	 * @param user
	 * @return
	 */
	int insertUser(User user);

}
