package com.jycz.common.dao;

import com.jycz.common.model.entity.Book;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    /**
     * 根据图书名与作者查询书籍,如此是因为除了数据库id之外需要依靠书名+作者确定书籍唯一
     * @param book 书籍信息
     * @return id
     */
    @Select("select id from book where name=#{name} and author=#{author}")
    Integer selectIdByNameAndAuthor(Book book);
    @Select("select * from book where status = #{status}")
    List<Book> selectAllByStatus(Integer status);
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
}