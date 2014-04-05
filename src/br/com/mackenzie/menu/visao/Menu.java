package br.com.mackenzie.menu.visao;

import gueei.binding.v30.app.BindingActivityV30;
import android.os.Bundle;
import br.com.mackenzie.R;

public class Menu  extends BindingActivityV30 {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Cria a ViewModel e a associa ao layout.
		MenuVM menuVM = new MenuVM (getApplicationContext());
        this.inflateAndBind(R.xml.cadastro_ambiente, menuVM);

	}
	
}
