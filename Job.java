public class Job implements HiringService {
    private String jobTitle;
    private String jobSkills;

    public Job(String jobTitle, String jobSkills) {
        this.jobTitle = jobTitle;
        this.jobSkills = jobSkills;
    }

    // Implement the getIdentifier method from the HiringService interface
    @Override
    public String getIdentifier() {
        return jobTitle;
    }

    // Getters for job title and skills
    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobSkills() {
        return jobSkills;
    }

    // Override toString() to provide a formatted job description
    @Override
    public String toString() {
        return jobTitle + ", Skills Required: " + jobSkills;
    }
}