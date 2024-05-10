import java.security.KeyPair;

public class Qualification implements HiringService {
    private String qualificationName;
    private String institution;
    private String graduateYear;
	private String studentIdentifier;

	private String name;
	private String username;
	private String email;
	private String role;
	private String province;
	private String password;
	private KeyPair keyPair;

User user=new User (name, username, email, role, province, email, keyPair);
	public Qualification(String studentIdentifier, String qualificationName, String institution, String graduateYear) {
        this.studentIdentifier = studentIdentifier;
        this.qualificationName = qualificationName;
        this.institution = institution;
        this.graduateYear = graduateYear;
    }

    // Implement the getIdentifier method from the HiringService interface
    @Override
    public String getIdentifier() {
        return qualificationName + " from " + institution;
    }
    public String getStudentIdentifier() {
		return studentIdentifier;
	}

	public void setStudentIdentifier(String studentIdentifier) {
		this.studentIdentifier = studentIdentifier;
	}

    // Getters for the Qualification class fields
    public String getQualificationName() {
        return qualificationName;
    }

    public String getInstitution() {
        return institution;
    }

    public String getGraduateYear() {
        return graduateYear;
    }

    // Override toString() to provide a formatted qualification description
    @Override
    public String toString() {
        return "StudentID:"+studentIdentifier+", Degree: "+qualificationName + ", Institution: " + institution + ", Year: " + graduateYear;
    }
}