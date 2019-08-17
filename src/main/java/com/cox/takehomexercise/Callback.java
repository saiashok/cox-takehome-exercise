package com.cox.takehomexercise;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import io.swagger.client.api.DataSetApi;
import io.swagger.client.api.DealersApi;
import io.swagger.client.api.VehiclesApi;
import io.swagger.client.model.DealerAnswer;
import io.swagger.client.model.VehicleAnswer;

public class Callback {

	private static Callback callbackinstance;

	/**
	 * The Static initializer constructs the instance at class
	 */
	static {
		callbackinstance = new Callback();
	}

	private String dataSetID = null;
	private List<DealerAnswer> dealers = null;
	private List<Integer> VehicleIds = null;
	private ConcurrentHashMap<Integer, List<VehicleAnswer>> map = null;

	private DataSetApi dataSetApi = null;
	private DealersApi dealertApi = null;
	private VehiclesApi vehcilesApi = null;

	public Callback() {

		if (this.dataSetApi == null) {
			this.dataSetApi = new DataSetApi();
		}

		if (this.vehcilesApi == null) {
			this.vehcilesApi = new VehiclesApi();
		}

		if (this.dealertApi == null) {
			this.dealertApi = new DealersApi();
		}

	}

	public static Callback getInstance() {
		return callbackinstance;
	}

	public String getDataSetID() {
		return dataSetID;
	}

	public void setDataSetID(String dataSetID) {
		this.dataSetID = dataSetID;
	}

	public List<DealerAnswer> getDealers() {
		return dealers;
	}

	public void setDealers(List<DealerAnswer> dealers) {
		this.dealers = dealers;
	}

	public List<Integer> getVehicleIDs() {
		return VehicleIds;
	}

	public void setVehicleIDs(List<Integer> vehicleIds) {
		this.VehicleIds = vehicleIds;
	}

	public ConcurrentHashMap<Integer, List<VehicleAnswer>> getMap() {
		return map;
	}

	public void setMap(ConcurrentHashMap<Integer, List<VehicleAnswer>> map) {
		this.map = map;
	}

	public DataSetApi getDataSetApi() {
		return dataSetApi;
	}

	public VehiclesApi getVehcilesApi() {
		return vehcilesApi;
	}

	public DealersApi getDealertApi() {
		return dealertApi;
	}


	public List<Integer> getVehicleIds() {
		return VehicleIds;
	}

	public void setVehicleIds(List<Integer> vehicleIds) {
		VehicleIds = vehicleIds;
	}

}