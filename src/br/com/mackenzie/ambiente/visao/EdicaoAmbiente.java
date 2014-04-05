package br.com.mackenzie.ambiente.visao;

import gueei.binding.app.BindingActivity;
import gueei.binding.v30.app.BindingActivityV30;
import android.os.Bundle;

import br.com.mackenzie.R;
import br.com.mackenzie.ambiente.modelo.Ambiente;

/**
 * Esta classe implementa uma atividade de edi��o de ambientes.
 * O papel desta classe � relacionar um layout XML a uma vis�o de modelo (ViewModel).
 * @author Leandro Luque, �rico Veriscimo, Gird�cio Pereira
 * @version 1.0
 * @since 1.0
 */
                                    // Aqui pode ser herdada tanto a classe BindingActivity quanto BindingActivityV30.
									// A V30 � preferida por se tratar da �ltima vers�o, com suporte a novos recursos do Android.
public class EdicaoAmbiente extends BindingActivity //BindingActivityV30
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Recupera uma refer�ncia para o ambiente que ser� editado.
		// Ele foi passado por par�metro quando o usu�rio clicou no ambiente e esta atividade
		// foi iniciada.
		Bundle bundle = this.getIntent().getExtras();
		Ambiente ambiente = (Ambiente)bundle.getSerializable("ambiente");
		
		// Associa��o entre layout e ViewModel conforme vers�o antiga do framework (quando herdado de BindingActivity).
		 setAndBindRootView(R.layout.ambiente_cadastro, new AmbienteCadastroVM(ambiente, this));
		
		// Cria a ViewModel e a associa ao layout.
		//AmbienteCadastroVM ambienteCadastroVM = new AmbienteCadastroVM (ambiente, getApplicationContext());
        //this.inflateAndBind(R.layout.ambiente_cadastro, ambienteCadastroVM);
	}

}
