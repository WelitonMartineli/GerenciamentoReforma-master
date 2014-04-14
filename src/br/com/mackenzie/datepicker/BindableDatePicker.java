package br.com.mackenzie.datepicker;

import gueei.binding.IBindableView;
import gueei.binding.ViewAttribute;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

/**
 * Esta classe implementa um DatePicker que pode ser vinculado a atributos do
 * Android Binding. O atributo suportado é especificado no método
 * createViewAttribute, que verifica se o nome do atributo é "valor" e, em caso
 * afirmativo, retorna um novo atributo.
 * 
 */
public class BindableDatePicker extends DatePicker implements
		IBindableView<BindableDatePicker> {

	public BindableDatePicker(Context contexto, AttributeSet atributos,
			int estilo) {
		super(contexto, atributos, estilo);
	}

	public BindableDatePicker(Context contexto, AttributeSet atributos) {
		super(contexto, atributos);
	}

	public BindableDatePicker(Context contexto) {
		super(contexto);
	}

	@Override
	public ViewAttribute<? extends View, ?> createViewAttribute(
			String nomeAtributo) {
		if ("valor".equals(nomeAtributo)) {
			return new AtributoValor(this, "valor");
		}
		return null;
	}

}
