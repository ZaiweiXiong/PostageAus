package resouces;

public enum APIResources {
	
	retrieve_post_parcel_sizes("/postage/parcel/domestic/size.json"),
	postage_and_extra_cover("/postage/parcel/domestic/service.json"),
	calculate_total_delivery_price("/postage/parcel/domestic/calculate.json");
	
	private String path;
	
	APIResources(String path)
	{
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}

}
