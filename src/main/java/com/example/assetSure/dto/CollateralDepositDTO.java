package com.example.assetSure.dto;

public class CollateralDepositDTO {

    private Long id;
    private Long collateralMasterId;
    private Double weight;
    private String notes;
    private Boolean isReturned;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCollateralMasterId() {
        return collateralMasterId;
    }

    public void setCollateralMasterId(Long collateralMasterId) {
        this.collateralMasterId = collateralMasterId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }

    private String collateralMasterName;

    public String getCollateralMasterName() {
        return collateralMasterName;
    }
    public void setCollateralMasterName(String collateralMasterName) {
        this.collateralMasterName = collateralMasterName;
    }

}
