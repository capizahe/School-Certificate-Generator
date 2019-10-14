package certificados;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import condiciones.HeaderFooterPageEvent;
import datos.Matricula;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CertificadoMatricula extends Application {

	private Pane pane;
	private Label titulo;
	private Matricula matricula;
	private  static PdfFont regular;
	private static PdfFont  bold;	

	public CertificadoMatricula(){
		this.titulo= new Label("CERTIFICADO DE MATRICULA");
		this.titulo.setStyle("-fx-text-fill:green;-fx-font-family:arial bold");
		
		try {
			regular = PdfFontFactory.createFont("c:/windows/fonts/arial.ttf",PdfEncodings.IDENTITY_H,true);
			bold = PdfFontFactory.createFont("c:/windows/fonts/arialbd.ttf",PdfEncodings.IDENTITY_H,true);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.pane= new Pane();
		this.titulo.setLayoutX(70);
		this.titulo.setLayoutY(10);
		this.titulo.setFont(new Font("Arial Black", 20));
		this.pane.getChildren().add(titulo);
		stage.setTitle("Certificado de matricula");
		Scene scene = new Scene(pane,400,400);

		crearCampos();

		stage.setScene(scene);
		stage.show();
	}

	private void crearCampos() {

		Label labelnombre = new Label("Primer nombre:");
		final TextField nombre = new TextField();
		nombre.setFocusTraversable(true);
		VBox boxNombre = new VBox(labelnombre,nombre);
		boxNombre.setLayoutX(30);
		boxNombre.setLayoutY(50);


		Label labelsnombre = new Label("Segundo nombre:");
		final TextField snombre = new TextField();
		snombre.setFocusTraversable(true);
		VBox boxsNombre = new VBox(labelsnombre,snombre);
		boxsNombre.setLayoutX(30);
		boxsNombre.setLayoutY(100);

		Label labelapellido = new Label("Primer apellido:");
		final TextField apellido = new TextField();
		apellido.setFocusTraversable(true);
		VBox boxapellido = new VBox(labelapellido,apellido);
		boxapellido.setLayoutX(30);
		boxapellido.setLayoutY(150);

		Label labelsapellido = new Label("Segundo apellido:");
		final TextField sapellido = new TextField();
		sapellido.setFocusTraversable(true);
		VBox boxsapellido = new VBox(labelsapellido,sapellido);
		boxsapellido.setLayoutX(30);
		boxsapellido.setLayoutY(200);

		Label labelgenero = new Label("Genero:");
		final ChoiceBox<String> genero = new ChoiceBox<String>(FXCollections.observableArrayList("MASCULINO","FEMENINO"));		
		genero.setFocusTraversable(true);
		VBox boxgenero = new VBox(labelgenero,genero);
		boxgenero.setLayoutX(30);
		boxgenero.setLayoutY(250);
		//genero.getSelectionModel().select(1);

		Label labelano = new Label("Año:");
		final TextField ano = new TextField();
		ano.setFocusTraversable(true);
		VBox boxano = new VBox(labelano,ano);
		boxano.setLayoutX(230);
		boxano.setLayoutY(50);

		Label labeltdoc = new Label("Tipo de documento:");
		final ChoiceBox<String> tipo_documento = new ChoiceBox<String>(FXCollections.observableArrayList
				("tarjeta de identidad","cedula de ciudadania","pasaporte","NIT"));
		tipo_documento.setFocusTraversable(true);
		VBox boxtdoc = new VBox(labeltdoc,tipo_documento);
		boxtdoc.setLayoutX(230);
		boxtdoc.setLayoutY(100);

		Label labelndoc = new Label("Numero documento:");
		final TextField numero_documento = new TextField();
		numero_documento.setFocusTraversable(true);
		VBox boxndoc = new VBox(labelndoc,numero_documento);
		boxndoc.setLayoutX(230);
		boxndoc.setLayoutY(150);
		
		Label labelNEE = new Label("Necesidades Especiales:");
		final ChoiceBox<String> NEE = new ChoiceBox<String>(FXCollections.observableArrayList
				("Si","No"));
		NEE.setFocusTraversable(true);
		VBox boxNEE = new VBox(labelNEE,NEE);
		boxNEE.setLayoutX(230);
		boxNEE.setLayoutY(250);

		Label labelcurso = new Label("Curso Actual:");
		final ChoiceBox<String> curso = new ChoiceBox<String>(FXCollections.observableArrayList
				("KINDER","TRANSICIÓN","PRIMERO","SEGUNDO","TERCERO","CUARTO","QUINTO","SEXTO","SEPTIMO","OCTAVO","NOVENO","DECIMO","ONCE"));
		
		
		curso.setFocusTraversable(true);
		VBox boxcurso = new VBox(labelcurso,curso);
		boxcurso.setLayoutX(230);
		boxcurso.setLayoutY(200);

		ToggleButton button = new ToggleButton("generar certificado");

		button.setLayoutX(140);
		button.setLayoutY(350);

		pane.getChildren().addAll(boxNombre,boxsNombre,boxapellido,boxsapellido,boxgenero,boxano,boxtdoc,boxndoc,boxcurso,button,boxNEE);

		button.setOnMouseClicked(e->{
			saveData(nombre.getText(), snombre.getText(), apellido.getText(), sapellido.getText(), tipo_documento.getSelectionModel().getSelectedItem(),
					numero_documento.getText(), ano.getText(),curso.getSelectionModel().getSelectedItem(), "",genero.getSelectionModel().getSelectedItem(),NEE.getSelectionModel().getSelectedItem());
		});   
	}

	private void saveData(String nombre, String segundo_nombre, String apellido, String segundo_apellido,
			String tipo_documento, String numero_documento, String ano, String curso, String proyecto,String genero,String necesidades_especiales) {

		this.matricula= new Matricula(nombre,segundo_nombre,apellido,segundo_apellido,tipo_documento,numero_documento,ano,curso,proyecto,genero);
        this.matricula.setNecesidades_especiales(necesidades_especiales.equals("Si")?true:false);
		generarPdf(this.matricula);
	}

	private Paragraph setPdfData(Matricula matricula) {
		Paragraph parrafo=null;
		String nee= (matricula.isNecesidades_especiales())?" PROYECTO DE INCLUSIÓN ESCOLAR PARA ESTUDIANTES CON NECESIDADES EDUCATIVAS ESPECIALES ":" ";
		String tipo_educacion= matricula.getTipoEducacion();
		if(matricula.getGenero().equals("MASCULINO")) {
		parrafo =new Paragraph()
				.add(new Text("El " + "estudiante" + " "))
				.add(new Text(matricula.getApellido()+" "+matricula.getSegundo_apellido()+" "+matricula.getNombre()+" "+matricula.getSegundo_nombre()).setFont(bold))
				.add(new Text(" identificado con" +matricula.getTipo_documento()+" no. "+matricula.getNumero_documento()+" se encuentra matriculado en la institución en el grado").setFont(regular))
				.add(new Text(" "+matricula.getCurso()+" EDUCACIÓN "+tipo_educacion+" "+nee).setFont(bold))
				.add(new Text("en el presente año lectivo ").setFont(regular))
				.add(new Text(matricula.getAno()+"."));
		}
		else {
			parrafo =new Paragraph()
					.add(new Text("La " + "estudiante" + " "))
					.add(new Text(matricula.getApellido()+" "+matricula.getSegundo_apellido()+" "+matricula.getNombre()+" "+matricula.getSegundo_nombre()).setFont(bold))
					.add(new Text(" identificada con" +matricula.getTipo_documento()+" no. "+matricula.getNumero_documento()+" se encuentra matriculada en la institución en el grado").setFont(regular))
					.add(new Text(" "+matricula.getCurso()+" EDUCACIÓN "+tipo_educacion+" "+nee).setFont(bold))
					.add(new Text("en el presente año lectivo ").setFont(regular))
					.add(new Text(matricula.getAno()+"."));
		}
		
		return parrafo;
	}

	private void generarPdf(Matricula matricula) {

		try {
			File folder = new File(System.getProperty("user.home"),"/Desktop/certificados");
			if(!folder.exists()) folder.mkdir();

			PdfWriter writer = new PdfWriter(new File(folder.getAbsoluteFile()+"/"+matricula.getApellido()+"_"+matricula.getSegundo_apellido()+"_"+matricula.getNombre()+"_"+matricula.getSegundo_nombre()+".pdf"));
			PdfDocument pdf= new PdfDocument(writer);
			Document document= new Document(pdf,PageSize.LETTER);
			try {

				//Espacios titulo
				document.add(new Paragraph("\n\n\n\n\n\n\n\n"));
				//Titulo
				Paragraph tittle= new Paragraph("EL RECTOR DEL COLEGIO RICAURTE DE SOACHA EU").setFont(bold).setFontSize(16);
				tittle.setTextAlignment(TextAlignment.CENTER);
				document.add(tittle);
				document.add(new Paragraph("\n\n"));
				Paragraph tittle2= new Paragraph("CERTIFICA QUE:").setFont(bold).setFontSize(16);
				tittle2.setTextAlignment(TextAlignment.CENTER);
				//tamaño);
				document.add(tittle2);
				document.add(new Paragraph("\n\n"));

				Paragraph texto = setPdfData(matricula);
				texto.setTextAlignment(TextAlignment.JUSTIFIED);

				document.add(texto);
				document.add(new Paragraph("Con una intensidad de 35 horas semanales. jornada única.").setFont(regular));
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Atentamente;").setFont(regular));
				document.add(new Paragraph("\n\n"));
				document.add(new Paragraph("YONATHAN ORTIZ BAQUERO").setFont(bold));
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Rector").setFont(bold));
	
				HeaderFooterPageEvent event = new HeaderFooterPageEvent(document);

				pdf.addEventHandler(PdfDocumentEvent.END_PAGE, event);
				document.close();
			} catch (Exception e) {	
			    System.out.println("Aqui fue.");
			    
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
		    System.err.println("Aqui fue..");

			e.printStackTrace();
		}

	}

}
