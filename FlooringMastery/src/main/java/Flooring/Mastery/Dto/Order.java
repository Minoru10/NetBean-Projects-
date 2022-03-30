package Flooring.Mastery.Dto;

import java.math.BigDecimal;

public class Order {
    
    private String date;
    private int OrderNumber;
    private String customerName;
    private String state;
    private String productType;
    private String Area;
    private String TaxRate;
    private String CostPerSquareFoot;
    private String LaborCostPerSquareFoot;
    private String MaterialCost;
    private String LaborCost;
    private String Tax;
    private String Total;

    public Order(int OrderNumber) {
        this.OrderNumber = OrderNumber;
    }
    public int getOrderNumber() {
        return OrderNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String priductType) {
        this.productType = priductType;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    public String getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(String TaxRate) {
        this.TaxRate = TaxRate;
    }

    public String getCostPerSquareFoot() {
        return CostPerSquareFoot;
    }

    public void setCostPerSquareFoot(String CostPerSquareFoot) {
        this.CostPerSquareFoot = CostPerSquareFoot;
    }

    public String getLaborCostPerSquareFoot() {
        return LaborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(String LaborCostPerSquareFoot) {
        this.LaborCostPerSquareFoot = LaborCostPerSquareFoot;
    }

    public String getMaterialCost() {
        return MaterialCost;
    }

    public void setMaterialCost(String MaterialCost) {
        this.MaterialCost = MaterialCost;
    }

    public String getLaborCost() {
        return LaborCost;
    }

    public void setLaborCost(String LaborCost) {
        this.LaborCost = LaborCost;
    }

    public String getTax() {
        return Tax;
    }

    public void setTax(String Tax) {
        this.Tax = Tax;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }
    
    
}
