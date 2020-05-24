package com.jycz.consumer.service;

import com.github.pagehelper.PageInfo;
import com.jycz.common.model.vo.BookVo;
import com.jycz.common.response.BusinessException;
import com.jycz.consumer.model.dto.RecommendDto;

import java.util.List;

/**
 * @author ling
 * @data 2020/4/8 11:25
 */
public interface UserService {

    /**
     * 添加图书推荐
     * @param uid 用户id
     * @param recommendDto 相关信息
     * @return 是否成功
     */
    public boolean addBookRecommend(Integer uid, RecommendDto recommendDto) throws BusinessException;

    /**
     * 获取用户收藏的书籍
     * @param page 页码
     * @param pageSize 页容量
     * @return 书籍列表
     */
    public PageInfo<BookVo> getCollectBooks(int page, int pageSize);
}
