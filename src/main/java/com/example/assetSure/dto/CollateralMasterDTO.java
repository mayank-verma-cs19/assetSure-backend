package com.example.assetSure.dto;

public class CollateralMasterDTO {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Double getPurity() {
        return purity;
    }

    public void setPurity(Double purity) {
        this.purity = purity;
    }

    public Double getEstimatedWeight() {
        return estimatedWeight;
    }

    public void setEstimatedWeight(Double estimatedWeight) {
        this.estimatedWeight = estimatedWeight;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    private String material;
    private Double purity;
    private Double estimatedWeight;
    private Boolean isActive;

    // getters and setters
}
