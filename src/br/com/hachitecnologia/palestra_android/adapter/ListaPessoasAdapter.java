package br.com.hachitecnologia.palestra_android.adapter;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.hachitecnologia.palestra_android.R;
import br.com.hachitecnologia.palestra_android.modelo.Pessoa;

/**
 * Adapter responsavel por injetar os objetos na ListView
 * 
 * @author lucasfreitas
 *
 */
public class ListaPessoasAdapter extends ArrayAdapter<Pessoa> {
	
	private final List<Pessoa> pessoas;
	private final Activity activity;

	public ListaPessoasAdapter(Activity activity, int textViewResourceId, 
			List<Pessoa> pessoas) {
		super(activity, textViewResourceId, pessoas);
		this.activity = activity;
		this.pessoas = pessoas;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Pessoa pessoa = pessoas.get(position);
		View view = activity.getLayoutInflater().inflate(R.layout.item_lista_pessoas, null);
		
		// Nome
		TextView nome = (TextView) view.findViewById(R.id.item_pessoa_nome);
		nome.setText(pessoa.getNome());
		
		// Telefone
		TextView telefone = (TextView) view.findViewById(R.id.item_pessoa_telefone);
		telefone.setText(pessoa.getTelefone());
		
		return view;
	}
	
	@Override
	public long getItemId(int position) {
		return pessoas.get(position).getId();
	}

	@Override
	public int getCount() {
		return super.getCount();
	}

	@Override
	public Pessoa getItem(int position) {
		return pessoas.get(position);
	}

}
