package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection7Binding;

public class Section7Activity extends AppCompatActivity {

    ActivitySection7Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_7);
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


        MainApp.fc.setsA(String.valueOf(json));
    }

}
