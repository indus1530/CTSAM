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
import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionJBinding;
import edu.aku.hassannaqvi.ctsam.ui.other.EndingActivity;
import edu.aku.hassannaqvi.ctsam.utils.Util;

public class SectionJActivity extends AppCompatActivity {

    ActivitySectionJBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_j);
        bi.setCallback(this);

        List<Integer> givenList = Arrays.asList(1, 2, 3, 4);
        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));

        switch (randomElement) {
            case 1:
                bi.s10q1a.setChecked(true);
                bi.s10q1a.setBackgroundColor(Color.parseColor("#FFD6D6"));
                bi.s10q1ax.setFocusable(true);
                bi.s10q1ax.requestFocus();
                bi.s10q1b.setEnabled(false);
                bi.s10q1c.setEnabled(false);
                bi.s10q1d.setEnabled(false);
                break;
            case 2:
                bi.s10q1b.setChecked(true);
                bi.s10q1b.setBackgroundColor(Color.parseColor("#D6D6FF"));
                bi.s10q1bx.setFocusable(true);
                bi.s10q1bx.requestFocus();
                bi.s10q1a.setEnabled(false);
                bi.s10q1c.setEnabled(false);
                bi.s10q1d.setEnabled(false);
                break;
            case 3:
                bi.s10q1c.setChecked(true);
                bi.s10q1c.setBackgroundColor(Color.parseColor("#D6FFD6"));
                bi.s10q1cx.setFocusable(true);
                bi.s10q1cx.requestFocus();
                bi.s10q1a.setEnabled(false);
                bi.s10q1b.setEnabled(false);
                bi.s10q1d.setEnabled(false);
                break;
            case 4:
                bi.s10q1d.setChecked(true);
                bi.s10q1d.setBackgroundColor(Color.parseColor("#FFDBAC"));
                bi.s10q1dx.setFocusable(true);
                bi.s10q1dx.requestFocus();
                bi.s10q1a.setEnabled(false);
                bi.s10q1b.setEnabled(false);
                bi.s10q1c.setEnabled(false);
                break;
        }

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

        json.put("s10q1", bi.s10q1a.isChecked() ? "1"
                : bi.s10q1b.isChecked() ? "2"
                : bi.s10q1c.isChecked() ? "3"
                : bi.s10q1d.isChecked() ? "4"
                : "0");

        json.put("s10q1ax", bi.s10q1ax.getText().toString());
        json.put("s10q1bx", bi.s10q1bx.getText().toString());
        json.put("s10q1cx", bi.s10q1cx.getText().toString());
        json.put("s10q1dx", bi.s10q1dx.getText().toString());

        json.put("s10q2", bi.s10q2.getText().toString());

        MainApp.fc.setsA3(String.valueOf(json));


    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionJ);
    }


    public void BtnEnd() {
        if (formValidation()) {
            Util.contextEndActivity(this);
        }
    }


}
