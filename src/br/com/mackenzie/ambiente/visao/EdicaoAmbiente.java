package br.com.mackenzie.ambiente.visao;

import gueei.binding.app.BindingActivity;
import gueei.binding.v30.app.BindingActivityV30;
import android.os.Bundle;

import br.com.mackenzie.R;
import br.com.mackenzie.ambiente.modelo.Ambiente;

/**
 * Esta classe implementa uma atividade de edição de ambientes.
 * O papel desta classe é relacionar um layout XML a uma visão de modelo (ViewModel).
 * @author Leandro Luque, Érico Veriscimo, Girdácio Pereira
 * @version 1.0
 * @since 1.0
 */
                                    // Aqui pode ser herdada tanto a classe BindingActivity quanto BindingActivityV30.
									// A V30 é preferida por se tratar da última versão, com suporte a novos recursos do Android.
public class EdicaoAmbiente extends BindingActivity //BindingActivityV30
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Recupera uma referência para o ambiente que será editado.
		// Ele foi passado por parâmetro quando o usuário clicou no ambiente e esta atividade
		// foi iniciada.
		Bundle bundle = this.getIntent().getExtras();
		Ambiente ambiente = (Ambiente)bundle.getSerializable("ambiente");
		
		// Associação entre layout e ViewModel conforme versão antiga do framework (quando herdado de BindingActivity).
		 setAndBindRootView(R.layout.ambiente_cadastro, new AmbienteCadastroVM(ambiente, this));
		
		// Cria a ViewModel e a associa ao layout.
		//AmbienteCadastroVM ambienteCadastroVM = new AmbienteCadastroVM (ambiente, getApplicationContext());
        //this.inflateAndBind(R.layout.ambiente_cadastro, ambienteCadastroVM);
	}

}
