package br.com.hachitecnologia.palestra_android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.hachitecnologia.palestra_android.R;
import br.com.hachitecnologia.palestra_android.dao.PessoaDAO;
import br.com.hachitecnologia.palestra_android.modelo.Pessoa;

/**
 * Activity responsavel pelo cadastro de pessoas.
 * 
 * @author lucasfreitas
 *
 */
public class CadastroPessoaActivity extends Activity {
	
	private Pessoa pessoa = new Pessoa();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        
        /**
         * Acao do botao Cancelar.
         */
        Button cancelar = (Button) findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        
        /**
         * Acao do botao Salvar.
         */
        Button salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pessoa.setNome(((EditText)findViewById(R.id.campo_nome)).getText().toString());
				pessoa.setTelefone(((EditText)findViewById(R.id.campo_telefone)).getText().toString());
				
				PessoaDAO dao = new PessoaDAO(getApplicationContext());
				dao.salva(pessoa);
				
				Toast.makeText(getApplicationContext(), "Cadastro Salvo!", Toast.LENGTH_LONG).show();
				
				Intent i = new Intent(getApplicationContext(), ListaPessoasActivity.class);
				startActivity(i);
			}
		});
        
    }

    
}
