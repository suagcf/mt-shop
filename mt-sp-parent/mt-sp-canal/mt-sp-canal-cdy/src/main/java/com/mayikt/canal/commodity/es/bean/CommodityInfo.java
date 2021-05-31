package com.mayikt.canal.commodity.es.bean;

import lombok.Data;
//
//import org.elasticsearch.search.DocValueFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 余胜军
 * @ClassName CommodityInfo
 * @qq 644064779
 * @addres www.mayikt.com
 * @createTime 2021年04月29日 18:39:00
 */
@Data
//通过这个注解可以声明一个文档，指定其所在的索引库和type
@Document(indexName = "commodityinfo")
public class CommodityInfo implements Serializable {
    @Id
    private Long id;
    private Long commodityId;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String mainImage;
    @Field(type = FieldType.Text)
    private String subImage;
    @Field(type = FieldType.Text)
    private String detail;
    @Field(type = FieldType.Text)
    private String attributeList;
    @Field(type = FieldType.Double)
    private BigDecimal price;
    @Field(type = FieldType.Integer)
    private Long stock;
    @Field(type = FieldType.Integer)
    private Integer status;
    @Field(type = FieldType.Date)
    private Date createTime;
    private Date updateTime;


}
