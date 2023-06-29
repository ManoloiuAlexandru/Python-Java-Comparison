import requests
from bs4 import BeautifulSoup
import re


def currency_converting():
    """
    Function that send an api call to bnr bank and gets the exchange rates for RON
    We use BeautifulSoup to parse the response of the API and for the dictionary
    :return: the dictionary  with the exchange rates for RON
    """
    currency_dict = {}
    soup = BeautifulSoup(requests.get('https://www.cursbnr.ro/').content, 'lxml')
    values = soup.find('select', class_='form-control input-sm')

    for value in values.find_all('option'):
        currency_dict[value.text] = value['value']

    return currency_dict


# bank_dict = {}
# r = requests.get('https://www.cursbnr.ro/curs-valutar-banci')
# soup = BeautifulSoup(r.content, 'lxml')
# values = soup.find('div', class_='col-lg-12')
# counter = 1
# sell = 0
# buy = 0
# bank_name = ""
# for value in values.find_all('td'):
#     if not bool(re.search(r'\d', value.text.strip())):
#         bank_name = value.text.strip()
#     elif counter == 2:
#         sell = value.text.strip()
#     elif counter == 3:
#         buy = value.text.strip()
#     elif counter == 4:
#         bank_dict[bank_name] = {"sell": sell, "buy": buy}
#         counter = 0
#     counter += 1
#
# print(bank_dict)
