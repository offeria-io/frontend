package offeria.frontend.model;

import java.math.BigDecimal;

public class RFQItem {
    private String name;
    private String description;
    private BigDecimal quantity;
    private String unit;
    private BigDecimal unitPrice;
    private String remark;

    public RFQItem() {
        this.quantity = BigDecimal.ZERO;
        this.unitPrice = BigDecimal.ZERO;
    }

    public RFQItem(String name, String description, BigDecimal quantity, String unit, BigDecimal unitPrice, String remark) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.remark = remark;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}
