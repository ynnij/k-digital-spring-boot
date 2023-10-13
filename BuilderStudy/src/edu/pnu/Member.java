package edu.pnu;

public class Member {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Member [username=" + username + ", password=" + password + "]";
	}

	public static MemberBuilder builder() {
		return new MemberBuilder();
	}
	
	static class MemberBuilder {
		private String username;
		private String password;
		
		public MemberBuilder username(String username) {
			this.username = username;
			return this;
		}

		public MemberBuilder password(String password) {
			this.password = password;
			return this;
		}
		public Member build() {
			Member m = new Member();
			m.setUsername(username);
			m.setPassword(password);
			return m;
		}
		
	}
}
