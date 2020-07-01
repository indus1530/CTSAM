package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionIBinding;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

public class SectionIActivity extends AppCompatActivity {

    ActivitySectionIBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_i);
        bi.setCallback(this);


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
                startActivity(new Intent(this, SectionJActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SI, MainApp.fc.getsI());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("s9q1",
                bi.s9q101.isChecked() ? "1" :
                        bi.s9q102.isChecked() ? "2" :
                                bi.s9q103.isChecked() ? "3" :
                                        bi.s9q104.isChecked() ? "4" :
                                                bi.s9q105.isChecked() ? "5" :
                                                        bi.s9q106.isChecked() ? "6" :
                                                                bi.s9q107.isChecked() ? "7" :
                                                                        bi.s9q108.isChecked() ? "8" :
                                                                                bi.s9q109.isChecked() ? "9" :
                                                                                        bi.s9q110.isChecked() ? "10" :
                                                                                                bi.s9q111.isChecked() ? "11" :
                                                                                                        bi.s9q112.isChecked() ? "12" :
                                                                                                                bi.s9q113.isChecked() ? "13" :
                                                                                                                        bi.s9q196.isChecked() ? "96" :
                                                                                                                                "-1");

        json.put("s9q196x", bi.s9q196x.getText().toString().trim().isEmpty() ? "-1" : bi.s9q196x.getText().toString());

        json.put("s9q2",
                bi.s9q201.isChecked() ? "1" :
                        bi.s9q202.isChecked() ? "2" :
                                bi.s9q203.isChecked() ? "3" :
                                        bi.s9q204.isChecked() ? "4" :
                                                bi.s9q205.isChecked() ? "5" :
                                                        bi.s9q206.isChecked() ? "6" :
                                                                bi.s9q207.isChecked() ? "7" :
                                                                        bi.s9q208.isChecked() ? "8" :
                                                                                bi.s9q209.isChecked() ? "9" :
                                                                                        bi.s9q210.isChecked() ? "10" :
                                                                                                bi.s9q211.isChecked() ? "11" :
                                                                                                        bi.s9q212.isChecked() ? "12" :
                                                                                                                bi.s9q213.isChecked() ? "13" :
                                                                                                                        bi.s9q296.isChecked() ? "96" :
                                                                                                                                "-1");

        json.put("s9q296x", bi.s9q296x.getText().toString().trim().isEmpty() ? "-1" : bi.s9q296x.getText().toString());

        json.put("s9q3",
                bi.s9q301.isChecked() ? "1" :
                        bi.s9q302.isChecked() ? "2" :
                                bi.s9q303.isChecked() ? "3" :
                                        bi.s9q304.isChecked() ? "4" :
                                                bi.s9q305.isChecked() ? "5" :
                                                        bi.s9q306.isChecked() ? "6" :
                                                                bi.s9q307.isChecked() ? "7" :
                                                                        bi.s9q308.isChecked() ? "8" :
                                                                                bi.s9q309.isChecked() ? "9" :
                                                                                        bi.s9q310.isChecked() ? "10" :
                                                                                                bi.s9q311.isChecked() ? "11" :
                                                                                                        bi.s9q312.isChecked() ? "12" :
                                                                                                                bi.s9q313.isChecked() ? "13" :
                                                                                                                        bi.s9q396.isChecked() ? "96" :
                                                                                                                                "-1");

        json.put("s9q396x", bi.s9q396x.getText().toString().trim().isEmpty() ? "-1" : bi.s9q396x.getText().toString());

        MainApp.fc.setsI(String.valueOf(json));
    }


    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionI);
    }


    public void BtnEnd() {
        UtilKt.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }
}
