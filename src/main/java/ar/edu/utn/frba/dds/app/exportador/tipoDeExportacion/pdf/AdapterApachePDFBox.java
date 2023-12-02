package ar.edu.utn.frba.dds.app.exportador.tipoDeExportacion.pdf;

import ar.edu.utn.frba.dds.app.config.Config;
import ar.edu.utn.frba.dds.app.exceptions.ExportadorException;
import ar.edu.utn.frba.dds.app.exportador.exportable.Exportable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AdapterApachePDFBox implements AdapterExportadorPDF {
    private final String nombreDeArchivo;

    public AdapterApachePDFBox(String nombreDeArchivo) {
        this.nombreDeArchivo = nombreDeArchivo;
    }

    public String exportar(Exportable exportable) {
        try (PDDocument doc = new PDDocument()) {
            PDPage myPage = new PDPage();
            doc.addPage(myPage);
            PDPageContentStream cont = new PDPageContentStream(doc, myPage);
            cont.beginText();
            cont.setLeading(14.5f);
            cont.newLineAtOffset(25, 700);
            agregarTitulo(cont);
            cont.setFont(PDType1Font.TIMES_ROMAN, 12);

            agregarDatos(cont, exportable.datos());

            cont.endText();
            cont.close();
            doc.save(rutaCompletaDelArchivo());
        } catch (IOException e) {
            throw new ExportadorException("Error al exportar datos como PDF: " + e.getMessage());
        }
        return rutaCompletaDelArchivo();
    }

    private String rutaCompletaDelArchivo(){
        return Config.RUTA_EXPORTACION + this.nombreDeArchivo;
    }

    private void agregarTitulo(PDPageContentStream contentStream) throws IOException {
        contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
        contentStream.showText("Ranking de incidentes");
        contentStream.newLine();
        contentStream.newLine();
    }

    private void agregarDatos(PDPageContentStream pagina, Map<String, List<String>> datos) throws IOException {
        for (Map.Entry<String, List<String>> entry : datos.entrySet()) {
            pagina.newLine();
            String datosDeLaFila = String.join(", ", entry.getValue());
            pagina.showText(datosDeLaFila);
        }
    }

}
