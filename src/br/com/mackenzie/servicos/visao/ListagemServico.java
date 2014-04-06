package br.com.mackenzie.servicos.visao;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import br.com.mackenzie.R;
import gueei.binding.app.BindingActivity;

public class ListagemServico extends BindingActivity implements View.OnClickListener {
	
	
	private CheckBox chkGesso, chkPintura, chkAcabamentoParede, chkAcabamentoPorta, chkAcabamentoJanela;
	private Button btnDisplay;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		
		// Associa��o entre layout e ViewModel conforme vers�o antiga do framework (quando herdado de BindingActivity).
		//setAndBindRootView(R.layout.listagem_servico, new ListagemServicoVM(this));//new ServicoVM(servico, this));
		setContentView(R.layout.listagem_servico);
		
		chkGesso = (CheckBox) findViewById(R.id.chkGesso);
		chkPintura = (CheckBox) findViewById(R.id.chkPintura);
		chkAcabamentoParede = (CheckBox) findViewById(R.id.chkAcabamentoParede);
		chkAcabamentoPorta = (CheckBox) findViewById(R.id.chkAcabamentoPorta);
		chkAcabamentoJanela = (CheckBox) findViewById(R.id.chkAcabamentoJanela);


		chkGesso.setOnClickListener(this);
		chkPintura.setOnClickListener(this);
		chkAcabamentoParede.setOnClickListener(this);
		chkAcabamentoPorta.setOnClickListener(this);
		chkAcabamentoJanela.setOnClickListener(this);


	}

	@Override
	public void onClick(View view) {
			
		CheckBox t = (CheckBox) view;
			
		if(t.isChecked()){
			Toast.makeText(ListagemServico.this, "Servi�o Inclu�do!",
						Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(ListagemServico.this, "Servi�o Exclu�do!",
						Toast.LENGTH_LONG).show();
		}
	}
	
}
