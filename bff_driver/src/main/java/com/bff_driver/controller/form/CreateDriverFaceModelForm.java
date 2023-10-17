package com.bff_driver.controller.form;

import com.tencentcloudapi.live.v20180801.models.StreamName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "创建司机人脸模型归档的表单")
public class CreateDriverFaceModelForm {

    @Schema(description = "司机ID")
    private Long driverId;

    @NotBlank(message = "photo不能为空")
    @Schema(description = "司机面部照片Base64字符串")
    private String photo;
}
