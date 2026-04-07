package offeria.frontend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RFQ {
    private Long id;
    private String rfqNumber;
    private String title;
    private String remark;
    private String clientName;
    private String status;
    private LocalDateTime createdDate;
    private LocalDateTime deadline;
    private String department;
    private Double budget;
    private List<RFQItem> items = new ArrayList<>();

    public RFQ() {}

    public RFQ(Long id, String rfqNumber, String title, String remark, String clientName, String status, LocalDateTime createdDate, LocalDateTime deadline, String department, Double budget) {
        this.id = id;
        this.rfqNumber = rfqNumber;
        this.title = title;
        this.remark = remark;
        this.clientName = clientName;
        this.status = status;
        this.createdDate = createdDate;
        this.deadline = deadline;
        this.department = department;
        this.budget = budget;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRfqNumber() { return rfqNumber; }
    public void setRfqNumber(String rfqNumber) { this.rfqNumber = rfqNumber; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
    public LocalDateTime getDeadline() { return deadline; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }
    public List<RFQItem> getItems() { return items; }
    public void setItems(List<RFQItem> items) { this.items = items; }
}
