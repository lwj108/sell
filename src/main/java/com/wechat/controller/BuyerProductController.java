package com.wechat.controller;

import com.wechat.VO.ProductInfoVO;
import com.wechat.VO.ProductVO;
import com.wechat.VO.ResultVO;
import com.wechat.dataobject.ProductCategory;
import com.wechat.dataobject.ProductInfo;
import com.wechat.service.CategoryService;
import com.wechat.service.ProductService;
import com.wechat.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResultVO list(){
        //1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询类目（一次性查询，提升性能）
        //List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法for循环
        //        for(ProductInfo productInfo:productInfoList){
        //            categoryTypeList.add(productInfo.getCategoryType());
        //        }

        /**
         * 精简方法（Java8，lambda）
         */
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e ->e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            /**
             * 注意不要将查询语句放入for循环，时间开销大
             */
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    //spring提供BeanUtils.copyproperties(A->B)
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        //json格式一层套一层信息
        //ResultVO resultVO = new ResultVO();
        //ProductVO productVO = new ProductVO();
        //ProductInfoVO productInfoVO = new ProductInfoVO();

        //productInfoVO放入productVO中再放入resultVO中
        //productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
        //resultVO.setData(Arrays.asList(productVO));

        //resultVO.setCode(0);
        //resultVO.setMsg("成功");
        //resultVO.setData(productVOList);

        //ResultVOUtil封装简化上面代码书写
        return ResultVOUtil.success(productVOList);
    }
}
