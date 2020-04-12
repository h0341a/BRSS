package com.jycz.user.service;


import com.jycz.common.response.BusinessException;
import com.jycz.user.model.dto.RecommendDto;

/**
 * 书籍
 * @author ling
 */
public interface BookService {
    /**
     * 添加图书推荐
     * @param uid 用户id
     * @param recommendDto 相关信息
     * @return 是否成功
     */
    public boolean addBookRecommend(Integer uid, RecommendDto recommendDto) throws BusinessException;


}
