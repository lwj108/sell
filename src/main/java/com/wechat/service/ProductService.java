package com.wechat.service;

import com.wechat.dataobject.ProductInfo;
import com.wechat.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 分页查询，注意Pageable选择
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /** 加库存,取消订单加 */
    void increaseStock(List<CartDTO> cartDTOList);

    /** 减库存,选择订单减 */
    void decreaseStock(List<CartDTO> cartDTOList);
}
