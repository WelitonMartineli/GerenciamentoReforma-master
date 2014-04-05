package br.com.mackenzie;

import gueei.binding.AttributeBinder;
import gueei.binding.v30.DefaultKernelV30;
import br.com.mackenzie.datepicker.DatePickerBindingProvider;

/**
 * Esta classe implementa um kernel personalizado que registra um provedor criado para o componente DatePicker
 * (ainda não suportador pelo framework).
 * Este kernel deve ser referenciado no processo de inicialização do framework, geralmente realizado em subclasse
 * de Application.
 * @author Leandro Luque, Érico Veriscimo, Girdácio Pereira
 * @version 1.0
 * @since 1.0
 */
public class NovoKernel extends DefaultKernelV30 {

	@Override
	protected void registerProviders(AttributeBinder attrBinder) {
		super.registerProviders(attrBinder);
		// Registra um provedor personalizado para o componente DatePicker (ainda não suportado no framework).
		getAttributeBinder().registerProvider(new DatePickerBindingProvider());
	}
	
}
