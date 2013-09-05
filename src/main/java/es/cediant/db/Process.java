package es.cediant.db;
// Generated Aug 29, 2013 4:45:28 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;

public class Process implements Serializable {
    
    private static final long serialVersionUID = 286049946510030890L;

    private Integer idProcess;
    private User user;
    private String username;
    private String name;
    private String type;
    private int priority;
    private String status;
    private Date startTime;
    private Date endTime;

    public Process() {
    }
	
    public Process(User user, int priority) {
        this.user = user;
        this.priority = priority;
    }
    public Process(User user, String name, String type, int priority, Date startTime, Date endTime) {
       this.user = user;
       this.name = name;
       this.type = type;
       this.priority = priority;
       this.startTime = startTime;
       this.endTime = endTime;
    }
   
    public Integer getIdProcess() {
        return this.idProcess;
    }
    
    public void setIdProcess(Integer idProcess) {
        this.idProcess = idProcess;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {     
        username = user.getUserName();
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }        
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}

