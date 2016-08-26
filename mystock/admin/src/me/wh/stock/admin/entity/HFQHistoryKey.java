package me.wh.stock.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;

import com.fasterxml.jackson.annotation.JsonFormat;

import coo.core.hibernate.search.DateBridge;

@Embeddable
public class HFQHistoryKey implements Serializable{
  
    private static final long serialVersionUID = -1396155673534827133L;
    @Field(analyze = Analyze.NO)
    private String code;
    @Temporal(TemporalType.DATE)
    @Field(analyze = Analyze.NO, bridge = @FieldBridge(impl = DateBridge.class))
    @JsonFormat(pattern="yyyy-MM-dd")
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((tradeDate == null) ? 0 : tradeDate.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HFQHistoryKey other = (HFQHistoryKey) obj;
        if(other.getCode().equals(code)&& tradeDate.equals(other.getTradeDate())){
            return true;
        }
        return false;
    }
    
    

}
