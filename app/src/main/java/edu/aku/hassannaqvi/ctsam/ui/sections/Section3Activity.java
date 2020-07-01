package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection3Binding;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

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

        /*bi.s1q8.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.s1q8b.getId()) {
                bi.fldGrpCVs1q8r.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVs1q8r);
                bi.fldGrpCVs1q8r.setVisibility(View.GONE);
            }

        });*/


        bi.fus3q1m.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {

                Clear.clearAllFields(bi.fldGrpSec101);

                Clear.clearAllFields(bi.fldGrpCVfus3q2);
                bi.fldGrpCVfus3q2.setVisibility(View.GONE);

                Clear.clearAllFields(bi.fldGrpCVfus3q3);
                bi.fldGrpCVfus3q3.setVisibility(View.GONE);

                Clear.clearAllFields(bi.fldGrpCVfus3q4);
                bi.fldGrpCVfus3q4.setVisibility(View.GONE);

                Clear.clearAllFields(bi.fldGrpCVfus3q5);
                bi.fldGrpCVfus3q5.setVisibility(View.GONE);

                Clear.clearAllFields(bi.fldGrpCVfus3q6);
                bi.fldGrpCVfus3q6.setVisibility(View.GONE);

            } else {

                bi.fldGrpCVfus3q2.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus3q3.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus3q4.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus3q5.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus3q6.setVisibility(View.VISIBLE);
            }
        });

        bi.fus3q2.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.fus3q2b.getId()) {

                Clear.clearAllFields(bi.fldGrpCVfus3q3);
                bi.fldGrpCVfus3q3.setVisibility(View.GONE);

                Clear.clearAllFields(bi.fldGrpCVfus3q4);
                bi.fldGrpCVfus3q4.setVisibility(View.GONE);

                Clear.clearAllFields(bi.fldGrpCVfus3q5);
                bi.fldGrpCVfus3q5.setVisibility(View.GONE);

                Clear.clearAllFields(bi.fldGrpCVfus3q6);
                bi.fldGrpCVfus3q6.setVisibility(View.GONE);

            } else {

                bi.fldGrpCVfus3q3.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus3q4.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus3q5.setVisibility(View.VISIBLE);
                bi.fldGrpCVfus3q6.setVisibility(View.VISIBLE);
            }

        }));

        bi.fus3q5.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.fus3q5b.getId()) {

                Clear.clearAllFields(bi.fldGrpCVfus3q6);
                bi.fldGrpCVfus3q6.setVisibility(View.GONE);

            } else {

                bi.fldGrpCVfus3q6.setVisibility(View.VISIBLE);
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

        MainApp.fc.setsC(String.valueOf(json));
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
                startActivity(new Intent(this, Section4Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SC, MainApp.fc.getsC());
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
        UtilKt.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


    public void showTooltip(@NotNull View view) {
        if (view.getId() != View.NO_ID) {
            String package_name = getApplicationContext().getPackageName();
            // Question Number Textview ID must be prefixed with q_ e.g.: 'q_aa12a'
            String infoid = view.getResources().getResourceName(view.getId()).replace(package_name + ":id/q_", "");
            // Question info text must be suffixed with _info e.g.: aa12a_info
            int stringRes = this.getResources().getIdentifier(infoid + "_info", "string", getApplicationContext().getPackageName());
            // Fetch info text from strings.xml
            //String infoText = (String) getResources().getText(stringRes);
            // Check if string resource exists to avoid crash on missing info string
            if (stringRes != 0) {
                // Fetch info text from strings.xml
                String infoText = (String) getResources().getText(stringRes);
                new AlertDialog.Builder(this)
                        .setTitle("Info: " + infoid.toUpperCase())
                        .setMessage(infoText)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            } else {
                Toast.makeText(this, "No information available on this question.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No ID Associated with this question.", Toast.LENGTH_SHORT).show();
        }
    }

}