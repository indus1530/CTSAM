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
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionGBinding;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

public class SectionGActivity extends AppCompatActivity {

    ActivitySectionGBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_g);
        bi.setCallback(this);

        setlistener();

    }

    private void setlistener() {

        bi.s7q1.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.s7q102.getId()) {
                bi.fldGrpCVs7q2.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVs7q2);
            } else {
                bi.fldGrpCVs7q2.setVisibility(View.VISIBLE);
            }

        }));

        //s7q505
        bi.s7q205.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.s7q2check, false);
                bi.s7q2check.setTag("-1");
            } else {
                Clear.clearAllFields(bi.s7q2check, true);
                bi.s7q2check.setTag("0");
            }
        });

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
                startActivity(new Intent(this, SectionHActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SG, MainApp.fc.getsG());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("s7q1", bi.s7q101.isChecked() ? "1"
                : bi.s7q102.isChecked() ? "2"
                : "-1");

        json.put("s7q201", bi.s7q201.isChecked() ? "1" : "-1");
        json.put("s7q202", bi.s7q202.isChecked() ? "2" : "-1");
        json.put("s7q203", bi.s7q203.isChecked() ? "3" : "-1");
        json.put("s7q204", bi.s7q204.isChecked() ? "4" : "-1");
        json.put("s7q205", bi.s7q205.isChecked() ? "99" : "-1");

        json.put("s7q301", bi.s7q301.isChecked() ? "1" : "-1");
        json.put("s7q302", bi.s7q302.isChecked() ? "2" : "-1");
        json.put("s7q303", bi.s7q303.isChecked() ? "3" : "-1");
        json.put("s7q304", bi.s7q304.isChecked() ? "4" : "-1");
        json.put("s7q305", bi.s7q305.isChecked() ? "5" : "-1");
        json.put("s7q306", bi.s7q306.isChecked() ? "6" : "-1");
        json.put("s7q307", bi.s7q307.isChecked() ? "7" : "-1");
        json.put("s7q308", bi.s7q308.isChecked() ? "8" : "-1");
        json.put("s7q309", bi.s7q309.isChecked() ? "9" : "-1");
        json.put("s7q396", bi.s7q396.isChecked() ? "96" : "-1");

        json.put("s7q396x", bi.s7q396x.getText().toString().trim().isEmpty() ? "-1" : bi.s7q396x.getText().toString());

        MainApp.fc.setsG(String.valueOf(json));
    }


    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionG);
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
