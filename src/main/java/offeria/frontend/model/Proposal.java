package offeria.frontend.model;

import java.time.LocalDateTime;

public class Proposal {
    private Long id;
    private Long rfqId;
    private String vendorName;
    private Double totalAmount;
    private String status;
    private LocalDateTime submissionDate;
    private String technicalDetails;
    private String commercialDetails;

    public Proposal() {}

    public Proposal(Long id, Long rfqId, String vendorName, Double totalAmount, String status, LocalDateTime submissionDate, String technicalDetails, String commercialDetails) {
        this.id = id;
        this.rfqId = rfqId;
        this.vendorName = vendorName;
        this.totalAmount = totalAmount;
        this.status = status;
        this.submissionDate = submissionDate;
        this.technicalDetails = technicalDetails;
        this.commercialDetails = commercialDetails;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getRfqId() { return rfqId; }
    public void setRfqId(Long rfqId) { this.rfqId = rfqId; }
    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(LocalDateTime submissionDate) { this.submissionDate = submissionDate; }
    public String getTechnicalDetails() { return technicalDetails; }
    public void setTechnicalDetails(String technicalDetails) { this.technicalDetails = technicalDetails; }
    public String getCommercialDetails() { return commercialDetails; }
    public void setCommercialDetails(String commercialDetails) { this.commercialDetails = commercialDetails; }
}
