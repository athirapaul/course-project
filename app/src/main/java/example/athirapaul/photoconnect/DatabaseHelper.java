package example.athirapaul.photoconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public  static  final  String DATABASE_NAME = "PhotoConnect.db";
   public  static  final  String TABLE_NAME = "User_table";
   public static  final String TABLE_NAME1 = "Map_table";
    public static  final String TABLE_NAME2 = "Photography_table";
   // public static  final String TABLE_NAME3 = "Image_table";
    private byte[] Image;



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase PhotoConnectSQLite) {
        String sqlUsertable = "create table User_table (ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT,LASTNAME TEXT,IMAGE BLOB)";
        String sqlMaptable = "create table Map_table (ID INTEGER PRIMARY KEY AUTOINCREMENT,LATITUDE TEXT,LONGITUDE TEXT)";
        String sqlPhotographytable = "create table Photography_table (ID INTEGER PRIMARY KEY AUTOINCREMENT,CAMERANAME TEXT,PHOTOGRAPHY_STYLE TEXT,IMAGE BLOB)";
       // String sqlImagetable = "create table Image_table (ID INTEGER PRIMARY KEY AUTOINCREMENT)";

        PhotoConnectSQLite.execSQL(sqlUsertable);
        PhotoConnectSQLite.execSQL(sqlMaptable);
        PhotoConnectSQLite.execSQL(sqlPhotographytable);
        //PhotoConnectSQLite.execSQL(sqlImagetable);

    }
public boolean addMapTable(Double LATITUDE, Double LONGITUDE){
    SQLiteDatabase PhotoConnectSQLite = getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    // contentValues.put("ID",ID);
    contentValues.put("LATITUDE",LATITUDE);
    contentValues.put("LONGITUDE",LONGITUDE);
    PhotoConnectSQLite.insert("Map_table",null,contentValues);
    PhotoConnectSQLite.close();
    return true;
}
    public  boolean addUserTable( String FIRSTNAME, String LASTNAME,byte[] IMAGE){
        SQLiteDatabase PhotoConnectSQLite = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
           // contentValues.put("ID",ID);
            contentValues.put("FIRSTNAME",FIRSTNAME);
            contentValues.put("LASTNAME",LASTNAME);

        contentValues.put("IMAGE",IMAGE);

        PhotoConnectSQLite.insert("User_table",null,contentValues);
        PhotoConnectSQLite.close();
        return true;
    }
    public boolean addPhotographytable(String CAMERANAME,String PHOTOGRAPHY_STYLE) {
        SQLiteDatabase PhotoConnectSQLite = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // contentValues.put("ID",ID);
        contentValues.put("CAMERANAME", CAMERANAME);
        contentValues.put("PHOTOGRAPHY_STYLE", PHOTOGRAPHY_STYLE);
        PhotoConnectSQLite.insert("Photography_table", null, contentValues);
        PhotoConnectSQLite.close();
        return true;
    }
//    public boolean addImageTable(byte[] IMAGE) {
//
//        SQLiteDatabase PhotoConnectSQLite = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        // contentValues.put("ID",ID);
//        contentValues.put("IMAGE",IMAGE);
//
//        PhotoConnectSQLite.insert("Image_table", null, contentValues);
//        PhotoConnectSQLite.close();
//        return true;
//    }

    @Override
    public void onUpgrade(SQLiteDatabase PhotoConnectSQLite, int oldVersion, int newVersion) {
        String sqlUserTable = "DROP TABLE IF EXISTS  "+ TABLE_NAME ;
        String sqlMapTable = "DROP TABLE IF EXISTS  "+ TABLE_NAME1 ;
        String sqlPhotographyTable = "DROP TABLE IF EXISTS  "+ TABLE_NAME2 ;
        //String sqlImageTable = "DROP TABLE IF EXISTS  "+ TABLE_NAME3 ;


        PhotoConnectSQLite.execSQL(sqlUserTable);
        PhotoConnectSQLite.execSQL(sqlMapTable);
        PhotoConnectSQLite.execSQL(sqlPhotographyTable);
      //  PhotoConnectSQLite.execSQL(sqlImageTable);

onCreate(PhotoConnectSQLite);

    }

//public  byte[] getBitmapbyname(String FIRSTNAME, String LASTNAME){
//    SQLiteDatabase PhotoConnectSQLite = getWritableDatabase();
//    SQLiteQueryBuilder QB = new SQLiteQueryBuilder();
//    String[] select = {COL_IMAGE};
//
//
//}

    //   public  boolean updateData(){
//        SQLiteDatabase PhotoConnectSQLite = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("FIRSTNAME",FIRSTNAME);
//        contentValues.put("LASTNAME",LASTNAME);
//        contentValues.put("CAMERANAME",CAMERANAME);
//        //contentValues.put("PHOTOGRAPHYSTYLE",PHOTOGRAPHYSTYLE);
//        PhotoConnectSQLite.update(TABLE_NAME,contentValues, "CAMERANAME = ?",new String[]{CAMERANAME});
//        return true;
 //   }


}
