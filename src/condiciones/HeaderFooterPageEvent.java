package condiciones;

import java.io.File;
import java.net.URISyntaxException;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

public class HeaderFooterPageEvent implements IEventHandler {

	private final Document documento;

	public HeaderFooterPageEvent(Document doc) {
		documento = doc;
	}

	private Rectangle crearRectanguloEncabezado(PdfDocumentEvent docEvent) {
		PdfDocument pdfDoc = docEvent.getDocument();
		PdfPage page = docEvent.getPage();        

		float xEncabezado = pdfDoc.getDefaultPageSize().getX() + documento.getLeftMargin();
		float yEncabezado = pdfDoc.getDefaultPageSize().getTop() - documento.getTopMargin();
		float anchoEncabezado = page.getPageSize().getWidth() - 72;
		float altoEncabezado = 50F;

		Rectangle rectanguloEncabezado = new Rectangle(xEncabezado, yEncabezado, anchoEncabezado, altoEncabezado);
		return rectanguloEncabezado;        
	}

	@SuppressWarnings("resource")
	@Override
	public void handleEvent(Event event) {
		
		try {
			try {



				PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
				PdfDocument pdfDoc = docEvent.getDocument();
				PdfPage page = docEvent.getPage();
				PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);        

				ImageData imageFile;
				ImageData imageFile2;
				ImageData imageFile3;

				imageFile = ImageDataFactory.create(getClass().getClassLoader().getResource("logop1.png"));
				Image image= new Image(imageFile);
				imageFile2 = ImageDataFactory.create(getClass().getClassLoader().getResource("logop2.png"));
				Image image2= new Image(imageFile2);
				imageFile3 = ImageDataFactory.create(getClass().getClassLoader().getResource("pieCertificado.PNG"));
				Image image3= new Image(imageFile3);
				//Escudo
				image.setFixedPosition(65f,630f,100f);
				//Info
				image2.setFixedPosition(170f,650f,350f);
				//footer
				float footerpos= pdfDoc.getDefaultPageSize().getBottom()- documento.getBottomMargin();
				image3.setFixedPosition(pdfDoc.getDefaultPageSize().getX() + documento.getLeftMargin() +30, pdfDoc.getDefaultPageSize().getBottom()-footerpos, 500f);

				new Canvas(canvas, pdfDoc, crearRectanguloEncabezado(docEvent))
				.add(image)
				.add(image2)		
				.add(image3)
				.close();

			}catch(Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}