package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
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
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionCBinding;
import edu.aku.hassannaqvi.ctsam.utils.UtilKt;

public class SectionCActivity extends AppCompatActivity {

    ActivitySectionCBinding bi;
    String hf_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_c);
        bi.setCallback(this);
        totalTextWatcher();
    }

    public void totalTextWatcher() {

        EditText[] txtListener = new EditText[]{bi.s3qa, bi.s3qb, bi.s3qc, bi.s3qd, bi.s3qe};
        for (EditText txtItem : txtListener) {
            txtItem.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    if (bi.s3qa.getText().toString().equals("") || bi.s3qb.getText().toString().equals("") || bi.s3qc.getText().toString().equals("")
                            || bi.s3qd.getText().toString().equals("") || bi.s3qe.getText().toString().equals("")) {
                        return;
                    }

                    int a3, b3, c3, d3, e3, total;
                    a3 = Integer.parseInt(bi.s3qa.getText().toString().trim());
                    b3 = Integer.parseInt(bi.s3qb.getText().toString().trim());
                    c3 = Integer.parseInt(bi.s3qc.getText().toString().trim());
                    d3 = Integer.parseInt(bi.s3qd.getText().toString().trim());
                    e3 = Integer.parseInt(bi.s3qe.getText().toString().trim());
                    total = a3 + b3 + c3 + d3 + e3;
                    bi.s3qf.setText(String.valueOf(total));
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (bi.s3qa.getText().toString().equals("") || bi.s3qb.getText().toString().equals("") || bi.s3qc.getText().toString().equals("")
                            || bi.s3qd.getText().toString().equals("") || bi.s3qe.getText().toString().equals("")) {
                        return;
                    }

                    int a3, b3, c3, d3, e3, total;
                    a3 = Integer.parseInt(bi.s3qa.getText().toString().trim());
                    b3 = Integer.parseInt(bi.s3qb.getText().toString().trim());
                    c3 = Integer.parseInt(bi.s3qc.getText().toString().trim());
                    d3 = Integer.parseInt(bi.s3qd.getText().toString().trim());
                    e3 = Integer.parseInt(bi.s3qe.getText().toString().trim());
                    total = a3 + b3 + c3 + d3 + e3;
                    bi.s3qf.setText(String.valueOf(total));
                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (bi.s3qa.getText().toString().equals("") || bi.s3qb.getText().toString().equals("") || bi.s3qc.getText().toString().equals("")
                            || bi.s3qd.getText().toString().equals("") || bi.s3qe.getText().toString().equals("")) {
                        return;
                    }

                    int a3, b3, c3, d3, e3, total;
                    a3 = Integer.parseInt(bi.s3qa.getText().toString().trim());
                    b3 = Integer.parseInt(bi.s3qb.getText().toString().trim());
                    c3 = Integer.parseInt(bi.s3qc.getText().toString().trim());
                    d3 = Integer.parseInt(bi.s3qd.getText().toString().trim());
                    e3 = Integer.parseInt(bi.s3qe.getText().toString().trim());
                    total = a3 + b3 + c3 + d3 + e3;
                    bi.s3qf.setText(String.valueOf(total));
                }
            });
        }
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
                startActivity(new Intent(this, SectionDActivity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SC, MainApp.fc.getsC());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("s3qa", bi.s3qa.getText().toString().trim().isEmpty() ? "-1" : bi.s3qa.getText().toString());
        json.put("s3qb", bi.s3qb.getText().toString().trim().isEmpty() ? "-1" : bi.s3qb.getText().toString());
        json.put("s3qc", bi.s3qc.getText().toString().trim().isEmpty() ? "-1" : bi.s3qc.getText().toString());
        json.put("s3qd", bi.s3qd.getText().toString().trim().isEmpty() ? "-1" : bi.s3qd.getText().toString());
        json.put("s3qe", bi.s3qe.getText().toString().trim().isEmpty() ? "-1" : bi.s3qe.getText().toString());
        json.put("s3qf", bi.s3qf.getText().toString().trim().isEmpty() ? "-1" : bi.s3qf.getText().toString());

        MainApp.fc.setsC(String.valueOf(json));
    }

    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.fldGrpSectionC)) {
            return false;
        }

        int a3, b3, c3, children;
        a3 = Integer.parseInt(bi.s3qa.getText().toString().trim());
        b3 = Integer.parseInt(bi.s3qb.getText().toString().trim());
        c3 = Integer.parseInt(bi.s3qc.getText().toString().trim());
        children = a3 + b3 + c3;
        if (children == 0) {
            Toast.makeText(this, "No child 06-59-month age entered, please re-check", Toast.LENGTH_LONG).show();
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
}
