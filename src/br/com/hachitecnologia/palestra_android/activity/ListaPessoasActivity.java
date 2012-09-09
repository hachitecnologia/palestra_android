package br.com.hachitecnologia.palestra_android.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.hachitecnologia.palestra_android.R;
import br.com.hachitecnologia.palestra_android.adapter.ListaPessoasAdapter;
import br.com.hachitecnologia.palestra_android.dao.PessoaDAO;
import br.com.hachitecnologia.palestra_android.modelo.Pessoa;

/**
 * Activity responsavel por listar na tela as pessoas cadastradas.
 * 
 * @author lucasfreitas
 *
 */
public class ListaPessoasActivity extends Activity {
	
	private ListView listaPessoas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoas);
        listaPessoas = (ListView) findViewById(R.id.lista_pessoas);
    }

    /**
     * Define o adapter da ListView de pessoas.
     * 
     * @param adapter
     */
    private void carregaListaPessoas(ArrayAdapter<Pessoa> adapter) {
    	listaPessoas.setAdapter(adapter);
	}
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	// Consulta as pessoas cadastradas no banco de dados.
    	PessoaDAO dao = new PessoaDAO(getApplicationContext());
		final List<Pessoa> pessoas = dao.listaTodos();
		dao.close();

		// Instancia o adapter que sera setado na ListView
		ArrayAdapter<Pessoa> adapter = 
				new ListaPessoasAdapter(this, android.R.layout.simple_list_item_1, pessoas);

		// Carrega o adapter
		carregaListaPessoas(adapter);
    }

    
}
