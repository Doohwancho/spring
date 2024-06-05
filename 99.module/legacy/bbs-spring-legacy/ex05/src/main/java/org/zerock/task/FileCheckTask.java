package org.zerock.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zerock.domain.BoardAttachVO;
import org.zerock.mapper.BoardAttachMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class FileCheckTask {
	
	@Setter(onMethod_ = {@Autowired})
	private BoardAttachMapper attachMapper;
	
	private String getFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String str = sdf.format(cal.getTime());
		return str.replace("-", File.separator); //File.separator를 쓰는 이유는, 운영체제마다 다른 파일 구분자(\이나 /이나 :)를 쓰고 있기 때문에, 통일시킨것.
		/*
		 * File file = new File("honeymon" + File.separator + "test" + File.separator + "file-separator");
			
		       이렇게 할 수도 있긴 한데, 요신 Paths를 주로 쓴다.
		       
		   File file = Paths.get("honeymon", "test", "path").toFile();

		 */
	}
	
	@Scheduled(cron="0 0 2 * * *") //매일 새벽 2시 정각에 작동
	public void checkFiles() throws Exception{
		log.warn("File Check Task run..............");
		log.warn(new Date());
		
		//file list in db
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		
		//ready for check file in dir with db file list
		List<Path> fileListPaths = fileList.stream().map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid()+"_"+vo.getFileName())).collect(Collectors.toList());
		
		//image file has thumbnail file
		fileList.stream().filter(vo -> vo.isFileType() == true).map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid()+"s_"+vo.getFileName())).forEach(p -> fileListPaths.add(p));
		
		log.warn("===================================");
		
		fileListPaths.forEach(p -> log.warn(p));
		
		//files in yesterday directory
		File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();
		
		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
		
		log.warn("=======================================");
		for(File file : removeFiles) {
			log.warn(file.getAbsolutePath());
			file.delete();
		}
	} 
}
