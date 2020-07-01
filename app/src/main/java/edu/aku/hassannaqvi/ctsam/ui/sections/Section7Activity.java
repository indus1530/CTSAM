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
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection7Binding;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

public class Section7Activity extends AppCompatActivity {

    ActivitySection7Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_7);
        bi.setCallback(this);
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("fus7q101", bi.fus7q101a.isChecked() ? "1"
                : bi.fus7q101b.isChecked() ? "2"
                : "-1");

        json.put("fus7q102", bi.fus7q102a.isChecked() ? "1"
                : bi.fus7q102b.isChecked() ? "2"
                : "-1");

        json.put("fus7q103", bi.fus7q103a.isChecked() ? "1"
                : bi.fus7q103b.isChecked() ? "2"
                : "-1");

        json.put("fus7q104", bi.fus7q104a.isChecked() ? "1"
                : bi.fus7q104b.isChecked() ? "2"
                : "-1");

        json.put("fus7q201", bi.fus7q201a.isChecked() ? "1"
                : bi.fus7q201b.isChecked() ? "2"
                : "-1");

        json.put("fus7q301", bi.fus7q301a.isChecked() ? "1"
                : bi.fus7q301b.isChecked() ? "2"
                : "-1");

        json.put("fus7q401", bi.fus7q401a.isChecked() ? "1"
                : bi.fus7q401b.isChecked() ? "2"
                : "-1");

        MainApp.fc.setsG(String.valueOf(json));
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
                startActivity(new Intent(this, Section8Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SG, MainApp.fc.getsG());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSection7);
    }

    public void BtnEnd() {
        UtilKt.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }
}