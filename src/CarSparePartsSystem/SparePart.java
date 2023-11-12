package CarSparePartsSystem;

public class SparePart {
    private String id;
    private String name;
    private String type;
    private String category;
    private String specifications;
    private int stockLevel;
    private double price;
    private String warrantyInfo;
    private String maintenanceHistory;

    public SparePart(String id, String name, String type, String category, String specifications, int stockLevel, double price, String warrantyInfo, String maintenanceHistory) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.category = category; // Initialize category
        this.specifications = specifications;
        this.stockLevel = stockLevel;
        this.price = price;
        this.warrantyInfo = warrantyInfo;
        this.maintenanceHistory = maintenanceHistory;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getCategory() { return category; }
    public String getSpecifications() { return specifications; }
    public int getStockLevel() { return stockLevel; }
    public double getPrice() { return price; }
    public String getWarrantyInfo() { return warrantyInfo; }
    public String getMaintenanceHistory() { return maintenanceHistory; }
    public String getDetailedInformation() {
        return "Name: " + name + "\nType: " + type + "\nSpecifications: " + specifications;
    }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setCategory(String category) { this.category = category; }
    public void setSpecifications(String specifications) { this.specifications = specifications; }
    public void setStockLevel(int stockLevel) { this.stockLevel = stockLevel; }
    public void setPrice(double price) { this.price = price; }
    public void setWarrantyInfo(String warrantyInfo) { this.warrantyInfo = warrantyInfo; }
    public void setMaintenanceHistory(String maintenanceHistory) { this.maintenanceHistory = maintenanceHistory; }

    // Additional methods for business logic can be added here
}

