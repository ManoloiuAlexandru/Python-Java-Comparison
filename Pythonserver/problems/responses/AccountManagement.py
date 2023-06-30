import mysql.connector

"""
Database connection using mysql
"""
my_db = mysql.connector.connect(
    host="localhost",
    user="root",
    password="1234",
    database="itschool"
)
my_cursor = my_db.cursor()


def get_data_from_db():
    """
    Function that gets data from the database
    :return: list of data
    """
    try:
        my_cursor.execute("SELECT * FROM accountmanagement")
        db_data = my_cursor.fetchall()
        return db_data
    except Exception as e:
        print("Error at getting data from db:", e)


def check_account_name(account_name):
    """
    Function that validates that the account name is not in the database
    :param account_name: account name
    :return: True if the account is in the database or False otherwise
    """
    if any(account_name in result[1] for result in get_data_from_db()):
        print("Account with that name exists")
        return True
    return False


def check_account_email(account_email):
    """
    Function that validates that the account email is not in the database
    :param account_email: account email
    :return: True if the email is in the database or False otherwise
    """
    if any(account_email == result[3] for result in get_data_from_db()):
        print("Account with that email exists")
        return True
    return False


def check_account_website(website, account_name, account_email):
    """
    Function that validates that the website where the account needs to be is not in the database
    :param account_email: account email to check
    :param account_name: account name to check
    :param website: website
    :return: True if the website is in the database or False otherwise
    """
    if any(website == result[0] for result in get_data_from_db()):
        try:
            my_cursor.execute('SELECT * FROM accountmanagement where website="' + website+'"')
            db_data = my_cursor.fetchall()
            if any(account_email == result[3] or account_name == result[1] for result in db_data):
                print("Account with that name or email exists")
                return False
        except Exception as e:
            print("Error at getting data from db:", e)
        return True
    return True


def data_check(data):
    """
    Function that validates the data using the other methods
    :param data: daa from the front end
    :return: True if data is correct or False otherwise
    """
    if check_account_website(data.get("website"), data.get("accountName"), data.get("accountEmail")):
        return True
    elif check_account_name(data.get("accountName")) or check_account_email(data.get("accountEmail")):
        return False
    return True


def add_data_to_db(data):
    """
    Function that sends data to mysql server if the validation is correct
    :param data: data from user
    :return: Massage
    """
    if data_check(data):
        sql = "INSERT INTO accountmanagement (website, accountName,accountPassword,accountEmail,accountSecret," \
              "recoveryEmail) VALUES (%s, %s,%s,%s,%s,%s) "
        val = (data.get("website"), data.get("accountName"), data.get("accountPassword"), data.get("accountEmail"),
               data.get("accountSecret"), data.get("recoveryEmail"))
        my_cursor.execute(sql, val)
        my_db.commit()
        return "Registration successful"
    else:
        return "Error at adding data to database"
