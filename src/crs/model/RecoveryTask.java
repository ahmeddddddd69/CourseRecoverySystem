package crs.model;

public class RecoveryTask {

    private int week;
    private String description;
    private String status;   // Pending, Completed

    public RecoveryTask(int week, String description) {
        this.week = week;
        this.description = description;
        this.status = "Pending";
    }

    // Getters & Setters
    public int getWeek() { return week; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
