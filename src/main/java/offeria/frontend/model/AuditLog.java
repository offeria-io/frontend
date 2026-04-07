package offeria.frontend.model;

import java.time.LocalDateTime;

public class AuditLog {
    private Long id;
    private String user;
    private String action;
    private String entity;
    private Long entityId;
    private LocalDateTime timestamp;
    private String details;

    public AuditLog() {}

    public AuditLog(Long id, String user, String action, String entity, Long entityId, LocalDateTime timestamp, String details) {
        this.id = id;
        this.user = user;
        this.action = action;
        this.entity = entity;
        this.entityId = entityId;
        this.timestamp = timestamp;
        this.details = details;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public String getEntity() { return entity; }
    public void setEntity(String entity) { this.entity = entity; }
    public Long getEntityId() { return entityId; }
    public void setEntityId(Long entityId) { this.entityId = entityId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
