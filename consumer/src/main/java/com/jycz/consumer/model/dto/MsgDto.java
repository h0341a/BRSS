package com.jycz.consumer.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class MsgDto {

    @NotEmpty(message = "接受方不能为空")
    private Integer targetId;
    @NotBlank(message = "消息不能为空")
    private String content;
}
