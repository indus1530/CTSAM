package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection3Binding;
import edu.aku.hassannaqvi.ctsam.utils.Util;

public class Section3Activity extends AppCompatActivity {

    ActivitySection3Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_3);
        bi.setCallback(this);

        setupSkips();
    }

    private void setupSkips() {

        bi.fus3q1m.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.fldGrpSec101);
            }
        });

        bi.fus3q2.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.fus3q2b.getId()) {
                Clear.clearAllFields(bi.fldGrpSec102);
            }
        }));

        bi.fus3q5.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.fus3q5b.getId()) {
                Clear.clearAllFields(bi.fldGrpCVfus3q6);
            }
        }));

    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("fus3q1a", bi.fus3q1a.isChecked() ? "1" : "-1");
        json.put("fus3q1b", bi.fus3q1b.isChecked() ? "2" : "-1");
        json.put("fus3q1c", bi.fus3q1c.isChecked() ? "3" : "-1");
        json.put("fus3q1d", bi.fus3q1d.isChecked() ? "4" : "-1");
        json.put("fus3q1e", bi.fus3q1e.isChecked() ? "5" : "-1");
        json.put("fus3q1f", bi.fus3q1f.isChecked() ? "6" : "-1");
        json.put("fus3q1g", bi.fus3q1g.isChecked() ? "7" : "-1");
        json.put("fus3q1h", bi.fus3q1h.isChecked() ? "8" : "-1");
        json.put("fus3q1i", bi.fus3q1i.isChecked() ? "9" : "-1");
        json.put("fus3q1j", bi.fus3q1j.isChecked() ? "10" : "-1");
        json.put("fus3q1k", bi.fus3q1k.isChecked() ? "11" : "-1");
        json.put("fus3q1l", bi.fus3q1l.isChecked() ? "96" : "-1");
        json.put("fus3q1m", bi.fus3q1m.isChecked() ? "888" : "-1");
        json.put("fus3q1x", bi.fus3q1x.getText().toString().trim().isEmpty() ? "-1" : bi.fus3q1x.getText().toString());

        json.put("fus3q2", bi.fus3q2a.isChecked() ? "1"
                : bi.fus3q2b.isChecked() ? "2"
                : "-1");

        json.put("fus3q3a", bi.fus3q3a.isChecked() ? "1" : "-1");
        json.put("fus3q3b", bi.fus3q3b.isChecked() ? "2" : "-1");
        json.put("fus3q3c", bi.fus3q3c.isChecked() ? "3" : "-1");
        json.put("fus3q3d", bi.fus3q3d.isChecked() ? "4" : "-1");
        json.put("fus3q3e", bi.fus3q3e.isChecked() ? "5" : "-1");
        json.put("fus3q3f", bi.fus3q3f.isChecked() ? "6" : "-1");
        json.put("fus3q3g", bi.fus3q3g.isChecked() ? "7" : "-1");
        json.put("fus3q3h", bi.fus3q3h.isChecked() ? "8" : "-1");
        json.put("fus3q3i", bi.fus3q3i.isChecked() ? "9" : "-1");
        json.put("fus3q3j", bi.fus3q3j.isChecked() ? "96" : "-1");

        json.put("fus3q4", bi.fus3q4a.isChecked() ? "1"
                : bi.fus3q4b.isChecked() ? "2"
                : bi.fus3q4c.isChecked() ? "3"
                : bi.fus3q4d.isChecked() ? "96"
                : "-1");

        json.put("fus3q4dx", bi.fus3q4dx.getText().toString().trim().isEmpty() ? "-1" : bi.fus3q4dx.getText().toString());

        json.put("fus3q5", bi.fus3q5a.isChecked() ? "1"
                : bi.fus3q5b.isChecked() ? "2"
                : "-1");

        json.put("fus3q6", bi.fus3q6.getText().toString().trim().isEmpty() ? "-1" : bi.fus3q6.getText().toString());

        MainApp.fc.setsA(String.valueOf(json));
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
                startActivity(new Intent(this, Section4Activity.class));
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

        return Validator.emptyCheckingContainer(this, bi.fldGrpSection3);
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