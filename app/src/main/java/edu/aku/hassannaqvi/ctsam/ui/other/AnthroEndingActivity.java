package edu.aku.hassannaqvi.ctsam.ui.other;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import edu.aku.hassannaqvi.ctsam.CONSTANTS;
import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivityAnthroEndingBinding;
import edu.aku.hassannaqvi.ctsam.ui.sections.SectionK1Activity;
import edu.aku.hassannaqvi.ctsam.ui.sections.SectionK2Activity;

public class AnthroEndingActivity extends AppCompatActivity {

    ActivityAnthroEndingBinding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_anthro_ending);
        bi.setCallback(this);


        boolean check = getIntent().getBooleanExtra("complete", false);

        if (check) {
            bi.k208a.setEnabled(true);
            bi.k208b.setEnabled(false);
            bi.k208c.setEnabled(false);
        } else {
            bi.k208a.setEnabled(false);
            bi.k208b.setEnabled(true);
            bi.k208c.setEnabled(true);
        }

//
    }

    public void BtnEnd() {
        if (formValidation()) {
            SaveDraft();
            if (UpdateDB()) {
                finish();
                Intent intent;
                if (MainApp.anthro.getFormType().equals(CONSTANTS.ANTHRO_K1)) {
                    if (MainApp.mwraChildren.getFirst().size() > 0)
                        intent = new Intent(this, SectionK1Activity.class);
                    else
                        intent = new Intent(this, EndingActivity.class).putExtra("complete", true);
                } else {
                    if (MainApp.mwraChildrenAnthro.getFirst().size() > 0)
                        intent = new Intent(this, SectionK2Activity.class);
                    else
                        intent = new Intent(this, MainActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }


                startActivity(intent);
            } else {
                Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SaveDraft() {
        MainApp.anthro.setIstatus(bi.k208a.isChecked() ? "1"
                : bi.k208b.isChecked() ? "2"
                : bi.k208c.isChecked() ? "3"
                : "0");
    }

    public boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updateAnthroEnding();
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.anthroEnd);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }

}
