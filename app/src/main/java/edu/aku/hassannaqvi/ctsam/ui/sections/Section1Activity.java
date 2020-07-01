package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.ctsam.CONSTANTS;
import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection1Binding;
import edu.aku.hassannaqvi.ctsam.ui.other.EndingActivity;
import edu.aku.hassannaqvi.ctsam.utils.EndSectionActivity;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

public class Section1Activity extends AppCompatActivity implements EndSectionActivity {

    ActivitySection1Binding bi;

    private static final String TAG = "";
    public static FormsContract fc;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_1);
        bi.setCallback(this);

        //bi.fus1q8.setMinDate(DateUtils.getDaysBack("dd/MM/yyyy", 7));
        //bi.fus1q8.setMaxDate(DateUtils.getDaysBack("dd/MM/yyyy", 14));

        List<Integer> givenList = Arrays.asList(1, 2, 3, 4);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));

        switch (randomElement) {
            case 1:
                bi.fus1q1a.setChecked(true);
                bi.fus1q1a.setBackgroundColor(Color.parseColor("#FFD6D6"));
                bi.fus1q1b.setEnabled(false);
                bi.fus1q1c.setEnabled(false);
                bi.fus1q1d.setEnabled(false);
                break;
            case 2:
                bi.fus1q1b.setChecked(true);
                bi.fus1q1b.setBackgroundColor(Color.parseColor("#D6D6FF"));
                bi.fus1q1a.setEnabled(false);
                bi.fus1q1c.setEnabled(false);
                bi.fus1q1d.setEnabled(false);
                break;
            case 3:
                bi.fus1q1c.setChecked(true);
                bi.fus1q1c.setBackgroundColor(Color.parseColor("#D6FFD6"));
                bi.fus1q1a.setEnabled(false);
                bi.fus1q1b.setEnabled(false);
                bi.fus1q1d.setEnabled(false);
                break;
            case 4:
                bi.fus1q1d.setChecked(true);
                bi.fus1q1d.setBackgroundColor(Color.parseColor("#FFDBAC"));
                bi.fus1q1a.setEnabled(false);
                bi.fus1q1b.setEnabled(false);
                bi.fus1q1c.setEnabled(false);
                break;
        }
    }

    public void BtnContinue() {
        if (formValidation(true)) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, Section2Activity.class));
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
    }

    private void SaveDraft() throws JSONException {

        MainApp.fc = new FormsContract();
        MainApp.fc.setFormDate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        MainApp.fc.setFormType(CONSTANTS.CHILDFOLLOWUP);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setUser2(MainApp.userName2);
        MainApp.fc.setDeviceID(MainApp.appInfo.getDeviceID());
        MainApp.fc.setDevicetagID(MainApp.appInfo.getTagName());
        MainApp.fc.setAppversion(MainApp.appInfo.getAppVersion());
        MainApp.setGPS(this); // Set GPS

        JSONObject json = new JSONObject();

        json.put("fus1q1", bi.fus1q1a.isChecked() ? "1"
                : bi.fus1q1b.isChecked() ? "2"
                : bi.fus1q1c.isChecked() ? "3"
                : bi.fus1q1d.isChecked() ? "4"
                : "-1");

        json.put("fus1q2", bi.fus1q2a.isChecked() ? "1"
                : bi.fus1q2b.isChecked() ? "2"
                : "-1");

        json.put("fus1q3", bi.fus1q3.getText().toString().trim().isEmpty() ? "-1" : bi.fus1q3.getText().toString());
        json.put("fus1q4", bi.fus1q4.getText().toString().trim().isEmpty() ? "-1" : bi.fus1q4.getText().toString());
        json.put("fus1q5", bi.fus1q5.getText().toString().trim().isEmpty() ? "-1" : bi.fus1q5.getText().toString());
        json.put("fus1q6", bi.fus1q6.getText().toString().trim().isEmpty() ? "-1" : bi.fus1q6.getText().toString());

        json.put("fus1q7", bi.fus1q7a.isChecked() ? "1"
                : bi.fus1q7b.isChecked() ? "2"
                : bi.fus1q7c.isChecked() ? "3"
                : bi.fus1q7d.isChecked() ? "4"
                : bi.fus1q7e.isChecked() ? "5"
                : bi.fus1q7f.isChecked() ? "6"
                : bi.fus1q7g.isChecked() ? "96"
                : "-1");

        json.put("fus1q7gx", bi.fus1q7gx.getText().toString().trim().isEmpty() ? "-1" : bi.fus1q7gx.getText().toString());

        json.put("fus1q8", bi.fus1q8.getText().toString().trim().isEmpty() ? "-1" : bi.fus1q8.getText().toString());
        json.put("fus1q9", bi.fus1q9.getText().toString().trim().isEmpty() ? "-1" : bi.fus1q9.getText().toString());
        json.put("fus1q10", bi.fus1q10.getText().toString().trim().isEmpty() ? "-1" : bi.fus1q10.getText().toString());

        MainApp.fc.setsA(String.valueOf(json));
    }

    private boolean formValidation(boolean flag) {
        return Validator.emptyCheckingContainer(this, flag ? bi.fldGrpSection1 : bi.fldGrpSection101);
    }

    public void BtnEnd() {
        UtilKt.contextEndActivity(this);
    }

    @Override
    public void endSecActivity(boolean flag) {
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", flag));
        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }
    }
}