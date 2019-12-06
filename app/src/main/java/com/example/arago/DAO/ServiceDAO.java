//package com.example.arago.DAO;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//import com.example.arago.DATABASE.DBHelper;
//import com.example.arago.Model.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ServiceDAO {
//    private SQLiteDatabase db;
//    private DBHelper dbHelper;
//
//    public static final String TABLE_NAME = "Service";
//    public static final String SQL_SERVICE = "CREATE TABLE Service (" +
//            " service_id text primary key," +
//            " service_name text," +
//            " service_img int);";
//    public static final String TAG = "ServiceDAO";
//    public ServiceDAO(Context context) {
//        dbHelper = new DBHelper(context);
//        db = dbHelper. getWritableDatabase();
//    }
//
//    // insert
//    public int inserTheLoai(Service service){
//        ContentValues values = new ContentValues();
//        values.put("service_id",service.getService_id());
//        values.put("service_name",service.getService_name());
//        values.put("service_img",service.getService_images());
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
//    public List<Service> serviceGetAll() {
//        List<Service> servicesList = new ArrayList<>();
//        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
//        c.moveToFirst();
//        try {
//            while (c.isAfterLast() == false) {
//                Service ee = new Service();
//                ee.setService_id(c.getString(0));
//                ee.setService_name(c.getString(1));
//                ee.setService_images(c.getInt(3));
//                servicesList.add(ee);
//                Log.d("//=====", ee.toString());
//                c.moveToNext();
//            }
//            c.close();
//        } catch (Exception e){
//            Log.d(TAG, e.toString());
//        }
//        return servicesList;
//    }
//
//    // update
//    public int serviceUpdate(Service service){
//        ContentValues values = new ContentValues();
//        values.put("service_id", service.getService_id());
//        values.put("service_name", service.getService_name());
//        values.put("service_img", service.getService_images());
//        int result = db.update(TABLE_NAME, values, "service_id=?", new String[]{service.getService_id()});
//        if (result == 0){
//            return -1;
//        }
//        return 1;
//    }
//
//    // delete
//    public int serviceDeleteByID(String service){
//        int result = db.delete(TABLE_NAME,"service_id=?", new String[]{service});
//        if (result == 0)
//            return -1;
//        return 1;
//    }
//}
