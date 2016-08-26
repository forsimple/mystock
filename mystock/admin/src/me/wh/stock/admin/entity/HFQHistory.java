package me.wh.stock.admin.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import me.wh.stock.core.search.JsonBridge;

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
    @EmbeddedId
    @DocumentId
    @FieldBridge(impl = JsonBridge.class)
    private HFQHistoryKey key;
    @Field(analyze = Analyze.NO)
    private double open;
    @Field(analyze = Analyze.NO)
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
