package br.com.mackenzie.datepicker;

import gueei.binding.ViewAttribute;

import java.util.Calendar;

import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

/**
 * Esta classe implementa um atributo que permitir� a vincula��o do componente
 * DatePicker, ainda n�o suportador pelo framework.
 * 
 * @author Leandro Luque, �rico Veriscimo, Gird�cio Pereira
 * @version 1.0
 * @since 1.0
 */
public class AtributoValor extends ViewAttribute<DatePicker, Long> implements
		OnDateChangedListener {

	// Cria o atributo e define o valor do componente como sendo igual � data de
	// hoje.
	public AtributoValor(DatePicker visao, String nomeAtributo) {
		super(Long.class, visao, nomeAtributo);
		Calendar calendario = Calendar.getInstance();
		getView().init(calendario.get(Calendar.YEAR),
				calendario.get(Calendar.MONTH),
				calendario.get(Calendar.DAY_OF_MONTH), this);
	}

	// Quando a data for alterada, informa os observadores.
	@Override
	public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
		this.notifyChanged();
	}

	// Altera o valor da data.
	// A classe DatePicker possui um m�todo que permite a defini��o de uma data
	// a partir
	// do ano, m�s e dia, nesta ordem (updateDate).
	// No entanto, o atributo foi definido como tendo um valor do tipo Long
	// (veja generics no extends da classe).
	// Portanto, deve ser feita uma convers�o de Long para Date, para depois
	// serem recuperados o ano, m�s e dia.
	@Override
	protected void doSetAttributeValue(Object novoValor) {
		if (novoValor == null) {
			return;
		}
		Calendar calendario = Calendar.getInstance();
		calendario.setTimeInMillis((Long) novoValor);

		getView().updateDate(calendario.get(Calendar.YEAR),
				calendario.get(Calendar.MONTH),
				calendario.get(Calendar.DAY_OF_MONTH));
	}

	// Recupera o valor atual da data.
	// O componente DatePicker possui m�todos para recuperar o ano, m�s e dia da
	// data
	// atualmente selecionada.
	// No entanto, o valor que deve ser retornado � do tipo Long.
	// Portanto, deve ser feita uma convers�o de ano, m�s e dia para um �nico
	// valor Long.
	@Override
	public Long get() {
		Calendar calendario = Calendar.getInstance();
		calendario.set(getView().getYear(), getView().getMonth(), getView()
				.getDayOfMonth());
		return calendario.getTimeInMillis();
	}

}
