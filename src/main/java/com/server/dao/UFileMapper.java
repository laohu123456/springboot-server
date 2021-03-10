package com.server.dao;

import com.server.entity.UFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UFileMapper {

    int insertUploadFile(UFile uFile);

    List<UFile> findFileByUserId(@Param("userId") String userId,
                                 @Param("curPage") Integer curPage,
                                 @Param("pageSize") Integer pageSize);

    int findAllSize(@Param("userId") String userId);

    int deleteFileById(@Param("id") String id);

    int deleteAllFileByUserId(@Param("userId") String userId);

    UFile findFileById(@Param("id") String id);

    List<UFile> findAllFileByUserId(@Param("userId") String userId);

}
