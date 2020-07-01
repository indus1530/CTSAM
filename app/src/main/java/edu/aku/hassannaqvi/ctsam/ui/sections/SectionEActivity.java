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
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionEBinding;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

public class SectionEActivity extends AppCompatActivity {

    ActivitySectionEBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_e);
        bi.setCallback(this);


        //s5q197
        /*bi.s5q197.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.s5q1all);
                Clear.clearAllFields(bi.fldGrpCVs5q2);
                Clear.clearAllFields(bi.fldGrpCVs5q3);
                Clear.clearAllFields(bi.fldGrpCVs5q4);
                Clear.clearAllFields(bi.fldGrpCVs5q5);
                bi.s5q1all.setVisibility(View.GONE);
                bi.fldGrpCVs5q2.setVisibility(View.GONE);
                bi.fldGrpCVs5q3.setVisibility(View.GONE);
                bi.fldGrpCVs5q4.setVisibility(View.GONE);
                bi.fldGrpCVs5q5.setVisibility(View.GONE);
            } else {
                Clear.clearAllFields(bi.s5q1all);
                bi.s5q1all.setVisibility(View.VISIBLE);
                bi.fldGrpCVs5q2.setVisibility(View.VISIBLE);
                bi.fldGrpCVs5q3.setVisibility(View.VISIBLE);
                bi.fldGrpCVs5q4.setVisibility(View.VISIBLE);
                bi.fldGrpCVs5q5.setVisibility(View.VISIBLE);
            }
        });*/

        bi.s5q2.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.s5q2a.getId()) {
                bi.fldGrpCVs5q3.setVisibility(View.VISIBLE);
                bi.fldGrpCVs5q4.setVisibility(View.VISIBLE);
                bi.fldGrpCVs5q5.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVs5q3);
                Clear.clearAllFields(bi.fldGrpCVs5q4);
                Clear.clearAllFields(bi.fldGrpCVs5q5);
                bi.fldGrpCVs5q3.setVisibility(View.GONE);
                bi.fldGrpCVs5q4.setVisibility(View.GONE);
                bi.fldGrpCVs5q5.setVisibility(View.GONE);
            }

        }));

        bi.s5q4.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.s5q4a.getId()) {
                bi.fldGrpCVs5q5.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVs5q5);
                bi.fldGrpCVs5q5.setVisibility(View.GONE);
            }

        }));


        bi.s5q196.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.s5q196b.getId() && bi.s5q101b.isChecked() && bi.s5q102b.isChecked() && bi.s5q103b.isChecked() && bi.s5q104b.isChecked()
                    && bi.s5q105b.isChecked() && bi.s5q106b.isChecked() && bi.s5q107b.isChecked() && bi.s5q108b.isChecked()) {

                Clear.clearAllFields(bi.fldGrpCVs5q2);
                Clear.clearAllFields(bi.fldGrpCVs5q3);
                Clear.clearAllFields(bi.fldGrpCVs5q4);
                Clear.clearAllFields(bi.fldGrpCVs5q5);

                bi.fldGrpCVs5q2.setVisibility(View.GONE);
                bi.fldGrpCVs5q3.setVisibility(View.GONE);
                bi.fldGrpCVs5q4.setVisibility(View.GONE);
                bi.fldGrpCVs5q5.setVisibility(View.GONE);

            } else {

                bi.fldGrpCVs5q2.setVisibility(View.VISIBLE);
                bi.fldGrpCVs5q3.setVisibility(View.VISIBLE);
                bi.fldGrpCVs5q4.setVisibility(View.VISIBLE);
                bi.fldGrpCVs5q5.setVisibility(View.VISIBLE);
            }

        }));


    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, SectionF1Activity.class));
        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
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


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("s5q101",
                bi.s5q101a.isChecked() ? "1" :
                        bi.s5q101b.isChecked() ? "2" :
                                "-1");
        json.put("s5q102",
                bi.s5q102a.isChecked() ? "1" :
                        bi.s5q102b.isChecked() ? "2" :
                                "-1");
        json.put("s5q103",
                bi.s5q103a.isChecked() ? "1" :
                        bi.s5q103b.isChecked() ? "2" :
                                "-1");
        json.put("s5q104",
                bi.s5q104a.isChecked() ? "1" :
                        bi.s5q104b.isChecked() ? "2" :
                                "-1");
        json.put("s5q105",
                bi.s5q105a.isChecked() ? "1" :
                        bi.s5q105b.isChecked() ? "2" :
                                "-1");
        json.put("s5q106",
                bi.s5q106a.isChecked() ? "1" :
                        bi.s5q106b.isChecked() ? "2" :
                                "-1");
        json.put("s5q107",
                bi.s5q107a.isChecked() ? "1" :
                        bi.s5q107b.isChecked() ? "2" :
                                "-1");
        json.put("s5q108",
                bi.s5q108a.isChecked() ? "1" :
                        bi.s5q108b.isChecked() ? "2" :
                                "-1");
        json.put("s5q196",
                bi.s5q196a.isChecked() ? "1" :
                        bi.s5q196b.isChecked() ? "2" :
                                "-1");

        json.put("s5q196x", bi.s5q196x.getText().toString().trim().isEmpty() ? "-1" : bi.s5q196x.getText().toString());

        json.put("s5q2",
                bi.s5q2a.isChecked() ? "1" :
                        bi.s5q2b.isChecked() ? "2" :
                                "-1");

        json.put("s5q301", bi.s5q301.isChecked() ? "1" : "-1");
        json.put("s5q302", bi.s5q302.isChecked() ? "2" : "-1");
        json.put("s5q303", bi.s5q303.isChecked() ? "3" : "-1");
        json.put("s5q304", bi.s5q304.isChecked() ? "4" : "-1");
        json.put("s5q305", bi.s5q305.isChecked() ? "5" : "-1");
        json.put("s5q306", bi.s5q306.isChecked() ? "6" : "-1");
        json.put("s5q307", bi.s5q307.isChecked() ? "7" : "-1");
        json.put("s5q308", bi.s5q308.isChecked() ? "8" : "-1");
        json.put("s5q3096", bi.s5q3096.isChecked() ? "96" : "-1");

        json.put("s5q3096x", bi.s5q3096x.getText().toString().trim().isEmpty() ? "-1" : bi.s5q3096x.getText().toString());

        json.put("s5q4",
                bi.s5q4a.isChecked() ? "1" :
                        bi.s5q4b.isChecked() ? "2" :
                                "-1");

        json.put("s5q5", bi.s5q5.getText().toString().trim().isEmpty() ? "-1" : bi.s5q5.getText().toString());

        MainApp.fc.setsE(String.valueOf(json));
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionE);
    }


    public void BtnEnd() {
        UtilKt.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }
}
