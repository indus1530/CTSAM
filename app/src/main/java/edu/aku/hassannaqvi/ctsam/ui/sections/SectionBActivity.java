package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionBBinding;
import edu.aku.hassannaqvi.ctsam.utils.Util;

public class SectionBActivity extends AppCompatActivity {

    ActivitySectionBBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_b);
        bi.setCallback(this);


    }


    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, SectionCActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {
        /*long updcount = db.addForm(MainApp.fc);
        MainApp.fc.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            MainApp.fc.set_UID(MainApp.fc.getDeviceID() + MainApp.fc.get_ID());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, MainApp.fc.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }*/

        return true;
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("muac1", bi.muac1.getText().toString());

        json.put("muac2", bi.muac2.getText().toString());

        json.put("s2q1", bi.s2q1a.isChecked() ? "1"
                : bi.s2q1b.isChecked() ? "2"
                : "0");

        json.put("s2q2", bi.s2q2a.isChecked() ? "1"
                : bi.s2q2b.isChecked() ? "2"
                : bi.s2q2c.isChecked() ? "3"
                : bi.s2q2d.isChecked() ? "4"
                : "0");

        json.put("s2q3", bi.s2q3a.isChecked() ? "1"
                : bi.s2q3b.isChecked() ? "2"
                : "0");

        json.put("s2q4", bi.s2q4a.isChecked() ? "1"
                : bi.s2q4b.isChecked() ? "2"
                : bi.s2q4c.isChecked() ? "3"
                : "0");

        json.put("s2q501", bi.s2q501a.isChecked() ? "1"
                : bi.s2q501b.isChecked() ? "2"
                : "0");

        json.put("s2q502", bi.s2q502a.isChecked() ? "1"
                : bi.s2q502b.isChecked() ? "2"
                : "0");

        json.put("s2q503", bi.s2q503a.isChecked() ? "1"
                : bi.s2q503b.isChecked() ? "2"
                : "0");

        json.put("s2q504", bi.s2q504a.isChecked() ? "1"
                : bi.s2q504b.isChecked() ? "2"
                : "0");

        json.put("s2q505", bi.s2q505a.isChecked() ? "1"
                : bi.s2q505b.isChecked() ? "2"
                : "0");

        json.put("s2q506", bi.s2q506a.isChecked() ? "1"
                : bi.s2q506b.isChecked() ? "2"
                : "0");

        json.put("s2q507", bi.s2q507a.isChecked() ? "1"
                : bi.s2q507b.isChecked() ? "2"
                : "0");

        json.put("s2q508", bi.s2q508a.isChecked() ? "1"
                : bi.s2q508b.isChecked() ? "2"
                : "0");

        json.put("s2q6", bi.s2q6a.isChecked() ? "1"
                : bi.s2q6b.isChecked() ? "2"
                : "0");

        json.put("s2q7", bi.s2q7a.isChecked() ? "1"
                : bi.s2q7b.isChecked() ? "2"
                : "0");

        json.put("s2q8", bi.s2q8a.isChecked() ? "1"
                : bi.s2q8b.isChecked() ? "2"
                : "0");

        json.put("s2q9", bi.s2q9a.isChecked() ? "1"
                : bi.s2q9b.isChecked() ? "2"
                : "0");


    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionB);
    }


    public void BtnEnd() {
        if (formValidation()) {
            Util.contextEndActivity(this);
        }
    }


}
