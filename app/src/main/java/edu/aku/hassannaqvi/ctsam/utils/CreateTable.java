package edu.aku.hassannaqvi.ctsam.utils;

import edu.aku.hassannaqvi.ctsam.contracts.BLRandomContract.SingleRandomHH;
import edu.aku.hassannaqvi.ctsam.contracts.ChildContract.SingleChild;
import edu.aku.hassannaqvi.ctsam.contracts.EnumBlockContract;
import edu.aku.hassannaqvi.ctsam.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.contracts.HealthFacilitiesContract;
import edu.aku.hassannaqvi.ctsam.contracts.TalukasContract;
import edu.aku.hassannaqvi.ctsam.contracts.UsersContract;
import edu.aku.hassannaqvi.ctsam.contracts.VersionAppContract;
import edu.aku.hassannaqvi.ctsam.contracts.VillagesContract;
import edu.aku.hassannaqvi.ctsam.contracts.VisionContract;

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
            + FormsContract.FormsTable.COLUMN_APPVERSION + " TEXT,"
            + FormsContract.FormsTable.COLUMN_CLUSTERCODE + " TEXT,"
            + FormsContract.FormsTable.COLUMN_STUDYID + " TEXT,"
            + FormsContract.FormsTable.COLUMN_HF_CODE + " TEXT,"
            + FormsContract.FormsTable.COLUMN_HHNO + " TEXT,"
            + FormsContract.FormsTable.COLUMN_FORMTYPE + " TEXT,"
            + FormsContract.FormsTable.COLUMN_LUID + " TEXT,"
            + FormsContract.FormsTable.COLUMN_USER + " TEXT,"
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


  /*   public static final String SQL_CREATE_UCS = "CREATE TABLE " + UCsContract.singleUCs.TABLE_NAME + "("
            + UCsContract.singleUCs._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UCsContract.singleUCs.COLUMN_UCCODE + " TEXT,"
            + UCsContract.singleUCs.COLUMN_TALUKA_CODE + " TEXT,"
            + UCsContract.singleUCs.COLUMN_UCS + " TEXT );";


    public static final String SQL_CREATE_AREAS = "CREATE TABLE " + AreasContract.singleAreas.TABLE_NAME + "("
            + AreasContract.singleAreas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AreasContract.singleAreas.COLUMN_AREACODE + " TEXT,"
            + AreasContract.singleAreas.COLUMN_UC_CODE + " TEXT,"
            + AreasContract.singleAreas.COLUMN_AREA + " TEXT );";*/

    public static final String SQL_CREATE_BL_RANDOM = "CREATE TABLE " + SingleRandomHH.TABLE_NAME + "("
            + SingleRandomHH.COLUMN_ID + " TEXT,"
            + SingleRandomHH.COLUMN_ENUM_BLOCK_CODE + " TEXT,"
            + SingleRandomHH.COLUMN_LUID + " TEXT,"
            + SingleRandomHH.COLUMN_HH + " TEXT,"
            + SingleRandomHH.COLUMN_STRUCTURE_NO + " TEXT,"
            + SingleRandomHH.COLUMN_FAMILY_EXT_CODE + " TEXT,"
            + SingleRandomHH.COLUMN_HH_HEAD + " TEXT,"
            + SingleRandomHH.COLUMN_CONTACT + " TEXT,"
            + SingleRandomHH.COLUMN_HH_SELECTED_STRUCT + " TEXT,"
            + SingleRandomHH.COLUMN_RANDOMDT + " TEXT,"
            + SingleRandomHH.COLUMN_SNO_HH + " TEXT );";

    public static final String SQL_CREATE_PSU_TABLE = "CREATE TABLE " + EnumBlockContract.EnumBlockTable.TABLE_NAME + " (" +
            EnumBlockContract.EnumBlockTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            EnumBlockContract.EnumBlockTable.COLUMN_DIST_ID + " TEXT, " +
            EnumBlockContract.EnumBlockTable.COLUMN_GEO_AREA + " TEXT, " +
            EnumBlockContract.EnumBlockTable.COLUMN_CLUSTER_AREA + " TEXT " +
            ");";

    public static final String SQL_CREATE_CHILD_TABLE = "CREATE TABLE " + SingleChild.TABLE_NAME + "("
            + SingleChild._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SingleChild.COLUMN_UID + " TEXT,"
            + SingleChild.COLUMN__UUID + " TEXT,"
            + SingleChild.COLUMN_DEVICEID + " TEXT,"
            + SingleChild.COLUMN_FORMDATE + " TEXT,"
            + SingleChild.COLUMN_USER + " TEXT,"
            + SingleChild.COLUMN_SC1 + " TEXT,"
            + SingleChild.COLUMN_SC2 + " TEXT,"
            + SingleChild.COLUMN_SC3 + " TEXT,"
            + SingleChild.COLUMN_SC4 + " TEXT,"
            + SingleChild.COLUMN_SC5 + " TEXT,"
            + SingleChild.COLUMN_SC6 + " TEXT,"
            + SingleChild.COLUMN_SL + " TEXT,"
            + SingleChild.COLUMN_SM + " TEXT,"
            + SingleChild.COLUMN_SK1 + " TEXT,"
            + SingleChild.COLUMN_DEVICETAGID + " TEXT,"
            + SingleChild.COLUMN_SYNCED + " TEXT,"
            + SingleChild.COLUMN_SYNCED_DATE + " TEXT );";

    public static final String SQL_CREATE_FAMILY_MEMBERS = "CREATE TABLE " + FamilyMembersContract.SingleMember.TABLE_NAME + "("
            + FamilyMembersContract.SingleMember.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FamilyMembersContract.SingleMember.COLUMN_UID + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_UUID + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_LUID + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_KISH_SELECTED + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_CLUSTERNO + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_HHNO + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_SERIAL_NO + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_NAME + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_RELATION_HH + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_AGE + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_MOTHER_NAME + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_MOTHER_SERIAL + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_GENDER + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_MARITAL + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_SD + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_SYNCED + " TEXT," +
            FamilyMembersContract.SingleMember.COLUMN_SYNCED_DATE + " TEXT"
            + ");";


    public static final String SQL_CREATE_VISION = "CREATE TABLE " + VisionContract.visionTable.TABLE_NAME + "("
            + VisionContract.visionTable.COLUMN__ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VisionContract.visionTable.COLUMN_UID + " TEXT,"
            + VisionContract.visionTable.COLUMN__UUID + " TEXT,"
            + VisionContract.visionTable.COLUMN_DEVICEID + " TEXT,"
            + VisionContract.visionTable.COLUMN_FORMDATE + " TEXT,"
            + VisionContract.visionTable.COLUMN_USER + " TEXT,"
            + VisionContract.visionTable.COLUMN_SE2 + " TEXT,"
            + VisionContract.visionTable.COLUMN_DEVICETAGID + " TEXT,"
            + VisionContract.visionTable.COLUMN_SYNCED + " TEXT,"
            + VisionContract.visionTable.COLUMN_SYNCED_DATE + " TEXT );";

}
