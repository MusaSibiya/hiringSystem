public class JobApplication implements HiringService {
    private String employeeProfile;
    private String briefDescription;

    public JobApplication(String employeeProfile, String briefDescription) {
        this.employeeProfile = employeeProfile;
        this.briefDescription = briefDescription;
    }

    // Getters and setters for employeeProfile and briefDescription

    @Override
    public String toString() {
        return "JobApplication{" +
               "employeeProfile='" + employeeProfile + '\'' +
               ", briefDescription='" + briefDescription + '\'' +
               '}';
    }

	@Override
	public String getIdentifier() {
		return  employeeProfile;
	               
	}
}
