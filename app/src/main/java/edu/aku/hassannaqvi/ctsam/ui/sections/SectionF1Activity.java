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
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionF1Binding;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

public class SectionF1Activity extends AppCompatActivity {

    ActivitySectionF1Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_f1);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        bi.s6q3.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.s6q3b.getId()) {
                bi.fldGrpCVs6q4.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVs6q4);
            } else {
                bi.fldGrpCVs6q4.setVisibility(View.VISIBLE);
            }

        }));

        bi.s6q6.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.s6q6b.getId()) {
                bi.fldGrpCVs6q7.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVs6q7);
            } else {
                bi.fldGrpCVs6q7.setVisibility(View.VISIBLE);
            }

        }));

        bi.s6q9.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.s6q9j.getId()) {
                bi.fldGrpSectionf01.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpSectionf01);
            } else {
                bi.fldGrpSectionf01.setVisibility(View.VISIBLE);
            }

        }));
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
                startActivity(new Intent(this, SectionF2Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SF, MainApp.fc.getsF());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("s6q1", bi.s6q1a.isChecked() ? "1"
                : bi.s6q1b.isChecked() ? "2"
                : bi.s6q1c.isChecked() ? "3"
                : bi.s6q1d.isChecked() ? "4"
                : bi.s6q1e.isChecked() ? "5"
                : bi.s6q1f.isChecked() ? "6"
                : bi.s6q1g.isChecked() ? "7"
                : bi.s6q196.isChecked() ? "96"
                : "-1");

        json.put("s6q196x", bi.s6q196x.getText().toString().trim().isEmpty() ? "-1" : bi.s6q196x.getText().toString());

        json.put("s6q2", bi.s6q2a.isChecked() ? "1"
                : bi.s6q2b.isChecked() ? "2"
                : bi.s6q2c.isChecked() ? "3"
                : bi.s6q2d.isChecked() ? "4"
                : bi.s6q2e.isChecked() ? "5"
                : bi.s6q2f.isChecked() ? "6"
                : bi.s6q2g.isChecked() ? "7"
                : bi.s6q2h.isChecked() ? "8"
                : bi.s6q2i.isChecked() ? "9"
                : bi.s6q2j.isChecked() ? "10"
                : bi.s6q2k.isChecked() ? "11"
                : bi.s6q2l.isChecked() ? "12"
                : bi.s6q2m.isChecked() ? "13"
                : bi.s6q2n.isChecked() ? "14"
                : bi.s6q2o.isChecked() ? "15"
                : bi.s6q2p.isChecked() ? "16"
                : bi.s6q296.isChecked() ? "96"
                : "-1");

        json.put("s6q296x", bi.s6q296x.getText().toString().trim().isEmpty() ? "-1" : bi.s6q296x.getText().toString());

        json.put("s6q3", bi.s6q3a.isChecked() ? "1"
                : bi.s6q3b.isChecked() ? "2"
                : "-1");

        json.put("s6q4", bi.s6q4a.isChecked() ? "1"
                : bi.s6q4b.isChecked() ? "2"
                : bi.s6q4c.isChecked() ? "3"
                : bi.s6q4d.isChecked() ? "4"
                : bi.s6q4e.isChecked() ? "5"
                : bi.s6q4f.isChecked() ? "6"
                : bi.s6q496.isChecked() ? "96"
                : "-1");

        json.put("s6q496x", bi.s6q496x.getText().toString().trim().isEmpty() ? "-1" : bi.s6q496x.getText().toString());

        json.put("s6q5", bi.s6q5a.isChecked() ? "1"
                : bi.s6q5b.isChecked() ? "2"
                : bi.s6q5c.isChecked() ? "3"
                : bi.s6q5d.isChecked() ? "4"
                : bi.s6q5e.isChecked() ? "5"
                : bi.s6q5f.isChecked() ? "6"
                : bi.s6q5g.isChecked() ? "7"
                : bi.s6q5h.isChecked() ? "8"
                : bi.s6q5i.isChecked() ? "9"
                : bi.s6q596.isChecked() ? "96"
                : "-1");

        json.put("s6q596x", bi.s6q596x.getText().toString().trim().isEmpty() ? "-1" : bi.s6q596x.getText().toString());

        json.put("s6q6", bi.s6q6a.isChecked() ? "1" : bi.s6q6b.isChecked() ? "2" : "-1");

        json.put("s6q7", bi.s6q7.getText().toString().trim().isEmpty() ? "-1" : bi.s6q7.getText().toString());

        json.put("s6q8a", bi.s6q8a.isChecked() ? "1" : "-1");
        json.put("s6q8b", bi.s6q8b.isChecked() ? "2" : "-1");
        json.put("s6q8c", bi.s6q8c.isChecked() ? "3" : "-1");
        json.put("s6q8d", bi.s6q8d.isChecked() ? "4" : "-1");
        json.put("s6q8e", bi.s6q8e.isChecked() ? "5" : "-1");
        json.put("s6q8f", bi.s6q8f.isChecked() ? "6" : "-1");
        json.put("s6q8g", bi.s6q8g.isChecked() ? "7" : "-1");
        json.put("s6q8h", bi.s6q8h.isChecked() ? "8" : "-1");
        json.put("s6q8i", bi.s6q8i.isChecked() ? "9" : "-1");
        json.put("s6q8j", bi.s6q8j.isChecked() ? "10" : "-1");
        json.put("s6q8k", bi.s6q8k.isChecked() ? "11" : "-1");
        json.put("s6q8l", bi.s6q8l.isChecked() ? "12" : "-1");
        json.put("s6q8m", bi.s6q8m.isChecked() ? "13" : "-1");
        json.put("s6q8n", bi.s6q8n.isChecked() ? "14" : "-1");
        json.put("s6q8o", bi.s6q8o.isChecked() ? "15" : "-1");
        json.put("s6q8p", bi.s6q8p.isChecked() ? "16" : "-1");
        json.put("s6q8q", bi.s6q8q.isChecked() ? "17" : "-1");
        json.put("s6q8r", bi.s6q8r.isChecked() ? "18" : "-1");
        json.put("s6q8s", bi.s6q8s.isChecked() ? "19" : "-1");

        json.put("s6q9", bi.s6q9a.isChecked() ? "1"
                : bi.s6q9b.isChecked() ? "2"
                : bi.s6q9c.isChecked() ? "3"
                : bi.s6q9d.isChecked() ? "4"
                : bi.s6q9e.isChecked() ? "5"
                : bi.s6q9f.isChecked() ? "6"
                : bi.s6q9g.isChecked() ? "7"
                : bi.s6q9h.isChecked() ? "8"
                : bi.s6q9i.isChecked() ? "9"
                : bi.s6q9j.isChecked() ? "10"
                : bi.s6q996.isChecked() ? "96"
                : "-1");

        json.put("s6q996x", bi.s6q996x.getText().toString().trim().isEmpty() ? "-1" : bi.s6q996x.getText().toString());

        json.put("s6q10",
                bi.s6q1001.isChecked() ? "1" :
                        bi.s6q1002.isChecked() ? "2" :
                                bi.s6q1003.isChecked() ? "3" :
                                        bi.s6q1096.isChecked() ? "96" :
                                                "-1");

        json.put("s6q1096x", bi.s6q1096x.getText().toString().trim().isEmpty() ? "-1" : bi.s6q1096x.getText().toString());

        json.put("s6q11",
                bi.s6q1101.isChecked() ? "1" :
                        bi.s6q1102.isChecked() ? "2" :
                                "-1");

        MainApp.fc.setsF(String.valueOf(json));
    }


    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionF);
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
