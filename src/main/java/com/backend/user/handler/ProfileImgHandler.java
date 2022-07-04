package com.backend.user.handler;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component
@AllArgsConstructor
public class ProfileImgHandler {

	private final String UP_DIR = "/Users/jhs/Desktop/ezenSpring/spring_boot/springBackend/frontend/public/profileImg";
	
	public String uploadFile(MultipartFile file) {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>??");
		File folders = new File(UP_DIR);
		if(!folders.exists()) {
			folders.mkdirs();
		}
		String originalFileName = file.getOriginalFilename();
		
		String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator) + 1);
		
		UUID uuid = UUID.randomUUID();
		
		String fullFileName = "og_" + uuid.toString() + "_" + onlyFileName;
		File storeFile = new File(folders, fullFileName);
		try {
			file.transferTo(storeFile);
			File thumbnail = new File(folders, "th_" + uuid.toString()+ "_" + onlyFileName); 
			Thumbnails.of(storeFile).size(100, 100).toFile(thumbnail);
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
		}
		return fullFileName.substring(3);
	}
	
	public boolean removeFile(String fileName) {
		File file = new File(UP_DIR);
		String ogFileName = "og_" + fileName;
		String thFileName = "th_" + fileName;
		File ogFile = new File(file, ogFileName);
		File thFile = new File(file, thFileName);
		ogFile.delete();
		return thFile.delete();
	}
}
