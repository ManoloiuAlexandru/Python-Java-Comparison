package demoschool.ITSchool.data;

import lombok.Data;

@Data
public class AccountData {

	private String website;
	private String accountName;
	private String accountPassword;
	private String accountEmail;
	private String accountSecret;
	private String recoveryEmail;
}
