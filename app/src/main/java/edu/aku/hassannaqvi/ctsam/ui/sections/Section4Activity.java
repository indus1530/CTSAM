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
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection4Binding;
import edu.aku.hassannaqvi.ctsam.utils.Util;

public class Section4Activity extends AppCompatActivity {

    ActivitySection4Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_4);
        bi.setCallback(this);
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("fus4q1a", bi.fus4q1a.getText().toString().trim().isEmpty() ? "-1" : bi.fus4q1a.getText().toString());
        json.put("fus4q1b", bi.fus4q1b.getText().toString().trim().isEmpty() ? "-1" : bi.fus4q1b.getText().toString());

        json.put("fus4q1", bi.fus4q101.isChecked() ? "1"
                : bi.fus4q102.isChecked() ? "2"
                : bi.fus4q103.isChecked() ? "3"
                : "-1");

        json.put("fus4q2", bi.fus4q2a.isChecked() ? "1"
                : bi.fus4q2b.isChecked() ? "2"
                : bi.fus4q2c.isChecked() ? "3"
                : bi.fus4q2d.isChecked() ? "4"
                : "-1");

        json.put("fus4q3", bi.fus4q3a.isChecked() ? "1"
                : bi.fus4q3b.isChecked() ? "2"
                : "-1");

        json.put("fus4q4", bi.fus4q4a.isChecked() ? "1"
                : bi.fus4q4b.isChecked() ? "2"
                : bi.fus4q4c.isChecked() ? "3"
                : "-1");

        json.put("fus4q5a", bi.fus4q5a.getText().toString().trim().isEmpty() ? "-1" : bi.fus4q5a.getText().toString());
        json.put("fus4q5b", bi.fus4q5b.getText().toString().trim().isEmpty() ? "-1" : bi.fus4q5b.getText().toString());

        json.put("fus4q6", bi.fus4q6a.isChecked() ? "1"
                : bi.fus4q6b.isChecked() ? "2"
                : "-1");

        json.put("fus4q701", bi.fus4q701a.isChecked() ? "1"
                : bi.fus4q701b.isChecked() ? "2"
                : "-1");

        json.put("fus4q702", bi.fus4q702a.isChecked() ? "1"
                : bi.fus4q702b.isChecked() ? "2"
                : "-1");

        json.put("fus4q703", bi.fus4q703a.isChecked() ? "1"
                : bi.fus4q703b.isChecked() ? "2"
                : "-1");

        json.put("fus4q704", bi.fus4q704a.isChecked() ? "1"
                : bi.fus4q704b.isChecked() ? "2"
                : "-1");

        json.put("fus4q705", bi.fus4q705a.isChecked() ? "1"
                : bi.fus4q705b.isChecked() ? "2"
                : "-1");

        json.put("fus4q706", bi.fus4q706a.isChecked() ? "1"
                : bi.fus4q706b.isChecked() ? "2"
                : "-1");

        json.put("fus4q707", bi.fus4q707a.isChecked() ? "1"
                : bi.fus4q707b.isChecked() ? "2"
                : "-1");

        json.put("fus4q708", bi.fus4q708a.isChecked() ? "1"
                : bi.fus4q708b.isChecked() ? "2"
                : "-1");

        json.put("fus4q709", bi.fus4q709a.isChecked() ? "1"
                : bi.fus4q709b.isChecked() ? "2"
                : "-1");

        json.put("fus4q8", bi.fus4q8a.isChecked() ? "1"
                : bi.fus4q8b.isChecked() ? "2"
                : bi.fus4q8c.isChecked() ? "3"
                : "-1");

        json.put("fus4q8cx", bi.fus4q8cx.getText().toString().trim().isEmpty() ? "-1" : bi.fus4q8cx.getText().toString());
        json.put("fus4q9", bi.fus4q9.getText().toString().trim().isEmpty() ? "-1" : bi.fus4q9.getText().toString());
        json.put("fus4q10", bi.fus4q10.getText().toString().trim().isEmpty() ? "-1" : bi.fus4q10.getText().toString());
        json.put("fus4q11", bi.fus4q11.getText().toString().trim().isEmpty() ? "-1" : bi.fus4q11.getText().toString());

        MainApp.fc.setsD(String.valueOf(json));
    }


    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, Section5Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SD, MainApp.fc.getsD());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSection4);
    }

    public void BtnEnd() {
        if (formValidation()) {
            Util.contextEndActivity(this);
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }
}