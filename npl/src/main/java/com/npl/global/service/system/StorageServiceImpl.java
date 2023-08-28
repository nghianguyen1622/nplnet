package com.npl.global.service.system;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {
	
	private Logger logger = LoggerFactory.getLogger(StorageService.class);
	
	private final Path rootPdt = Paths.get("./fileupload/product");
	private final Path rootUser = Paths.get("./fileupload/users");
	private final Path rootCompany = Paths.get("./fileupload/company");
	private final Path rootMember = Paths.get("./fileupload/member");
	private final Path rootCat = Paths.get("./fileupload/category");
	private final Path rootBrand = Paths.get("./fileupload/brand");
	
	private Path root;

	@Override
	public Path getRoot(String type) {
		// TODO Auto-generated method stub
		Path root = null;
		switch(type) {
		case "product":
			root = this.rootPdt;
			break;
		case "user":
			root = this.rootUser;
			break;
		case "company":
			root = this.rootCompany;
			break;
		case "member":
			root = this.rootMember;
			break;
		case "cat":
			root = this.rootCat;
			break;
		case "brand":
			root = this.rootBrand;
			break;
		}
		return root;
	}

	@Override
	public void storeFolder(Path path) {
		// TODO Auto-generated method stub
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			logger.error("Could not initialize storage location: " + e.getMessage());
		}
	}

	@Override
	public String store(MultipartFile file, String type) {
		// TODO Auto-generated method stub
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				logger.error("Failed to store empty file " + fileName);
			}
			if (fileName.contains("..")) {
				// This is a security check
				logger.error("Cannot store file with relative path outside current directory " + fileName);
			}

			DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
			int i = fileName.lastIndexOf(".");
			fileName = fileName.substring(0, i).replaceAll(" ", "") + dateFormatter.format(new Date())
					+ fileName.substring(i);

			try (InputStream inputStream = file.getInputStream()) {
				this.root = getRoot(type);
				this.storeFolder(this.root);
				Files.copy(inputStream, this.root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			logger.error("Failed to store file " + fileName + "; " + e.getMessage());
		}

		return fileName;
	}

	@Override
	public boolean delete(String fileName, String type) {
		// TODO Auto-generated method stub
		try {
			Path path = load(fileName, type);
			File file = path.toFile();
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error("Failed to delete file " + fileName + "; " + e.getMessage());
			return false;
		}
	}

	@Override
	public Path load(String fileName, String type) {
		// TODO Auto-generated method stub
		this.root = getRoot(type); 
		return this.root.resolve(fileName);
	}

}
