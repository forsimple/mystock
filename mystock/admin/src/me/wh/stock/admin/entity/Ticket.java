package me.wh.stock.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import coo.core.hibernate.search.DateBridge;

/**
 * 基本信息。
 */
@Entity
@Table(name = "T_TICKET")
@Indexed(index = "TICKET")
public class Ticket implements Serializable {

     
    private static final long serialVersionUID = -6515776208870465543L;
    @Id
    @GeneratedValue(generator = "Assigned")
    @GenericGenerator(name = "Assigned", strategy = "org.hibernate.id.Assigned")
    @DocumentId
    private String code;
    private String name;
    private String industry;
    private String area;
    private double totalAssets;
    private double liquidAssets;
    private double fixedAssets;
    private double reserved;
    private double reservedPerShare;
    private double outstanding; // 流通股本
    private double totals;
    private double price;
    private double pe;
    private double pb;
    private double bvps;
    private double esp;
    @Temporal(TemporalType.DATE)
    @Field(analyze = Analyze.NO, bridge = @FieldBridge(impl = DateBridge.class))
    private Date timeToMarket;
    @Temporal(TemporalType.TIMESTAMP)
    @Field(analyze = Analyze.NO, bridge = @FieldBridge(impl = DateBridge.class))
    private Date updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getReserved() {
        return reserved;
    }

    public void setReserved(double reserved) {
        this.reserved = reserved;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPe() {
        return pe;
    }

    public void setPe(double pe) {
        this.pe = pe;
    }

    public double getPb() {
        return pb;
    }

    public void setPb(double pb) {
        this.pb = pb;
    }

    public double getBvps() {
        return bvps;
    }

    public void setBvps(double bvps) {
        this.bvps = bvps;
    }

    public double getEsp() {
        return esp;
    }

    public void setEsp(double esp) {
        this.esp = esp;
    }

    public Date getTimeToMarket() {
        return timeToMarket;
    }

    public void setTimeToMarket(Date timeToMarket) {
        this.timeToMarket = timeToMarket;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public double getLiquidAssets() {
        return liquidAssets;
    }

    public void setLiquidAssets(double liquidAssets) {
        this.liquidAssets = liquidAssets;
    }

    public double getReservedPerShare() {
        return reservedPerShare;
    }

    public void setReservedPerShare(double reservedPerShare) {
        this.reservedPerShare = reservedPerShare;
    }

    public double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public double getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(double fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(double outstanding) {
        this.outstanding = outstanding;
    }

    public double getTotals() {
        return totals;
    }

    public void setTotals(double totals) {
        this.totals = totals;
    }

}
