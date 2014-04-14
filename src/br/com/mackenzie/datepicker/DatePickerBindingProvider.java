package br.com.mackenzie.datepicker;

import gueei.binding.ViewAttribute;
import gueei.binding.bindingProviders.BindingProvider;
import android.widget.DatePicker;

/**
 * Provedor de vinculação para o componente DatePicker. Este provedor é
 * utilizado no kernel passado por parâmetro para o método de inicialização do
 * framework.
 * 
 */
public class DatePickerBindingProvider extends BindingProvider {

	// Caso o componente seja um DatePicker e o atributo encontrado seja "valor", retorna uma referência
	// para o atributo (que sabe como alterar o componente e avisar os observadores quando o componente
	// alterar).
	@SuppressWarnings("unchecked")
	@Override
	public <Tv extends android.view.View> gueei.binding.ViewAttribute<Tv, ?> createAttributeForView(
			android.view.View visao, String nomeAtributo) {
		if (!(visao instanceof DatePicker)) {
			return null;
		}
		if ("valor".equals(nomeAtributo)) {
			return (ViewAttribute<Tv, ?>) new AtributoValor((DatePicker) visao,
					"valor");
		}
		return null;
	};

}
