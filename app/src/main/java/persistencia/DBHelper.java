package persistencia;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static int VERSION=1;

    public static  String NOME_DB = "DB_SEUBANCO";

    public static String TABELA_CONTATO = "contato";

    public DBHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_CONTATO
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nomeContato TEXT NOT NULL, telContato TEXT);";
        try{
           db.execSQL(sql);
            Log.i("InforDB", "Sucesso ao criar a tabela");
        } catch (Exception e) {
            Log.i("InfoDB", "Erro ao criar a Table" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA_CONTATO + ";";
        onCreate(db);
        try{
            db.execSQL(sql);
            Log.i("InforDB", "Sucesso ao criar a tabela");
        } catch (Exception e) {
            Log.i("InfoDB", "Erro ao criar a Table" + e.getMessage());
        }
    }
}
