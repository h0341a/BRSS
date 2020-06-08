package com.jycz.consumer.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class AddRecommendDto {
    private Integer bid;
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "内容不能为空")
    @Length(min = 16, message = "最短不得少于16位字符")
    private String content;
}
