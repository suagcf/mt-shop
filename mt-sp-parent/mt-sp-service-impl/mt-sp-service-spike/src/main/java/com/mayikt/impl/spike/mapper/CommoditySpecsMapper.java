package com.mayikt.impl.spike.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mayikt.impl.spike.entity.CommoditySpecsEntity;
import org.apache.ibatis.annotations.Update;

/**
 * @author 余胜军
 * @ClassName CommoditySpecsMapper
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年05月06日 17:18:00
 */
public interface CommoditySpecsMapper extends BaseMapper<CommoditySpecsEntity> {

        @Update("update commodity_specs set commodity_stok=commodity_stok-1 where id=#{commoditySpecsId} " +
                "and commodity_stok>0;")
//    @Update("update commodity_specs set commodity_stok=commodity_stok-1 where id=#{commoditySpecsId} ")
    int deductionInventory(Long commoditySpecsId);
}
