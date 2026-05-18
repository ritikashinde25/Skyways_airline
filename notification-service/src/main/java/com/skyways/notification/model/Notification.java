package com.skyways.notification.model;
 
public class Notification {
 
    private Long id;
    private String username;
    private String email;
    private String subject;
    private String message;
    private String type;
    private String status;
    private String sentAt;
 
    public Notification() {}
 
    public Notification(Long id, String username, String email,
                        String subject, String message, String type,
                        String status, String sentAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.type = type;
        this.status = status;
        this.sentAt = sentAt;
    }
 
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
 
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
 
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
 
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
 
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
 
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
 
    public String getSentAt() { return sentAt; }
    public void setSentAt(String sentAt) { this.sentAt = sentAt; }
}
 