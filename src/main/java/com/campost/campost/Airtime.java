package com.campost.campost;

public class Airtime {
private String	 provider = "mtn_mobilemoney_cm";
	private String reference = "677777777";
	private int amount = 300;
	private String secret = "b51f99a9a7d574e9a92b1ba8d9c4f519";
	private String iv = "74ae6fb3981d22519bdcaf3411df16f4";
	private String purchaseref = "";
	private String store = "AFC9160";
	private String code = "";
	private String notifurl = "https://webhook.site/6ce2d6e8-1667-4adc-a78d-e9d2684c3d6e";
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getIv() {
		return iv;
	}
	public void setIv(String iv) {
		this.iv = iv;
	}
	public String getPurchaseref() {
		return purchaseref;
	}
	public void setPurchaseref(String purchaseref) {
		this.purchaseref = purchaseref;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNotifurl() {
		return notifurl;
	}
	public void setNotifurl(String notifurl) {
		this.notifurl = notifurl;
	}
	public Airtime(String provider, String reference, int amount, String secret, String iv, String purchaseref,
			String store, String code, String notifurl) {
		super();
		this.provider = provider;
		this.reference = reference;
		this.amount = amount;
		this.secret = secret;
		this.iv = iv;
		this.purchaseref = purchaseref;
		this.store = store;
		this.code = code;
		this.notifurl = notifurl;
	}
	public Airtime() {
		super();
		// TODO Auto-generated constructor stub
	}

}
