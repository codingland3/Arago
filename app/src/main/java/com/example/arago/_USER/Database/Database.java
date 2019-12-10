package com.example.arago._USER.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.arago.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME="aragagotwoop.db";
    private static final int DB_VER= 1;
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }
    public List<Order> getCardt()
    {
        SQLiteDatabase dd=getReadableDatabase();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();

        String[] sqlSelect={"Name","Phone","Address","ErrorType"};
        String sqlTable="OrderDetail";
        qb.setTables(sqlTable);

        Cursor c=qb.query(dd,sqlSelect,null,null,null,null,null);
        final List<Order>result=new ArrayList<>();


            while (c.moveToNext()){
//
                result.add(new Order(c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3)
                ));
            }

        return result;
    }

    public void addtocard(Order order) {
        SQLiteDatabase db = getReadableDatabase();
        String query=String.format("INSERT INTO OrderDetail(Name,Phone,Address,ErrorType) VALUES('%s','%s','%s','%s');",
                order.getName(),
                order.getPhone(),
                order.getAddress(),
                order.getErrortype());
        db.execSQL(query);


    }
    public void cleanCart()
    {
        SQLiteDatabase db=getReadableDatabase();
        String query=String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }
}
