package edu.aku.hassannaqvi.ctsam.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.ctsam.contracts.AreasContract;
import edu.aku.hassannaqvi.ctsam.contracts.AreasContract.singleAreas;
import edu.aku.hassannaqvi.ctsam.contracts.BLRandomContract;
import edu.aku.hassannaqvi.ctsam.contracts.BLRandomContract.SingleRandomHH;
import edu.aku.hassannaqvi.ctsam.contracts.ChildContract;
import edu.aku.hassannaqvi.ctsam.contracts.ChildContract.SingleChild;
import edu.aku.hassannaqvi.ctsam.contracts.EnumBlockContract;
import edu.aku.hassannaqvi.ctsam.contracts.EnumBlockContract.EnumBlockTable;
import edu.aku.hassannaqvi.ctsam.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.ctsam.contracts.FamilyMembersContract.SingleMember;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract;
import edu.aku.hassannaqvi.ctsam.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.ctsam.contracts.HealthFacilitiesContract;
import edu.aku.hassannaqvi.ctsam.contracts.TalukasContract;
import edu.aku.hassannaqvi.ctsam.contracts.UCsContract;
import edu.aku.hassannaqvi.ctsam.contracts.UsersContract;
import edu.aku.hassannaqvi.ctsam.contracts.UsersContract.SingleUser;
import edu.aku.hassannaqvi.ctsam.contracts.VersionAppContract;
import edu.aku.hassannaqvi.ctsam.contracts.VillagesContract;
import edu.aku.hassannaqvi.ctsam.contracts.VillagesContract.SingleVillage;
import edu.aku.hassannaqvi.ctsam.contracts.VisionContract;

import static edu.aku.hassannaqvi.ctsam.utils.CreateTable.DATABASE_NAME;
import static edu.aku.hassannaqvi.ctsam.utils.CreateTable.DATABASE_VERSION;
import static edu.aku.hassannaqvi.ctsam.utils.CreateTable.SQL_CREATE_BL_RANDOM;
import static edu.aku.hassannaqvi.ctsam.utils.CreateTable.SQL_CREATE_CHILD_TABLE;
import static edu.aku.hassannaqvi.ctsam.utils.CreateTable.SQL_CREATE_FAMILY_MEMBERS;
import static edu.aku.hassannaqvi.ctsam.utils.CreateTable.SQL_CREATE_FORMS;
import static edu.aku.hassannaqvi.ctsam.utils.CreateTable.SQL_CREATE_HEALTH_FACILITIES;
import static edu.aku.hassannaqvi.ctsam.utils.CreateTable.SQL_CREATE_PSU_TABLE;
import static edu.aku.hassannaqvi.ctsam.utils.CreateTable.SQL_CREATE_TALUKAS;
import static edu.aku.hassannaqvi.ctsam.utils.CreateTable.SQL_CREATE_USERS;
import static edu.aku.hassannaqvi.ctsam.utils.CreateTable.SQL_CREATE_VERSIONAPP;
import static edu.aku.hassannaqvi.ctsam.utils.CreateTable.SQL_CREATE_VILLAGES;
import static edu.aku.hassannaqvi.ctsam.utils.CreateTable.SQL_CREATE_VISION;


