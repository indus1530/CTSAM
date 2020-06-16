package edu.aku.hassannaqvi.ctsam.ui.sections;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import edu.aku.hassannaqvi.ctsam.CONSTANTS;
import edu.aku.hassannaqvi.ctsam.R;
import edu.aku.hassannaqvi.ctsam.contracts.EnumBlockContract;
import edu.aku.hassannaqvi.ctsam.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.ctsam.core.DatabaseHelper;
import edu.aku.hassannaqvi.ctsam.core.MainApp;
import edu.aku.hassannaqvi.ctsam.databinding.ActivitySectionInfoBinding;

public class SectionInfoActivity extends AppCompatActivity {

    ActivitySectionInfoBinding bi;
    private DatabaseHelper db;
    ArrayList<FamilyMembersContract> famList;
    private int selectedBTN;
    public static File outputDirectory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_info);
        bi.setCallback(this);
        db = MainApp.appInfo.getDbHelper();

        setUIComponent();
    }


    private void setUIComponent() {

        bi.a101.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (bi.a101.getText().hashCode() == s.hashCode()) {
                    Clear.clearAllFields(bi.fldGrpSectionA01);
                    bi.fldGrpSectionA01.setVisibility(View.GONE);
                    bi.btnNext.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        bi.a112.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Objects.requireNonNull(bi.a112.getText()).hashCode() == s.hashCode()) {
                    bi.btnNext.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        selectedBTN = getIntent().getIntExtra(CONSTANTS.MAIN_INTENT, 0);

    }


    public void BtnContinue() {
    }

    public void BtnCheckCluster() {

        if (!Validator.emptyTextBox(this, bi.a101)) return;
        boolean loginFlag;
        int cluster = Integer.valueOf(bi.a101.getText().toString());
        if (cluster <= 5000) {
            loginFlag = !(MainApp.userName.equals("test1234") || MainApp.userName.equals("dmu@aku") || MainApp.userName.substring(0, 4).equals("user"));
        } else {
            loginFlag = MainApp.userName.equals("test1234") || MainApp.userName.equals("dmu@aku") || MainApp.userName.substring(0, 4).equals("user");
        }
        if (!loginFlag) {
            Toast.makeText(this, "Can't proceed test cluster for current user!!", Toast.LENGTH_SHORT).show();
            return;
        }

        EnumBlockContract enumBlockContract = db.getEnumBlock(bi.a101.getText().toString());
        if (enumBlockContract != null) {
            String selected = enumBlockContract.getGeoarea();
            if (!selected.equals("")) {

                String[] selSplit = selected.split("\\|");

                bi.fldGrpSectionA01.setVisibility(View.VISIBLE);
                bi.a104.setText(selSplit[0]);
                bi.a105.setText(selSplit[1].equals("") ? "----" : selSplit[1]);
                bi.a106.setText(selSplit[2].equals("") ? "----" : selSplit[2]);
                bi.a107.setText(selSplit[3]);

            }
        } else {
            Toast.makeText(this, "Sorry cluster not found!!", Toast.LENGTH_SHORT).show();
        }

    }

    public void BtnCheckHH() {
        if (!Validator.emptyTextBox(this, bi.a112)) return;
        MainApp.indexKishMWRA = db.getFamilyMember(bi.a101.getText().toString(), bi.a112.getText().toString().toUpperCase(), "1", null);
        if (MainApp.indexKishMWRA == null) {
            Toast.makeText(this, "No Household found!", Toast.LENGTH_SHORT).show();
            bi.btnNext.setVisibility(View.GONE);
            return;
        }

        if (selectedBTN == 1) {
            famList = MainApp.appInfo.getDbHelper().getFamilyMemberList(bi.a101.getText().toString(), bi.a112.getText().toString().toUpperCase(), MainApp.indexKishMWRA);
            if (famList.size() == 0) {
                Toast.makeText(this, "No Members found!", Toast.LENGTH_SHORT).show();
                return;
            }
            famList.add(MainApp.indexKishMWRA);
        } else {
            MainApp.indexKishMWRAChild = db.getFamilyMember(bi.a101.getText().toString(), bi.a112.getText().toString().toUpperCase(), "2", MainApp.indexKishMWRA);
        }
        Toast.makeText(this, "Members Found!", Toast.LENGTH_SHORT).show();
        bi.btnNext.setVisibility(View.VISIBLE);
    }
}
