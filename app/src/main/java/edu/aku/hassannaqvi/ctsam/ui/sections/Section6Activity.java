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
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection6Binding;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

public class Section6Activity extends AppCompatActivity {

    ActivitySection6Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_6);
        bi.setCallback(this);
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("fus6q1", bi.fus6q1a.isChecked() ? "1"
                : bi.fus6q1b.isChecked() ? "2"
                : bi.fus6q1c.isChecked() ? "3"
                : bi.fus6q1d.isChecked() ? "4"
                : bi.fus6q1e.isChecked() ? "5"
                : bi.fus6q1f.isChecked() ? "6"
                : "-1");

        json.put("fus6q2", bi.fus6q2a.isChecked() ? "1"
                : bi.fus6q2b.isChecked() ? "2"
                : bi.fus6q2c.isChecked() ? "3"
                : bi.fus6q2d.isChecked() ? "4"
                : bi.fus6q2e.isChecked() ? "5"
                : "-1");

        json.put("fus6q2ax", bi.fus6q2ax.getText().toString().trim().isEmpty() ? "-1" : bi.fus6q2ax.getText().toString());
        json.put("fus6q2bx", bi.fus6q2bx.getText().toString().trim().isEmpty() ? "-1" : bi.fus6q2bx.getText().toString());
        json.put("fus6q2cx", bi.fus6q2cx.getText().toString().trim().isEmpty() ? "-1" : bi.fus6q2cx.getText().toString());
        json.put("fus6q2dx", bi.fus6q2dx.getText().toString().trim().isEmpty() ? "-1" : bi.fus6q2dx.getText().toString());
        json.put("fus6q2ex", bi.fus6q2ex.getText().toString().trim().isEmpty() ? "-1" : bi.fus6q2ex.getText().toString());

        json.put("fus6q3", bi.fus6q3.getText().toString().trim().isEmpty() ? "-1" : bi.fus6q3.getText().toString());

        MainApp.fc.setsF(String.valueOf(json));
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
                startActivity(new Intent(this, Section7Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SF, MainApp.fc.getsF());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSection6);
    }

    public void BtnEnd() {
        UtilKt.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }
}