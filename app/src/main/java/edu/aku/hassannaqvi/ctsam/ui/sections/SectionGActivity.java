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
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionGBinding;
import edu.aku.hassannaqvi.ctsam.utils.Util;

public class SectionGActivity extends AppCompatActivity {

    ActivitySectionGBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_g);
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
                startActivity(new Intent(this, SectionHActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {
        /*long updcount = db.addForm(MainApp.fc);
        MainApp.fc.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            MainApp.fc.set_UID(MainApp.fc.getDeviceID() + MainApp.fc.get_ID());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, MainApp.fc.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }*/

        return true;
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("s7q1",
                bi.s7q101.isChecked() ? "1" :
                        bi.s7q102.isChecked() ? "2" :
                                bi.s7q103.isChecked() ? "3" :
                                        bi.s7q104.isChecked() ? "4" :
                                                bi.s7q105.isChecked() ? "5" :
                                                        "0");
        json.put("s7q2",
                bi.s7q201.isChecked() ? "1" :
                        bi.s7q202.isChecked() ? "2" :
                                "0");
        json.put("s7q301", bi.s7q301.isChecked() ? "1" : "0");
        json.put("s7q302", bi.s7q302.isChecked() ? "2" : "0");
        json.put("s7q303", bi.s7q303.isChecked() ? "3" : "0");
        json.put("s7q304", bi.s7q304.isChecked() ? "4" : "0");
        json.put("s7q305", bi.s7q305.isChecked() ? "99" : "0");
        json.put("s7q4",
                bi.s7q401.isChecked() ? "1" :
                        bi.s7q402.isChecked() ? "2" :
                                "0");
        json.put("s7q501", bi.s7q501.isChecked() ? "1" : "0");
        json.put("s7q502", bi.s7q502.isChecked() ? "2" : "0");
        json.put("s7q503", bi.s7q503.isChecked() ? "3" : "0");
        json.put("s7q504", bi.s7q504.isChecked() ? "4" : "0");
        json.put("s7q505", bi.s7q505.isChecked() ? "99" : "0");
        json.put("s7q601", bi.s7q601.isChecked() ? "1" : "0");
        json.put("s7q602", bi.s7q602.isChecked() ? "2" : "0");
        json.put("s7q603", bi.s7q603.isChecked() ? "3" : "0");
        json.put("s7q604", bi.s7q604.isChecked() ? "4" : "0");
        json.put("s7q605", bi.s7q605.isChecked() ? "5" : "0");
        json.put("s7q606", bi.s7q606.isChecked() ? "6" : "0");
        json.put("s7q607", bi.s7q607.isChecked() ? "7" : "0");
        json.put("s7q608", bi.s7q608.isChecked() ? "8" : "0");
        json.put("s7q609", bi.s7q609.isChecked() ? "9" : "0");
        json.put("s7q696", bi.s7q696.isChecked() ? "96" : "0");
        json.put("s7q696x", bi.s7q696x.getText().toString());


    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        if (formValidation()) {
            Util.contextEndActivity(this);
        }
    }


}