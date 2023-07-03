package demoschool.ITSchool.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import demoschool.ITSchool.data.AccountData;
import lombok.RequiredArgsConstructor;

/*
 * @author Manoloiu Ionut-Alexandru
 */
@Service
@RequiredArgsConstructor
public class AccountManagementServices {
	private static Connection dbConnection() {
		/*
		 * Method that connects to the mysql server
		 * 
		 * @returns the connection to the server
		 */
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/itschool?useSSL=false&serverTimezone=GMT", "root", "1234");
			return con;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	private static ArrayList<AccountData> getDataFromDB() {
		/*
		 * Method that connects to the mysql server and returns a list of accountsdata
		 * 
		 * @returns list of accountsdata
		 */
		try (Statement stmt = dbConnection().createStatement();) {
			ArrayList<AccountData> result = new ArrayList<>();
			ResultSet rs = stmt.executeQuery("select * from accountmanagement");
			while (rs.next()) {
				AccountData accountData = new AccountData(rs.getString(rs.findColumn("website")),
						rs.getString(rs.findColumn("accountName")), rs.getString(rs.findColumn("accountPassword")),
						rs.getString(rs.findColumn("accountEmail")), rs.getString(rs.findColumn("accountSecret")),
						rs.getString(rs.findColumn("recoveryEmail")));
				result.add(accountData);
			}
			return result;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	private static ArrayList<AccountData> getDataFromDBWithSpecificWebsite(String website) {
		/*
		 * Method that connects to the mysql server and returns a list of accountsdata
		 * looking for a specific website
		 * 
		 * @returns list of accountsdata
		 */
		try (Statement stmt = dbConnection().createStatement();) {
			ArrayList<AccountData> result = new ArrayList<>();
			ResultSet rs = stmt.executeQuery("select * from accountmanagement where website=\"" + website + "\";");
			while (rs.next()) {
				AccountData accountData = new AccountData(rs.getString(rs.findColumn("website")),
						rs.getString(rs.findColumn("accountName")), rs.getString(rs.findColumn("accountPassword")),
						rs.getString(rs.findColumn("accountEmail")), rs.getString(rs.findColumn("accountSecret")),
						rs.getString(rs.findColumn("recoveryEmail")));
				result.add(accountData);
			}
			return result;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	private static boolean checkAccountName(String accountName) {
		/*
		 * Method that validates that the account name is not in the database
		 * 
		 * @returns True if the account is in the database or False otherwise
		 */
		for (AccountData account : getDataFromDB())
			if (accountName.equals(account.getAccountName()))
				return true;
		return false;
	}

	private static boolean checkAccountEmail(String accountEmail) {
		/*
		 * Method that validates that the email is not in the database
		 * 
		 * @returns True if the account is in the database or False otherwise
		 */
		for (AccountData account : getDataFromDB())
			if (accountEmail.equals(account.getAccountEmail()))
				return true;
		return false;
	}

	private static boolean checkWebsite(AccountData accountData) {
		/*
		 * Method that validates that the account name or email is not used on a specify
		 * web-site in the database
		 * 
		 * @returns True if the account is in the database or False otherwise
		 */
		for (AccountData account : getDataFromDB())
			if (accountData.getWebsite().equals(account.getWebsite())) {
				for (AccountData accountFromWebsite : getDataFromDBWithSpecificWebsite(accountData.getWebsite())) {
					if (accountFromWebsite.getAccountName().equals(accountData.getAccountName())
							|| accountFromWebsite.getAccountEmail().equals(accountData.getAccountEmail()))
						return false;
				}
			}
		return true;
	}

	private static boolean checkData(AccountData accountData) {
		/*
		 * Method that validates the data using the other methods
		 * 
		 * @returns True if the account is in the database or False otherwise
		 */
		if (checkWebsite(accountData))
			return true;
		else if (checkAccountName(accountData.getAccountName()) || checkAccountEmail(accountData.getAccountEmail()))
			return false;
		return true;
	}

	private static String addNewAccountToDB(AccountData accountData) {
		/*
		 * Method that sends data to mysql server if the validation is correct
		 * 
		 * @returns Massage
		 */
		if (checkData(accountData)) {
			try (Statement stmt = dbConnection().createStatement();) {
				stmt.executeUpdate(
						"INSERT INTO accountmanagement (website, accountName,accountPassword,accountEmail,accountSecret,recoveryEmail) values (\""
								+ accountData.getWebsite() + "\",\"" + accountData.getAccountName() + "\",\""
								+ accountData.getAccountPassword() + "\",\"" + accountData.getAccountEmail() + "\",\""
								+ accountData.getAccountSecret() + "\",\"" + accountData.getRecoveryEmail() + "\");");
			} catch (Exception e) {
				System.out.println(e);
			}
			return "Registration successful";
		}
		return "Error at adding data to database";
	}

	public String printDataFromDB(AccountData accountData) throws IOException {
		return addNewAccountToDB(accountData);
	}
}
