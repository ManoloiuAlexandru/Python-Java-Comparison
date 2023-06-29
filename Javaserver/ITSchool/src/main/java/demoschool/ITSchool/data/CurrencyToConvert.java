package demoschool.ITSchool.data;

import lombok.Data;

@Data
public class CurrencyToConvert {
	private String currency;
	private String currencyToConvertTo;
	private float amount;

}
