public class Staff {
    private String staffId;
    private String name;
    private String email;

    public Staff(String staffId, String name, String email) {
        this.staffId = staffId;
        this.name = name;
        this.email = email;
    }

    public String getStaffId() { return staffId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("Staff[%s: %s (%s)]", staffId, name, email);
    }
}