package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.ctsam.CONSTANTS;
import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.contracts.HealthFacilitiesContract;
import edu.aku.hassannaqvi.ctsam.contracts.TalukasContract;
import edu.aku.hassannaqvi.ctsam.contracts.VillagesContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionABinding;
import edu.aku.hassannaqvi.ctsam.ui.other.EndingActivity;
import edu.aku.hassannaqvi.ctsam.utils.EndSectionActivity;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

public class SectionAActivity extends AppCompatActivity implements EndSectionActivity {

    private static final String TAG = "";
    public static FormsContract fc;
    public List<String> talukaName, ucName, villageName, usersName, teamLeadName, healthFacilityCode;
    public List<String> talukaCode, ucCode, villageCode, usersCode, teamLeadCode, healthFacilityName;
    ActivitySectionABinding bi;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_a);
        bi.setCallback(this);
        setupSkips();
    }


    private void setupSkips() {
        db = new DatabaseHelper(this);
        populateSpinner(this);
        /*bi.s1q8.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == bi.s1q8b.getId()) {
                bi.fldGrpCVs1q8r.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVs1q8r);
                bi.fldGrpCVs1q8r.setVisibility(View.GONE);
            }
        });*/
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
                startActivity(new Intent(this, SectionBActivity.class));
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
        MainApp.fc.setFormType(CONSTANTS.CHILDRECRUITMENT);
        MainApp.fc.setUser(MainApp.userName);
        MainApp.fc.setUser2(MainApp.userName2);
        MainApp.fc.setDeviceID(MainApp.appInfo.getDeviceID());
        MainApp.fc.setDevicetagID(MainApp.appInfo.getTagName());
        MainApp.fc.setAppversion(MainApp.appInfo.getAppVersion());
        MainApp.setGPS(this); // Set GPS

        NumberFormat f = new DecimalFormat("00");
        String hf_name = bi.s1q1.getSelectedItem().toString();
        long hf_code = getHfCode(hf_name);
        MainApp.fc.setHfCode(f.format(hf_code));

        JSONObject json = new JSONObject();

        json.put("s1q1", bi.s1q1.getChildCount() == 0 ? "-1" : bi.s1q1.getSelectedItem().toString());

        json.put("s1q2", bi.s1q2.getChildCount() == 0 ? "-1" : bi.s1q2.getSelectedItem().toString());

        json.put("s1q3", bi.s1q3.getChildCount() == 0 ? "-1" : bi.s1q3.getSelectedItem().toString());

        json.put("s1q4", bi.s1q4.getText().toString().trim().isEmpty() ? "-1" : bi.s1q4.getText().toString());

        json.put("s1q8", bi.s1q8a.isChecked() ? "1" : bi.s1q8b.isChecked() ? "2" : "-1");

        json.put("s1q8r", bi.s1q8r.getText().toString().trim().isEmpty() ? "-1" : bi.s1q8r.getText().toString());

        MainApp.fc.setsA(String.valueOf(json));
    }


    private boolean formValidation(boolean flag) {
        if (flag) return Validator.emptyCheckingContainer(this, bi.fldGrpSectionA);
        else return Validator.emptyCheckingContainer(this, bi.fldGrpSectionA01);
    }


    public void BtnEnd() {
        if (!formValidation(false)) return;
        UtilKt.contextEndActivity(this, false);
    }

    public void populateSpinner(final Context context) {
        // Spinner Drop down elements
        talukaName = new ArrayList<>();
        talukaCode = new ArrayList<>();

        talukaName.add("....");
        talukaCode.add("....");

        Collection<TalukasContract> dc = db.getTalukas();
        for (TalukasContract d : dc) {
            talukaName.add(d.getTaluka());
            talukaCode.add(d.getTalukacode());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, talukaName);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        bi.s1q2.setAdapter(dataAdapter);

        bi.s1q2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                healthFacilityCode = new ArrayList<>();
                healthFacilityName = new ArrayList<>();


                healthFacilityCode.add("....");
                healthFacilityName.add("....");

                Collection<HealthFacilitiesContract> pc = db.getHealthFacilities(talukaCode.get(position));
                for (HealthFacilitiesContract p : pc) {
                    healthFacilityCode.add(p.getFacilityCode());
                    healthFacilityName.add(p.getFacilityName());
                }
                ArrayAdapter<String> psuAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, healthFacilityName);

                psuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                bi.s1q1.setAdapter(psuAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        bi.s1q1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                villageCode = new ArrayList<>();
                villageName = new ArrayList<>();


                villageCode.add("....");
                villageName.add("....");

                Collection<VillagesContract> pc = db.getVillages(healthFacilityCode.get(position));
                for (VillagesContract p : pc) {
                    villageCode.add(p.getVillagecode());
                    villageName.add(p.getVillagename());
                }
                ArrayAdapter<String> vilAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, villageName);

                vilAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                bi.s1q3.setAdapter(vilAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    long getHfCode(String hf_name) {

        DatabaseHelper db = new DatabaseHelper(this);
        Cursor res = db.getHfCode("healthFacilities", hf_name);
        res.moveToFirst();
        return Long.parseLong(res.getString(res.getColumnIndex(HealthFacilitiesContract.SingleHealthFacilities.COLUMN_FACILITY_CODE)));
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
