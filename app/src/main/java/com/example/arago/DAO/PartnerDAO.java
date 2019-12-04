//package com.example.arago.DAO;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//import com.example.arago.DATABASE.DBHelper;
//import com.example.arago.USER.Model.Partner;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PartnerDAO {
//    private SQLiteDatabase db;
//    private DBHelper dbHelper;
//
//    public static final String TABLE_NAME = "Partner";
//    public static final String SQL_PARTNER = "CREATE TABLE Partner (" +
//            " partner_id integer primary key AUTOINCREMENT," +
//            " partner_name text," +
//            " partner_email text," +
//            " partner_password text," +
//            " partner_address text," +
//            " partner_phone text," +
//            " partner_birthday date," +
//            " partner_sex text," +
//            " partner_cmnd text," +
//            " partner_img int);";
//    public static final String TAG = "PartnerDAO";
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    public PartnerDAO(Context context) {
//        dbHelper = new DBHelper(context);
//        db = dbHelper. getWritableDatabase();
//    }
//
//    // insert
//    public int partnerInsert(Partner partner){
//        ContentValues values = new ContentValues();
//        values.put("partner_name",partner.getPartner_name());
//        values.put("partner_img",partner.getPartner_images());
//        values.put("partner_email",partner.getPartner_email());
//        values.put("partner_password",partner.getPartner_pass());
//        values.put("partner_address",partner.getPartner_address());
//        values.put("partner_phone",partner.getPartner_phone());
//        values.put("partner_birthday",sdf.format(partner.getPartner_birthday()));
//        values.put("partner_sex",partner.getPartner_sex());
//        values.put("partner_cmnd",partner.getPartner_cmnd());
//        try {
//            if (db.insert(TABLE_NAME,null,values)== -1){
//                return -1;
//            }
//        }catch (Exception ex){
//            Log.e(TAG,ex.toString());
//        }
//        return 1;
//    }
//
//    // getall
//    public List<Partner> partnerGetAll() throws ParseException {
//        List<Partner> partnerList = new ArrayList<>();
//        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
//        c.moveToFirst();
//        try {
//            while (c.isAfterLast() == false) {
//                Partner ee = new Partner();
//                ee.setPartner_id(c.getInt(0));
//                ee.setPartner_name(c.getString(1));
//                ee.setPartner_images(c.getInt(3));
//                ee.setPartner_email(c.getString(4));
//                ee.setPartner_pass(c.getString(5));
//                ee.setPartner_address(c.getString(6));
//                ee.setPartner_phone(c.getString(7));
//                ee.setPartner_birthday(sdf.parse(c.getString(8))); // còn lỗi
//                ee.setPartner_images(c.getInt(9));
//                ee.setPartner_cmnd(c.getString(10));
//                partnerList.add(ee);
//                Log.d("//=====", ee.toString());
//                c.moveToNext();
//            }
//            c.close();
//        } catch (Exception e){
//            Log.d(TAG, e.toString());
//        }
//        return partnerList;
//    }
//
//    // update
//    public int partnerUpdate(Partner partner){
//        ContentValues values = new ContentValues();
//        values.put("partner_id",partner.getPartner_id());
//        values.put("partner_name",partner.getPartner_name());
//        values.put("partner_img",partner.getPartner_images());
//        values.put("partner_email",partner.getPartner_email());
//        values.put("partner_password",partner.getPartner_pass());
//        values.put("partner_address",partner.getPartner_address());
//        values.put("partner_phone",partner.getPartner_phone());
//        values.put("partner_birthday",partner.getPartner_birthday().toString());
//        values.put("partner_sex",partner.getPartner_sex());
//        values.put("partner_cmnd",partner.getPartner_cmnd());
//        int result = db.update(TABLE_NAME, values, "partner_id=?", new String[]{String.valueOf(partner.getPartner_id())});
//        if (result == 0){
//            return -1;
//        }
//        return 1;
//    }
//
//    // delete
//    public int partnerDeleteByID(String partner){
//        int result = db.delete(TABLE_NAME,"partner_id=?", new String[]{partner});
//        if (result == 0)
//            return -1;
//        return 1;
//    }
//}
