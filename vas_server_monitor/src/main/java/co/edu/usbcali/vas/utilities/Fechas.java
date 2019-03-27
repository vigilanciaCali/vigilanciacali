package co.edu.usbcali.vas.utilities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


public class Fechas {
	static long milisegundos_dia = 24 * 60 * 60 * 1000;
	
	public static java.util.Date strToDate(String strFecha, String patron) {

		try {
			return new SimpleDateFormat(patron).parse(strFecha);
		} catch (ParseException pex) {
			pex.printStackTrace();
		}
		return null;
	}

	public static Date strToDateFormat(String strFecha, String patronInicial, String patronFinal) {

		try {

			DateFormat readFormat = new SimpleDateFormat(patronInicial);
			DateFormat writeFormat = new SimpleDateFormat(patronFinal);

			Date date = null;
			date = readFormat.parse(strFecha.replaceAll(":0000", ":00"));

			String formattedDate = "";
			formattedDate = writeFormat.format(date);

			Date finalDate = strToDate(formattedDate, patronFinal);

			return finalDate;

		} catch (ParseException pex) {
			pex.printStackTrace();
		}
		return null;
	}

	public static String dateToStr(java.util.Date fecha, String patron) {
		try {
			Format formatter = new SimpleDateFormat(patron);
			if (fecha != null) {
				return formatter.format(fecha);
			}
		} catch (Exception e) {
			System.out.println("Error en dateToStr (" + fecha + "," + patron + "): " + e.toString());
		}
		return "";
	}

	public static java.util.Date numberToDate(String fechaNumerica) {
		java.util.Date convertDate = null;
		String fechaTotal = "";
		String patron = "yyyy/MM/dd";
		try {
			if ((fechaNumerica != null) && (!fechaNumerica.equals("")) && (Utilities.isNumeric(fechaNumerica))
					&& (fechaNumerica.trim().length() == 8)) {
				String ano = fechaNumerica.substring(0, 4);
				String mes = fechaNumerica.substring(4, 6);
				String dia = fechaNumerica.substring(6, 8);
				if ((ano.equals("")) || (mes.equals("")) || (dia.equals(""))) {
					return convertDate;
				}
				fechaTotal = ano + "/" + mes + "/" + dia;
				return new SimpleDateFormat(patron).parse(fechaTotal);
			}
			System.out.println("Error en las validaciones de " + fechaNumerica);
		} catch (ParseException pex) {
			System.out.println("Error tratando de obtener una fecha de " + fechaNumerica);
		}
		return convertDate;
	}

	public static double diasEntreFechas(java.util.Date fechafinal, java.util.Date fechainicial) {
		try {
			return (fechafinal.getTime() - fechainicial.getTime()) / 86400000L;
		} catch (Exception e) {
		}
		return 0.0D;
	}

