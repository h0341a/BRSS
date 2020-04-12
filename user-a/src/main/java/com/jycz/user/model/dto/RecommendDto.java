package com.jycz.user.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author ling
 * @data 2020/4/12 13:06
 */
@Data
public class RecommendDto {
    /**
     * 书名
     */
    @NotBlank(message = "书名不能为空")
    @Length(max = 64, message = "最长不得超过64位字符")
    String bookName;
    /**
     * 作者
     */
    @NotBlank(message = "作者不能为空")
    @Length(max = 64, message = "最长不得超过64位字符")
    String bookAuthor;
    /**
     * 书籍介绍
     */
    String introduction;
    /**
     * 推荐原因的详细
     */
    @NotBlank(message = "内容不能为空")
    @Length(min = 16, message = "最短不得少于16位字符")
    private String content;
}
