package es.cediant.db;

public class Node  implements java.io.Serializable {
    
    private static final long serialVersionUID = -7185281796990436419L;

    private Integer idNode;
    private String hostname;
    private String ip;
    private String status;
    private String cpu;
    private String mem;
    private String disk;

    public Node() {
    }

    public Node(String hostname, String ip) {
        this.hostname = hostname;
        this.ip = ip;
    }
    
    public Node(String hostname, String ip, String status, String cpu, String mem, String disk) {      
       this.hostname = hostname;
       this.ip = ip;
       this.status = status;
       this.cpu = cpu;
       this.mem = mem;
       this.disk = disk;
    }

    public Integer getIdNode() {
        return idNode;
    }

    public void setIdNode(Integer idNode) {
        this.idNode = idNode;
    }
    
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
        this.mem = mem;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }           

}

