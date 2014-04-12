package br.com.mackenzie.servicos.visao;

import gueei.binding.app.BindingActivity;
import gueei.binding.v30.app.BindingActivityV30;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;
import br.com.mackenzie.R;
import br.com.mackenzie.ambiente.modelo.Ambiente;
import br.com.mackenzie.ambiente.visao.AmbienteCadastroVM;
import br.com.mackenzie.menu.visao.MenuVM;

public class Servico extends BindingActivity {
	

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Bundle bundle = this.getIntent().getExtras();
		//Servico servico = (Servico)bundle.getSerializable("servico");		
		
		// Associação entre layout e ViewModel conforme versão antiga do framework (quando herdado de BindingActivity).
		setAndBindRootView(R.layout.servico, new ServicoVM(getApplicationContext()));//new ServicoVM(servico, this));
		
		
		// Cria a ViewModel e a associa ao layout.
		//ServicoVM servicosVM = new ServicoVM (getApplicationContext());
        //this.inflateAndBind(R.layout.servico, servicosVM);

	}
}
