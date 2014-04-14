package br.com.mackenzie.ambiente.visao;

import br.com.mackenzie.R;
import gueei.binding.app.BindingActivity;
import gueei.binding.v30.app.BindingActivityV30;
import android.os.Bundle;


/**
 * Esta classe implementa uma atividade de cadastro e edi��o de ambientes.
 * O papel desta classe � relacionar um layout XML a uma vis�o de modelo (ViewModel).
 */
                                    // Aqui pode ser herdada tanto a classe BindingActivity quanto BindingActivityV30.
public class CadastroAmbiente extends BindingActivity  {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Associa��o entre layout e ViewModel conforme vers�o antiga do framework (quando herdado de BindingActivity).
		setAndBindRootView(R.layout.ambiente_cadastro, new AmbienteCadastroVM(getApplicationContext()));
		
		// Cria a ViewModel e a associa ao layout.
		//AmbienteCadastroVM ambienteCadastroVM = new AmbienteCadastroVM (getApplicationContext());
        //this.inflateAndBind(R.layout.ambiente_cadastro, ambienteCadastroVM);
	}

}
