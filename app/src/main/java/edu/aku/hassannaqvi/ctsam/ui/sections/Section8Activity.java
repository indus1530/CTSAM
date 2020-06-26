package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection8Binding;

public class Section8Activity extends AppCompatActivity {

    ActivitySection8Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_8);
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


        MainApp.fc.setsA(String.valueOf(json));
    }

}
