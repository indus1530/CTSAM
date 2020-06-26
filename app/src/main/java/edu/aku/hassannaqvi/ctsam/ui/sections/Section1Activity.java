package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection1Binding;

public class Section1Activity extends AppCompatActivity {

    ActivitySection1Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_1);
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("fus1q1", bi.fus1q1a.isChecked() ? "1"
                : bi.fus1q1b.isChecked() ? "2"
                : bi.fus1q1c.isChecked() ? "3"
                : bi.fus1q1d.isChecked() ? "4"
                : "-1");

        json.put("fus1q2", bi.fus1q2a.isChecked() ? "1"
                : bi.fus1q2b.isChecked() ? "2"
                : "-1");

        json.put("fus1q3", bi.fus1q3.getText().toString());

        json.put("fus1q4", bi.fus1q4.getText().toString());

        json.put("fus1q5", bi.fus1q5.getText().toString());

        json.put("fus1q6", bi.fus1q6.getText().toString());

        json.put("fus1q7", bi.fus1q7a.isChecked() ? "1"
                : bi.fus1q7b.isChecked() ? "2"
                : bi.fus1q7c.isChecked() ? "3"
                : bi.fus1q7d.isChecked() ? "4"
                : bi.fus1q7e.isChecked() ? "5"
                : bi.fus1q7f.isChecked() ? "6"
                : bi.fus1q7g.isChecked() ? "96"
                : "-1");

        json.put("fus1q8", bi.fus1q8.getText().toString());

        json.put("fus1q9", bi.fus1q9.getText().toString());

        json.put("fus1q10", bi.fus1q10.getText().toString());


        MainApp.fc.setsA(String.valueOf(json));
    }

}
