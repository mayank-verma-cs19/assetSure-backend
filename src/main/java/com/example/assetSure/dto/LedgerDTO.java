package com.example.assetSure.dto;

import com.example.assetSure.model.Lender;

import java.time.LocalDate;
import java.util.List;

public class LedgerDTO {

    private Long id;
    private String name;
    private String fatherName;
    private String address;
    private String phone;
    private Double amount;
    private Integer estimateDays;
    private Double roi;
    private LocalDate date;
    private String description;

    private String status; // ACTIVE, CLOSED, etc.
    private Double repaymentAmount;
    private Double interestCharged;
    private String closedByName;
    private String closedByContact;
    private String finalComments;
    private Long lendedBy;

    public Long getLendedBy() {
        return lendedBy;
    }

    public void setLendedBy(Long lendedBy) {
        this.lendedBy = lendedBy;
    }

    private List<CollateralDepositDTO> collaterals;

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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getEstimateDays() {
        return estimateDays;
    }

    public void setEstimateDays(Integer estimateDays) {
        this.estimateDays = estimateDays;
    }

    public Double getRoi() {
        return roi;
    }

    public void setRoi(Double roi) {
        this.roi = roi;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(Double repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public Double getInterestCharged() {
        return interestCharged;
    }

    public void setInterestCharged(Double interestCharged) {
        this.interestCharged = interestCharged;
    }

    public String getClosedByName() {
        return closedByName;
    }

    public void setClosedByName(String closedByName) {
        this.closedByName = closedByName;
    }

    public String getClosedByContact() {
        return closedByContact;
    }

    public void setClosedByContact(String closedByContact) {
        this.closedByContact = closedByContact;
    }

    public String getFinalComments() {
        return finalComments;
    }

    public void setFinalComments(String finalComments) {
        this.finalComments = finalComments;
    }

    public List<CollateralDepositDTO> getCollaterals() {
        return collaterals;
    }

    public void setCollaterals(List<CollateralDepositDTO> collaterals) {
        this.collaterals = collaterals;
    }

    // getters and setters
}
