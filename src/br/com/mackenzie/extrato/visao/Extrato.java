package br.com.mackenzie.extrato.visao;


import java.math.BigDecimal;

import gueei.binding.app.BindingActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import br.com.mackenzie.R;
import br.com.mackenzie.ambiente.modelo.Ambiente;
import br.com.mackenzie.servico.dao.ServicoAmbienteDao;

public class Extrato extends BindingActivity {
	

	private Ambiente ambiente;
	private TextView descricaoAmbiente;
	private TextView areaCalculada;
	private ListView listExtratoServico;
	private TextView totalGeral;

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
			
		String[] values = ServicoAmbienteDao.listarTodosServicos(this.ambiente);

		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, values);
	    
        // Assign adapter to ListView
		listExtratoServico.setAdapter(adapter); 
		
		
		totalGeral = (TextView) findViewById(R.id.textValorTotal);
		
		String total = getValorTotal(ambiente, values).toString();
		totalGeral.setText(total);
		
		
	}

	
	//TODO: Esse metodo simula o calculo dos servicos para o ambiente.
	private BigDecimal getValorTotal(Ambiente ambiente, String[] qtdServicos){
		
		BigDecimal total;
		BigDecimal areaCalculada = new BigDecimal(ambiente.getMetragem());
		BigDecimal valorExterno = new BigDecimal(345);
		BigDecimal servicos = new BigDecimal(qtdServicos != null ?qtdServicos.length: 0);

		//Find nos servicos cadastrados
		//calcular preco dos sevicos via api externa
		
		// Simular valores.
		total = areaCalculada.multiply(valorExterno.multiply(servicos));
		
		
		return total;
		
	}
	
}
