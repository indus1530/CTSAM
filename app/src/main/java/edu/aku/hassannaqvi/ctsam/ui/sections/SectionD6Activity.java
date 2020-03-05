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
import edu.aku.hassannaqvi.ctsam.contracts.FoodFreqContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionD6Binding;
import edu.aku.hassannaqvi.ctsam.utils.Util;

public class SectionD6Activity extends AppCompatActivity {

    ActivitySectionD6Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d6);
        bi.setCallback(this);


    }


    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, SectionD7Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void BtnEnd() {
        Util.openEndActivity(this);
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFoodFreqColumn(FoodFreqContract.SingleFoodFreq.COLUMN_SD6, MainApp.foodFreq.getsD6());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("d601",
                bi.d601a.isChecked() ? "1" :
                        bi.d601b.isChecked() ? "2" :
                                bi.d601c.isChecked() ? "3" :
                                        bi.d601d.isChecked() ? "4" :
                                                bi.d601e.isChecked() ? "5" :
                                                        bi.d601f.isChecked() ? "6" :
                                                                "0");
        json.put("d602",
                bi.d602a.isChecked() ? "1" :
                        bi.d602b.isChecked() ? "2" :
                                bi.d602c.isChecked() ? "3" :
                                        bi.d602d.isChecked() ? "4" :
                                                bi.d602e.isChecked() ? "5" :
                                                        bi.d602f.isChecked() ? "6" :
                                                                "0");
        json.put("d603",
                bi.d603a.isChecked() ? "1" :
                        bi.d603b.isChecked() ? "2" :
                                bi.d603c.isChecked() ? "3" :
                                        bi.d603d.isChecked() ? "4" :
                                                bi.d603e.isChecked() ? "5" :
                                                        bi.d603f.isChecked() ? "6" :
                                                                "0");
        json.put("d604",
                bi.d604a.isChecked() ? "1" :
                        bi.d604b.isChecked() ? "2" :
                                bi.d604c.isChecked() ? "3" :
                                        bi.d604d.isChecked() ? "4" :
                                                bi.d604e.isChecked() ? "5" :
                                                        bi.d604f.isChecked() ? "6" :
                                                                "0");
        json.put("d605",
                bi.d605a.isChecked() ? "1" :
                        bi.d605b.isChecked() ? "2" :
                                bi.d605c.isChecked() ? "3" :
                                        bi.d605d.isChecked() ? "4" :
                                                bi.d605e.isChecked() ? "5" :
                                                        bi.d605f.isChecked() ? "6" :
                                                                "0");
        json.put("d606",
                bi.d606a.isChecked() ? "1" :
                        bi.d606b.isChecked() ? "2" :
                                bi.d606c.isChecked() ? "3" :
                                        bi.d606d.isChecked() ? "4" :
                                                bi.d606e.isChecked() ? "5" :
                                                        "0");
        json.put("d607",
                bi.d607a.isChecked() ? "1" :
                        bi.d607b.isChecked() ? "2" :
                                bi.d607c.isChecked() ? "3" :
                                        bi.d607d.isChecked() ? "4" :
                                                bi.d607e.isChecked() ? "5" :
                                                        "0");

        MainApp.foodFreq.setsD6(String.valueOf(json));

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionD6);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }


}
