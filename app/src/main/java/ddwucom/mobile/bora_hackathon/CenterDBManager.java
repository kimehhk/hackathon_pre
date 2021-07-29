package ddwucom.mobile.bora_hackathon;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class CenterDBManager {
    CenterDBHelper centerDBHelper = null;
    Cursor cursor = null;

    public CenterDBManager(Context context) { centerDBHelper = new CenterDBHelper(context); }

    public ArrayList<Center> getAllCenter() {
        ArrayList centerList = new ArrayList();
        SQLiteDatabase db = centerDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + centerDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(centerDBHelper.COL_ID));
            String receptionContent = cursor.getString(cursor.getColumnIndex(centerDBHelper.COL_RECEPTIONCONTENT));
            String institution = cursor.getString(cursor.getColumnIndex(centerDBHelper.COL_INSTITUTION));
            String number = cursor.getString(cursor.getColumnIndex(centerDBHelper.COL_NUMBER));
            String address = cursor.getString(cursor.getColumnIndex(centerDBHelper.COL_ADDRESS));
            centerList.add ( new Center (id, receptionContent, institution, number, address) );
        }

        cursor.close();
        centerDBHelper.close();
        return centerList;
    }
}