/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String SQL_DELETE_VILLAGES = "DROP TABLE IF EXISTS " + SingleVillage.TABLE_NAME;
    private static final String SQL_DELETE_TALUKAS = "DROP TABLE IF EXISTS " + TalukasContract.SingleTalukas.TABLE_NAME;
    private static final String SQL_DELETE_UCS = "DROP TABLE IF EXISTS " + UCsContract.singleUCs.TABLE_NAME;
    private static final String SQL_DELETE_AREAS = "DROP TABLE IF EXISTS " + singleAreas.TABLE_NAME;

    private final String TAG = "DatabaseHelper";

    public String spDateT = new SimpleDateFormat("dd-MM-yy").format(new Date().getTime());

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_FORMS);
        db.execSQL(SQL_CREATE_PSU_TABLE);
        db.execSQL(SQL_CREATE_BL_RANDOM);
        db.execSQL(SQL_CREATE_VERSIONAPP);
        db.execSQL(SQL_CREATE_TALUKAS);
        db.execSQL(SQL_CREATE_HEALTH_FACILITIES);
        db.execSQL(SQL_CREATE_VILLAGES);
        db.execSQL(SQL_CREATE_FAMILY_MEMBERS);
        db.execSQL(SQL_CREATE_CHILD_TABLE);
        db.execSQL(SQL_CREATE_VISION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL(SQL_DELETE_USERS);
//        db.execSQL(SQL_DELETE_FORMS);
//        db.execSQL("DROP TABLE IF EXISTS " + LHWContract.lhwEntry.TABLE_NAME);
//        db.execSQL(SQL_DELETE_CHILDREN);
//        db.execSQL(SQL_DELETE_CHILDLIST);
//        db.execSQL(SQL_DELETE_VILLAGES);
//        db.execSQL(SQL_DELETE_TALUKAS);
//        db.execSQL(SQL_DELETE_UCS);
//        db.execSQL(SQL_DELETE_AREAS);


    }

    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    public void syncEnumBlocks(JSONArray Enumlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EnumBlockContract.EnumBlockTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Enumlist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                EnumBlockContract Vc = new EnumBlockContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(EnumBlockContract.EnumBlockTable.COLUMN_DIST_ID, Vc.getDsit_code());
                values.put(EnumBlockContract.EnumBlockTable.COLUMN_GEO_AREA, Vc.getGeoarea());
                values.put(EnumBlockContract.EnumBlockTable.COLUMN_CLUSTER_AREA, Vc.getCluster());

                db.insert(EnumBlockContract.EnumBlockTable.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncTalukas(JSONArray Talukaslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TalukasContract.SingleTalukas.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Talukaslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                TalukasContract Vc = new TalukasContract();
                Vc.sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(TalukasContract.SingleTalukas.COLUMN_TALUKA_CODE, Vc.getTalukacode());
                values.put(TalukasContract.SingleTalukas.COLUMN_TALUKA, Vc.getTaluka());

                db.insert(TalukasContract.SingleTalukas.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }


    public void syncHealthFacilities(JSONArray HealthFacilitylist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(HealthFacilitiesContract.SingleHealthFacilities.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = HealthFacilitylist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                HealthFacilitiesContract Vc = new HealthFacilitiesContract();
                Vc.sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(HealthFacilitiesContract.SingleHealthFacilities.COLUMN_FACILITY_CODE, Vc.getFacilityCode());
                values.put(HealthFacilitiesContract.SingleHealthFacilities.COLUMN_FACILITY_NAME, Vc.getFacilityName());
                values.put(HealthFacilitiesContract.SingleHealthFacilities.COLUMN_TALUKA_CODE, Vc.getTalukaCode());

                db.insert(HealthFacilitiesContract.SingleHealthFacilities.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncBLRandom(JSONArray BLlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SingleRandomHH.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = BLlist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                BLRandomContract Vc = new BLRandomContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(SingleRandomHH.COLUMN_ID, Vc.get_ID());
                values.put(SingleRandomHH.COLUMN_LUID, Vc.getLUID());
                values.put(SingleRandomHH.COLUMN_STRUCTURE_NO, Vc.getStructure());
                values.put(SingleRandomHH.COLUMN_FAMILY_EXT_CODE, Vc.getExtension());
                values.put(SingleRandomHH.COLUMN_HH, Vc.getHh());
                values.put(SingleRandomHH.COLUMN_ENUM_BLOCK_CODE, Vc.getSubVillageCode());
                values.put(SingleRandomHH.COLUMN_RANDOMDT, Vc.getRandomDT());
                values.put(SingleRandomHH.COLUMN_HH_HEAD, Vc.getHhhead());
                values.put(SingleRandomHH.COLUMN_CONTACT, Vc.getContact());
                values.put(SingleRandomHH.COLUMN_HH_SELECTED_STRUCT, Vc.getSelStructure());
                values.put(SingleRandomHH.COLUMN_SNO_HH, Vc.getSno());

                db.insert(SingleRandomHH.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncUCs(JSONArray UCslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UCsContract.singleUCs.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = UCslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                UCsContract Vc = new UCsContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(UCsContract.singleUCs.COLUMN_UCCODE, Vc.getUccode());
                values.put(UCsContract.singleUCs.COLUMN_UCS, Vc.getUcs());
                values.put(UCsContract.singleUCs.COLUMN_TALUKA_CODE, Vc.getTaluka_code());

                db.insert(UCsContract.singleUCs.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncAreas(JSONArray Areaslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(singleAreas.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Areaslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                AreasContract Vc = new AreasContract();
                Vc.Sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(singleAreas.COLUMN_AREACODE, Vc.getAreacode());
                values.put(singleAreas.COLUMN_AREA, Vc.getArea());
                values.put(singleAreas.COLUMN_UC_CODE, Vc.getUc_code());

                db.insert(singleAreas.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }


    public void syncVillages(JSONArray Villageslist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VillagesContract.SingleVillage.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Villageslist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectCC = jsonArray.getJSONObject(i);

                VillagesContract Vc = new VillagesContract();
                Vc.sync(jsonObjectCC);

                ContentValues values = new ContentValues();

                values.put(VillagesContract.SingleVillage.COLUMN_VILLAGE_CODE, Vc.getVillagecode());
                values.put(VillagesContract.SingleVillage.COLUMN_VILLAGE_NAME, Vc.getVillagename());
                values.put(SingleVillage.COLUMN_HF_CODE, Vc.getHFCode());

                db.insert(VillagesContract.SingleVillage.TABLE_NAME, null, values);
            }
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    //Get BLRandom data
    public BLRandomContract getHHFromBLRandom(String subAreaCode, String hh) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                SingleRandomHH.COLUMN_ID,
                SingleRandomHH.COLUMN_LUID,
                SingleRandomHH.COLUMN_STRUCTURE_NO,
                SingleRandomHH.COLUMN_FAMILY_EXT_CODE,
                SingleRandomHH.COLUMN_HH,
                SingleRandomHH.COLUMN_ENUM_BLOCK_CODE,
                SingleRandomHH.COLUMN_RANDOMDT,
                SingleRandomHH.COLUMN_HH_SELECTED_STRUCT,
                SingleRandomHH.COLUMN_CONTACT,
                SingleRandomHH.COLUMN_HH_HEAD,
                SingleRandomHH.COLUMN_SNO_HH
        };

        String whereClause = SingleRandomHH.COLUMN_ENUM_BLOCK_CODE + "=? AND " + SingleRandomHH.COLUMN_HH + "=?";
        String[] whereArgs = new String[]{subAreaCode, hh};
        String groupBy = null;
        String having = null;

        String orderBy =
                SingleRandomHH.COLUMN_ID + " ASC";

        BLRandomContract allBL = null;
        try {
            c = db.query(
                    SingleRandomHH.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allBL = new BLRandomContract().hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allBL;
    }

    public Collection<TalukasContract> getAllTalukas() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                TalukasContract.SingleTalukas.COLUMN_TALUKA_CODE,
                TalukasContract.SingleTalukas.COLUMN_TALUKA
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                TalukasContract.SingleTalukas.COLUMN_TALUKA + " ASC";

        Collection<TalukasContract> allDC = new ArrayList<>();
        try {
            c = db.query(
                    TalukasContract.SingleTalukas.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                TalukasContract dc = new TalukasContract();
                allDC.add(dc.hydrateTalukas(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }

    public Collection<UCsContract> getAllUCs(String talukaCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                UCsContract.singleUCs.COLUMN_UCCODE,
                UCsContract.singleUCs.COLUMN_UCS,
                UCsContract.singleUCs.COLUMN_TALUKA_CODE
        };

        String whereClause = UCsContract.singleUCs.COLUMN_TALUKA_CODE + "=?";
        String[] whereArgs = new String[]{talukaCode};
        String groupBy = null;
        String having = null;

        String orderBy =
                UCsContract.singleUCs.COLUMN_UCS + " ASC";

        Collection<UCsContract> allDC = new ArrayList<>();
        try {
            c = db.query(
                    UCsContract.singleUCs.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                UCsContract dc = new UCsContract();
                allDC.add(dc.HydrateUCs(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allDC;
    }

    public Collection<AreasContract> getAllAreas(int UCCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                singleAreas.COLUMN_AREACODE,
                singleAreas.COLUMN_AREA,
                singleAreas.COLUMN_UC_CODE
        };

        String whereClause = singleAreas.COLUMN_UC_CODE + "=?";
        String[] whereArgs = new String[]{String.valueOf(UCCode)};
        String groupBy = null;
        String having = null;

        String orderBy =
                singleAreas.COLUMN_AREA + " ASC";

        Collection<AreasContract> allAC = new ArrayList<>();
        try {
            c = db.query(
                    singleAreas.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                AreasContract dc = new AreasContract();
                allAC.add(dc.HydrateUCs(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allAC;
    }

    public Collection<VillagesContract> getVillages(String hf_code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                SingleVillage.COLUMN_HF_CODE,
                SingleVillage.COLUMN_VILLAGE_CODE,
                SingleVillage.COLUMN_VILLAGE_NAME,
        };

        String whereClause = SingleVillage.COLUMN_HF_CODE + "=?";
        String[] whereArgs = new String[]{String.valueOf(hf_code)};
        String groupBy = null;
        String having = null;

        String orderBy =
                SingleVillage.COLUMN_VILLAGE_NAME + " ASC";

        Collection<VillagesContract> allAC = new ArrayList<>();
        try {
            c = db.query(
                    SingleVillage.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                VillagesContract dc = new VillagesContract();
                allAC.add(dc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allAC;
    }

    public List<String> getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {SingleUser.ROW_USERNAME};

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        List<String> allAC = new ArrayList<>();
        allAC.add("....");
        try {
            c = db.query(
                    SingleUser.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allAC.add(c.getString(c.getColumnIndex(SingleUser.ROW_USERNAME)));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allAC;
    }

    public void syncVersionApp(JSONArray Versionlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VersionAppContract.VersionAppTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = Versionlist;
            JSONObject jsonObjectCC = jsonArray.getJSONObject(0);

            VersionAppContract Vc = new VersionAppContract();
            Vc.Sync(jsonObjectCC);

            ContentValues values = new ContentValues();

            values.put(VersionAppContract.VersionAppTable.COLUMN_PATH_NAME, Vc.getPathname());
            values.put(VersionAppContract.VersionAppTable.COLUMN_VERSION_CODE, Vc.getVersioncode());
            values.put(VersionAppContract.VersionAppTable.COLUMN_VERSION_NAME, Vc.getVersionname());

            db.insert(VersionAppContract.VersionAppTable.TABLE_NAME, null, values);
        } catch (Exception e) {
        } finally {
            db.close();
        }
    }

    public void syncUser(JSONArray userlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersContract.SingleUser.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                UsersContract user = new UsersContract();
                user.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UsersContract.SingleUser.ROW_USERNAME, user.getUserName());
                values.put(UsersContract.SingleUser.ROW_PASSWORD, user.getPassword());
                values.put(UsersContract.SingleUser.DIST_ID, user.getDIST_ID());
//                values.put(SingleUser.REGION_DSS, user.getREGION_DSS());
                db.insert(UsersContract.SingleUser.TABLE_NAME, null, values);
            }


        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
        } finally {
            db.close();
        }
    }

    public boolean Login(String username, String password) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + UsersContract.SingleUser.TABLE_NAME + " WHERE " + UsersContract.SingleUser.ROW_USERNAME + "=? AND " + UsersContract.SingleUser.ROW_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {

            if (mCursor.getCount() > 0) {

                if (mCursor.moveToFirst()) {
                    MainApp.DIST_ID = mCursor.getString(mCursor.getColumnIndex(UsersContract.SingleUser.DIST_ID));
                }
                return true;
            }
        }
        return false;
    }

    public Long addForm(FormsContract fc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_PROJECT_NAME, fc.getProjectName());
        values.put(FormsTable.COLUMN_UID, fc.get_UID());
        values.put(FormsTable.COLUMN_FORMDATE, fc.getFormDate());
        values.put(FormsTable.COLUMN_LUID, fc.getLuid());
        values.put(FormsTable.COLUMN_USER, fc.getUser());
        values.put(FormsTable.COLUMN_ISTATUS, fc.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88x, fc.getIstatus88x());
        values.put(FormsTable.COLUMN_ENDINGDATETIME, fc.getEndingdatetime());
        values.put(FormsTable.COLUMN_SA, fc.getsA());
        values.put(FormsTable.COLUMN_SB, fc.getsB());
        values.put(FormsTable.COLUMN_SC, fc.getsC());
        values.put(FormsTable.COLUMN_SD, fc.getsD());
        values.put(FormsTable.COLUMN_SE, fc.getsE());
        values.put(FormsTable.COLUMN_SF, fc.getsF());
        values.put(FormsTable.COLUMN_SG, fc.getsG());
        values.put(FormsTable.COLUMN_SH, fc.getsH());
        values.put(FormsTable.COLUMN_SI, fc.getsI());
        values.put(FormsTable.COLUMN_SJ, fc.getsJ());
        values.put(FormsTable.COLUMN_GPSLAT, fc.getGpsLat());
        values.put(FormsTable.COLUMN_GPSLNG, fc.getGpsLng());
        values.put(FormsTable.COLUMN_GPSDATE, fc.getGpsDT());
        values.put(FormsTable.COLUMN_GPSACC, fc.getGpsAcc());
        values.put(FormsTable.COLUMN_DEVICETAGID, fc.getDevicetagID());
        values.put(FormsTable.COLUMN_DEVICEID, fc.getDeviceID());
        values.put(FormsTable.COLUMN_APPVERSION, fc.getAppversion());
        values.put(FormsTable.COLUMN_CLUSTERCODE, fc.getClusterCode());
        values.put(FormsTable.COLUMN_HHNO, fc.getHhno());
        values.put(FormsTable.COLUMN_STUDYID, fc.getStudyId());
        values.put(FormsTable.COLUMN_HF_CODE, fc.getHfCode());
        values.put(FormsTable.COLUMN_FORMTYPE, fc.getFormType());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsTable.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public Long addFamilyMember(FamilyMembersContract fmc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SingleMember.COLUMN_ID, fmc.get_id());
        values.put(SingleMember.COLUMN_UID, fmc.getUid());
        values.put(SingleMember.COLUMN_UUID, fmc.getUuid());
        values.put(SingleMember.COLUMN_LUID, fmc.getLuid());
        values.put(SingleMember.COLUMN_KISH_SELECTED, fmc.getKishSelected());
        values.put(SingleMember.COLUMN_CLUSTERNO, fmc.getClusterno());
        values.put(SingleMember.COLUMN_HHNO, fmc.getHhno());
        values.put(SingleMember.COLUMN_SERIAL_NO, fmc.getSerialno());
        values.put(SingleMember.COLUMN_NAME, fmc.getName());
        values.put(SingleMember.COLUMN_RELATION_HH, fmc.getRelHH());
        values.put(SingleMember.COLUMN_AGE, fmc.getAge());
        values.put(SingleMember.COLUMN_MOTHER_NAME, fmc.getMother_name());
        values.put(SingleMember.COLUMN_MOTHER_SERIAL, fmc.getMother_serial());
        values.put(SingleMember.COLUMN_GENDER, fmc.getGender());
        values.put(SingleMember.COLUMN_MARITAL, fmc.getMarital());
        values.put(SingleMember.COLUMN_SD, fmc.getsD());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                SingleMember.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public Long addVision(VisionContract vc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
//        values.put(MWRATable._ID, indexMwra.get_ID());
        values.put(VisionContract.visionTable.COLUMN__UUID, vc.get_UUID());
        values.put(VisionContract.visionTable.COLUMN_DEVICEID, vc.getDeviceId());
        values.put(VisionContract.visionTable.COLUMN_FORMDATE, vc.getFormDate());
        values.put(VisionContract.visionTable.COLUMN_USER, vc.getUser());
        values.put(VisionContract.visionTable.COLUMN_DEVICETAGID, vc.getDevicetagID());
        values.put(VisionContract.visionTable.COLUMN_SE2, vc.getsE2());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                VisionContract.visionTable.TABLE_NAME,
                null,
                values);
        return newRowId;
    }

    public FormsContract isDataExists(String studyId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = null;

// New value for one column
        String[] columns = {
                FormsTable.COLUMN_LUID,
                FormsTable.COLUMN_ISTATUS,

        };

// Which row to update, based on the ID
        String selection = FormsTable.COLUMN_LUID + " = ? AND "
                + FormsTable.COLUMN_ISTATUS + " = ?";
        String[] selectionArgs = new String[]{studyId, "1"};

        FormsContract allFC = new FormsContract();
        try {
            c = db.query(FormsTable.TABLE_NAME, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                       //filter by row groups
                    null);                   // The sort order

            while (c.moveToNext()) {
                allFC.setLuid(c.getString(c.getColumnIndex(FormsTable.COLUMN_LUID)));
                allFC.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;


    }

    public int updateEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_ISTATUS, MainApp.fc.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88x, MainApp.fc.getIstatus88x());
        values.put(FormsTable.COLUMN_ENDINGDATETIME, MainApp.fc.getEndingdatetime());

        String selection = FormsTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    //Get FamilyMembers data for info activity
    public FamilyMembersContract getFamilyMember(String cluster, String hhno, String kishType, FamilyMembersContract mother) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                SingleMember.COLUMN_ID,
                SingleMember.COLUMN_UID,
                SingleMember.COLUMN_UUID,
                SingleMember.COLUMN_LUID,
                SingleMember.COLUMN_KISH_SELECTED,
                SingleMember.COLUMN_CLUSTERNO,
                SingleMember.COLUMN_HHNO,
                SingleMember.COLUMN_SERIAL_NO,
                SingleMember.COLUMN_NAME,
                SingleMember.COLUMN_RELATION_HH,
                SingleMember.COLUMN_AGE,
                SingleMember.COLUMN_MOTHER_NAME,
                SingleMember.COLUMN_MOTHER_SERIAL,
                SingleMember.COLUMN_GENDER,
                SingleMember.COLUMN_MARITAL,
                SingleMember.COLUMN_SD,

        };

        String whereClause;
        String[] whereArgs;
        if (mother != null) {
            whereClause = SingleMember.COLUMN_CLUSTERNO + "=? AND " + SingleMember.COLUMN_HHNO + "=? AND "
                    + SingleMember.COLUMN_KISH_SELECTED + "=? AND "
                    + SingleMember.COLUMN_MOTHER_SERIAL + "=? AND " + SingleMember.COLUMN_UUID + "=? AND " + SingleMember.COLUMN_MOTHER_NAME + "=?";
            whereArgs = new String[]{cluster, hhno, kishType, mother.getSerialno(), mother.getUuid(), mother.getName()};
        } else {
            whereClause = SingleMember.COLUMN_CLUSTERNO + "=? AND " + SingleMember.COLUMN_HHNO + "=? AND "
                    + SingleMember.COLUMN_KISH_SELECTED + "=? ";
            whereArgs = new String[]{cluster, hhno, kishType};
        }
        String groupBy = null;
        String having = null;

        String orderBy = SingleMember.COLUMN_ID + " ASC";

        FamilyMembersContract allBL = null;
        try {
            c = db.query(
                    SingleMember.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allBL = new FamilyMembersContract().hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allBL;
    }

    public ArrayList<FamilyMembersContract> getFamilyMemberList(String cluster, String hhno, FamilyMembersContract mother) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                SingleMember.COLUMN_ID,
                SingleMember.COLUMN_UID,
                SingleMember.COLUMN_LUID,
                SingleMember.COLUMN_UUID,
                SingleMember.COLUMN_KISH_SELECTED,
                SingleMember.COLUMN_CLUSTERNO,
                SingleMember.COLUMN_HHNO,
                SingleMember.COLUMN_SERIAL_NO,
                SingleMember.COLUMN_NAME,
                SingleMember.COLUMN_RELATION_HH,
                SingleMember.COLUMN_AGE,
                SingleMember.COLUMN_MOTHER_NAME,
                SingleMember.COLUMN_MOTHER_SERIAL,
                SingleMember.COLUMN_GENDER,
                SingleMember.COLUMN_MARITAL,
                SingleMember.COLUMN_SD,

        };

        String whereClause = SingleMember.COLUMN_CLUSTERNO + "=? AND " + SingleMember.COLUMN_HHNO + "=? AND "
                + SingleMember.COLUMN_MOTHER_SERIAL + "=? AND " + SingleMember.COLUMN_UUID + "=? AND " + SingleMember.COLUMN_MOTHER_NAME + "=? AND (" + SingleMember.COLUMN_AGE + "  IN (6,7,8,9))";
        String[] whereArgs = {cluster, hhno, mother.getSerialno(), mother.getUuid(), mother.getName()};
        String groupBy = null;
        String having = null;

        String orderBy = SingleMember.COLUMN_ID + " ASC";

        ArrayList<FamilyMembersContract> allBL = new ArrayList<>();
        try {
            c = db.query(
                    SingleMember.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allBL.add(new FamilyMembersContract().hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allBL;
    }

    //Get EnumBlock
    public EnumBlockContract getEnumBlock(String cluster) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                EnumBlockTable._ID,
                EnumBlockTable.COLUMN_DIST_ID,
                EnumBlockTable.COLUMN_GEO_AREA,
                EnumBlockTable.COLUMN_CLUSTER_AREA
        };

        String whereClause = EnumBlockTable.COLUMN_CLUSTER_AREA + " =?";
        String[] whereArgs = new String[]{cluster};
        String groupBy = null;
        String having = null;

        String orderBy = EnumBlockTable._ID + " ASC";
        EnumBlockContract allEB = null;
        try {
            c = db.query(
                    EnumBlockTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB = new EnumBlockContract().HydrateEnum(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allEB;
    }

    public VersionAppContract getVersionApp() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VersionAppContract.VersionAppTable._ID,
                VersionAppContract.VersionAppTable.COLUMN_VERSION_CODE,
                VersionAppContract.VersionAppTable.COLUMN_VERSION_NAME,
                VersionAppContract.VersionAppTable.COLUMN_PATH_NAME
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = null;

        VersionAppContract allVC = new VersionAppContract();
        try {
            c = db.query(
                    VersionAppContract.VersionAppTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allVC.hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allVC;
    }

    //UPDATE FUNCTIONS

    //Generic update FormColumn
    public int updatesFormColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        return db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //Generic update FormColumn
    public int updatesFormColumn2(String column, String value, String column2, String value2) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);
        values.put(column2, value2);

        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        return db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //Generic update FamilyMemberColumn
    public int updatesFamilyMemberColumn(String column, String value, String valueID) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = SingleMember._ID + " =? ";
        String[] selectionArgs = {String.valueOf(valueID)};

        return db.update(SingleMember.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public int updatesFamilyMemberColumn(String column, String value, FamilyMembersContract fmc) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = SingleMember.COLUMN_CLUSTERNO + " =? AND "
                + SingleMember.COLUMN_HHNO + " =? AND "
                + SingleMember.COLUMN_SERIAL_NO + " =? AND "
                + SingleMember.COLUMN_UID + " =? AND "
                + SingleMember.COLUMN_UUID + " =?";
        String[] selectionArgs = {fmc.getClusterno(), fmc.getHhno(), fmc.getSerialno(), fmc.getUid(), fmc.getUuid()};

        return db.update(SingleMember.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //Generic update VisionColumn
    public int updatesVisionColumn(String column, String value, VisionContract vc) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = VisionContract.visionTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(vc.get_ID())};

        return db.update(VisionContract.visionTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //Generic update ChildColumn
    public int updatesChildColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = SingleChild._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.child.get_ID())};

        return db.update(SingleChild.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    //SYNCING FUNCTIONS
    public Collection<FamilyMembersContract> getAllFamilyMembersForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                SingleMember.COLUMN_ID,
                SingleMember.COLUMN_UID,
                SingleMember.COLUMN_UUID,
                SingleMember.COLUMN_LUID,
                SingleMember.COLUMN_KISH_SELECTED,
                SingleMember.COLUMN_CLUSTERNO,
                SingleMember.COLUMN_HHNO,
                SingleMember.COLUMN_SERIAL_NO,
                SingleMember.COLUMN_NAME,
                SingleMember.COLUMN_RELATION_HH,
                SingleMember.COLUMN_AGE,
                SingleMember.COLUMN_MOTHER_NAME,
                SingleMember.COLUMN_MOTHER_SERIAL,
                SingleMember.COLUMN_GENDER,
                SingleMember.COLUMN_MARITAL,
                SingleMember.COLUMN_SD,
        };
        String whereClause = SingleMember.COLUMN_SYNCED + " is null";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<FamilyMembersContract> allFC = new ArrayList<>();
        try {
            c = db.query(
                    SingleMember.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FamilyMembersContract fc = new FamilyMembersContract();
                allFC.add(fc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<FormsContract> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88x,
                FormsTable.COLUMN_LUID,
                FormsTable.COLUMN_ENDINGDATETIME,
                FormsTable.COLUMN_SA,
                FormsTable.COLUMN_SB,
                FormsTable.COLUMN_SC,
                FormsTable.COLUMN_SD,
                FormsTable.COLUMN_SE,
                FormsTable.COLUMN_SF,
                FormsTable.COLUMN_SG,
                FormsTable.COLUMN_SH,
                FormsTable.COLUMN_SI,
                FormsTable.COLUMN_SJ,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION,
                FormsTable.COLUMN_CLUSTERCODE,
                FormsTable.COLUMN_HHNO,
                FormsTable.COLUMN_STUDYID,
                FormsTable.COLUMN_HF_CODE,
                FormsTable.COLUMN_FORMTYPE
        };


        String whereClause = FormsTable.COLUMN_SYNCED + " is null";

        String[] whereArgs = null;

        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<ChildContract> getUnsyncedChildForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                SingleChild._ID,
                SingleChild.COLUMN_UID,
                SingleChild.COLUMN__UUID,
                SingleChild.COLUMN_DEVICEID,
                SingleChild.COLUMN_FORMDATE,
                SingleChild.COLUMN_USER,
                SingleChild.COLUMN_SC1,
                SingleChild.COLUMN_SC2,
                SingleChild.COLUMN_SC3,
                SingleChild.COLUMN_SC4,
                SingleChild.COLUMN_SC5,
                SingleChild.COLUMN_SC6,
                SingleChild.COLUMN_SL,
                SingleChild.COLUMN_SM,
                SingleChild.COLUMN_SK1,
                SingleChild.COLUMN_DEVICETAGID,

        };


        String whereClause = SingleChild.COLUMN_SYNCED + " is null";

        String[] whereArgs = null;

        String groupBy = null;
        String having = null;

        String orderBy =
                SingleChild._ID + " ASC";

        Collection<ChildContract> allFC = new ArrayList<ChildContract>();
        try {
            c = db.query(
                    SingleChild.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                ChildContract fc = new ChildContract();
                allFC.add(fc.hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<FormsContract> getTodayForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_LUID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,

        };
        String whereClause = FormsTable.COLUMN_FORMDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                fc.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                fc.setLuid(c.getString(c.getColumnIndex(FormsTable.COLUMN_LUID)));
                fc.setFormDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMDATE)));
                fc.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                fc.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allFC.add(fc);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public void updateSyncedForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedChildForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(SingleChild.COLUMN_SYNCED, true);
        values.put(SingleChild.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = SingleChild._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                SingleChild.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedVCForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(VisionContract.visionTable.COLUMN_SYNCED, true);
        values.put(VisionContract.visionTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = VisionContract.visionTable._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                VisionContract.visionTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedFamilyMemForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(SingleMember.COLUMN_SYNCED, true);
        values.put(SingleMember.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = SingleMember._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                SingleMember.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    //Get All Talukas
    public List<TalukasContract> getTalukas() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                TalukasContract.SingleTalukas.COLUMN_TALUKA_CODE,
                TalukasContract.SingleTalukas.COLUMN_TALUKA
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = TalukasContract.SingleTalukas.COLUMN_TALUKA_CODE + " ASC";
        List<TalukasContract> allEB = new ArrayList<>();
        try {
            c = db.query(
                    TalukasContract.SingleTalukas.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new TalukasContract().hydrateTalukas(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allEB;
    }


    //Get All Health Facilities
    public List<HealthFacilitiesContract> getHealthFacilities(String talukaCode) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                HealthFacilitiesContract.SingleHealthFacilities._ID,
                HealthFacilitiesContract.SingleHealthFacilities.COLUMN_FACILITY_CODE,
                HealthFacilitiesContract.SingleHealthFacilities.COLUMN_FACILITY_NAME,
                HealthFacilitiesContract.SingleHealthFacilities.COLUMN_TALUKA_CODE,
        };

        String whereClause = HealthFacilitiesContract.SingleHealthFacilities.COLUMN_TALUKA_CODE + " =?";
        String[] whereArgs = {talukaCode};
        String groupBy = null;
        String having = null;

        String orderBy = null;
        List<HealthFacilitiesContract> allEB = new ArrayList<>();
        try {
            c = db.query(
                    HealthFacilitiesContract.SingleHealthFacilities.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new HealthFacilitiesContract().hydrateHealthFacilities(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allEB;
    }


    public Cursor getHfCode(String tableName, String hf_name) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + tableName + " where hf_name = '" + hf_name + "'", null);
        return res;
    }

    public Cursor getStudyID(String tableName, String study_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + tableName + " where studyId = '" + study_id + "'", null);
        return res;
    }
}