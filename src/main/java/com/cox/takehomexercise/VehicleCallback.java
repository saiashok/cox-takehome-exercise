package com.cox.takehomexercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.swagger.client.ApiCallback;
import io.swagger.client.ApiException;
import io.swagger.client.model.VehicleAnswer;
import io.swagger.client.model.VehicleResponse;

public class VehicleCallback implements ApiCallback<VehicleResponse> {

	private Callback callback;

	public VehicleCallback() {
		this.callback = Callback.getInstance();
	}

	@Override
	public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
		
		try {
			throw new ApiException(statusCode, "Unable to get the Vehicle details "+ e.getMessage());
		}catch(Exception e1) {
			
		}
	 
	}

	@Override
	public void onSuccess(VehicleResponse result, int statusCode, Map<String, List<String>> responseHeaders) {
		if (statusCode == Application.SUCCESS_CODE) {
			List<VehicleAnswer> vehicles = new ArrayList<VehicleAnswer>();
			if (!callback.getMap().containsKey(result.getDealerId())) {
				VehicleAnswer v = new VehicleAnswer();
				v.setVehicleId(result.getVehicleId());
				v.setMake(result.getMake());
				v.setModel(result.getModel());
				v.setYear(result.getYear());
				vehicles.add(v);
				callback.getMap().put(result.getDealerId(), vehicles);
				
				try {
					CallbackFlow.getInstance().getvehicalIdcallback(result.getDealerId());
				} catch (ApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				vehicles = this.callback.getMap().get(result.getDealerId());

				VehicleAnswer v = new VehicleAnswer();
				v.setVehicleId(result.getVehicleId());
				v.setMake(result.getMake());
				v.setModel(result.getModel());
				v.setYear(result.getYear());
				vehicles.add(v);
				this.callback.getMap().put(result.getDealerId(), vehicles);
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