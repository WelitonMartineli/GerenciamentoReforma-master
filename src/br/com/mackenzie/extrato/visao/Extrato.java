package br.com.mackenzie.extrato.visao;


import gueei.binding.app.BindingActivity;
import android.os.Bundle;
import android.widget.TextView;
import br.com.mackenzie.R;
import br.com.mackenzie.ambiente.modelo.Ambiente;

public class Extrato extends BindingActivity {
	
	private TextView textView;
	private Ambiente ambiente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle bundle = this.getIntent().getExtras();
		this.ambiente = (Ambiente)bundle.getSerializable("ambiente");
		
		setContentView(R.layout.extrato);
		
		
	}

}
