package br.com.mackenzie.servicos.visao;

import android.content.Context;
import android.os.Bundle;
import br.com.mackenzie.R;
import gueei.binding.app.BindingActivity;

public class ListagemServico extends BindingActivity {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		
		// Associação entre layout e ViewModel conforme versão antiga do framework (quando herdado de BindingActivity).
		setAndBindRootView(R.layout.listagem_servico, new ListagemServicoVM(this));//new ServicoVM(servico, this));
	
		
		// Cria a ViewModel e a associa ao layout.
		//ServicoVM servicosVM = new ServicoVM (getApplicationContext());
        //this.inflateAndBind(R.layout.servico, servicosVM);

	}
}
