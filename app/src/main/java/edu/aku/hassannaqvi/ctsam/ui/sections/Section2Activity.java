package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySection2Binding;

public class Section2Activity extends AppCompatActivity {

    ActivitySection2Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_2);
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("fumeas01", bi.fumeas01.getText().toString());

        json.put("fus2q1m1hei", bi.fus2q1m1hei.getText().toString());

        json.put("fus2q2m1wei", bi.fus2q2m1wei.getText().toString());

        json.put("fus2q3m1mua", bi.fus2q3m1mua.getText().toString());

        json.put("fumeas02", bi.fumeas02.getText().toString());

        json.put("fus2q1m2hei", bi.fus2q1m2hei.getText().toString());

        json.put("fus2q2m2wei", bi.fus2q2m2wei.getText().toString());

        json.put("fus2q3m2mua", bi.fus2q3m2mua.getText().toString());


        MainApp.fc.setsA(String.valueOf(json));
    }

}
