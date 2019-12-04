//package com.example.arago.DAO;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//import com.example.arago.DATABASE.DBHelper;
//import com.example.arago.USER.Model.Request;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//public class RequestDAO {
//    private SQLiteDatabase db;
//    private DBHelper dbHelper;
//
//    public static final String TABLE_NAME = "Request";
//    public static final String SQL_REQUEST = "CREATE TABLE Request (" +
//            " request_id integer primary key AUTOINCREMENT," +
//            " request_service_id integer," +
//            " request_errortype text," +
//            " request_brand text," +
//            " request_datetime date);";
//    public static final String TAG = "RequestDAO";
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    public RequestDAO(Context context) {
//        dbHelper = new DBHelper(context);
//        db = dbHelper. getWritableDatabase();
//    }
//
//    // insert
//    public int requestInsert(Request request){
//        ContentValues values = new ContentValues();
//        values.put("request_service_id",request.getRequest_service_id());
//        values.put("request_errortype",request.getRequest_errortype());
//        values.put("request_brand",request.getRequest_brand());
//        values.put("request_datetime",sdf.format(request.getRequest_datetime()));
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
//    public List<Request> requestGetAll() throws ParseException {
//        List<Request> requestList = new ArrayList<>();
//
//        String sSQL =  "SELECT request_id, request_errortype, request_brand, request_datetime" + "Service.service_id";
//        Cursor c = db.rawQuery(sSQL, null);
//
////        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
//        c.moveToFirst();
//        try {
//            while (c.isAfterLast() == false) {
//                Request ee = new Request();
//                ee.setRequest_id(c.getInt(0));
//                ee.setRequest_service_id(c.getInt(1));
//                ee.setRequest_errortype(c.getString(2));
//                ee.setRequest_brand(c.getString(3));
//                ee.setRequest_datetime(sdf.parse(c.getString(4)));
//                requestList.add(ee);
//                Log.d("//=====", ee.toString());
//                c.moveToNext();
//            }
//            c.close();
//        } catch (Exception e){
//            Log.d(TAG, e.toString());
//        }
//        return requestList;
//    }
//
//    // update
//    public int requestUpdate(Request request){
//        ContentValues values = new ContentValues();
//        values.put("request_id",request.getRequest_id());
//        values.put("request_service_id",request.getRequest_service_id());
//        values.put("request_errortype",request.getRequest_errortype());
//        values.put("request_brand",request.getRequest_brand());
//        values.put("request_datetime",request.getRequest_datetime().toString());
//        int result = db.update(TABLE_NAME, values, "request_id=?", new String[]{String.valueOf(request.getRequest_id())});
//        if (result == 0){
//            return -1;
//        }
//        return 1;
//    }
//
////    // delete
////    public int requestDeleteByID(String request){
////        int result = db.delete(TABLE_NAME,"request_id=?", new String[]{request});
////        if (result == 0)
////            return -1;
////        return 1;
////    }
//
//    // delete:
//    public boolean checkPrimaryKey(String strPrimaryKey){
//        // Select
//        String[] columns = {"request_id"};
//        // Where clause
//        String selection = "request_id = ?";
//        // Where clause arguments
//        String[] selectionArgs = {strPrimaryKey};
//        Cursor c = null;
//        try {
//            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
//            c.moveToFirst();
//            int i = c.getCount();
//            c.close();
//            if (1 <= 0) {
//                return false;
//            }
//            return true;
//        }catch(Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//}
