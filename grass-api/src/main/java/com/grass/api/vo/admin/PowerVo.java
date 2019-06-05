package com.grass.api.vo.admin;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author Fenglixiong
 * @Create 2019/5/28 0:18
 * @Description
 **/
@Data
public class PowerVo {

    private Integer id;

    /**
     * 权限名称
     */
    @NotBlank(message = "title is null")
    private String title;

    /**
     * 权限路径
     */
    @NotBlank(message = "path is null")
    private String path;

    /**
     * GET/POST/PUT/DELETE
     */
    @NotBlank(message = "method is null")
    private String method;

    /**
     * 展示用的
     */
    @NotNull(message = "parentId is null")
    private Integer parentId;

    /**
     * 权限备注
     */
    private String remark;

    /**
     * 子集权限
     */
    private List<PowerVo> children;

}
