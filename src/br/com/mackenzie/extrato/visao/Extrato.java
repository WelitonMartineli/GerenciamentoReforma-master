package br.com.mackenzie.extrato.visao;


import gueei.binding.app.BindingActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import br.com.mackenzie.R;
import br.com.mackenzie.ambiente.modelo.Ambiente;

public class Extrato extends BindingActivity {
	

	private Ambiente ambiente;
	private TextView descricaoAmbiente;
	private TextView areaCalculada;
	private ListView listExtratoServico;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.extrato);
		
		Bundle bundle = this.getIntent().getExtras();
		this.ambiente = (Ambiente)bundle.getSerializable("ambiente");
		
		
		// Setando extrato ambiente
		descricaoAmbiente = (TextView) findViewById(R.id.extratoAmbiente); 
		areaCalculada = (TextView) findViewById(R.id.extratoAreaCalculada); 		
		
		descricaoAmbiente.setText(ambiente.getNome().toString());
		areaCalculada.setText(ambiente.getMetragem().toString());

		//Setando extrato servico
		listExtratoServico = (ListView) findViewById(R.id.listExtratoServicos); 
		
		
		//ServicoAmbienteDao.listarTodosServicos();
        // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View", 
                                         "Adapter implementation",
                                         "Simple List View In Android",
                                         "Create List View Android", 
                                         "Android Example", 
                                         "List View Source Code", 
                                         "List View Array Adapter", 
                                         "Android Example List View" 
                                        };
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, values);
	    
        // Assign adapter to ListView
		listExtratoServico.setAdapter(adapter); 
		
		
	}

}
