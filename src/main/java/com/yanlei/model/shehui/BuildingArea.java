package com.yanlei.model.shehui;

import java.math.BigDecimal;

/**
 * @Author: x
 * @Date: Created in 11:39 2018/6/12
 */
public class BuildingArea {

    private BigDecimal totalArea;
    //private BigDecimal vacancyArea ;
    private String streetName;
    private BigDecimal liveArea;

    public BigDecimal getLiveArea() {
        return liveArea;
    }

    public void setLiveArea(BigDecimal liveArea) {
        this.liveArea = liveArea;
    }

   public BigDecimal getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(BigDecimal totalArea) {
        this.totalArea = totalArea;
    }

   /* public BigDecimal getVacancyArea() {
        return vacancyArea;
    }

    public void setVacancyArea(BigDecimal vacancyArea) {
        this.vacancyArea = vacancyArea;
    }*/

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

}
