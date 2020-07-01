package edu.aku.hassannaqvi.ctsam.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class FormsContract {

    private String _ID = "";
    private String _UID = "";
    private String formDate = ""; // Date
    private String formType = ""; // Date
    private String user = ""; // Interviewer
    private String user2 = ""; // Interviewer
    private String istatus = ""; // Interview Status
    private String istatus88x = ""; // Interview Status
    private String luid = "";
    private String endingdatetime = "";
    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";
    private String studyId = "";
    private String hfCode = "";
    private String sA = "";
    private String sB = "";
    private String sC = "";
    private String sD = "";
    private String sE = "";
    private String sF = "";
    private String sG = "";
    private String sH = "";
    private String sI = "";
    private String sJ = "";

    public static final String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.ctsam";
    public static final String PATH_FORMS = "forms";

    public FormsContract() {
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }


    public String getHfCode() {
        return hfCode;
    }

    public void setHfCode(String hfCode) {
        this.hfCode = hfCode;
    }

    public FormsContract Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(FormsTable.COLUMN_ID);
        this._UID = jsonObject.getString(FormsTable.COLUMN_UID);
        this.formDate = jsonObject.getString(FormsTable.COLUMN_FORMDATE);
        this.formType = jsonObject.getString(FormsTable.COLUMN_FORMTYPE);
        this.user = jsonObject.getString(FormsTable.COLUMN_USER);
        this.user2 = jsonObject.getString(FormsTable.COLUMN_USER2);
        this.istatus = jsonObject.getString(FormsTable.COLUMN_ISTATUS);
        this.istatus88x = jsonObject.getString(FormsTable.COLUMN_ISTATUS88x);
        this.luid = jsonObject.getString(FormsTable.COLUMN_LUID);
        this.endingdatetime = jsonObject.getString(FormsTable.COLUMN_ENDINGDATETIME);
        this.gpsLat = jsonObject.getString(FormsTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(FormsTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(FormsTable.COLUMN_GPSDATE);
        this.gpsAcc = jsonObject.getString(FormsTable.COLUMN_GPSACC);
        this.deviceID = jsonObject.getString(FormsTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.studyId = jsonObject.getString(FormsTable.COLUMN_STUDYID);
        this.hfCode = jsonObject.getString(FormsTable.COLUMN_HF_CODE);
        this.sA = jsonObject.getString(FormsTable.COLUMN_SA);
        this.sB = jsonObject.getString(FormsTable.COLUMN_SB);
        this.sC = jsonObject.getString(FormsTable.COLUMN_SC);
        this.sD = jsonObject.getString(FormsTable.COLUMN_SD);
        this.sE = jsonObject.getString(FormsTable.COLUMN_SE);
        this.sF = jsonObject.getString(FormsTable.COLUMN_SF);
        this.sG = jsonObject.getString(FormsTable.COLUMN_SG);
        this.sH = jsonObject.getString(FormsTable.COLUMN_SH);
        this.sI = jsonObject.getString(FormsTable.COLUMN_SI);
        this.sJ = jsonObject.getString(FormsTable.COLUMN_SJ);

        return this;
    }

    public FormsContract Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.formDate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMDATE));
        this.formType = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMTYPE));
        this.user = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_USER));
        this.user2 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_USER2));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus88x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS88x));
        this.luid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_LUID));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ENDINGDATETIME));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APPVERSION));
        this.studyId = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_STUDYID));
        this.hfCode = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_HF_CODE));
        this.sA = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SA));
        this.sB = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SB));
        this.sC = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SC));
        this.sD = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SD));
        this.sE = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SE));
        this.sF = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SF));
        this.sG = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SG));
        this.sH = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SH));
        this.sI = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SI));
        this.sJ = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SJ));

        return this;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(FormsTable.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(FormsTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(FormsTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(FormsTable.COLUMN_FORMTYPE, this.formType == null ? JSONObject.NULL : this.formType);
        json.put(FormsTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(FormsTable.COLUMN_USER2, this.user2 == null ? JSONObject.NULL : this.user2);
        json.put(FormsTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(FormsTable.COLUMN_ISTATUS88x, this.istatus88x == null ? JSONObject.NULL : this.istatus88x);
        json.put(FormsTable.COLUMN_LUID, this.luid == null ? JSONObject.NULL : this.luid);
        json.put(FormsTable.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);
        json.put(FormsTable.COLUMN_STUDYID, this.studyId == null ? JSONObject.NULL : this.studyId);
        json.put(FormsTable.COLUMN_HF_CODE, this.hfCode == null ? JSONObject.NULL : this.hfCode);

        if (!this.sA.equals("")) {
            json.put(FormsTable.COLUMN_SA, new JSONObject(this.sA));
        }

        if (!this.sB.equals("")) {
            json.put(FormsTable.COLUMN_SB, new JSONObject(this.sB));
        }

        if (!this.sC.equals("")) {
            json.put(FormsTable.COLUMN_SC, new JSONObject(this.sC));
        }

        if (!this.sD.equals("")) {
            json.put(FormsTable.COLUMN_SD, new JSONObject(this.sD));
        }

        if (!this.sE.equals("")) {
            json.put(FormsTable.COLUMN_SE, new JSONObject(this.sE));
        }

        if (!this.sF.equals("")) {
            json.put(FormsTable.COLUMN_SF, new JSONObject(this.sF));
        }

        if (!this.sG.equals("")) {
            json.put(FormsTable.COLUMN_SG, new JSONObject(this.sG));
        }

        if (!this.sH.equals("")) {
            json.put(FormsTable.COLUMN_SH, new JSONObject(this.sH));
        }

        if (!this.sI.equals("")) {
            json.put(FormsTable.COLUMN_SI, new JSONObject(this.sI));
        }

        if (!this.sJ.equals("")) {
            json.put(FormsTable.COLUMN_SJ, new JSONObject(this.sJ));
        }

        json.put(FormsTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put(FormsTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put(FormsTable.COLUMN_GPSDATE, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
        json.put(FormsTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
        json.put(FormsTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(FormsTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(FormsTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);

        return json;
    }


    public String getsA() {
        return sA;
    }

    public void setsA(String sA) {
        this.sA = sA;
    }


    public String getsB() {
        return sB;
    }

    public void setsB(String sB) {
        this.sB = sB;
    }


    public String getsC() {
        return sC;
    }

    public void setsC(String sC) {
        this.sC = sC;
    }


    public String getsD() {
        return sD;
    }

    public void setsD(String sD) {
        this.sD = sD;
    }


    public String getsE() {
        return sE;
    }

    public void setsE(String sE) {
        this.sE = sE;
    }


    public String getsF() {
        return sF;
    }

    public void setsF(String sF) {
        this.sF = sF;
    }

    public String getsG() {
        return sG;
    }

    public void setsG(String sG) {
        this.sG = sG;
    }


    public String getsH() {
        return sH;
    }

    public void setsH(String sH) {
        this.sH = sH;
    }


    public String getsI() {
        return sI;
    }

    public void setsI(String sI) {
        this.sI = sI;
    }


    public String getsJ() {
        return sJ;
    }

    public void setsJ(String sJ) {
        this.sJ = sJ;
    }

    public String getProjectName() {
        String projectName = "ctsam_2020";
        return projectName;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getIstatus88x() {
        return istatus88x;
    }

    public void setIstatus88x(String istatus88x) {
        this.istatus88x = istatus88x;
    }

    public String getLuid() {
        return luid;
    }

    public void setLuid(String Study_Id) {
        this.luid = Study_Id;
    }

    public String getEndingdatetime() {
        return endingdatetime;
    }

    public void setEndingdatetime(String endingdatetime) {
        this.endingdatetime = endingdatetime;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }

    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }

    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;

    }

    public interface FormsTable extends BaseColumns {

        String TABLE_NAME = "forms";
        String COLUMN_NAME_NULLABLE = "NULLHACK";
        String COLUMN_PROJECT_NAME = "projectName";
        String COLUMN_ID = "_id";
        String COLUMN_UID = "_uid";
        String COLUMN_FORMDATE = "formdate";
        String COLUMN_FORMTYPE = "formtype";
        String COLUMN_USER = "username";
        String COLUMN_USER2 = "username2";
        String COLUMN_ISTATUS = "istatus";
        String COLUMN_ISTATUS88x = "istatus88x";
        String COLUMN_LUID = "_luid";
        String COLUMN_ENDINGDATETIME = "endingdatetime";
        String COLUMN_GPSLAT = "gpslat";
        String COLUMN_GPSLNG = "gpslng";
        String COLUMN_GPSDATE = "gpsdate";
        String COLUMN_GPSACC = "gpsacc";
        String COLUMN_DEVICEID = "deviceid";
        String COLUMN_DEVICETAGID = "tagid";
        String COLUMN_SYNCED = "synced";
        String COLUMN_SYNCED_DATE = "synced_date";
        String COLUMN_APPVERSION = "appversion";
        String COLUMN_STUDYID = "studyId";
        String COLUMN_HF_CODE = "hfCode";
        String COLUMN_SA = "sA";
        String COLUMN_SB = "sB";
        String COLUMN_SC = "sC";
        String COLUMN_SD = "sD";
        String COLUMN_SE = "sE";
        String COLUMN_SF = "sF";
        String COLUMN_SG = "sG";
        String COLUMN_SH = "sH";
        String COLUMN_SI = "sI";
        String COLUMN_SJ = "sJ";
    }
}