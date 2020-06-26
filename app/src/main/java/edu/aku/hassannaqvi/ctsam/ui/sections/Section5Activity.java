package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection5Binding;

public class Section5Activity extends AppCompatActivity {

    ActivitySection5Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_5);
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("fus5q1", bi.fus5q1a.isChecked() ? "1"
                : bi.fus5q1b.isChecked() ? "2"
                : bi.fus5q1c.isChecked() ? "3"
                : bi.fus5q1d.isChecked() ? "4"
                : "-1");

        json.put("fus5q1dx", bi.fus5q1dx.getText().toString());
        json.put("fus5q2", bi.fus5q2a.isChecked() ? ""
                : bi.fus5q2b.isChecked() ? ""
                : bi.fus5q2c.isChecked() ? ""
                : bi.fus5q2d.isChecked() ? ""
                : bi.fus5q2e.isChecked() ? ""
                : bi.fus5q2f.isChecked() ? ""
                : bi.fus5q2g.isChecked() ? "96"
                : "-1");

        json.put("fus5q2ax", bi.fus5q2ax.getText().toString());
        json.put("fus5q2bx", bi.fus5q2bx.getText().toString());
        json.put("fus5q2cx", bi.fus5q2cx.getText().toString());
        json.put("fus5q2dx", bi.fus5q2dx.getText().toString());
        json.put("fus5q2ex", bi.fus5q2ex.getText().toString());
        json.put("fus5q2fx", bi.fus5q2fx.getText().toString());
        json.put("fus5q2gx", bi.fus5q2gx.getText().toString());
        json.put("fus5q3", bi.fus5q3a.isChecked() ? "0"
//                : bi.fus5q3b.isChecked() ? "88"
                : "-1");

        json.put("fus5q3ax", bi.fus5q3ax.getText().toString());
        json.put("fus5q4", bi.fus5q4a.isChecked() ? "1"
                : bi.fus5q4b.isChecked() ? "2"
                : bi.fus5q4c.isChecked() ? "3"
                : bi.fus5q4d.isChecked() ? "4"
                : bi.fus5q4e.isChecked() ? "5"
                : bi.fus5q4f.isChecked() ? "96"
                : "-1");

        json.put("fus5q4fx", bi.fus5q4fx.getText().toString());
        json.put("fus5q5", bi.fus5q5a.isChecked() ? ""
                : bi.fus5q5b.isChecked() ? ""
                : "-1");

        json.put("fus5q5ax", bi.fus5q5ax.getText().toString());
        json.put("fus5q5bx", bi.fus5q5bx.getText().toString());
        json.put("fus5q6", bi.fus5q6a.isChecked() ? "1"
                : bi.fus5q6b.isChecked() ? "2"
                : "-1");

        json.put("fus5q7", bi.fus5q7a.isChecked() ? "1"
                : bi.fus5q7b.isChecked() ? "2"
                : bi.fus5q7c.isChecked() ? "3"
                : bi.fus5q7d.isChecked() ? "4"
//                : bi.fus5q7e.isChecked() ? "96"
                : "-1");

        json.put("fus5q8", bi.fus5q8a.isChecked() ? "1"
                : bi.fus5q8b.isChecked() ? "2"
                : "-1");

        json.put("fus5q9", bi.fus5q9a.isChecked() ? ""
                : bi.fus5q9b.isChecked() ? ""
                : bi.fus5q9c.isChecked() ? "96"
                : "-1");

        json.put("fus5q9ax", bi.fus5q9ax.getText().toString());
        json.put("fus5q9bx", bi.fus5q9bx.getText().toString());
        json.put("fus5q9cx", bi.fus5q9cx.getText().toString());
        json.put("fus5q10", bi.fus5q10a.isChecked() ? "1"
                : bi.fus5q10b.isChecked() ? "2"
                : bi.fus5q10c.isChecked() ? "3"
                : bi.fus5q10d.isChecked() ? "4"
                : bi.fus5q10e.isChecked() ? "96"
                : "-1");

        json.put("fus5q10ex", bi.fus5q10ex.getText().toString());


        MainApp.fc.setsA(String.valueOf(json));
    }

}
