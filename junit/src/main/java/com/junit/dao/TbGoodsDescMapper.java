package com.junit.dao;

import com.junit.model.TbGoodsDesc;

public interface TbGoodsDescMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods_desc
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods_desc
     *
     * @mbggenerated
     */
    int insert(TbGoodsDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods_desc
     *
     * @mbggenerated
     */
    int insertSelective(TbGoodsDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods_desc
     *
     * @mbggenerated
     */
    TbGoodsDesc selectByPrimaryKey(Long goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods_desc
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TbGoodsDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_goods_desc
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TbGoodsDesc record);
}