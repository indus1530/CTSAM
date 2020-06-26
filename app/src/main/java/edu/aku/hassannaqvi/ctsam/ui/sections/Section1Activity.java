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
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection1Binding;
import edu.aku.hassannaqvi.ctsam.utils.Util;

public class Section1Activity extends AppCompatActivity {

    ActivitySection1Binding bi;

    private static final String TAG = "";
    public static FormsContract fc;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_1);
        bi.setCallback(this);
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
                startActivity(new Intent(this, Section2Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addForm(MainApp.fc);
        MainApp.fc.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            MainApp.fc.set_UID(MainApp.fc.getDeviceID() + MainApp.fc.get_ID());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, MainApp.fc.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("fus1q1", bi.fus1q1a.isChecked() ? "1"
                : bi.fus1q1b.isChecked() ? "2"
                : bi.fus1q1c.isChecked() ? "3"
                : bi.fus1q1d.isChecked() ? "4"
                : "-1");

        json.put("fus1q2", bi.fus1q2a.isChecked() ? "1"
                : bi.fus1q2b.isChecked() ? "2"
                : "-1");

        json.put("fus1q3", bi.fus1q3.getText().toString());

        json.put("fus1q4", bi.fus1q4.getText().toString());

        json.put("fus1q5", bi.fus1q5.getText().toString());

        json.put("fus1q6", bi.fus1q6.getText().toString());

        json.put("fus1q7", bi.fus1q7a.isChecked() ? "1"
                : bi.fus1q7b.isChecked() ? "2"
                : bi.fus1q7c.isChecked() ? "3"
                : bi.fus1q7d.isChecked() ? "4"
                : bi.fus1q7e.isChecked() ? "5"
                : bi.fus1q7f.isChecked() ? "6"
                : bi.fus1q7g.isChecked() ? "96"
                : "-1");

        json.put("fus1q8", bi.fus1q8.getText().toString());

        json.put("fus1q9", bi.fus1q9.getText().toString());

        json.put("fus1q10", bi.fus1q10.getText().toString());


        MainApp.fc.setsA(String.valueOf(json));
    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSection1);
    }

    public void BtnEnd() {
        if (formValidation()) {
            Util.contextEndActivity(this);
        }
    }
}