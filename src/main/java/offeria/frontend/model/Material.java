package offeria.frontend.model;

public class Material {
    private Long id;
    private String name;
    private String sku;
    private String category;
    private String unit;
    private Double unitPrice;
    private Integer stock;

    public Material() {}

    public Material(Long id, String name, String sku, String category, String unit, Double unitPrice, Integer stock) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.category = category;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.stock = stock;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
