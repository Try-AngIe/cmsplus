package kr.or.kosa.cmsplusmain.payment.entity;

public enum PaymentType {
	AUTO(Values.AUTO), BUYER(Values.BUYER), VIRTUAL(Values.VIRTUAL_ACCOUNT);

	private final String typeName;

	PaymentType(String name) {
		this.typeName = name;
	}

	public static class Values {
		public static final String AUTO = "CMS";
		public static final String VIRTUAL_ACCOUNT = "VIRTUAL";
		public static final String BUYER = "BUYER";
	}
}
