package com.cox.takehomexercise;

import java.util.List;
import java.util.Map;
import io.swagger.client.ApiCallback;
import io.swagger.client.ApiException;
import io.swagger.client.model.DealerAnswer;
import io.swagger.client.model.DealersResponse;

public class DealerCallback implements ApiCallback<DealersResponse> {

	private Callback callback;

	public DealerCallback() {
		this.callback = Callback.getInstance();
	}

	@Override
	public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
		
		try {
		 throw new ApiException(statusCode, "Unable to get the Dealer details "+  e.getMessage());
		} catch(Exception e1) {
			
		}
	}

	@Override
	public void onSuccess(DealersResponse result, int statusCode, Map<String, List<String>> responseHeaders) {
		if (statusCode == Application.SUCCESS_CODE) {
			DealerAnswer dealer = new DealerAnswer();
			dealer.setDealerId(result.getDealerId());
			dealer.setName(result.getName());
			callback.getDealers().add(dealer);
			
			if (callback.getDealers().size() == callback.getMap().size()) {
				
				for (DealerAnswer dealerAnswers : callback.getDealers()) {
					dealerAnswers.setVehicles(callback.getMap().get(dealerAnswers.getDealerId()));
				}
				
				try {
					CallbackFlow.getInstance().postAnswer(callback.getDealers());
				} catch (ApiException e) {
					// TODO Auto-generated catch block
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
