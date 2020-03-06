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
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionDBinding;
import edu.aku.hassannaqvi.ctsam.utils.Util;

public class SectionDActivity extends AppCompatActivity {

    ActivitySectionDBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d);
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
                startActivity(new Intent(this, SectionEActivity.class));
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
        json.put("s4q1", bi.s4q1a.isChecked() ? "1"
                : bi.s4q1b.isChecked() ? "2"
                : bi.s4q1c.isChecked() ? "3"
                : bi.s4q1d.isChecked() ? "4"
                : "0");

        json.put("s4q2", bi.s4q2.getText().toString());
        json.put("s4q3", bi.s4q3.getText().toString());
        json.put("s4q4", bi.s4q4.getText().toString());
        json.put("s4q5", bi.s4q5.getText().toString());
        json.put("s4q6", bi.s4q6a.isChecked() ? "1" :
                bi.s4q6b.isChecked() ? "2" : "0");

        json.put("s4q7", bi.s4q7a.isChecked() ? "1"
                : bi.s4q7b.isChecked() ? "2"
                : "0");

        json.put("s4q7dob", bi.s4q7dob.getText().toString());
        json.put("s4q7days", bi.s4q7days.getText().toString());
        json.put("s4q7mon", bi.s4q7mon.getText().toString());
        json.put("s4q8", bi.s4q8a.isChecked() ? "1"
                : bi.s4q8b.isChecked() ? "2"
                : "0");
        json.put("s4q9", bi.s4q9.getText().toString());

        json.put("s4q10", bi.s410q410a.isChecked() ? "1"
                : bi.s410q410b.isChecked() ? "2"
                : "0");

        json.put("s4q11", bi.s4q11.getText().toString());
        json.put("meas01", bi.meas01.getText().toString());
        json.put("s4q14m1hei", bi.s4q14m1hei.getText().toString());
        json.put("s4q15m1wei", bi.s4q15m1wei.getText().toString());
        json.put("s4q15m1mua", bi.s4q15m1mua.getText().toString());
        json.put("meas02", bi.meas02.getText().toString());
        json.put("s4q14m2hei", bi.s4q14m2hei.getText().toString());
        json.put("s4q15m2wei", bi.s4q15m2wei.getText().toString());
        json.put("s4q15m2mua", bi.s4q15m2mua.getText().toString());


    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionD);
    }


    public void BtnEnd() {
        if (formValidation()) {
            Util.contextEndActivity(this);
        }
    }


}