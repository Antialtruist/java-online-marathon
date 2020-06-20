package sprint03.PracticeTaskInnerOuterClassEnum;

//	Create ClientType enum that contains NEW, SILVER, GOLD and PLATINUM constants that represent client status.
//	For storing count of months for each client create private "months" field of type int and initialize it using constructor with int parameter.
//	Set for each item of enum a value according to next table:
//	Client status     Count of months
//	NEW               0
//	SILVER            12
//	GOLD              30
//	PLATINUM          60
//	Create public method named “discount()” that return discount value as coefficient from 1.0 to 0.0 according to client status.
//	By default discount() method should return value 1.0 of double type.
//	Override discount() method for each constant and return discount value that calculated by next formula:
//	(100 - <count of months per client> * 0,35) / 100
	
enum ClientType {
	
	NEW(0) {
		@Override
		public double discount() {
			return super.discount();
		}
	},
	
	SILVER(12) {
		@Override
		public double discount() {
			return super.discount();
		}
	},
	
	GOLD(30) {
		@Override
		public double discount() {
			return super.discount();
		}
	},
	
	PLATINUM(60) {
		@Override
		public double discount() {
			return super.discount();
		}
	};
	
	private int months;

	private ClientType(int months) {
		this.months = months;
	}
	
	public double discount() {
		double dis = (100 - months * 0.35) / 100;
		if(dis < 1.0 && dis > 0.0) {
			return dis;
		} else {
	    return 1.0;
	    }
	}
}

