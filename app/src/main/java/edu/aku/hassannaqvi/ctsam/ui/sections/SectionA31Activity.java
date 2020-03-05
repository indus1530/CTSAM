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

import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionA31Binding;
import edu.aku.hassannaqvi.ctsam.utils.Util;

public class SectionA31Activity extends AppCompatActivity {

    ActivitySectionA31Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_a31);
        bi.setCallback(this);
        setupSkips();

        bi.txtHeadLbl.setText(new StringBuilder(MainApp.indexKishMWRA.getName().toUpperCase()).append("\n")
                .append("Serial:")
                .append(MainApp.indexKishMWRA.getSerialno()));

    }


    private void setupSkips() {

        bi.a308.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.a308c.getId()) {
                bi.fldGrpCVa309.setVisibility(View.VISIBLE);
                bi.fldGrpCVa310.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVa309);
                Clear.clearAllFields(bi.fldGrpCVa310);
                bi.fldGrpCVa309.setVisibility(View.GONE);
                bi.fldGrpCVa310.setVisibility(View.GONE);
            }
        }));

        bi.a313.setOnCheckedChangeListener(((radioGroup, i) -> {

            if (i == bi.a313a.getId()) {
                bi.fldGrpCVa314.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVa314);
                bi.fldGrpCVa314.setVisibility(View.GONE);
            }

        }));


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
                startActivity(new Intent(this, SectionA32Activity.class));
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
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SA3, MainApp.fc.getsA3());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("a301",
                bi.a301a.isChecked() ? "1" :
                        bi.a301b.isChecked() ? "2" :
                                bi.a301c.isChecked() ? "3" :
                                        bi.a301d.isChecked() ? "4" :
                                                bi.a301e.isChecked() ? "5" :
                                                        bi.a301f.isChecked() ? "6" :
                                                                bi.a301g.isChecked() ? "7" :
                                                                        bi.a301h.isChecked() ? "8" :
                                                                                bi.a301i.isChecked() ? "9" :
                                                                                        bi.a301j.isChecked() ? "10" :
                                                                                                bi.a30196.isChecked() ? "96" :
                                                                                                        "0");
        json.put("a30196x", bi.a30196x.getText().toString());

        json.put("a302",
                bi.a302a.isChecked() ? "1" :
                        bi.a302b.isChecked() ? "2" :
                                bi.a302c.isChecked() ? "3" :
                                        bi.a302d.isChecked() ? "4" :
                                                bi.a30296.isChecked() ? "96" :
                                                        "0");
        json.put("a30296x", bi.a30296x.getText().toString());

        json.put("a303",
                bi.a303a.isChecked() ? "1" :
                        bi.a303b.isChecked() ? "2" :
                                bi.a303c.isChecked() ? "3" :
                                        bi.a303d.isChecked() ? "4" :
                                                bi.a303e.isChecked() ? "5" :
                                                        bi.a303f.isChecked() ? "6" :
                                                                bi.a303g.isChecked() ? "7" :
                                                                        bi.a303h.isChecked() ? "8" :
                                                                                bi.a303i.isChecked() ? "9" :
                                                                                        bi.a303j.isChecked() ? "10" :
                                                                                                bi.a303k.isChecked() ? "11" :
                                                                                                        bi.a30396.isChecked() ? "96" :
                                                                                                                "0");
        json.put("a30396x", bi.a30396x.getText().toString());

        json.put("a304",
                bi.a304a.isChecked() ? "1" :
                        bi.a304b.isChecked() ? "2" :
                                bi.a304c.isChecked() ? "3" :
                                        bi.a304d.isChecked() ? "4" :
                                                bi.a304e.isChecked() ? "5" :
                                                        bi.a304f.isChecked() ? "6" :
                                                                bi.a304g.isChecked() ? "7" :
                                                                        bi.a304h.isChecked() ? "8" :
                                                                                bi.a304i.isChecked() ? "9" :
                                                                                        bi.a304j.isChecked() ? "10" :
                                                                                                bi.a304k.isChecked() ? "11" :
                                                                                                        bi.a304l.isChecked() ? "12" :
                                                                                                                bi.a304m.isChecked() ? "13" :
                                                                                                                        bi.a304n.isChecked() ? "14" :
                                                                                                                                bi.a304o.isChecked() ? "15" :
                                                                                                                                        bi.a30496.isChecked() ? "96" :
                                                                                                                                                "0");
        json.put("a30496x", bi.a30496x.getText().toString());

        json.put("a305",
                bi.a305a.isChecked() ? "1" :
                        bi.a305b.isChecked() ? "2" :
                                bi.a305c.isChecked() ? "3" :
                                        bi.a305d.isChecked() ? "4" :
                                                bi.a305e.isChecked() ? "5" :
                                                        bi.a305f.isChecked() ? "6" :
                                                                bi.a305g.isChecked() ? "7" :
                                                                        bi.a305h.isChecked() ? "8" :
                                                                                bi.a305i.isChecked() ? "9" :
                                                                                        bi.a305j.isChecked() ? "10" :
                                                                                                bi.a305k.isChecked() ? "11" :
                                                                                                        bi.a305l.isChecked() ? "12" :
                                                                                                                bi.a305m.isChecked() ? "13" :
                                                                                                                        bi.a305n.isChecked() ? "14" :
                                                                                                                                bi.a305o.isChecked() ? "15" :
                                                                                                                                        bi.a305p.isChecked() ? "16" :
                                                                                                                                                bi.a30596.isChecked() ? "96" :
                                                                                                                                                        "0");
        json.put("a30596x", bi.a30596x.getText().toString());

        json.put("a306aa",
                bi.a306aaa.isChecked() ? "1" :
                        bi.a306aab.isChecked() ? "2" :
                                "0");

        json.put("a306",
                bi.a306a.isChecked() ? "1" :
                        bi.a306b.isChecked() ? "2" :
                                bi.a306c.isChecked() ? "3" :
                                        bi.a306d.isChecked() ? "4" :
                                                bi.a306e.isChecked() ? "5" :
                                                        bi.a306f.isChecked() ? "6" :
                                                                bi.a306g.isChecked() ? "7" :
                                                                        bi.a306h.isChecked() ? "8" :
                                                                                bi.a306i.isChecked() ? "9" :
                                                                                        bi.a306j.isChecked() ? "10" :
                                                                                                bi.a306k.isChecked() ? "11" :
                                                                                                        bi.a306l.isChecked() ? "12" :
                                                                                                                bi.a306m.isChecked() ? "13" :
                                                                                                                        bi.a306n.isChecked() ? "14" :
                                                                                                                                bi.a30696.isChecked() ? "96" :
                                                                                                                                        "0");
        json.put("a30696x", bi.a30696x.getText().toString());


        json.put("a307aa",
                bi.a307aaa.isChecked() ? "1" :
                        bi.a307aab.isChecked() ? "2" :
                                "0");

        json.put("a307",
                bi.a307a.isChecked() ? "1" :
                        bi.a307b.isChecked() ? "2" :
                                bi.a307c.isChecked() ? "3" :
                                        bi.a307d.isChecked() ? "4" :
                                                bi.a307e.isChecked() ? "5" :
                                                        bi.a307f.isChecked() ? "6" :
                                                                bi.a307g.isChecked() ? "7" :
                                                                        bi.a307h.isChecked() ? "8" :
                                                                                bi.a307i.isChecked() ? "9" :
                                                                                        bi.a307j.isChecked() ? "10" :
                                                                                                bi.a307k.isChecked() ? "11" :
                                                                                                        bi.a307l.isChecked() ? "12" :
                                                                                                                bi.a307m.isChecked() ? "13" :
                                                                                                                        bi.a307n.isChecked() ? "14" :
                                                                                                                                bi.a30796.isChecked() ? "96" :
                                                                                                                                        "0");
        json.put("a30796x", bi.a30796x.getText().toString());

        json.put("a308",
                bi.a308a.isChecked() ? "1" :
                        bi.a308b.isChecked() ? "2" :
                                bi.a308c.isChecked() ? "3" :
                                        "0");

        json.put("a309", bi.a30998.isChecked() ? "98" : bi.a309.getText().toString());

        json.put("a310",
                bi.a310a.isChecked() ? "1" :
                        bi.a310b.isChecked() ? "2" :
                                bi.a310c.isChecked() ? "3" :
                                        bi.a310d.isChecked() ? "4" :
                                                bi.a310e.isChecked() ? "5" :
                                                        "0");

        json.put("a311",
                bi.a311a.isChecked() ? "1" :
                        bi.a311b.isChecked() ? "2" :
                                "0");

        json.put("a312",
                bi.a312a.isChecked() ? "1" :
                        bi.a312b.isChecked() ? "2" :
                                bi.a31298.isChecked() ? "98" :
                                        "0");

        json.put("a313",
                bi.a313a.isChecked() ? "1" :
                        bi.a313b.isChecked() ? "2" :
                                bi.a31398.isChecked() ? "98" :
                                        "0");

        json.put("a314",
                bi.a314a.isChecked() ? "1" :
                        bi.a314b.isChecked() ? "2" :
                                bi.a314c.isChecked() ? "3" :
                                        bi.a314d.isChecked() ? "4" :
                                                bi.a314e.isChecked() ? "5" :
                                                        bi.a314f.isChecked() ? "6" :
                                                                bi.a31496.isChecked() ? "96" :
                                                                        "0");
        json.put("a31496x", bi.a31496x.getText().toString());

        json.put("a315", bi.a315.getText().toString());

        json.put("a316",
                bi.a316a.isChecked() ? "1" :
                        bi.a316b.isChecked() ? "2" :
                                bi.a31696.isChecked() ? "96" :
                                        "0");
        json.put("a31696x", bi.a31696x.getText().toString());

        json.put("a317",
                bi.a317a.isChecked() ? "1" :
                        bi.a317b.isChecked() ? "2" :
                                bi.a317c.isChecked() ? "3" :
                                        bi.a317d.isChecked() ? "4" :
                                                bi.a31796.isChecked() ? "96" :
                                                        "0");
        json.put("a31796x", bi.a31796x.getText().toString());

        MainApp.fc.setsA3(String.valueOf(json));
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }


}

