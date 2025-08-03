public class Vendor {
	private int id;
	private String name;
	private String email;
	private boolean approved;

	// Getters and setters
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public boolean isApproved() { return approved; }
	public void setApproved(boolean approved) { this.approved = approved; }
}