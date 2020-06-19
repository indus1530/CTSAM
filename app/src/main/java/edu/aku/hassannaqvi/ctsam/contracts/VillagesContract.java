package edu.aku.hassannaqvi.ctsam.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 10/31/2016.
 */

public class VillagesContract {

    private String villagecode;
    private String villagename;
    private String hf_code;


    public VillagesContract() {
    }


    public String getHFCode() {
        return hf_code;
    }

    public String getVillagecode() {
        return villagecode;
    }

    public String getVillagename() {
        return villagename;
    }

    public VillagesContract sync(JSONObject jsonObject) throws JSONException {
        this.hf_code = jsonObject.getString(SingleVillage.COLUMN_HF_CODE);
        this.villagecode = jsonObject.getString(SingleVillage.COLUMN_VILLAGE_CODE);
        this.villagename = jsonObject.getString(SingleVillage.COLUMN_VILLAGE_NAME);

        return this;
    }

    public VillagesContract hydrate(Cursor cursor) {
        this.hf_code = cursor.getString(cursor.getColumnIndex(SingleVillage.COLUMN_HF_CODE));
        this.villagecode = cursor.getString(cursor.getColumnIndex(SingleVillage.COLUMN_VILLAGE_CODE));
        this.villagename = cursor.getString(cursor.getColumnIndex(SingleVillage.COLUMN_VILLAGE_NAME));

        return this;
    }


    public static abstract class SingleVillage implements BaseColumns {

        public static final String TABLE_NAME = "villages";
        public static final String _ID = "_id";
        public static final String COLUMN_VILLAGE_CODE = "village_code";
        public static final String COLUMN_VILLAGE_NAME = "village_name";
        public static final String COLUMN_HF_CODE = "hf_code";

        public static final String _URI = "villages.php";
    }

}