package kr.or.kosa.cmsplusmain.payment.entity;

public enum PaymentMethod {
	CMS(Values.CMS, true), CARD(Values.CARD, true),
	VIRTUAL_ACCOUNT(Values.VIRTUAL_ACCOUNT, false), BUYER(Values.BUYER, false);

	private final String name;
	private final boolean autoPaymentMethod;


	PaymentMethod(String name, boolean autoPaymentMethod) {
		this.name = name;
		this.autoPaymentMethod = autoPaymentMethod;
	}

	public static class Values {
		public static final String CMS = "CMS";
		public static final String CARD = "CARD";
		public static final String VIRTUAL_ACCOUNT = "VIRTUAL_ACCOUNT";
		public static final String BUYER = "BUYER";
	}
}
