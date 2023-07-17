package com.file;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

	int insertFile(String absolutePath);
	List<FileDTO> selectAllFile();
	FileDTO selectFile(int fno);

}
