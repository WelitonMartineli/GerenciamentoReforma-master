package br.com.mackenzie.servicos.visao;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import br.com.mackenzie.R;
import gueei.binding.app.BindingActivity;

public class ListagemServico extends BindingActivity {
	
	
	private CheckBox chkGesso, chkPintura, chkAcabamentoParede, chkAcabamentoPorta, chkAcabamentoJanela;
	private Button btnDisplay;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		
		// Associação entre layout e ViewModel conforme versão antiga do framework (quando herdado de BindingActivity).
		//setAndBindRootView(R.layout.listagem_servico, new ListagemServicoVM(this));//new ServicoVM(servico, this));
		setContentView(R.layout.listagem_servico);
		addListenerOnChkIos();
		addListenerOnButton();

	}
	
	 public void addListenerOnChkIos() {
		 
			chkGesso = (CheckBox) findViewById(R.id.chkGesso);
		 
			chkGesso.setOnClickListener(new OnClickListener() {
		 
			  @Override
			  public void onClick(View v) {
		                //is chkIos checked?
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(ListagemServico.this,
				 	   "Bro, try Android :)", Toast.LENGTH_LONG).show();
				}
		 
			  }
			});
		 
		  }
		 
		  public void addListenerOnButton() {
		 
			chkGesso = (CheckBox) findViewById(R.id.chkGesso);
			chkPintura = (CheckBox) findViewById(R.id.chkPintura);
			chkAcabamentoParede = (CheckBox) findViewById(R.id.chkAcabamentoParede);
			chkAcabamentoPorta = (CheckBox) findViewById(R.id.chkAcabamentoPorta);
			chkAcabamentoJanela = (CheckBox) findViewById(R.id.chkAcabamentoJanela);
			btnDisplay = (Button) findViewById(R.id.btnDisplay);
		 
			btnDisplay.setOnClickListener(new OnClickListener() {
		 
		          //Run when button is clicked
			  @Override
			  public void onClick(View v) {
		 
				StringBuffer result = new StringBuffer();
				result.append("Gesso check : ").append(chkGesso.isChecked());
				result.append("\nPintura check : ").append(chkPintura.isChecked());
				result.append("\nAcabamento Parede check :").append(chkAcabamentoParede.isChecked());
				result.append("\nAcabamento Porta check : ").append(chkAcabamentoPorta.isChecked());
				result.append("\nAcabamento Janela check :").append(chkAcabamentoJanela.isChecked());
		 
				Toast.makeText(ListagemServico.this, result.toString(),
						Toast.LENGTH_LONG).show();
		 
			  }
			});	
		  }
	
}
