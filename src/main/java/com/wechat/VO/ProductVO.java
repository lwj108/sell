package com.wechat.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品(包含类目)
 * @JsonProperty 返回json名称，使代码容易区分，而返回json还是文档中的格式
 * 不使用Product实例是为了安全，重新建VO对象
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    //存放productInfoVO对象
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
