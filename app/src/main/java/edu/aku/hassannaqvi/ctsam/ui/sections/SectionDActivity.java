package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionDBinding;
import edu.aku.hassannaqvi.ctsam.ui.other.EndingActivity;
import edu.aku.hassannaqvi.ctsam.utils.DateUtils;
import edu.aku.hassannaqvi.ctsam.utils.Util;

public class SectionDActivity extends AppCompatActivity {

    ActivitySectionDBinding bi;
    boolean studyIDFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d);
        bi.setCallback(this);

        bi.s4q7dob.setMinDate(DateUtils.getMonthsBack("dd/MM/yyyy", -36));

        List<Integer> givenList = Arrays.asList(1, 2, 3, 4);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));

        switch (randomElement) {
            case 1:
                bi.s4q1a.setChecked(true);
                bi.s4q1a.setBackgroundColor(Color.parseColor("#FFD6D6"));
                bi.s4q1b.setEnabled(false);
                bi.s4q1c.setEnabled(false);
                bi.s4q1d.setEnabled(false);
                break;
            case 2:
                bi.s4q1b.setChecked(true);
                bi.s4q1b.setBackgroundColor(Color.parseColor("#D6D6FF"));
                bi.s4q1a.setEnabled(false);
                bi.s4q1c.setEnabled(false);
                bi.s4q1d.setEnabled(false);
                break;
            case 3:
                bi.s4q1c.setChecked(true);
                bi.s4q1c.setBackgroundColor(Color.parseColor("#D6FFD6"));
                bi.s4q1a.setEnabled(false);
                bi.s4q1b.setEnabled(false);
                bi.s4q1d.setEnabled(false);
                break;
            case 4:
                bi.s4q1d.setChecked(true);
                bi.s4q1d.setBackgroundColor(Color.parseColor("#FFDBAC"));
                bi.s4q1a.setEnabled(false);
                bi.s4q1b.setEnabled(false);
                bi.s4q1c.setEnabled(false);
                break;
        }

        bi.s4q2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bi.s4q2.setError(null);
                if (!bi.s4q2.isTextEqualToPattern()) return;
                String[] splitStudy = bi.s4q2.getText().toString().split("-");
                int first_part = Integer.parseInt(splitStudy[0]);
                if (first_part < 1 || first_part > 10) {
                    bi.s4q2.setError("Pattern not match: XX-XXX");
                    studyIDFlag = true;
                }
                studyIDFlag = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
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


       /* DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SA3, MainApp.fc.getsA3());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }*/
    }


    private void SaveDraft() throws JSONException {

        MainApp.fc = new FormsContract();
        MainApp.fc.setFormDate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));

        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setDeviceID(MainApp.appInfo.getDeviceID());
        MainApp.fc.setDevicetagID(MainApp.appInfo.getTagName());
        MainApp.fc.setAppversion(MainApp.appInfo.getAppVersion());
        //MainApp.fc.setClusterCode(bi.a101.getText().toString());
        //MainApp.fc.setHhno(bi.a112.getText().toString());
        // MainApp.fc.setLuid(bl.getLUID());
        MainApp.setGPS(this); // Set GPS

        JSONObject json = new JSONObject();

        json.put("s4q1", bi.s4q1a.isChecked() ? "1"
                : bi.s4q1b.isChecked() ? "2"
                : bi.s4q1c.isChecked() ? "3"
                : bi.s4q1d.isChecked() ? "4"
                : "0");

        json.put("s4q2", bi.s4q2.getText().toString());
        json.put("s4q3", bi.s4q3.getText().toString());
        json.put("s4q4", bi.s4q4.getText().toString());
        json.put("s4q5", bi.s4q5.getText().toString());
        json.put("s4q6", bi.s4q6a.isChecked() ? "1" :
                bi.s4q6b.isChecked() ? "2" : "0");

        json.put("s4q7", bi.s4q7a.isChecked() ? "1"
                : bi.s4q7b.isChecked() ? "2"
                : "0");

        json.put("s4q7dob", bi.s4q7dob.getText().toString());
        json.put("s4q7days", bi.s4q7days.getText().toString());
        json.put("s4q7mon", bi.s4q7mon.getText().toString());
        json.put("s4q8", bi.s4q8a.isChecked() ? "1"
                : bi.s4q8b.isChecked() ? "2"
                : "0");
        json.put("s4q9", bi.s4q9.getText().toString());

        json.put("s4q10", bi.s410q410a.isChecked() ? "1"
                : bi.s410q410b.isChecked() ? "2"
                : "0");

        json.put("s4q11", bi.s4q11.getText().toString());
        json.put("meas01", bi.meas01.getText().toString());
        json.put("s4q14m1hei", bi.s4q14m1hei.getText().toString());
        json.put("s4q15m1wei", bi.s4q15m1wei.getText().toString());
        json.put("s4q15m1mua", bi.s4q15m1mua.getText().toString());
        json.put("meas02", bi.meas02.getText().toString());
        json.put("s4q14m2hei", bi.s4q14m2hei.getText().toString());
        json.put("s4q15m2wei", bi.s4q15m2wei.getText().toString());
        json.put("s4q15m2mua", bi.s4q15m2mua.getText().toString());

        MainApp.fc.setsA3(String.valueOf(json));


    }


    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) return false;
        if (studyIDFlag) {
            Toast.makeText(this, "StudyID is not validating!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public void BtnEnd() {
        if (formValidation()) {
            Util.contextEndActivity(this);
        }
    }


}
