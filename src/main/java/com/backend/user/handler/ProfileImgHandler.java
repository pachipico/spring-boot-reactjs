package com.backend.user.handler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component
@AllArgsConstructor
public class ProfileImgHandler {


//	private final String PROFILE_UP_DIR = "/Users/jhs/Desktop/ezenSpring/spring_boot/springBackend/frontend/public/profileImg";
	private final String PROFILE_UP_DIR = "/home/ubuntu/qnaprj/frontend/build/profileImg";
//	private final String UP_DIR = "/Users/jhs/Desktop/ezenSpring/spring_boot/springBackend/frontend/public/img";
	private final String UP_DIR = "/home/ubuntu/qnaprj/frontend/build/img";
	
	public String uploadProfile(MultipartFile file) {

		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>upload profile");
		File folders = new File(PROFILE_UP_DIR);
		if(!folders.exists()) {
			folders.mkdirs();
		}
		String originalFileName = file.getOriginalFilename();
		
		String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator) + 1);
		
		UUID uuid = UUID.randomUUID();
		
		String fullFileName = "og_" + uuid.toString() +".jpeg";
		File storeFile = new File(folders.getAbsolutePath(), fullFileName);
		try {
			file.transferTo(storeFile);
			File thumbnail = new File(folders, "th_" + uuid.toString() + ".jpeg");
			Thumbnails.of(storeFile).size(100, 100).toFile(thumbnail);
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
		}
		return fullFileName.substring(3);
	}
	public String uploadFile(MultipartFile file) {
		if(file == null) return null;
		log.debug(">>>>>>>>>>>>>>>upload file>>>>>>>>>>>>>??");
		File folders = new File(UP_DIR);
		if(!folders.exists()) {
			folders.mkdirs();
		}
		String originalFileName = file.getOriginalFilename();

		String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator) + 1);

		UUID uuid = UUID.randomUUID();

		String fullFileName = "og_" + uuid.toString() +".jpeg";
		File storeFile = new File(folders.getAbsolutePath(), fullFileName);

		try {
			file.transferTo(storeFile);

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return fullFileName.substring(3);
	}
	
	public boolean removeProfile(String fileName) {
		File file = new File(PROFILE_UP_DIR);
		String ogFileName = "og_" + fileName;
		String thFileName = "th_" + fileName;
		File ogFile = new File(file, ogFileName);
		File thFile = new File(file, thFileName);
		ogFile.delete();
		return thFile.delete();
	}
	public boolean removeFile(String fileName) {
		File file = new File(UP_DIR);
		String ogFileName = "og_" + fileName;

		File ogFile = new File(file, ogFileName);

		return ogFile.delete();
	}
}
