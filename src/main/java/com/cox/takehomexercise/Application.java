package com.cox.takehomexercise;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.squareup.okhttp.Call;

import io.swagger.client.ApiException;

public class Application {
	public final static int SUCCESS_CODE = 200;
	public static Callback call = Callback.getInstance();

	public static void main(String[] args) throws ApiException, InterruptedException, ExecutionException {
		Executor executor = Executors.newFixedThreadPool(2);
		CompletableFuture<String> getDataSetApi = CompletableFuture.supplyAsync(() -> {
			String dataSetId = "";
			try {
				dataSetId = call.getDataSetApi().getDataSetId().getDatasetId();
			} catch (ApiException e) {
				throw new IllegalStateException(e);
			}
			System.out.println(dataSetId);
			return dataSetId;
		});

		CompletableFuture<Call> greetingFuture = getDataSetApi.thenApplyAsync(dataSetId -> {
			call.setDataSetID(dataSetId);
			Call c = null;
			VehicleIdsCallback vehicleIdsCallback = new VehicleIdsCallback();
			try {
				c = call.getVehcilesApi().getIdsAsync(dataSetId, vehicleIdsCallback);
			} catch (ApiException e) {
				e.printStackTrace();
			}
			return c;
		}, executor);

		greetingFuture.get().isExecuted();

	}

}
