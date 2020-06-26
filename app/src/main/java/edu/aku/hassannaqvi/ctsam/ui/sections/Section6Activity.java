package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection6Binding;

public class Section6Activity extends AppCompatActivity {

    ActivitySection6Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_6);
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

        json.put("fus6q2", bi.fus6q2a.isChecked() ? ""
                : bi.fus6q2b.isChecked() ? ""
                : bi.fus6q2c.isChecked() ? ""
                : bi.fus6q2d.isChecked() ? ""
                : bi.fus6q2e.isChecked() ? ""
                : "-1");

        json.put("fus6q2ax", bi.fus6q2ax.getText().toString());
        json.put("fus6q2bx", bi.fus6q2bx.getText().toString());
        json.put("fus6q2cx", bi.fus6q2cx.getText().toString());
        json.put("fus6q2dx", bi.fus6q2dx.getText().toString());
        json.put("fus6q2ex", bi.fus6q2ex.getText().toString());
        json.put("fus6q3", bi.fus6q3.getText().toString());

        MainApp.fc.setsA(String.valueOf(json));
    }

}
