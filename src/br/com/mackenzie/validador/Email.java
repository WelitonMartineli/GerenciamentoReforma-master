package br.com.mackenzie.validador;

import gueei.binding.labs.validation.ValidatorBase;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.regex.Pattern;

import android.content.Context;

@Retention(RetentionPolicy.RUNTIME)
/**
 * Validador personalizado de e-mails.
 */
public @interface Email {

	// Método obrigatório para a definição da classe do validador.
	public Class<?> Validator() default ValidadorEmail.class;

	// Atributos da anotação.
	public String Padrao() default "[a-zA-Z0-9]+[a-zA-Z0-9_.-]+@{1}[a-zA-Z0-9_.-]*\\.+[a-z]{2,4}";

	public String ErrorMessage() default "O campo %nomeCampo% não contém um e-mail válido";

	public String ErrorMessageRes() default "";

	public class ValidadorEmail extends ValidatorBase<Email> {

		@Override
		public Class<Email> getAcceptedAnnotation() {
			return Email.class;
		}
		
		// Formata e retorna uma mensagem de erro.
		@Override
		protected String doFormatErrorMessage(Context contexto,
				Email parametros, String nomeCampo, String mensagemErro) {
			return mensagemErro.replace("%nomeCampo%", nomeCampo);
		}

		// Valida o valor recebido.
		@Override
		protected boolean doValidate(Context contexto, Object valor,
				Email parametros, Object modelo) {
			if (valor == null)
				return false;
			if (valor instanceof CharSequence) {
				return Pattern.matches(parametros.Padrao(),
						((CharSequence) valor));
			}
			return true;
		}

	}
}
