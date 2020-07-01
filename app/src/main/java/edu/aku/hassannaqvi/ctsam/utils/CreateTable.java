package edu.aku.hassannaqvi.ctsam.utils;

import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.contracts.HealthFacilitiesContract;
import edu.aku.hassannaqvi.ctsam.contracts.TalukasContract;
import edu.aku.hassannaqvi.ctsam.contracts.UsersContract;
import edu.aku.hassannaqvi.ctsam.contracts.VersionAppContract;
import edu.aku.hassannaqvi.ctsam.contracts.VillagesContract;

public final class CreateTable {

    public static final String DATABASE_NAME = "ctsam20.db";
    public static final String DB_NAME = "ctsam20_copy.db";
    public static final String PROJECT_NAME = "DMU-CTSAM2020";
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsContract.FormsTable.TABLE_NAME + "("
            + FormsContract.FormsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsContract.FormsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsContract.FormsTable.COLUMN_UID + " TEXT,"
            + FormsContract.FormsTable.COLUMN_FORMDATE + " TEXT,"
            + FormsContract.FormsTable.COLUMN_FORMTYPE + " TEXT,"
            + FormsContract.FormsTable.COLUMN_APPVERSION + " TEXT,"
            + FormsContract.FormsTable.COLUMN_STUDYID + " TEXT,"
            + FormsContract.FormsTable.COLUMN_HF_CODE + " TEXT,"
            + FormsContract.FormsTable.COLUMN_LUID + " TEXT,"
            + FormsContract.FormsTable.COLUMN_USER + " TEXT,"
            + FormsContract.FormsTable.COLUMN_USER2 + " TEXT,"
            + FormsContract.FormsTable.COLUMN_SA + " TEXT,"
            + FormsContract.FormsTable.COLUMN_SB + " TEXT,"
            + FormsContract.FormsTable.COLUMN_SC + " TEXT,"
            + FormsContract.FormsTable.COLUMN_SD + " TEXT,"
            + FormsContract.FormsTable.COLUMN_SE + " TEXT,"
            + FormsContract.FormsTable.COLUMN_SF + " TEXT,"
            + FormsContract.FormsTable.COLUMN_SG + " TEXT,"
            + FormsContract.FormsTable.COLUMN_SH + " TEXT,"
            + FormsContract.FormsTable.COLUMN_SI + " TEXT,"
            + FormsContract.FormsTable.COLUMN_SJ + " TEXT,"
            + FormsContract.FormsTable.COLUMN_ISTATUS + " TEXT,"
            + FormsContract.FormsTable.COLUMN_ISTATUS88x + " TEXT,"
            + FormsContract.FormsTable.COLUMN_ENDINGDATETIME + " TEXT,"
            + FormsContract.FormsTable.COLUMN_GPSLAT + " TEXT,"
            + FormsContract.FormsTable.COLUMN_GPSLNG + " TEXT,"
            + FormsContract.FormsTable.COLUMN_GPSDATE + " TEXT,"
            + FormsContract.FormsTable.COLUMN_GPSACC + " TEXT,"
            + FormsContract.FormsTable.COLUMN_DEVICEID + " TEXT,"
            + FormsContract.FormsTable.COLUMN_DEVICETAGID + " TEXT,"
            + FormsContract.FormsTable.COLUMN_SYNCED + " TEXT,"
            + FormsContract.FormsTable.COLUMN_SYNCED_DATE + " TEXT"
            + " );";

    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersContract.SingleUser.TABLE_NAME + "("
            + UsersContract.SingleUser._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersContract.SingleUser.ROW_USERNAME + " TEXT,"
            + UsersContract.SingleUser.ROW_PASSWORD + " TEXT,"
            + UsersContract.SingleUser.DIST_ID + " TEXT"
            + " );";

    public static final String SQL_CREATE_VERSIONAPP = "CREATE TABLE " + VersionAppContract.VersionAppTable.TABLE_NAME + " (" +
            VersionAppContract.VersionAppTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VersionAppContract.VersionAppTable.COLUMN_VERSION_CODE + " TEXT, " +
            VersionAppContract.VersionAppTable.COLUMN_VERSION_NAME + " TEXT, " +
            VersionAppContract.VersionAppTable.COLUMN_PATH_NAME + " TEXT " +
            ");";
    public static final String SQL_CREATE_TALUKAS = "CREATE TABLE " + TalukasContract.SingleTalukas.TABLE_NAME + "("
            + TalukasContract.SingleTalukas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TalukasContract.SingleTalukas.COLUMN_TALUKA_CODE + " TEXT,"
            + TalukasContract.SingleTalukas.COLUMN_TALUKA + " TEXT );";


    public static final String SQL_CREATE_HEALTH_FACILITIES = "CREATE TABLE " + HealthFacilitiesContract.SingleHealthFacilities.TABLE_NAME + "("
            + HealthFacilitiesContract.SingleHealthFacilities._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + HealthFacilitiesContract.SingleHealthFacilities.COLUMN_FACILITY_CODE + " TEXT,"
            + HealthFacilitiesContract.SingleHealthFacilities.COLUMN_FACILITY_NAME + " TEXT,"
            + HealthFacilitiesContract.SingleHealthFacilities.COLUMN_TALUKA_CODE + " TEXT );";

    public static final String SQL_CREATE_VILLAGES = "CREATE TABLE " + VillagesContract.SingleVillage.TABLE_NAME + "("
            + VillagesContract.SingleVillage._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VillagesContract.SingleVillage.COLUMN_VILLAGE_CODE + " TEXT,"
            + VillagesContract.SingleVillage.COLUMN_VILLAGE_NAME + " TEXT,"
            + VillagesContract.SingleVillage.COLUMN_HF_CODE + " TEXT );";
}
