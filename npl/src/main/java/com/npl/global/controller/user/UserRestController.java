package com.npl.global.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.npl.global.common.Constant;
import com.npl.global.common.FileUploadUtil;
import com.npl.global.dao.user.UserDao;
import com.npl.global.dto.ResultProcDto;
import com.npl.global.dto.user.UserDto;
import com.npl.global.model.user.UserModel;
import com.npl.global.security.NplUserDetails;
import com.npl.global.service.system.StorageService;
import com.npl.global.service.user.UserService;

@RestController
public class UserRestController {

	@Autowired private UserService service;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private UserDao userDao;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/1010")
	public  @ResponseBody List<UserModel> getAll() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			List<UserModel> listUser = service.findAll(comId);
			return listUser;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/1010/{id}")
	public  @ResponseBody UserModel getInfo(@PathVariable(name = "id") String userId) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			String comId = loggedUser.getUser().getCompany().getComId();
			UserModel userInfo = service.findUserName(userId);
			return userInfo;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@PostMapping(value = "/1010/save", consumes = { "multipart/form-data" }, produces = { "application/json", "application/xml" })
	public @ResponseBody ResultProcDto save(@RequestPart UserDto userSave, @RequestPart("fileImage") MultipartFile multipartFile) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			NplUserDetails loggedUser = (NplUserDetails) authentication.getPrincipal();
			
			ResultProcDto result;

			String comId = loggedUser.getUser().getCompany().getComId();
			String workUser = loggedUser.getUser().getWorkUser();
			String comCd = loggedUser.getUser().getCompany().getComCd();
			
			userSave.setComId(comId);
			userSave.setWorkUser(workUser);
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String pass = passwordEncoder.encode(userSave.getPasswd());
			String checkpass = userDao.encryptPass(userSave.getPasswd());
			
			userSave.setPasswd(pass);
			userSave.setCheckPw(checkpass);
			
			result = this.service.saveUser(userSave);
			
			if(!multipartFile.isEmpty()) {
				String fileName = storageService.store(multipartFile, "user");
//				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				
				userSave.setFilePath("fileupload/users/" + fileName);
				userSave.setFileName(fileName);
				userSave.setFileNameOrg(fileName);
				
				userSave.setKindCD(comCd + "u10");
				userSave.setUserId(result.getKeyValue());
				
				ResultProcDto result1 = this.service.saveUserImage(userSave);
				if(!result1.getRetCode().equals(Constant.RETCODE_OK)) {
					return result1;
				}
				
//				String uploadDir = "fileupload/users/" + fileName;
//				FileUploadUtil.clearDir(uploadDir);
//				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			}else {
				if(!userSave.getFileName().equals("")) {
					storageService.delete(userSave.getFileName(), "user");
				}
				userSave.setFilePath("");
				userSave.setFileName("");
				userSave.setFileNameOrg("");
			}
			
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			
			return null;
		}
	}
	
	@RequestMapping(value = "/1010/delete/{userId}")
	public  @ResponseBody ResultProcDto  delete( @PathVariable(name = "userId") String userId) {
		try {
			String fileName = this.service.findFileName(userId).getFileName();
			ResultProcDto result = this.service.delUser(userId);
			
			storageService.delete(fileName, "user");
			//return
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
}
