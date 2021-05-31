package com.mayikt.canal.commodity.es.repository;

//
//import org.springframework.data.repository.CrudRepository;

import com.mayikt.canal.commodity.es.bean.CommodityInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 余胜军
 * @ClassName CommodityInfoRepository
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月29日 18:47:00
 */
@Repository
public interface CommodityInfoRepository extends ElasticsearchRepository<CommodityInfo, Long> {
}
