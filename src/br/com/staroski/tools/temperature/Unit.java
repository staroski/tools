package br.com.staroski.tools.temperature;

/**
 * Abstra&ccedil;&atilde;o para unidades de temperatura, disponibiliza m&eacute;todos para obter inst&acirc;ncias das unidades {@link Celsius}, {@link Farenheit} e {@link Kelvin}<BR>
 *  - Para obter inst&acirc;ncias de {@link Celsius}, utilize o m&eacute;todo {@link #celsius(double)}<BR>
 *  - Para obter inst&acirc;ncias de {@link Farenheit}, utilize o m&eacute;todo {@link #farenheit(double)}<BR>
 *  - Para obter inst&acirc;ncias de {@link Kelvin}, utilize o m&eacute;todo {@link #kelvin(double)}<BR>.
 * 
 * @author Ricardo Artur Staroski
 */
public abstract class Unit extends Number {

	/**
	 * Unidade de temperatura Celsius
	 * 
	 * @author Ricardo Artur Staroski
	 */
	public static class Celsius extends Unit {

		private static final long serialVersionUID = 1;

		private Celsius(double value) {
			super(value);
		}

		@Override
		public Celsius toCelsius() {
			return this;
		}

		@Override
		public Farenheit toFarenheit() {
			return farenheit(value * 1.8 + 32);
		}

		@Override
		public Kelvin toKelvin() {
			return kelvin(value + 273.15);
		}
	}

	/**
	 * Unidade de temperatura Farenheit
	 * 
	 * @author Ricardo Artur Staroski
	 */
	public static class Farenheit extends Unit {

		private static final long serialVersionUID = 1;

		private Farenheit(double value) {
			super(value);
		}

		@Override
		public Celsius toCelsius() {
			return celsius((value - 32) / 1.8);
		}

		@Override
		public Farenheit toFarenheit() {
			return this;
		}

		@Override
		public Kelvin toKelvin() {
			return kelvin((value + 459.67) * (5 / 9.0));
		}
	}

	/**
	 * Unidade de temperatura Kelvin
	 * 
	 * @author Ricardo Artur Staroski
	 */
	public static class Kelvin extends Unit {

		private static final long serialVersionUID = 1;

		private Kelvin(double value) {
			super(value);
		}

		@Override
		public Celsius toCelsius() {
			return celsius(value - 273.15);
		}

		@Override
		public Farenheit toFarenheit() {
			return farenheit((value / (5 / 9.0)) - 459.67);
		}

		@Override
		public Kelvin toKelvin() {
			return this;
		}
	}

	/**
	 * Obt&eacute;m uma inst&acirc;ncia da unidade {@link Celsius} com o valor informado
	 * 
	 * @param value O valor da unidade
	 * 
	 * @return A inst&acirc;ncia de {@link Celsius}
	 */
	public static Celsius celsius(double value) {
		return new Celsius(value);
	}

	/**
	 * Obt&eacute;m uma inst&acirc;ncia da unidade {@link Farenheit} com o valor informado
	 * 
	 * @param value O valor da unidade
	 * 
	 * @return A inst&acirc;ncia de {@link Farenheit}
	 */
	public static Farenheit farenheit(double value) {
		return new Farenheit(value);
	}

	/**
	 * Obt&eacute;m uma inst&acirc;ncia da unidade {@link Kelvin} com o valor informado
	 * 
	 * @param value O valor da unidade
	 * 
	 * @return A inst&acirc;ncia de {@link Kelvin}
	 */
	public static Kelvin kelvin(double value) {
		return new Kelvin(value);
	}

	private static final long serialVersionUID = 1;

	/**
	 * O valor encapsulado por esta unidade de temperatura
	 */
	public final double value;

	private Unit(double value) {
		this.value = value;
	}

	@Override
	public double doubleValue() {
		return value;
	}

	@Override
	public float floatValue() {
		return (float) value;
	}

	@Override
	public int intValue() {
		return (int) value;
	}

	@Override
	public long longValue() {
		return (long) value;
	}

	/**
	 * Converte esta unidade de temperatura para {@link Celsius}
	 * 
	 * @return O equivalente desta unidade de temperatura em {@link Celsius}
	 */
	public abstract Celsius toCelsius();

	/**
	 * Converte esta unidade de temperatura para {@link Farenheit}
	 * 
	 * @return O equivalente desta unidade de temperatura em {@link Farenheit}
	 */
	public abstract Farenheit toFarenheit();

	/**
	 * Converte esta unidade de temperatura para {@link Kelvin}
	 * 
	 * @return O equivalente desta unidade de temperatura em {@link Kelvin}
	 */
	public abstract Kelvin toKelvin();

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}