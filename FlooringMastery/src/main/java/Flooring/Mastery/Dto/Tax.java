
package Flooring.Mastery.Dto;

import java.math.BigDecimal;
import java.util.Objects;


public class Tax {
    private String stateAbbrv;
    private String stateName;
    private BigDecimal TaxRate;

    public Tax(String stateAbbrv, String stateName, BigDecimal TaxRate) {
        this.stateAbbrv = stateAbbrv;
        this.stateName = stateName;
        this.TaxRate = TaxRate;
    }

    public String getStateAbbrv() {
        return stateAbbrv;
    }

    public void setStateAbbrv(String stateAbbrv) {
        this.stateAbbrv = stateAbbrv;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(BigDecimal TaxRate) {
        this.TaxRate = TaxRate;
    }
    
    
    public String toString(){
        return "State abbreviation: " + stateAbbrv + ", state:" + stateName + ", tax rate:"+ TaxRate.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.stateAbbrv);
        hash = 71 * hash + Objects.hashCode(this.stateName);
        hash = 71 * hash + Objects.hashCode(this.TaxRate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tax other = (Tax) obj;
        if (!Objects.equals(this.stateAbbrv, other.stateAbbrv)) {
            return false;
        }
        if (!Objects.equals(this.stateName, other.stateName)) {
            return false;
        }
        if (!Objects.equals(this.TaxRate, other.TaxRate)) {
            return false;
        }
        return true;
    }
    
    
}
