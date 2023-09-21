package com.npl.global.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.npl.global.entity.User;
import com.npl.global.model.user.RoleModel;
import com.npl.global.model.user.UserModel;

public interface UserDao extends PagingAndSortingRepository<User, String>{

	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User getUserByUserName(String username);

	@Query(value=" SELECT user_id  as userId                                      "
			+"          , CREATED_TIME  as createdTime                            "
			+"          , UPDATE_TIME as updatedTime                              "
			+"          , WORK_USER as workUser                                   "
			+"          , COM_ID as  comId                                        "
			+"          , file_name as  fileName                                  "
			+"          , file_name_org as  fileNameOrg                           "
			+"          , file_path as  filePath                                  "
			+"          , email as  email                                         "
			+"          , ADDR AS adress                                          "
			+"          , USER_NAME AS username                                   "
			+"          , PHONE AS phone                                          "
			+"          , passwd AS passwd                                        "
			+"          , CHECK_PASS AS checkPass                                 "
			+"          , IMG AS img                                              "
			+"          , IDENTITY_CARD AS identity                               "
			+"          , BIRTH_DAY AS birthDay                                   "
			+"          , enabled AS enabled                                      "
			+"       FROM users                                                   "
			+"      WHERE user_id = :userId                                       "
			, nativeQuery = true)
	public UserModel findUserName(String userId);
	
	@Query(value=" SELECT user_id  as userId                                                     "
			+"          , CREATED_TIME  as createdTime                                           "
			+"          , UPDATE_TIME as updatedTime                                             "
			+"          , WORK_USER as workUser                                                  "
			+"          , COM_ID as  comId                                                       "
			+"          , file_name as  fileName                                                 "
			+"          , file_name_org as  fileNameOrg                                          "
			+"          , file_path as  filePath                                                 "
			+"          , email as  email                                                        "
			+"          , ADDR AS adress                                                         "
			+"          , USER_NAME AS userName                                                  "
			+"          , PHONE AS phone                                                         "
			+"          , passwd AS passwd                                                       "
			+"          , CHECK_PASS AS checkPass                                                "
			+"          , IDENTITY_CARD AS identity                                              "
			+"          , BIRTH_DAY AS birthDay                                                  "
			+"          , enabled AS enabled                                                     "
			+"       FROM users a                                                                "
			+"      WHERE ((:fromDate = '' AND :toDate = '')                                     "
			+"             OR created_time BETWEEN uf_converttoyyymmdd(:fromDate, 'DD-MM-YYYY')  "
			+"                AND uf_converttoyyymmdd(:toDate, 'DD-MM-YYYY') )                   "
			+"        AND (COM_ID = :comId OR :comId = 'All')                                    "
			, nativeQuery = true)
	public List<UserModel> findAll(String fromDate, String toDate, String comId);
	
	@Query(value=" SELECT user_id  as userId                                                     "
			+"          , CREATED_TIME  as createdTime                                           "
			+"          , UPDATE_TIME as updatedTime                                             "
			+"          , WORK_USER as workUser                                                  "
			+"          , COM_ID as  comId                                                       "
			+"          , file_name as  fileName                                                 "
			+"          , file_name_org as  fileNameOrg                                          "
			+"          , file_path as  filePath                                                 "
			+"          , email as  email                                                        "
			+"          , ADDR AS adress                                                         "
			+"          , USER_NAME AS userName                                                  "
			+"          , PHONE AS phone                                                         "
			+"          , passwd AS passwd                                                       "
			+"          , CHECK_PASS AS checkPass                                                "
			+"          , IDENTITY_CARD AS identity                                              "
			+"          , BIRTH_DAY AS birthDay                                                  "
			+"          , enabled AS enabled                                                     "
			+"       FROM users a                                                                "
			+"      WHERE com_id = :comId                                                        "
			+"        AND ((:fromDate = '' AND :toDate = '')                                     "
			+"             OR created_time BETWEEN uf_converttoyyymmdd(:fromDate, 'DD-MM-YYYY')  "
			+"                AND uf_converttoyyymmdd(:toDate, 'DD-MM-YYYY') )                   "
			, nativeQuery = true)
	public List<UserModel> findByComId(String comId, String fromDate, String toDate);

	@Query(value=" SELECT file_name  as fileName                                  "
			+"          , file_name_org  as fileNameOrg                           "
			+"          , file_path  as filePath                                  "
			+"       FROM user_image                                              "
			+"      WHERE user_id = :userId                                       "
			, nativeQuery = true)
	public UserModel findFileName(String userId);
	
	@Query(value=" SELECT id as roleId       "
			+"          , name as name       "
			+"     FROM roles                "
			+"     WHERE id <> 1             "
			, nativeQuery = true)
	public List<RoleModel> findRole();
	
	

	/**
	 * @screen Đăng kí tài khoản
     * @param passwd
	 * @return String
	 */
	@Query(value="SELECT encode(digest(:passwd, 'sha256'), 'base64');" 
			, nativeQuery = true)
	public String encryptPass(String passwd); 
}
