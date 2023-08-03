package com.example.xixi.erpSkuMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author : xi-xi
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class SkuMap {
    //id	parentSkuBom	parentSkuOriginId	childSkuBom	childSkuOriginId
// childSkuQt	unitPriceRate	_version	createdAt	updatedAt	isDeleted	deletedAt	UpdatedBy	CreatedBy
    Long id;
    String parentSkuBom;
    String parentSkuOriginId;
    String childSkuBom;
    String childSkuOriginId;
    String childSkuQt;
    String unitPriceRate;
    int _version=1;
    String createdAt;
    String updatedAt;
    int  isDeleted =0;
    int  deletedAt =0 ;
    int UpdatedBy=0;
    int CreatedBy=0;
}
