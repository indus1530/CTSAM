package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionF2Binding;
import edu.aku.hassannaqvi.ctsam.utils.JSONUtils;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

public class SectionF2Activity extends AppCompatActivity {

    ActivitySectionF2Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_f2);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {


        bi.s6q17.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.s6q17b.getId()) {
                bi.fldGrpCVs6q18.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVs6q18);
            } else {
                bi.fldGrpCVs6q18.setVisibility(View.VISIBLE);
            }

        }));

        //////////// Checkbox
        bi.s6q1898.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bi.s6q1898.isChecked()) {
                    bi.s6q18ax.setVisibility(View.GONE);
                    Clear.clearAllFields(bi.s6q18ax);
                    bi.s6q18bx.setVisibility(View.GONE);
                    Clear.clearAllFields(bi.s6q18bx);
                } else {
                    bi.s6q18ax.setVisibility(View.VISIBLE);
                    bi.s6q18bx.setVisibility(View.VISIBLE);
                }
            }
        });


        bi.s6q19.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.s6q1902.getId()) {

                bi.fldGrpCVs6q20.setVisibility(View.GONE);
                bi.fldGrpCVs6q20a.setVisibility(View.GONE);
                bi.fldGrpCVs6q20b.setVisibility(View.GONE);
                bi.fldGrpCVs6q20c.setVisibility(View.GONE);
                bi.fldGrpCVs6q20d.setVisibility(View.GONE);
                bi.fldGrpCVs6q20e.setVisibility(View.GONE);
                bi.fldGrpCVs6q20f.setVisibility(View.GONE);
                bi.fldGrpCVs6q20g.setVisibility(View.GONE);

                Clear.clearAllFields(bi.fldGrpCVs6q20a);
                Clear.clearAllFields(bi.fldGrpCVs6q20b);
                Clear.clearAllFields(bi.fldGrpCVs6q20c);
                Clear.clearAllFields(bi.fldGrpCVs6q20d);
                Clear.clearAllFields(bi.fldGrpCVs6q20e);
                Clear.clearAllFields(bi.fldGrpCVs6q20f);
                Clear.clearAllFields(bi.fldGrpCVs6q20g);

            } else {

                bi.fldGrpCVs6q20.setVisibility(View.VISIBLE);
                bi.fldGrpCVs6q20a.setVisibility(View.VISIBLE);
                bi.fldGrpCVs6q20b.setVisibility(View.VISIBLE);
                bi.fldGrpCVs6q20c.setVisibility(View.VISIBLE);
                bi.fldGrpCVs6q20d.setVisibility(View.VISIBLE);
                bi.fldGrpCVs6q20e.setVisibility(View.VISIBLE);
                bi.fldGrpCVs6q20f.setVisibility(View.VISIBLE);
                bi.fldGrpCVs6q20g.setVisibility(View.VISIBLE);
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
                startActivity(new Intent(this, SectionGActivity.class));
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

        json.put("s6q12",
                bi.s6q1201.isChecked() ? "1" :
                        bi.s6q1202.isChecked() ? "2" :
                                bi.s6q1203.isChecked() ? "3" :
                                        bi.s6q1204.isChecked() ? "4" :
                                                bi.s6q1205.isChecked() ? "5" :
                                                        bi.s6q1206.isChecked() ? "6" :
                                                                bi.s6q1207.isChecked() ? "7" :
                                                                        bi.s6q1208.isChecked() ? "8" :
                                                                                bi.s6q1209.isChecked() ? "9" :
                                                                                        bi.s6q1210.isChecked() ? "10" :
                                                                                                bi.s6q1211.isChecked() ? "11" :
                                                                                                        bi.s6q1296.isChecked() ? "96" :
                                                                                                                "-1");

        json.put("s6q1296x", bi.s6q1296x.getText().toString().trim().isEmpty() ? "-1" : bi.s6q1296x.getText().toString());

        json.put("s6q13",
                bi.s6q1301.isChecked() ? "1" :
                        bi.s6q1302.isChecked() ? "2" :
                                bi.s6q1303.isChecked() ? "3" :
                                        bi.s6q1304.isChecked() ? "4" :
                                                bi.s6q1305.isChecked() ? "5" :
                                                        bi.s6q1306.isChecked() ? "6" :
                                                                bi.s6q1307.isChecked() ? "7" :
                                                                        bi.s6q1308.isChecked() ? "8" :
                                                                                bi.s6q1309.isChecked() ? "9" :
                                                                                        bi.s6q1310.isChecked() ? "10" :
                                                                                                bi.s6q1311.isChecked() ? "11" :
                                                                                                        bi.s6q1312.isChecked() ? "12" :
                                                                                                                bi.s6q1313.isChecked() ? "13" :
                                                                                                                        bi.s6q1396.isChecked() ? "96" :
                                                                                                                                "-1");

        json.put("s6q1396x", bi.s6q1396x.getText().toString().trim().isEmpty() ? "-1" : bi.s6q1396x.getText().toString());

        json.put("s6q14",
                bi.s6q1401.isChecked() ? "1" :
                        bi.s6q1402.isChecked() ? "2" :
                                bi.s6q1403.isChecked() ? "3" :
                                        bi.s6q1404.isChecked() ? "4" :
                                                bi.s6q1405.isChecked() ? "5" :
                                                        bi.s6q1406.isChecked() ? "6" :
                                                                bi.s6q1407.isChecked() ? "7" :
                                                                        bi.s6q1408.isChecked() ? "8" :
                                                                                bi.s6q1409.isChecked() ? "9" :
                                                                                        bi.s6q1410.isChecked() ? "10" :
                                                                                                bi.s6q1411.isChecked() ? "11" :
                                                                                                        bi.s6q1496.isChecked() ? "96" :
                                                                                                                "-1");

        json.put("s6q1496x", bi.s6q1496x.getText().toString().trim().isEmpty() ? "-1" : bi.s6q1496x.getText().toString());

        json.put("s6q15", bi.s6q15.getText().toString().trim().isEmpty() ? "-1" : bi.s6q15.getText().toString());

        json.put("s6q16a",
                bi.s6q16a01.isChecked() ? "1" :
                        bi.s6q16a02.isChecked() ? "2" :
                                "-1");

        json.put("s6q16b",
                bi.s6q16b01.isChecked() ? "1" :
                        bi.s6q16b02.isChecked() ? "2" :
                                "-1");

        json.put("s6q16c",
                bi.s6q16c01.isChecked() ? "1" :
                        bi.s6q16c02.isChecked() ? "2" :
                                "-1");

        json.put("s6q16d",
                bi.s6q16d01.isChecked() ? "1" :
                        bi.s6q16d02.isChecked() ? "2" :
                                "-1");

        json.put("s6q16e",
                bi.s6q16e01.isChecked() ? "1" :
                        bi.s6q16e02.isChecked() ? "2" :
                                "-1");

        json.put("s6q16f",
                bi.s6q16f01.isChecked() ? "1" :
                        bi.s6q16f02.isChecked() ? "2" :
                                "-1");

        json.put("s6q16g",
                bi.s6q16g01.isChecked() ? "1" :
                        bi.s6q16g02.isChecked() ? "2" :
                                "-1");

        json.put("s6q16h",
                bi.s6q16h01.isChecked() ? "1" :
                        bi.s6q16h02.isChecked() ? "1" :
                                "-1");

        json.put("s6q16i",
                bi.s6q16i01.isChecked() ? "1" :
                        bi.s6q16i02.isChecked() ? "2" :
                                "-1");

        json.put("s6q16j",
                bi.s6q16j01.isChecked() ? "1" :
                        bi.s6q16j02.isChecked() ? "2" :
                                "-1");

        json.put("s6q17", bi.s6q17a.isChecked() ? "1" :
                bi.s6q17b.isChecked() ? "2" :
                        "-1");

        json.put("s6q1898", bi.s6q1898.isChecked() ? "1" : "-1");

        json.put("s6q18ax", bi.s6q18ax.getText().toString().trim().isEmpty() ? "-1" : bi.s6q18ax.getText().toString());

        json.put("s6q18bx", bi.s6q18bx.getText().toString().trim().isEmpty() ? "-1" : bi.s6q18bx.getText().toString());


        json.put("s6q19", bi.s6q1901.isChecked() ? "1"
                : bi.s6q1902.isChecked() ? "2"
                : "-1");

        json.put("s6q20ax", bi.s6q20a.getText().toString().trim().isEmpty() ? "-1" : bi.s6q20a.getText().toString());
        json.put("s6q20bx", bi.s6q20b.getText().toString().trim().isEmpty() ? "-1" : bi.s6q20b.getText().toString());
        json.put("s6q20cx", bi.s6q20c.getText().toString().trim().isEmpty() ? "-1" : bi.s6q20c.getText().toString());
        json.put("s6q20dx", bi.s6q20d.getText().toString().trim().isEmpty() ? "-1" : bi.s6q20d.getText().toString());
        json.put("s6q20ex", bi.s6q20e.getText().toString().trim().isEmpty() ? "-1" : bi.s6q20e.getText().toString());
        json.put("s6q20fx", bi.s6q20f.getText().toString().trim().isEmpty() ? "-1" : bi.s6q20f.getText().toString());
        json.put("s6q20gx", bi.s6q20g.getText().toString().trim().isEmpty() ? "-1" : bi.s6q20g.getText().toString());

        try {
            JSONObject json_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.fc.getsF()), json);
            MainApp.fc.setsF(String.valueOf(json_merge));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.fldGrpSectionF2)) {
            return false;
        }

        if (bi.fldGrpCVs6q18.getVisibility() == View.VISIBLE
                && !bi.s6q1898.isChecked()
                && Integer.parseInt(bi.s6q18ax.getText().toString()) == 0
                && Integer.parseInt(bi.s6q18bx.getText().toString()) == 0) {

            Toast.makeText(this, "S6Q18: At least one value must be greater than zero", Toast.LENGTH_LONG).show();
            return false;
        }

        if (bi.s6q1901.isChecked() && Integer.parseInt(bi.s6q20a.getText().toString()) == 0 && Integer.parseInt(bi.s6q20b.getText().toString()) == 0
                && Integer.parseInt(bi.s6q20c.getText().toString()) == 0 && Integer.parseInt(bi.s6q20d.getText().toString()) == 0
                && Integer.parseInt(bi.s6q20e.getText().toString()) == 0 && Integer.parseInt(bi.s6q20f.getText().toString()) == 0) {

            Toast.makeText(this, "S6Q20: At least one value must be greater than zero", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
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
