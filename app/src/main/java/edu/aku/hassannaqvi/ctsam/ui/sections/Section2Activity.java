package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection2Binding;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

public class Section2Activity extends AppCompatActivity {

    ActivitySection2Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_2);
        bi.setCallback(this);
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("fumeas01", bi.fumeas01.getText().toString().trim().isEmpty() ? "-1" : bi.fumeas01.getText().toString());
        json.put("fus2q1m1hei", bi.fus2q1m1hei.getText().toString().trim().isEmpty() ? "-1" : bi.fus2q1m1hei.getText().toString());
        json.put("fus2q2m1wei", bi.fus2q2m1wei.getText().toString().trim().isEmpty() ? "-1" : bi.fus2q2m1wei.getText().toString());
        json.put("fus2q3m1mua", bi.fus2q3m1mua.getText().toString().trim().isEmpty() ? "-1" : bi.fus2q3m1mua.getText().toString());

        json.put("fumeas02", bi.fumeas01.getText().toString().trim().isEmpty() ? "-1" : bi.fumeas01.getText().toString());
        json.put("fus2q1m2hei", bi.fus2q1m2hei.getText().toString().trim().isEmpty() ? "-1" : bi.fus2q1m2hei.getText().toString());
        json.put("fus2q2m2wei", bi.fus2q2m2wei.getText().toString().trim().isEmpty() ? "-1" : bi.fus2q2m2wei.getText().toString());
        json.put("fus2q3m2mua", bi.fus2q3m2mua.getText().toString().trim().isEmpty() ? "-1" : bi.fus2q3m2mua.getText().toString());

        MainApp.fc.setsB(String.valueOf(json));
    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, Section3Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SB, MainApp.fc.getsB());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSection2);
    }

    public void BtnEnd() {
        UtilKt.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }
}