package br.com.mackenzie.conversor;

import gueei.binding.Converter;
import gueei.binding.IObservable;

/**
 * Esta classe implementa um conversor que verifica se um determinado valor �
 * inferior a outro.
 * 
 */
public class LESS extends Converter<Boolean> {
	public LESS(IObservable<?>[] dependents) {
		super(Boolean.class, dependents);
	}

	// Aplica o conversor e retorna o valor apropriado.
	public Boolean calculateValue(Object... parametros) throws Exception {
		if (parametros.length < 2) { // Se tiver menos que 2 par�metros, n�o aplica.
			return null;
		}
		if (parametros[0] == null || parametros[1] == null) { // Se os par�metros forem nulos, n�o aplica.
			return null;
		}
		double valor1 = 0, valor2 = 0;
		try {
			valor1 = Double.parseDouble(parametros[0].toString());
			valor2 = Double.parseDouble(parametros[1].toString());
		} catch (NumberFormatException erro) {
			return null;
		}

		return valor1 < valor2; // Se o valor do primeiro par�metro for estritamente menor que o do segundo, retorna verdadeiro.
	}

}