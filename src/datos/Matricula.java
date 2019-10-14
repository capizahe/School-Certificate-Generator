package datos;

public class Matricula {

	private String nombre;
	private String segundo_nombre;
	private String apellido;
	private String segundo_apellido;
	private String tipo_documento;
	private String numero_documento;
	private String ano;
	private String curso;
	private boolean necesidades_especiales=false;
	private String proyecto;
	private String genero;
	private String tipo_educacion;



	public Matricula(String nombre, String segundo_nombre, String apellido, String segundo_apellido,
			String tipo_documento, String numero_documento, String ano, String curso, String proyecto, String genero) {
		this.nombre = nombre;
		this.segundo_nombre = segundo_nombre;
		this.apellido = apellido;
		this.segundo_apellido = segundo_apellido;
		this.tipo_documento = tipo_documento;
		this.numero_documento = numero_documento;
		this.ano = ano;
		this.curso = curso;
		this.proyecto = proyecto;
		this.genero = genero;
	}


	private String toUpperCase(String str) {
		String aux="";
		for (int i = 0; i < str.length(); i++) 
		aux+=Character.toUpperCase(str.charAt(i));
		return aux;
	}

	public String getNombre() {
	return toUpperCase(nombre);
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getSegundo_nombre() {
		return toUpperCase(segundo_nombre);
	}



	public void setSegundo_nombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}



	public String getApellido() {
		return toUpperCase(apellido);
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getSegundo_apellido() {
		return toUpperCase(segundo_apellido);
	}



	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}



	public String getTipo_documento() {
		return tipo_documento;
	}



	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}



	public String getNumero_documento() {
		return numero_documento;
	}



	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}



	public String getAno() {
		return ano;
	}



	public void setAno(String ano) {
		this.ano = ano;
	}



	public String getCurso() {
		return curso;
	}



	public void setCurso(String curso) {
		this.curso = curso;
	}



	public String getProyecto() {
		return proyecto;
	}



	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}



	public String getGenero() {
		return genero;
	}



	public void setGenero(String genero) {
		this.genero = genero;
	}



	@Override
	public String toString() {

		if(this.genero.equals("MASCULINO")) {
			return "El estudiante: "+apellido+" "+segundo_apellido+" "+nombre+" "+segundo_nombre +
					" identificado con " +tipo_documento+" no. "+ numero_documento +" se encuentra matriculado"+
					"en la institución en el grado "+ curso+ " DE EDUCACIÓN BÁSICA SECUNDARIA en el presente año lectivo "+ ano +"\n\n"
					+" con una intencidad de 35 horas semanales. Jornada única."+"\n\n\n"+"Atentamente;"
					;
		}
		else if(this.genero.equals("FEMENINO")) {
			return "La estudiante: "+apellido+" "+segundo_apellido+" "+nombre+" "+segundo_nombre +
					" identificada con " +tipo_documento+" no. "+ numero_documento +" se encuentra matriculada "+
					"en la institución en el grado "+ curso+ " DE EDUCACIÓN BÁSICA SECUNDARIA en el presente año lectivo "+ ano +"\n\n"
					+" con una intencidad de 35 horas semanales. Jornada única."+"\n\n\n"+"Atentamente;"
					;
		}

		return null;
	}


	public boolean isNecesidades_especiales() {
		return necesidades_especiales;
	}


	public void setNecesidades_especiales(boolean necesidades_especiales) {
		this.necesidades_especiales = necesidades_especiales;
	}

	public String getTipoEducacion() {
		
		if(curso.equals("KINDER") || curso.equals("TRANSICION")) 
			this.tipo_educacion ="EDUCACION PREESCOLAR";	
		if(curso.equals("PRIMERO") || curso.equals("SEGUNDO") || curso.equals("TERCERO") || curso.equals("CUARTO") || curso.equals("QUINTO"))
			this.tipo_educacion= "BASICA PRIMARIA";
		if(curso.equals("SEXTO") || curso.equals("SEPTIMO") || curso.equals("OCTAVO") || curso.equals("NOVENO"))
			this.tipo_educacion= "BASICA SECUNDARIA";
		if(curso.equals("DECIMO") || curso.equals("ONCE"))
			this.tipo_educacion= "MEDIA ACADEMICA";
	
		return this.tipo_educacion;
		
		
	}
}