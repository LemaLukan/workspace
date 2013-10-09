package badCode;

public abstract class DateAddress {
		private String address;
		private String province;
		private MyDate aDate;
		public DateAddress(String address, MyDate aDate, String province)
		{
			this.address = address;
			this.province = province;
			this.aDate = aDate;
		}
		
		public String getAddress()
		{
			return this.address;
		}
		public String getProvince()
		{
			return this.province;
		}
		public MyDate getDate()
		{
			return this.aDate;
		}
		public abstract String displayDateAddress();
}
