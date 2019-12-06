//package com.example.arago.DAO;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//import com.example.arago.DATABASE.DBHelper;
//import com.example.arago.Model.Bill;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BillDAO {
//    private SQLiteDatabase db;
//    private DBHelper dbHelper;
//
//    public static final String TABLE_NAME = "Bill";
//    public static final String SQL_BILL = "CREATE TABLE Bill (" +
//            " bill_id integer primary key AUTOINCREMENT," +
//            " bill_price double," +
//            " bill_errortype text," +
//            " bill_status text," +
//            " bill_address text," +
//            " bill_phone text," +
//            " bill_datetime date);";
//    public static final String TAG = "BillDAO";
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    public BillDAO(Context context) {
//        dbHelper = new DBHelper(context);
//        db = dbHelper. getWritableDatabase();
//    }
//
//    // insert
//    public int billInsert(Bill bill){
//        ContentValues values = new ContentValues();
//        values.put("bill_price",bill.getBill_price());
//        values.put("bill_errortype",bill.getBill_errortype());
//        values.put("bill_status",bill.getBill_status());
//        values.put("bill_address",bill.getBill_address());
//        values.put("bill_phone",bill.getBill_phone());
//        values.put("bill_datetime",sdf.format(bill.getBill_datetime()));
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
//    public List<Bill> billGetAll() {
//        List<Bill> billList = new ArrayList<>();
//
//        String sSQL =  "SELECT bill_id, bill_price, bill_status" +
//                "Request.request_errortype, Request.request_address, Request.request_phone, Request.request_datetime";
//        Cursor c = db.rawQuery(sSQL, null);
////        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
//        c.moveToFirst();
//        try {
//            while (c.isAfterLast() == false) {
//                Bill ee = new Bill();
//                ee.setBill_price(c.getDouble(0));
//                ee.setBill_errortype(c.getString(1));
//                ee.setBill_status(c.getString(2));
//                ee.setBill_address(c.getString(3));
//                ee.setBill_phone(c.getString(4));
//                ee.setBill_datetime(sdf.parse(c.getString(5)));
//                billList.add(ee);
//                Log.d("//=====", ee.toString());
//                c.moveToNext();
//            }
//            c.close();
//        } catch (Exception e){
//            Log.d(TAG, e.toString());
//        }
//        return billList;
//    }
//
//    // update
//    public int billUpdate(Bill bill){
//        ContentValues values = new ContentValues();
//        values.put("bill_id",bill.getBill_id());
//        values.put("bill_price",bill.getBill_price());
//        values.put("bill_errortype",bill.getBill_errortype());
//        values.put("bill_status",bill.getBill_status());
//        values.put("bill_address",bill.getBill_address());
//        values.put("bill_phone",bill.getBill_phone());
//        values.put("bill_datetime",bill.getBill_datetime().toString());
//        int result = db.update(TABLE_NAME, values, "bill_id=?", new String[]{String.valueOf(bill.getBill_id())});
//        if (result == 0){
//            return -1;
//        }
//        return 1;
//    }
//
//    // delete
//    public int billDeleteByID(String bill){
//        int result = db.delete(TABLE_NAME,"bill_id=?", new String[]{bill});
//        if (result == 0)
//            return -1;
//        return 1;
//    }
//}
