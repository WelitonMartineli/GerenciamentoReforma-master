package br.com.mackenzie.servicos.visao;

import gueei.binding.app.BindingActivity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import br.com.mackenzie.R;
import br.com.mackenzie.ambiente.modelo.Ambiente;
import br.com.mackenzie.extrato.visao.Extrato;
import br.com.mackenzie.servico.dao.ServicoAmbienteDao;
import br.com.mackenzie.servico.modelo.ServicoAmbiente;

public class ListagemServico extends BindingActivity implements View.OnClickListener {
	
	
	private CheckBox chkGesso, chkPintura, chkAcabamentoParede, chkAcabamentoPorta, chkAcabamentoJanela;
	private Button btnCalcular;	
	private Ambiente ambiente;
	private List<CheckBox> allChecks;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	

		setContentView(R.layout.listagem_servico);
		
		chkGesso = (CheckBox) findViewById(R.id.chkGesso);
		chkPintura = (CheckBox) findViewById(R.id.chkPintura);
		chkAcabamentoParede = (CheckBox) findViewById(R.id.chkAcabamentoParede);
		chkAcabamentoPorta = (CheckBox) findViewById(R.id.chkAcabamentoPorta);
		chkAcabamentoJanela = (CheckBox) findViewById(R.id.chkAcabamentoJanela);
		
		allChecks = new ArrayList<CheckBox>();
		allChecks.add(chkGesso);
		allChecks.add(chkPintura);
		allChecks.add(chkAcabamentoParede);
		allChecks.add(chkAcabamentoPorta);
		allChecks.add(chkAcabamentoJanela);
		
		btnCalcular = (Button) this.findViewById(R.id.btnCalcular);


		chkGesso.setOnClickListener(this);
		chkPintura.setOnClickListener(this);
		chkAcabamentoParede.setOnClickListener(this);
		chkAcabamentoPorta.setOnClickListener(this);
		chkAcabamentoJanela.setOnClickListener(this);
		
		btnCalcular.setOnClickListener(this);

		Bundle bundle = this.getIntent().getExtras();
		this.ambiente = (Ambiente)bundle.getSerializable("ambiente");
		
		
		//Verificar checkBox na base.
		verifyCheckBox();		

	}

	@Override
	public void onClick(View view) {
		
		if (view instanceof CheckBox ){
			
			CheckBox t = (CheckBox) view;
			if(t.isChecked()){
				Toast.makeText(ListagemServico.this, "Serviço Incluído para o id! " + this.ambiente.getNome(),
							Toast.LENGTH_LONG).show();
				;
				
				ServicoAmbiente serviceAmbiente = new ServicoAmbiente();
				serviceAmbiente.setId(t.getId());				
				serviceAmbiente.setCodServico(t.getText().toString());
				serviceAmbiente.setDesServico(t.getText().toString());
				serviceAmbiente.setIdAmbiente(this.ambiente.getId());
				
				ServicoAmbienteDao.inserir(serviceAmbiente);
				
;

			}else{
				
				ServicoAmbienteDao.excluir(new ServicoAmbiente(t.getId()));
				
				Toast.makeText(ListagemServico.this, "Serviço Excluído para o id! " + this.ambiente.getNome(),
							Toast.LENGTH_LONG).show();
			}


		} else {
		
			Intent intencao = new Intent(view.getContext(),
					Extrato.class);
			view.getContext().startActivity(intencao);
		}
		
	}
	
	private void verifyCheckBox() {
		
		boolean exists = false;
		ServicoAmbiente servicoAmbiente = new ServicoAmbiente();
		servicoAmbiente.setIdAmbiente(ambiente.getId());
		
		for (CheckBox servico : allChecks) {
			
			servicoAmbiente.setId(servico.getId());
			exists = ServicoAmbienteDao.findByServicoAmbientebyIDs(servicoAmbiente);
			
			if (exists){
				servico.setChecked(true);
			}else{
				servico.setChecked(false);

			}
				
		}

	}
	
}
