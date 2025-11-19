public class SchedulingUser {
    private String userId;
    private String name;
    private String role;

    public SchedulingUser(String userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getRole() { return role; }
}