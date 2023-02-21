package com.musclebeach.product.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity // 設定為實體類別
@Table(name = "product") // 設定映射資料表
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product implements Serializable {
    @Id // 設定識別屬性 ->⼀定要設定此Annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 設定識別值產⽣⽅式
    @Column(name = "pro_id")
    private Integer proID;
    @Column(name = "pro_name")
    private String proName;
    @Column(name = "type_id")
    private Integer typeID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    @JsonIgnore
    private ProductType productType;
    @Column(name = "pro_qty")
    private Integer proQty;
    @Column(name = "pro_price")
    private Integer proPrice;
    @Column(name = "pro_content")
    @JsonIgnore
    private String proContent;
    @Column(name = "pro_status", insertable = false)
    private Integer proStatus;
    @Column(name = "updatetime", insertable = false) // insertable = false ->新增時，跳過某欄位
    @JsonIgnore
    private Timestamp updateTime;
    @Column(name = "createtime", insertable = false, updatable = false) // updatable = false ->更新時，跳過某欄位
    @JsonIgnore
    private Timestamp createTime;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductImg> productImgs;
    @Transient
    @JsonIgnore
    private ProductImg updateImg; // 修改商品圖片用
}
