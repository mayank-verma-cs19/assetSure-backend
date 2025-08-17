package com.example.assetSure.dto;

public class CollateralDepositDTO {

    private Long id;
    private Long collateralMasterId;  // Use this consistently as field and getter/setter
    private Double weight;
    private String notes;
    private Boolean isReturned;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCollateralMasterId() {  // Correct getter name
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

    public Boolean getIsReturned() {  // Correct getter name for Boolean field
        return isReturned;
    }

    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }
}
