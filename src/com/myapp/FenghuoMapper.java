package com.myapp;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by JohnC on 2016-08-11.
 */
public interface FenghuoMapper
{
    @Insert("INSERT INTO `fenghuo_data`.`basicinfo` (`host`, `updatetime`, `countryid`, `topicid`, `heat`, `revertcount`, `taskurl`, `topictype`, `url`, `createtime`, `projectid`, `areaid`, `sitename`, `ip`, `domaintype`, `title`, `forumname`, `imagepath`, `domain`, `datasourcetype`, `charset`,`filePath`) VALUES (#{host},#{updatetime},#{countryid},#{topicid},#{heat},#{revertcount},#{taskurl},#{topictype},#{url},#{createtime},#{projectid},#{areaid},#{sitename},#{ip},#{domaintype},#{title},#{forumname},#{imagepath},#{domain},#{datasourcetype},#{charset},#{filePath});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insertBasicInfo(BasicInfo basicInfo);
    
    @Insert("INSERT INTO `fenghuo_data`.`article` (`nickname`, `postip`, `postfloor`, `posttime`, `location`, `userid`, `summary`, `source`, `postdata`, `imgurl`, `basicinfo_id`) VALUES (#{nickname}, #{postip}, #{postfloor}, #{posttime}, #{location}, #{userid}, #{summary}, #{source}, #{postdata}, #{imgurl}, #{basicinfo_id});")
    int insertArticle(Article article);
    
    @Select("SELECT count(id) FROM basicInfo WHERE filePath=#{filePath}")
    int countByfilePath(@Param("filePath") String filePath);
    
}
