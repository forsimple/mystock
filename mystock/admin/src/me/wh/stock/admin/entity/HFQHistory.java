package me.wh.stock.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
@Table(name = "HFQ_HISTORY")
@Indexed(index = "HFQHistory")
public class HFQHistory implements Serializable {
     
    private static final long serialVersionUID = -6515776208870465543L;
    @Id
    @GeneratedValue 
    @DocumentId
    private HFQHistoryKey key;
    
    @Field(analyze = Analyze.NO)
    private String code;
    private double open;
    private double close;
    private double high;
    private double low;
    private double factor;
    private double volume;
    private double amount;
    public HFQHistoryKey getKey() {
        return key;
    }
    public void setKey(HFQHistoryKey key) {
        this.key = key;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public double getOpen() {
        return open;
    }
    public void setOpen(double open) {
        this.open = open;
    }
    public double getClose() {
        return close;
    }
    public void setClose(double close) {
        this.close = close;
    }
    public double getHigh() {
        return high;
    }
    public void setHigh(double high) {
        this.high = high;
    }
    public double getLow() {
        return low;
    }
    public void setLow(double low) {
        this.low = low;
    }
    public double getFactor() {
        return factor;
    }
    public void setFactor(double factor) {
        this.factor = factor;
    }
    public double getVolume() {
        return volume;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    

}
