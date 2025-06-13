package persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Contato;

public class ContatoDAO implements IContatoDAO{

    private final String TABELA_EMISSORES = "Contato";
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public ContatoDAO(Context context) {
        DBHelper db = new DBHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Contato contato) {
        ContentValues cv = new ContentValues();
        cv.put("nomeContato", contato.getNomeContato());
        cv.put("telContato", contato.getTelContato());
        try{
            escreve.insert(DBHelper.TABELA_CONTATO, null, cv);
            Log.i("infoDB", "Susseco ao salvar o registro");
        } catch (Exception e) {
            Log.i("InfoDB", "Erro ao salvar o registro");
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Contato contato) {
        ContentValues cv = new ContentValues();
        cv.put("nomeContato", contato.getNomeContato());
        cv.put("telContato", contato.getTelContato());
        try{
            String[] args = {String.valueOf(contato.getId())};
            escreve.update(DBHelper.TABELA_CONTATO, cv, "id=?", args);
            Log.i("infoDB", "Susseco ao atualizar o registro");
        } catch (Exception e) {
            Log.i("InfoDB", "Erro ao atualizar o registro");
            return false;
        }
        return true;
    }

    @Override
    public boolean excluir(Contato contato) {
        try{
            String[] args = {String.valueOf(contato.getId())};
            escreve.delete(DBHelper.TABELA_CONTATO, "id=?", args);
            Log.i("infoDB", "Susseco ao excluir o registro");
        } catch (Exception e) {
            Log.i("InfoDB", "Erro ao excluir o registro");
            return false;
        }
        return true;
    }

    @Override
    public List<Contato> listar() {
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT * FROM " + DBHelper.TABELA_CONTATO + ";";
       try {
           Cursor cursor = le.rawQuery(sql, null);
           int indxId = cursor.getColumnIndexOrThrow("id");
           int idxNome = cursor.getColumnIndexOrThrow("nomeContato");
           int idxTelefone = cursor.getColumnIndexOrThrow("telContato");
           while (cursor.moveToNext()) {
               Contato contato = new Contato();
               contato.setId(cursor.getInt(indxId));
               contato.setNomeContato(cursor.getString(idxNome));
               contato.setTelContato(cursor.getString(idxTelefone));
               contatos.add(contato);
           }
           Log.i("infoDB", "Sucesso ao Pesquisar o registro.");
       } catch (Exception e) {
           Log.i("infoDB", "Erro ao pesquisar o resgistro.");
       }
       return contatos;
    }
}
