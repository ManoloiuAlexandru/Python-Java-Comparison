package demoschool.ITSchool.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * The AccountData class has 6 attributes:
 * website,accountName,accountPassword,accountEmail,accountSecret,recoveryEmail
 * @author  Manoliou Ionut-Alexandru
 */
@Data
@AllArgsConstructor
public class AccountData {

	private String website;
	private String accountName;
	private String accountPassword;
	private String accountEmail;
	private String accountSecret;
	private String recoveryEmail;

}
