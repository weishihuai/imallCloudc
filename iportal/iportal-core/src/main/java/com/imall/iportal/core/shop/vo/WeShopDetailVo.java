package com.imall.iportal.core.shop.vo;

import com.imall.commons.base.util.Node;
import com.imall.iportal.core.main.vo.FileMngSimpleVo;
import com.imall.iportal.core.main.vo.FileMngVo;
import com.imall.iportal.core.shop.entity.WeShop;

import java.util.List;

/**
 * Created by lxh on 2017/8/4.
 */
public class WeShopDetailVo extends WeShop{
    //省
    private List<Node> province;
    //市
    private List<Node> city;
    //区
    private List<Node> area;
    //省ID
    private Long provinceId;
    //市ID
    private Long cityId;
    //区ID
    private Long areaId;
    //店长微信
    private FileMngSimpleVo shopMgrWeChatPict;
    //门店头像
    private FileMngSimpleVo shopLogoPict;
    //门店图片
    private List<FileMngSimpleVo> shopPictList;

    public List<Node> getProvince() {
        return province;
    }

    public void setProvince(List<Node> province) {
        this.province = province;
    }

    public List<Node> getCity() {
        return city;
    }

    public void setCity(List<Node> city) {
        this.city = city;
    }

    public List<Node> getArea() {
        return area;
    }

    public void setArea(List<Node> area) {
        this.area = area;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public FileMngSimpleVo getShopMgrWeChatPict() {
        return shopMgrWeChatPict;
    }

    public void setShopMgrWeChatPict(FileMngSimpleVo shopMgrWeChatPict) {
        this.shopMgrWeChatPict = shopMgrWeChatPict;
    }

    public FileMngSimpleVo getShopLogoPict() {
        return shopLogoPict;
    }

    public void setShopLogoPict(FileMngSimpleVo shopLogoPict) {
        this.shopLogoPict = shopLogoPict;
    }

    public List<FileMngSimpleVo> getShopPictList() {
        return shopPictList;
    }

    public void setShopPictList(List<FileMngSimpleVo> shopPictList) {
        this.shopPictList = shopPictList;
    }
}
