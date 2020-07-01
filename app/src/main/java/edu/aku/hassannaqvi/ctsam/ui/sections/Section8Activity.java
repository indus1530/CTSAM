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
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection8Binding;
import edu.aku.hassannaqvi.ctsam.ui.other.EndingActivity;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

public class Section8Activity extends AppCompatActivity {

    ActivitySection8Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_8);
        bi.setCallback(this);
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("fus8q101", bi.fus8q101a.isChecked() ? "1"
                : bi.fus8q101b.isChecked() ? "2"
                : "-1");

        json.put("fus8q102", bi.fus8q102a.isChecked() ? "1"
                : bi.fus8q102b.isChecked() ? "2"
                : "-1");

        json.put("fus8q201", bi.fus8q201a.isChecked() ? "1"
                : bi.fus8q201b.isChecked() ? "2"
                : "-1");

        json.put("fus8q301", bi.fus8q301a.isChecked() ? "1"
                : bi.fus8q301b.isChecked() ? "2"
                : "-1");

        json.put("fus8q401", bi.fus8q401a.isChecked() ? "1"
                : bi.fus8q401b.isChecked() ? "2"
                : "-1");

        MainApp.fc.setsH(String.valueOf(json));
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
                startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SH, MainApp.fc.getsH());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSection8);
    }

    public void BtnEnd() {
        UtilKt.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }
}