package com.cox.takehomexercise;

import java.util.List;

import io.swagger.client.ApiException;
import io.swagger.client.model.Answer;
import io.swagger.client.model.DealerAnswer;

public class CallbackFlow {

	private static CallbackFlow callbackflow = null;
	private DealerCallback dcallback = null;

	static {
		callbackflow = new CallbackFlow();
	}

	private Callback callback;

	public CallbackFlow() {
		this.callback = Callback.getInstance();
		this.dcallback = new DealerCallback();
	}

	public static CallbackFlow getInstance() {
		return callbackflow;
	}

	public void getvehicalcallback(Integer vehicleId) throws ApiException {
		callback.getVehcilesApi().getVehicleAsync(callback.getDataSetID(), vehicleId, new VehicleCallback());
	}

	public void getvehicalIdcallback(Integer dealerId) throws ApiException {
		this.callback.getDealertApi().getDealerAsync(this.callback.getDataSetID(), dealerId, dcallback);
	}

	public void postAnswer(List<DealerAnswer> dealers) throws ApiException {
		Answer ans = new Answer();
		ans.setDealers(dealers);
		System.out.println(this.callback.getDataSetApi().postAnswer(this.callback.getDataSetID(), ans));
		System.exit(1);
	}

}
