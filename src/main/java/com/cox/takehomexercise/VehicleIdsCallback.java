package com.cox.takehomexercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import io.swagger.client.ApiCallback;
import io.swagger.client.ApiException;
import io.swagger.client.model.DealerAnswer;
import io.swagger.client.model.VehicleAnswer;
import io.swagger.client.model.VehicleIdsResponse;

public class VehicleIdsCallback implements ApiCallback<VehicleIdsResponse> {

	private Callback callback;

	public VehicleIdsCallback() {
		this.callback = Callback.getInstance();
	}

	@Override
	public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
		
		try {
			 throw new ApiException(statusCode, "Unable to get the Vehicle id details "+ e.getMessage());	
		}catch(Exception e1) {
			
		}
	
	}

	@Override
	public void onSuccess(VehicleIdsResponse result, int statusCode, Map<String, List<String>> responseHeaders) {
		if (statusCode == Application.SUCCESS_CODE) {
			callback.setVehicleIDs(result.getVehicleIds());
			callback.setMap(new ConcurrentHashMap<Integer, List<VehicleAnswer>>());
			callback.setDealers(new ArrayList<DealerAnswer>());
			
			for (int vehicleId : callback.getVehicleIDs()) {
				try {
					CallbackFlow.getInstance().getvehicalcallback(vehicleId);
				} catch (ApiException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
	}

	@Override
	public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
	}

}