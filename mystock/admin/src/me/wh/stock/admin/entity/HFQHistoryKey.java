package me.wh.stock.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;

import coo.core.hibernate.search.DateBridge;

@Embeddable
public class HFQHistoryKey implements Serializable{
  
    private static final long serialVersionUID = -1396155673534827133L;
    private String code;
    @Temporal(TemporalType.DATE)
    @Field(analyze = Analyze.NO, bridge = @FieldBridge(impl = DateBridge.class))
    private Date tradeDate;
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Date getTradeDate() {
        return tradeDate;
    }
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }
    
    

}
