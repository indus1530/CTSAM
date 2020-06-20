package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionFBinding;
import edu.aku.hassannaqvi.ctsam.utils.Util;

public class SectionFActivity extends AppCompatActivity {

    ActivitySectionFBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_f);
        bi.setCallback(this);
        setupSkips();
    }

    private void setupSkips() {

        bi.s6q3.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.s6q3b.getId()) {
                bi.fldGrpCVs6q4.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVs6q4);
            } else {
                bi.fldGrpCVs6q4.setVisibility(View.VISIBLE);
            }

        }));

        bi.s6q6.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.s6q6b.getId()) {
                bi.fldGrpCVs6q7.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVs6q7);
            } else {
                bi.fldGrpCVs6q7.setVisibility(View.VISIBLE);
            }

        }));

        bi.s6q9.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.s6q9j.getId()) {
                bi.fldGrpSectionf01.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpSectionf01);
            } else {
                bi.fldGrpSectionf01.setVisibility(View.VISIBLE);
            }

        }));


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
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, SectionGActivity.class).putExtra("complete", true));
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

        json.put("s6q1", bi.s6q1a.isChecked() ? "1"
                : bi.s6q1b.isChecked() ? "2"
                : bi.s6q1c.isChecked() ? "3"
                : bi.s6q1d.isChecked() ? "4"
                : bi.s6q1e.isChecked() ? "5"
                : bi.s6q1f.isChecked() ? "6"
                : bi.s6q1g.isChecked() ? "7"
                : bi.s6q196.isChecked() ? "96"
                : "0");
        json.put("s6q196x", bi.s6q196x.getText().toString());

        json.put("s6q2", bi.s6q2a.isChecked() ? "1"
                : bi.s6q2b.isChecked() ? "2"
                : bi.s6q2c.isChecked() ? "3"
                : bi.s6q2d.isChecked() ? "4"
                : bi.s6q2e.isChecked() ? "5"
                : bi.s6q2f.isChecked() ? "6"
                : bi.s6q2g.isChecked() ? "7"
                : bi.s6q2h.isChecked() ? "8"
                : bi.s6q2i.isChecked() ? "9"
                : bi.s6q2j.isChecked() ? "10"
                : bi.s6q2k.isChecked() ? "11"
                : bi.s6q2l.isChecked() ? "12"
                : bi.s6q2m.isChecked() ? "13"
                : bi.s6q2n.isChecked() ? "14"
                : bi.s6q2o.isChecked() ? "15"
                : bi.s6q2p.isChecked() ? "16"
                : bi.s6q296.isChecked() ? "96"
                : "0");
        json.put("s6q296x", bi.s6q296x.getText().toString());

        json.put("s6q3", bi.s6q3a.isChecked() ? "1"
                : bi.s6q3b.isChecked() ? "2"
                : "0");

        json.put("s6q4", bi.s6q4a.isChecked() ? "1"
                : bi.s6q4b.isChecked() ? "2"
                : bi.s6q4c.isChecked() ? "3"
                : bi.s6q4d.isChecked() ? "4"
                : bi.s6q4e.isChecked() ? "5"
                : bi.s6q4f.isChecked() ? "6"
                : bi.s6q496.isChecked() ? "96"
                : "0");
        json.put("s6q496x", bi.s6q496x.getText().toString());

        json.put("s6q5", bi.s6q5a.isChecked() ? "1"
                : bi.s6q5b.isChecked() ? "2"
                : bi.s6q5c.isChecked() ? "3"
                : bi.s6q5d.isChecked() ? "4"
                : bi.s6q5e.isChecked() ? "5"
                : bi.s6q5f.isChecked() ? "6"
                : bi.s6q5g.isChecked() ? "7"
                : bi.s6q5h.isChecked() ? "8"
                : bi.s6q5i.isChecked() ? "9"
                : bi.s6q596.isChecked() ? "96"
                : "0");
        json.put("s6q596x", bi.s6q596x.getText().toString());

        json.put("s6q6", bi.s6q6a.isChecked() ? "1"
                : bi.s6q6b.isChecked() ? "2"
                : "0");

        json.put("s6q7", bi.s6q7.getText().toString());

        json.put("s6q8a", bi.s6q8a.isChecked() ? "1" : "0");
        json.put("s6q8b", bi.s6q8b.isChecked() ? "2" : "0");
        json.put("s6q8c", bi.s6q8c.isChecked() ? "3" : "0");
        json.put("s6q8d", bi.s6q8d.isChecked() ? "4" : "0");
        json.put("s6q8e", bi.s6q8e.isChecked() ? "5" : "0");
        json.put("s6q8f", bi.s6q8f.isChecked() ? "6" : "0");
        json.put("s6q8g", bi.s6q8g.isChecked() ? "7" : "0");
        json.put("s6q8h", bi.s6q8h.isChecked() ? "8" : "0");
        json.put("s6q8i", bi.s6q8i.isChecked() ? "9" : "0");
        json.put("s6q8j", bi.s6q8j.isChecked() ? "10" : "0");
        json.put("s6q8k", bi.s6q8k.isChecked() ? "11" : "0");
        json.put("s6q8l", bi.s6q8l.isChecked() ? "12" : "0");
        json.put("s6q8m", bi.s6q8m.isChecked() ? "13" : "0");
        json.put("s6q8n", bi.s6q8n.isChecked() ? "14" : "0");
        json.put("s6q8o", bi.s6q8o.isChecked() ? "15" : "0");
        json.put("s6q8p", bi.s6q8p.isChecked() ? "16" : "0");
        json.put("s6q8q", bi.s6q8q.isChecked() ? "17" : "0");
        json.put("s6q8r", bi.s6q8r.isChecked() ? "18" : "0");
        json.put("s6q8s", bi.s6q8s.isChecked() ? "19" : "0");

        json.put("s6q9", bi.s6q9a.isChecked() ? "1"
                : bi.s6q9b.isChecked() ? "2"
                : bi.s6q9c.isChecked() ? "3"
                : bi.s6q9d.isChecked() ? "4"
                : bi.s6q9e.isChecked() ? "5"
                : bi.s6q9f.isChecked() ? "6"
                : bi.s6q9g.isChecked() ? "7"
                : bi.s6q9h.isChecked() ? "8"
                : bi.s6q9i.isChecked() ? "9"
                : bi.s6q9j.isChecked() ? "10"
                : bi.s6q996.isChecked() ? "96"
                : "0");
        json.put("s6q996x", bi.s6q996x.getText().toString());

        json.put("s6q10",
                bi.s6q1001.isChecked() ? "1" :
                        bi.s6q1002.isChecked() ? "2" :
                                bi.s6q1003.isChecked() ? "3" :
                                        bi.s6q1096.isChecked() ? "96" :
                                                "0");
        json.put("s6q1096x", bi.s6q1096x.getText().toString());

        json.put("s6q11",
                bi.s6q1101.isChecked() ? "1" :
                        bi.s6q1102.isChecked() ? "2" :
                                "0");

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
                                                                                                                "0");
        json.put("s6q1296x", bi.s6q1296x.getText().toString());

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
                                                                                                                                "0");
        json.put("s6q1396x", bi.s6q1396x.getText().toString());

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
                                                                                                                "0");
        json.put("s6q1496x", bi.s6q1496x.getText().toString());

        json.put("s6q15", bi.s6q15.getText().toString());

        json.put("s6q16a",
                bi.s6q16a01.isChecked() ? "1" :
                        bi.s6q16a02.isChecked() ? "2" :
                                "0");

        json.put("s6q16b",
                bi.s6q16b01.isChecked() ? "1" :
                        bi.s6q16b02.isChecked() ? "2" :
                                "0");

        json.put("s6q16c",
                bi.s6q16c01.isChecked() ? "1" :
                        bi.s6q16c02.isChecked() ? "2" :
                                "0");

        json.put("s6q16d",
                bi.s6q16d01.isChecked() ? "1" :
                        bi.s6q16d02.isChecked() ? "2" :
                                "0");

        json.put("s6q16e",
                bi.s6q16e01.isChecked() ? "1" :
                        bi.s6q16e02.isChecked() ? "2" :
                                "0");

        json.put("s6q16f",
                bi.s6q16f01.isChecked() ? "1" :
                        bi.s6q16f02.isChecked() ? "2" :
                                "0");

        json.put("s6q16g",
                bi.s6q16g01.isChecked() ? "1" :
                        bi.s6q16g02.isChecked() ? "2" :
                                "0");

        json.put("s6q16h",
                bi.s6q16h01.isChecked() ? "1" :
                        bi.s6q16h02.isChecked() ? "1" :
                                "0");

        json.put("s6q16i",
                bi.s6q16i01.isChecked() ? "1" :
                        bi.s6q16i02.isChecked() ? "2" :
                                "0");

        json.put("s6q16j",
                bi.s6q16j01.isChecked() ? "1" :
                        bi.s6q16j02.isChecked() ? "2" :
                                "0");

        json.put("s6q17", bi.s6q17a.isChecked() ? "1" :
                bi.s6q17b.isChecked() ? "2" :
                                "0");


        json.put("s6q1898", bi.s6q1898.isChecked() ? "1" : "0");

        json.put("s6q18ax", bi.s6q18ax.getText().toString());
        json.put("s6q18bx", bi.s6q18bx.getText().toString());


        json.put("s6q19", bi.s6q1901.isChecked() ? "1"
                : bi.s6q1902.isChecked() ? "2"
                : "0");

        json.put("s6q20ax", bi.s6q20a.getText().toString());
        json.put("s6q20bx", bi.s6q20b.getText().toString());
        json.put("s6q20cx", bi.s6q20c.getText().toString());
        json.put("s6q20dx", bi.s6q20d.getText().toString());
        json.put("s6q20ex", bi.s6q20e.getText().toString());
        json.put("s6q20fx", bi.s6q20f.getText().toString());
        json.put("s6q20gx", bi.s6q20g.getText().toString());

        MainApp.fc.setsA3(String.valueOf(json));


    }


    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.fldGrpSectionF)) {
            return false;
        }

        if (!bi.s6q1898.isChecked() && Integer.parseInt(bi.s6q18ax.getText().toString()) == 0 && Integer.parseInt(bi.s6q18bx.getText().toString()) == 0) {

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
        if (formValidation()) {
            Util.contextEndActivity(this);
        }
    }

}
