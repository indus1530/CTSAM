package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionABinding;
import edu.aku.hassannaqvi.ctsam.utils.Util;

public class SectionAActivity extends AppCompatActivity {

    ActivitySectionABinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_a);
        bi.setCallback(this);


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
                //startActivity(new Intent(this, FamilyMembersListActivity.class).putExtra("sno", Integer.valueOf(bl.getSno())));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {
        /*long updcount = db.addForm(MainApp.fc);
        MainApp.fc.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            MainApp.fc.set_UID(MainApp.fc.getDeviceID() + MainApp.fc.get_ID());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, MainApp.fc.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }*/

        return true;
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("s1q1", bi.s1q1.getSelectedItem().toString());

        json.put("s1q2", bi.s1q2.getSelectedItem().toString());

        json.put("s1q3", bi.s1q3.getSelectedItem().toString());

        json.put("s1q4", bi.s1q4.getText().toString());

        json.put("s1q5", bi.s1q5.getText().toString());

        json.put("s1q6", bi.s1q6.getText().toString());

        json.put("s1q7", bi.s1q7.getText().toString());

        json.put("s1q8", bi.s1q8a.isChecked() ? "1"
                : bi.s1q8b.isChecked() ? "2"
                : "0");

        json.put("s1q8reason", bi.s1q8reason.getText().toString());


    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        if (formValidation()) {
            Util.contextEndActivity(this);
        }
    }


}
