import java.io.Serializable;
import java.security.KeyPair;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String username;
	private String email;
	private String role;
	private String province;
	private String password;
	private KeyPair keyPair;

	public User(String name, String username, String email, String role, String province, String password,
			KeyPair keyPair) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.role = role;
		this.province = province;
		this.password = password;
		this.keyPair = keyPair;
	}

	// getters and setters for all fields...

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public KeyPair getKeyPair() {
		return keyPair;
	}

	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}
	
}
