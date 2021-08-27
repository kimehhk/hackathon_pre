package ddwucom.mobile.bora_hackathon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CenterDBHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "center.db";
    public final static String TABLE_NAME = "center_table";
    public final static String COL_ID = "_id";
    public final static String COL_RECEPTIONCONTENT = "receptionContent";
    public final static String COL_INSTITUTION = "institution";
    public final static String COL_NUMBER = "number";
    public final static String COL_ADDRESS = "address";

    public CenterDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " +
                COL_RECEPTIONCONTENT + " TEXT, " + COL_INSTITUTION + " TEXT, " + COL_NUMBER + " TEXT, " + COL_ADDRESS + " TEXT)";
        db.execSQL(sql);

        db.execSQL("insert into " + TABLE_NAME + " values (null, '가정폭력, 성폭력, 성매매 긴급 전화상담 및 보호', '한국여성인권진흥원', '1366', 'https://www.women1366.kr/_main/main.html');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '성폭력, 성매매, 학교, 가정폭력 상담·신고', '아동·여성·장애인 경찰지원센터', '117', 'http://www.safe182.go.kr/index.do#');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '가정폭력, 성폭력, 부부갈등해결, 부부캠프', '한국여성상담센터', '02-953-2017', 'http://www.iffeminist.or.kr/');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '여성인권, 가정폭력, 성평등 운동', '한국여성의전화', '02-2263-6464', 'http://www.hotline.or.kr/');");
//        db.execSQL("insert into " + TABLE_NAME + " values (null, '취업상담 및 교육, 사회·문화생활 사업 지원', '여성인력개발센터', '02-318-5880', 'http://www.vocation.or.kr/main.aspx');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '성상담, 자녀 성교육 상담', '푸른아우성', '02-332-9978', 'http://www.aoosung.com/');");
//        db.execSQL("insert into " + TABLE_NAME + " values (null, '여성 일자리 상담', '여성가족부', '1544-1199', 'http://www.mogef.go.kr/cc/tlc/cc_tlc_f001.do');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