	public static java.util.Date sumarConMinutos(java.util.Date fecha, int dias, int meses, int aF1os, int semanas,
			int minutos) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.add(5, dias);
			c.add(2, meses);
			c.add(1, aF1os);
			c.add(5, semanas * 7);
			c.add(12, minutos);
			return c.getTime();
		} catch (Exception e) {
			System.out.println("Error tratando de sumar dias a fecha");
		}
		return new java.util.Date();
	}

	public static java.util.Date sumarConMinutosYHoras(java.util.Date fecha, int dias, int meses, int aF1os,
			int semanas, int horas, int minutos) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.add(5, dias);
			c.add(2, meses);
			c.add(1, aF1os);
			c.add(5, semanas * 7);
			c.add(10, horas);
			c.add(12, minutos);
			return c.getTime();
		} catch (Exception e) {
			System.out.println("Error tratando de sumar dias a fecha");
		}
		return new java.util.Date();
	}

	public static java.util.Date sumar(java.util.Date fecha, int dias, int meses, int aF1os, int semanas) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.add(5, dias);
			c.add(2, meses);
			c.add(1, aF1os);
			c.add(5, semanas * 7);
			return c.getTime();
		} catch (Exception e) {
			System.out.println("Error tratando de sumar dias a fecha");
		}
		return new java.util.Date();
	}

	public static int getDiaDelMes(java.util.Date fecha) {
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		return c.get(5);
	}

	public static int getDiaDeLaSemana(java.util.Date fecha) {
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		return c.get(7);
	}

	public static int getSemanaDelAF1o(java.util.Date fecha) {
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		return c.get(3);
	}

	public static java.util.Date sumar(java.util.Date fecha, int dias, int meses, int aF1os, int semanas,
			int diaDeLaSemanaQueQueda) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.add(5, dias);
			c.add(2, meses);
			c.add(1, aF1os);
			c.add(5, semanas * 7);
			c.set(7, diaDeLaSemanaQueQueda);
			fecha = c.getTime();
			System.out.println("Quedo en " + dateToStr(fecha, "dd/MMMM/yyyy"));
			return fecha;
		} catch (Exception e) {
			System.out.println("Error tratando de sumar dias a fecha");
		}
		return new java.util.Date();
	}

	public static java.util.Date moverHastaDiaDeLaSemana(java.util.Date fecha, int dia, boolean retrocediendo) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			if ((!retrocediendo) && (dia < c.get(7))) {
				fecha = sumar(fecha, 0, 0, 0, 1);
				c.setTime(fecha);
			}
			c.set(7, dia);
			return c.getTime();
		} catch (Exception e) {
			System.out.println("Error tratando de moverHastaDiaDeLaSemana una fecha");
		}
		return new java.util.Date();
	}

	public static java.util.Date moverHastaDiaDeLaSemana(java.util.Date fecha, int dia, boolean retrocediendo,
			int semanasStep) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			if ((!retrocediendo) && (dia < c.get(7))) {
				fecha = sumar(fecha, 0, 0, 0, 1 * semanasStep);
				c.setTime(fecha);
			}
			c.set(7, dia);
			return c.getTime();
		} catch (Exception e) {
			System.out.println("Error tratando de moverHastaDiaDeLaSemana una fecha");
		}
		return new java.util.Date();
	}

	public static java.util.Date setDayOfTheWeek(java.util.Date fecha, int dia) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.set(7, dia);
			return c.getTime();
		} catch (Exception e) {
			System.out.println("Error tratando de setDayOfTheWeek");
		}
		return new java.util.Date();
	}

	public static java.util.Date moverHastaDiaDelMes(java.util.Date fecha, int dia, boolean retrocediendo) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			if ((!retrocediendo) && (dia < c.get(5))) {
				fecha = sumar(fecha, 0, 1, 0, 0);
				c.setTime(fecha);
			}
			while (!ExisteDiaEnMes(dia, c.get(2), c.get(1))) {
				dia--;
			}
			c.set(5, dia);
			return c.getTime();
		} catch (Exception e) {
			System.out.println("Error tratando de moverHastaDiaDelMes una fecha");
		}
		return new java.util.Date();
	}

	public static boolean ExisteDiaEnMes(int dia, int mes, int aF1o) {
		if (mes == 1) {
			if (aF1o % 4 == 0) {
				return (dia >= 1) && (dia <= 29);
			}
			return (dia >= 1) && (dia <= 28);
		}
		if ((mes == 0) || (mes == 2) || (mes == 4) || (mes == 6) || (mes == 7) || (mes == 9) || (mes == 11)) {
			return (dia >= 1) && (dia <= 31);
		}
		return (dia >= 1) && (dia <= 30);
	}

	public static java.util.Date moverHastaPrimerDiaDelMes(java.util.Date fecha) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.set(5, 1);
			return c.getTime();
		} catch (Exception e) {
			System.out.println("Error tratando de moverHastaDiaDelMes una fecha");
		}
		return new java.util.Date();
	}

	public static java.util.Date moverHastaDiaDelAF1o(java.util.Date fecha, int dia, int mes, int aF1o,
			boolean retrocediendo) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			if (aF1o == -1) {
				aF1o = c.get(1);
			}
			if ((!retrocediendo) && (dia < c.get(5))) {
				fecha = sumar(fecha, 0, 1, 0, 0);
				c.setTime(fecha);
			}
			c.set(1, aF1o);
			c.set(2, mes);
			while (!ExisteDiaEnMes(dia, c.get(2), c.get(1))) {
				dia--;
			}
			c.set(5, dia);
			return c.getTime();
		} catch (Exception e) {
			System.out.println("Error tratando de moverHastaDiaDelMes una fecha");
		}
		return new java.util.Date();
	}

	public static XMLGregorianCalendar convertirDateAXMLGregorianDate(java.util.Date fecha) throws Exception {
		if (fecha == null) {
			return null;
		}
		GregorianCalendar gregorianCalendar = new GregorianCalendar();

		gregorianCalendar.setTime(fecha);
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		} catch (Exception e) {
			throw e;
		}
	}

	public static java.util.Date sumarMesesAFecha(java.util.Date fecha, int numeroMeses) {
		try {
			Calendar c = DateToCalendar(fecha);
			c.add(2, numeroMeses);
			return c.getTime();
		} catch (Exception e) {
			System.out.println("Error tratando de sumar Meses a una fecha");
		}
		return null;
	}

	public static Calendar DateToCalendar(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static java.util.Date restarDias(java.util.Date fch, int dias) {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(fch.getTime());
		cal.add(5, -dias);
		return new java.util.Date(cal.getTimeInMillis());
	}

	public static boolean verificarFechaEntreRangoDeFechas(java.util.Date fechaActual,
			java.util.Date fechaUltimoIngreso, Long diasMaximosPermitidos) {
		boolean fechaPermitida = false;
		try {
			java.util.Date fechaMaximaPermitida = sumar(fechaUltimoIngreso, diasMaximosPermitidos.intValue(), 0, 0, 0);
			if (fechaActual.getTime() < fechaMaximaPermitida.getTime()) {
				fechaPermitida = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fechaPermitida;
	}

	public static java.util.Date trasnformDiaHoraSeparadoToDate(java.util.Date fechaSeleccionada,
			String horaConMinutos) {
		java.util.Date fecha = null;
		String patronRetorna = "yyyy/MM/dd HH:mm:ss";
		String patronOnlyFechaSinHoras = "yyyy/MM/dd";
		try {
			String fechaRecortadaStringConcatenada = dateToStr(fechaSeleccionada, patronOnlyFechaSinHoras) + " "
					+ horaConMinutos + ":00";
			fecha = strToDate(fechaRecortadaStringConcatenada, patronRetorna);
		} catch (Exception e) {
			System.out.println("Error tratando de obtener una fecha de " + fechaSeleccionada);
		}
		return fecha;
	}

	public static int getDayOfTheWeek(java.util.Date fecha) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fecha);
		return cal.get(7);
	}



	public static boolean compararHoras(String horaInicial, String horaFinal) {
		java.util.Date horaIni = null;
		java.util.Date horaFin = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			horaIni = dateFormat.parse(horaInicial);
			horaFin = dateFormat.parse(horaFinal);
			if (horaIni.compareTo(horaFin) < 0) {
				return true;
			}
			return false;
		} catch (ParseException ex) {
			System.out.println("Posee errores");
		}
		return false;
	}

	public static Date finalizarDate(Date input) {
		java.util.Calendar memory = java.util.Calendar.getInstance();
		memory.setTime(input);
		memory.set(java.util.Calendar.HOUR_OF_DAY, 23);
		memory.set(java.util.Calendar.MINUTE, 59);
		memory.set(java.util.Calendar.SECOND, 59);
		return memory.getTime();
	}

	public static Date initDate(Date input) {
		java.util.Calendar memory = java.util.Calendar.getInstance();
		memory.setTime(input);
		memory.set(java.util.Calendar.HOUR_OF_DAY, 0);
		memory.set(java.util.Calendar.MINUTE, 0);
		memory.set(java.util.Calendar.SECOND, 0);
		return memory.getTime();
	}

	public static Date getFinalDate(Date date) throws Exception {

		try {

			Calendar initialDate = Calendar.getInstance();
			initialDate.setTime(date);
			initialDate.set(java.util.Calendar.HOUR_OF_DAY, 23);
			initialDate.set(java.util.Calendar.MINUTE, 59);
			initialDate.set(java.util.Calendar.SECOND, 59);
			date = initialDate.getTime();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date dateFormat(java.util.Date fecha, String patron) {
		try {
			Format formatter = new SimpleDateFormat(patron);
			if (fecha != null) {
				return strToDate(formatter.format(fecha), patron);
			}
		} catch (Exception e) {
			System.out.println("Error en dateToStr (" + fecha + "," + patron + "): " + e.toString());
		}
		return null;
	}

	public static java.util.Calendar today() {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.set(calendar.get(java.util.Calendar.YEAR), calendar.get(java.util.Calendar.MONTH),
				calendar.get(java.util.Calendar.DATE), 0, 0, 0);

		return calendar;
	}

	public static Date dateToday() {
		Date today = null;
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.set(calendar.get(java.util.Calendar.YEAR), calendar.get(java.util.Calendar.MONTH),
				calendar.get(java.util.Calendar.DATE), 0, 0, 0);
		today = calendar.getTime();

		return today;
	}

	public static Date tomorrow() {
		Date tomorrow = null;

		Calendar calDiaSiguiente = Calendar.getInstance();
		calDiaSiguiente.add(Calendar.DAY_OF_YEAR, 1);
		tomorrow = calDiaSiguiente.getTime();

		return tomorrow;
	}

	// ---------------------------------------------------------------------------------

	/*
	 * Metodo que calcula la diferencia de las horas que han pasado entre dos
	 * fechas en java
	 */
	public static long diferenciaHorasDias(Calendar fechaInicial, Calendar fechaFinal) {
		// Milisegundos al día
		long diferenciaHoras = 0;
		// Restamos a la fecha final la fecha inicial y lo dividimos entre el
		// numero de milisegundos al dia
		diferenciaHoras = (fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / milisegundos_dia;
		if (diferenciaHoras > 0) {
			// Lo Multiplicaos por 24 por que estamos utilizando el formato
			// militar
			diferenciaHoras *= 24;
		}
		return diferenciaHoras;
	}

	/*
	 * Metodo que calcula la diferencia de los minutos entre dos fechas
	 */
	public static long diferenciaMinutos(Calendar fechaInicial, Calendar fechaFinal) {

		long diferenciaHoras = 0;
		diferenciaHoras = (fechaFinal.get(Calendar.MINUTE) - fechaInicial.get(Calendar.MINUTE));
		return diferenciaHoras;
	}

	/*
	 * Metodo que devuelve el Numero total de minutos que hay entre las dos
	 * Fechas
	 */
	public static long cantidadTotalMinutos(Calendar fechaInicial, Calendar fechaFinal) {

		long totalMinutos = 0;
		totalMinutos = ((fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / 1000 / 60);
		return totalMinutos;
	}
	
	public static long cantidadTotalMinutosSum(Calendar fechaInicial, Calendar fechaFinal) {

		long totalMinutos = 0;
		totalMinutos = ((fechaFinal.getTimeInMillis() + fechaInicial.getTimeInMillis()) / 1000 / 60);
		return totalMinutos;
	}

	/*
	 * Metodo que devuelve el Numero total de horas que hay entre las dos Fechas
	 */
	public static long cantidadTotalHoras(Calendar fechaInicial, Calendar fechaFinal) {

		long totalMinutos = 0;
		totalMinutos = ((fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / 1000 / 60 / 60);
		return totalMinutos;
	}

	/*
	 * Metodo que devuelve el Numero total de Segundos que hay entre las dos
	 * Fechas
	 */
	public static long cantidadTotalSegundos(Calendar fechaInicial, Calendar fechaFinal) {

		long totalMinutos = 0;
		totalMinutos = ((fechaFinal.getTimeInMillis() - fechaInicial.getTimeInMillis()) / 1000);
		return totalMinutos;
	}

	/* Metodo que calcula la diferencia de las horas entre dos fechas */
	public static long diferenciaHoras(Calendar fechaInicial, Calendar fechaFinal) {
		long diferenciaHoras = 0;
		diferenciaHoras = (fechaFinal.get(Calendar.HOUR_OF_DAY) - fechaInicial.get(Calendar.HOUR_OF_DAY));

		return diferenciaHoras;
	}

	public static java.util.Date actualDateWithTimeStamp() {
		return new Timestamp(new java.util.Date().getTime());
	}

	public static java.sql.Date sqlDate(java.util.Date calendarDate) {
		return new java.sql.Date(calendarDate.getTime());
	}
	
	public static Date getFirstDayOfActualMonth(){
		Date firstDay = null;

		java.util.Calendar first = java.util.Calendar.getInstance();   // this takes current date
		first.set(java.util.Calendar.DAY_OF_MONTH, 1);
		
		firstDay = first.getTime();
		return firstDay;
	}
	
	public static Date getLastDayOfActualMonth(){
		Date lastDay = null;

		java.util.Calendar last = java.util.Calendar.getInstance();
		last.set(java.util.Calendar.DATE, last.getActualMaximum(java.util.Calendar.DATE));
		
		lastDay = last.getTime();
		return lastDay;
	}

	public static void main(String[] args) {
		System.out.println(sqlDate(new java.util.Date()));
	}
}
