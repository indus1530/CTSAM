package edu.aku.hassannaqvi.ctsam.contracts;


import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class HealthFacilitiesContract {

    private static final String TAG = "HealthFacilities_CONTRACT";
    String facilityCode;
    String facilityName;
    String talukaCode;

    public HealthFacilitiesContract() {
        // Default Constructor
    }

    public HealthFacilitiesContract sync(JSONObject jsonObject) throws JSONException {
        this.facilityCode = jsonObject.getString(SingleHealthFacilities.COLUMN_FACILITY_CODE);
        this.facilityName = jsonObject.getString(SingleHealthFacilities.COLUMN_FACILITY_NAME);
        this.talukaCode = jsonObject.getString(SingleHealthFacilities.COLUMN_TALUKA_CODE);
        return this;
    }

    public HealthFacilitiesContract hydrateHealthFacilities(Cursor cursor) {
        this.facilityCode = cursor.getString(cursor.getColumnIndex(SingleHealthFacilities.COLUMN_FACILITY_CODE));
        this.facilityName = cursor.getString(cursor.getColumnIndex(SingleHealthFacilities.COLUMN_FACILITY_NAME));
        this.talukaCode = cursor.getString(cursor.getColumnIndex(SingleHealthFacilities.COLUMN_TALUKA_CODE));
        return this;
    }

    public String getFacilityCode() {
        return facilityCode;
    }

    public void setFacilityCode(String facilityCode) {
        this.facilityCode = facilityCode;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getTalukaCode() {
        return talukaCode;
    }

    public void setTalukaCode(String talukaCode) {
        this.talukaCode = talukaCode;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(SingleHealthFacilities.COLUMN_FACILITY_CODE, this.facilityCode == null ? JSONObject.NULL : this.facilityCode);
        json.put(SingleHealthFacilities.COLUMN_FACILITY_NAME, this.facilityName == null ? JSONObject.NULL : this.facilityName);
        json.put(SingleHealthFacilities.COLUMN_TALUKA_CODE, this.talukaCode == null ? JSONObject.NULL : this.talukaCode);
        return json;
    }


    public static abstract class SingleHealthFacilities implements BaseColumns {

        public static final String TABLE_NAME = "healthFacilities";
        public static final String _ID = "id";
        public static final String COLUMN_TALUKA_CODE = "taluka_code";
        public static final String COLUMN_FACILITY_CODE = "hf_code";
        public static final String COLUMN_FACILITY_NAME = "hf_name";

        public static final String _URI = "healthFacilities.php";
    }
}