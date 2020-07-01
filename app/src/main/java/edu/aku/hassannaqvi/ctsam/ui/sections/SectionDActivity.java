package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionDBinding;
import edu.aku.hassannaqvi.ctsam.utils.DateUtils;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

import static edu.aku.hassannaqvi.ctsam.core.MainApp.users;

public class SectionDActivity extends AppCompatActivity {

    ActivitySectionDBinding bi;
    boolean studyIDFlag = false;
    String hf_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d);
        bi.setCallback(this);

        bi.s4q7dob.setMinDate(DateUtils.getMonthsBack("dd/MM/yyyy", -59));
        bi.s4q7dob.setMaxDate(DateUtils.getMonthsBack("dd/MM/yyyy", -6));

        bi.user1.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, users));

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

        /*bi.s4q2.addTextChangedListener(new TextWatcher() {
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
                    bi.s4q2.setError("Pattern not matched: XX-XXX");
                    studyIDFlag = true;
                }
                studyIDFlag = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        bi.user1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    bi.user2.setEnabled(false);
                    bi.user2.setSelection(0);
                    return;
                }
                bi.user2.setEnabled(true);
                List<String> user2 = new ArrayList<>();
                for (String item : users) {
                    if (!item.equals(bi.user1.getSelectedItem().toString())) user2.add(item);
                }
                bi.user2.setAdapter(new ArrayAdapter<>(SectionDActivity.this, android.R.layout.simple_spinner_dropdown_item, user2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*Intent SectoionC = getIntent();
        hf_code = SectoionC.getExtras().getString("hf_code");
        Toast.makeText(this, hf_code + "", Toast.LENGTH_SHORT).show();
        String _uid = MainApp.fc.getDeviceID() + MainApp.fc.get_ID();
        Toast.makeText(this, _uid + "", Toast.LENGTH_SHORT).show();*/
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
                startActivity(new Intent(this, SectionEActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn2(FormsContract.FormsTable.COLUMN_SD, MainApp.fc.getsD(), FormsContract.FormsTable.COLUMN_STUDYID, MainApp.fc.getStudyId());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("s4q1", bi.s4q1a.isChecked() ? "1"
                : bi.s4q1b.isChecked() ? "2"
                : bi.s4q1c.isChecked() ? "3"
                : bi.s4q1d.isChecked() ? "4"
                : "-1");

        json.put("s4q2", MainApp.fc.getHfCode() + "-" + bi.s4q2.getText().toString());
        MainApp.fc.setStudyId(MainApp.fc.getHfCode() + "-" + bi.s4q2.getText().toString());

        json.put("s4q3", bi.s4q3.getText().toString().trim().isEmpty() ? "-1" : bi.s4q3.getText().toString());

        json.put("s4q4", bi.s4q4.getText().toString().trim().isEmpty() ? "-1" : bi.s4q4.getText().toString());

        json.put("s4q5", bi.s4q5.getText().toString().trim().isEmpty() ? "-1" : bi.s4q5.getText().toString());

        json.put("s4q6", bi.s4q6a.isChecked() ? "1" : bi.s4q6b.isChecked() ? "2" : "-1");

        json.put("s4q7", bi.s4q7a.isChecked() ? "1" : bi.s4q7b.isChecked() ? "2" : "-1");

        json.put("s4q7dob", bi.s4q7dob.getText().toString().trim().isEmpty() ? "-1" : bi.s4q7dob.getText().toString());

        json.put("s4q7days", bi.s4q7days.getText().toString().trim().isEmpty() ? "-1" : bi.s4q7days.getText().toString());

        json.put("s4q7mon", bi.s4q7mon.getText().toString().trim().isEmpty() ? "-1" : bi.s4q7mon.getText().toString());

        json.put("s4q8", bi.s4q8a.isChecked() ? "1" : bi.s4q8b.isChecked() ? "2" : "-1");

        json.put("s4q9", bi.s4q9.getText().toString().trim().isEmpty() ? "-1" : bi.s4q9.getText().toString());

        json.put("s4q10", bi.s410q410a.isChecked() ? "1" : bi.s410q410b.isChecked() ? "2" : "-1");

        json.put("s4q11", bi.s4q11.getText().toString().trim().isEmpty() ? "-1" : bi.s4q11.getText().toString());

        json.put("meas01", bi.meas01.getText().toString().trim().isEmpty() ? "-1" : bi.meas01.getText().toString());

        json.put("s4q14m1hei", bi.s4q14m1hei.getText().toString().trim().isEmpty() ? "-1" : bi.s4q14m1hei.getText().toString());

        json.put("s4q15m1wei", bi.s4q15m1wei.getText().toString().trim().isEmpty() ? "-1" : bi.s4q15m1wei.getText().toString());

        json.put("s4q15m1mua", bi.s4q15m1mua.getText().toString().trim().isEmpty() ? "-1" : bi.s4q15m1mua.getText().toString());

        json.put("meas02", bi.meas02.getText().toString().trim().isEmpty() ? "-1" : bi.meas02.getText().toString());

        json.put("s4q14m2hei", bi.s4q14m2hei.getText().toString().trim().isEmpty() ? "-1" : bi.s4q14m2hei.getText().toString());

        json.put("s4q15m2wei", bi.s4q15m2wei.getText().toString().trim().isEmpty() ? "-1" : bi.s4q15m2wei.getText().toString());

        json.put("s4q15m2mua", bi.s4q15m2mua.getText().toString().trim().isEmpty() ? "-1" : bi.s4q15m2mua.getText().toString());

        MainApp.fc.setsD(String.valueOf(json));
    }


    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.fldGrpSectionD)) {
            return false;
        }

        String CurStudy_id = MainApp.fc.getHfCode() + "-" + bi.s4q2.getText().toString();
        long checkDuplicate = getStudyID(CurStudy_id);

        if (checkDuplicate > 0) {
            Toast.makeText(this, "This Study ID already exists", Toast.LENGTH_SHORT).show();
            bi.s4q2.requestFocus();
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


    long getStudyID(String study_id) {

        DatabaseHelper db = new DatabaseHelper(this);
        Cursor res = db.getStudyID("forms", study_id);
        return res.getCount();
    }
}
