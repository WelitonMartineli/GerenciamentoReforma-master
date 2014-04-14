package br.com.mackenzie.conversor;

import gueei.binding.Converter;
import gueei.binding.IObservable;

/**
 * Esta classe implementa um conversor que verifica se um determinado valor é
 * inferior a outro.
 * 
 */
public class LESS extends Converter<Boolean> {
	public LESS(IObservable<?>[] dependents) {
		super(Boolean.class, dependents);
	}

	// Aplica o conversor e retorna o valor apropriado.
	public Boolean calculateValue(Object... parametros) throws Exception {
		if (parametros.length < 2) { // Se tiver menos que 2 parâmetros, não aplica.
			return null;
		}
		if (parametros[0] == null || parametros[1] == null) { // Se os parâmetros forem nulos, não aplica.
			return null;
		}
		double valor1 = 0, valor2 = 0;
		try {
			valor1 = Double.parseDouble(parametros[0].toString());
			valor2 = Double.parseDouble(parametros[1].toString());
		} catch (NumberFormatException erro) {
			return null;
		}

		return valor1 < valor2; // Se o valor do primeiro parâmetro for estritamente menor que o do segundo, retorna verdadeiro.
	}

}