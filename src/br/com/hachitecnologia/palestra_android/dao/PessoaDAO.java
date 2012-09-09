package br.com.hachitecnologia.palestra_android.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.hachitecnologia.palestra_android.modelo.Pessoa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DAO responsavel por gerenciar a tabela "pessoa".
 * 
 * @author Lucas Freitas
 *
 */
public class PessoaDAO extends SQLiteOpenHelper {
	
	// Versão da tabela
	private static final int VERSAO = 1;
	
	public PessoaDAO(Context context) {
		super(context, "pessoa", null, VERSAO);
	}
	
	/**
	 * Cria a tabela no banco de dados, caso ela nao exista.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE pessoa (" +
				"_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
				"nome TEXT NOT NULL, " +
				"telefone TEXT NOT NULL " +
				");";
		db.execSQL(sql);
	}
	
	/**
	 * Atualiza a estrutura da tabela no banco de dados, caso sua versao tenha mudado.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS pessoa";
		db.execSQL(sql);
		onCreate(db);
	}
	
	/**
	 * Adiciona objeto no banco de dados.
	 * 
	 * @param objeto
	 */
	public void adiciona(Pessoa pessoa) {
		// Define os valores do registro a ser inserido no banco de dados
		ContentValues values = new ContentValues();
		values.put("nome", pessoa.getNome());
		values.put("telefone", pessoa.getTelefone());
		
		SQLiteDatabase db = getWritableDatabase();
		// Insere o registro no banco de dados
		db.insert("pessoa", null, values);
		// Encerra a conexão com o banco de dados
		db.close();
	}

	/**
	 * Remove objeto do banco de dados.
	 * 
	 * @param objeto
	 */
	public void remove(Pessoa pessoa) {
		SQLiteDatabase db = getWritableDatabase();
		db.delete("pessoa", "_id=?", 
				new String[] { pessoa.getId().toString() });
		db.close();
	}
	
	/**
	 * Atualiza objeto no banco de dados.
	 * 
	 * @param objeto
	 */
	public void atualiza(Pessoa pessoa) {
		ContentValues values = preencheValores(pessoa);
		SQLiteDatabase db = getWritableDatabase();
		db.update("pessoa", values, 
				"_id=?", new String[] { pessoa.getId().toString() });
		db.close();
	}

	/**
	 * Salva objeto no banco de dados.
	 * Caso o objeto nao exista no banco de dados, ele o adiciona.
	 * Caso o objeto exista no banco de dados, apenas atualiza os valores dos campos modificados.
	 * 
	 * @param objeto
	 */
	public void salva(Pessoa pessoa) {
		if (pessoa.getId() == null) {
			adiciona(pessoa);
		} else {
			atualiza(pessoa);
		}
	}
	
	/**
	 * Faz um simples Adapter preenchendo os valores a serem 
	 * persistidos no banco de dados de acordo com os valores do 
	 * objeto recebido como parametro.
	 * 
	 * @param objeto
	 * @return
	 */
	private ContentValues preencheValores(Pessoa pessoa) {
		ContentValues values = new ContentValues();
		values.put("nome", pessoa.getNome());
		values.put("telefone", pessoa.getTelefone());
		
		return values;
	}
	
	/**
	 * Lista todos os registros da tabela.
	 * 
	 * @return
	 */
	public List<Pessoa> listaTodos() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.query("pessoa", null, null, null, null, 
				null, "nome ASC");
		try {
			while (c.moveToNext()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(c.getLong(c.getColumnIndex("_id")));
				pessoa.setNome(c.getString(c.getColumnIndex("nome")));
				pessoa.setTelefone(c.getString(c.getColumnIndex("telefone")));
				pessoas.add(pessoa);
			}
		} finally {
			c.close();
		}
		
		db.close();
		
		return pessoas;
	}

}
