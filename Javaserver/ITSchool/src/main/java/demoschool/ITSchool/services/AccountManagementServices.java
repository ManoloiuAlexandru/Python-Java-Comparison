package demoschool.ITSchool.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import demoschool.ITSchool.data.AccountData;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountManagementServices {
	private static Connection dbConnection() {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/itschool?useSSL=false&serverTimezone=GMT", "root", "1234");
			return con;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	private static AccountData createAccountData(ResultSet rs) {
		AccountData accountData = new AccountData();
		try {
			accountData.setWebsite(rs.getString(rs.findColumn("website")));
			accountData.setAccountEmail(rs.getString(rs.findColumn("accountEmail")));
			accountData.setAccountName(rs.getString(rs.findColumn("accountName")));
			accountData.setAccountPassword(rs.getString(rs.findColumn("accountPassword")));
			accountData.setAccountSecret(rs.getString(rs.findColumn("accountSecret")));
			accountData.setRecoveryEmail(rs.getString(rs.findColumn("recoveryEmail")));
		} catch (SQLException e) {
			System.out.println(e);
		}
		return accountData;
	}

	private static ArrayList<AccountData> getDataFromDB() {
		try (Statement stmt = dbConnection().createStatement();) {
			ArrayList<AccountData> result = new ArrayList<>();
			ResultSet rs = stmt.executeQuery("select * from accountmanagement");
			while (rs.next()) {
				result.add(createAccountData(rs));
			}
			return result;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	private static ArrayList<AccountData> getDataFromDBWithSpecificWebsite(String website) {
		try (Statement stmt = dbConnection().createStatement();) {
			ArrayList<AccountData> result = new ArrayList<>();
			ResultSet rs = stmt.executeQuery("select * from accountmanagement where website=\"" + website + "\";");
			while (rs.next()) {
				result.add(createAccountData(rs));
			}
			return result;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	private static boolean checkAccountName(String accountName) {
		for (AccountData account : getDataFromDB())
			if (accountName.equals(account.getAccountName()))
				return true;
		return false;
	}

	private static boolean checkAccountEmail(String accountEmail) {
		for (AccountData account : getDataFromDB())
			if (accountEmail.equals(account.getAccountEmail()))
				return true;
		return false;
	}

	private static boolean checkWebsite(String website) {
		for (AccountData account : getDataFromDB())
			if (website.equals(account.getWebsite())) {
				for (AccountData accountFromWebsite : getDataFromDBWithSpecificWebsite(website)) {
					if (accountFromWebsite.getAccountName().equals(account.getAccountName())
							|| accountFromWebsite.getAccountEmail().equals(account.getAccountEmail()))
						return false;
				}
			}
		return true;
	}

	private static boolean checkData(AccountData accountData) {
		if (checkWebsite(accountData.getWebsite()))
			return true;
		else if (checkAccountName(accountData.getAccountName()) || checkAccountEmail(accountData.getAccountEmail()))
			return false;
		return true;
	}

	private static String addNewAccountToDB(AccountData accountData) {
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
