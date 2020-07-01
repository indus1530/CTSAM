package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection5Binding;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

public class Section5Activity extends AppCompatActivity {

    ActivitySection5Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_5);
        bi.setCallback(this);

        setupSkips();
    }

    private void setupSkips() {

        bi.fus5q6.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.fus5q6b.getId()) {
                Clear.clearAllFields(bi.fldGrpSection501);
            }
        }));


        bi.fus5q1.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.fus5q1b.getId() || checkedId == bi.fus5q1c.getId()) {

                Clear.clearAllFields(bi.fldGrpCVfus5q2);
                Clear.clearAllFields(bi.fldGrpCVfus5q3);
                Clear.clearAllFields(bi.fldGrpCVfus5q4);
                Clear.clearAllFields(bi.fldGrpCVfus5q5);
                Clear.clearAllFields(bi.fldGrpCVfus5q6);
                Clear.clearAllFields(bi.fldGrpCVfus5q7);
                Clear.clearAllFields(bi.fldGrpCVfus5q8);
                Clear.clearAllFields(bi.fldGrpCVfus5q9);
                Clear.clearAllFields(bi.fldGrpCVfus5q10);
                bi.fldGrpCVfus5q2.setVisibility(View.GONE);
                bi.fldGrpCVfus5q3.setVisibility(View.GONE);
                bi.fldGrpCVfus5q4.setVisibility(View.GONE);
                bi.fldGrpCVfus5q5.setVisibility(View.GONE);
                bi.fldGrpCVfus5q6.setVisibility(View.GONE);
                bi.fldGrpCVfus5q7.setVisibility(View.GONE);
                bi.fldGrpCVfus5q8.setVisibility(View.GONE);
                bi.fldGrpCVfus5q9.setVisibility(View.GONE);
                bi.fldGrpCVfus5q10.setVisibility(View.GONE);

            } else {

                bi.fldGrpCVfus5q2.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus5q3.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus5q4.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus5q5.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus5q6.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus5q7.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus5q8.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus5q9.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus5q10.setVisibility(View.VISIBLE);
            }
        });


        bi.fus5q6.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.fus5q6b.getId()) {

                Clear.clearAllFields(bi.fldGrpCVfus5q7);
                Clear.clearAllFields(bi.fldGrpCVfus5q8);
                bi.fldGrpCVfus5q7.setVisibility(View.GONE);
                bi.fldGrpCVfus5q8.setVisibility(View.GONE);

            } else {

                bi.fldGrpCVfus5q7.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus5q8.setVisibility(View.VISIBLE);
            }

        });

    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("fus5q1", bi.fus5q1a.isChecked() ? "1"
                : bi.fus5q1b.isChecked() ? "2"
                : bi.fus5q1c.isChecked() ? "3"
                : "-1");

        json.put("fus5q1x", bi.fus5q1x.getText().toString().trim().isEmpty() ? "-1" : bi.fus5q1x.getText().toString());

        json.put("fus5q2", bi.fus5q2a.isChecked() ? "1"
                : bi.fus5q2b.isChecked() ? "2"
                : bi.fus5q2c.isChecked() ? "3"
                : bi.fus5q2d.isChecked() ? "4"
                : bi.fus5q2e.isChecked() ? "5"
                : bi.fus5q2f.isChecked() ? "6"
                : bi.fus5q2g.isChecked() ? "96"
                : "-1");

        json.put("fus5q2ax", bi.fus5q2ax.getText().toString().trim().isEmpty() ? "-1" : bi.fus5q2ax.getText().toString());
        json.put("fus5q2bx", bi.fus5q2bx.getText().toString().trim().isEmpty() ? "-1" : bi.fus5q2bx.getText().toString());
        json.put("fus5q2cx", bi.fus5q2cx.getText().toString().trim().isEmpty() ? "-1" : bi.fus5q2cx.getText().toString());
        json.put("fus5q2dx", bi.fus5q2dx.getText().toString().trim().isEmpty() ? "-1" : bi.fus5q2dx.getText().toString());
        json.put("fus5q2ex", bi.fus5q2ex.getText().toString().trim().isEmpty() ? "-1" : bi.fus5q2ex.getText().toString());
        json.put("fus5q2fx", bi.fus5q2fx.getText().toString().trim().isEmpty() ? "-1" : bi.fus5q2fx.getText().toString());
        json.put("fus5q2gx", bi.fus5q2gx.getText().toString().trim().isEmpty() ? "-1" : bi.fus5q2gx.getText().toString());

        json.put("fus5q3", bi.fus5q3a.isChecked() ? "0"
                : bi.fus5q3b.isChecked() ? "88"
                : "-1");

        json.put("fus5q3ax", bi.fus5q3ax.getText().toString().trim().isEmpty() ? "-1" : bi.fus5q3ax.getText().toString());

        json.put("fus5q4", bi.fus5q4a.isChecked() ? "1"
                : bi.fus5q4b.isChecked() ? "2"
                : bi.fus5q4c.isChecked() ? "3"
                : bi.fus5q4d.isChecked() ? "4"
                : bi.fus5q4e.isChecked() ? "5"
                : bi.fus5q4f.isChecked() ? "96"
                : "-1");

        json.put("fus5q4fx", bi.fus5q4fx.getText().toString().trim().isEmpty() ? "-1" : bi.fus5q4fx.getText().toString());

        json.put("fus5q5ax", bi.fus5q5ax.getText().toString().trim().isEmpty() ? "-1" : bi.fus5q5ax.getText().toString());
        json.put("fus5q5bx", bi.fus5q5bx.getText().toString().trim().isEmpty() ? "-1" : bi.fus5q5bx.getText().toString());

        json.put("fus5q6", bi.fus5q6a.isChecked() ? "1"
                : bi.fus5q6b.isChecked() ? "2"
                : "-1");

        json.put("fus5q7", bi.fus5q7a.isChecked() ? "1"
                : bi.fus5q7b.isChecked() ? "2"
                : bi.fus5q7c.isChecked() ? "3"
                : bi.fus5q7d.isChecked() ? "4"
                : bi.fus5q7e.isChecked() ? "96"
                : "-1");

        json.put("fus5q8", bi.fus5q8a.isChecked() ? "1"
                : bi.fus5q8b.isChecked() ? "2"
                : "-1");

        json.put("fus5q9", bi.fus5q9a.isChecked() ? ""
                : bi.fus5q9b.isChecked() ? ""
                : bi.fus5q9c.isChecked() ? "96"
                : "-1");

        json.put("fus5q9ax", bi.fus5q9ax.getText().toString());
        json.put("fus5q9bx", bi.fus5q9bx.getText().toString());
        json.put("fus5q9cx", bi.fus5q9cx.getText().toString());
        json.put("fus5q10", bi.fus5q10a.isChecked() ? "1"
                : bi.fus5q10b.isChecked() ? "2"
                : bi.fus5q10c.isChecked() ? "3"
                : bi.fus5q10d.isChecked() ? "4"
                : bi.fus5q10e.isChecked() ? "96"
                : "-1");

        json.put("fus5q10ex", bi.fus5q10ex.getText().toString().trim().isEmpty() ? "-1" : bi.fus5q10ex.getText().toString());

        MainApp.fc.setsE(String.valueOf(json));
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
                startActivity(new Intent(this, Section6Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SE, MainApp.fc.getsE());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSection5);
    }

    public void BtnEnd() {
        UtilKt.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }
}