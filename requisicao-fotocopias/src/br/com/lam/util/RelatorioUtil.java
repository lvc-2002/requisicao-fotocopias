package br.com.lam.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.lam.model.Item;

import com.sun.xml.internal.ws.util.UtilException;

public class RelatorioUtil {
	
	public StreamedContent geraRelatorio(Map<String, Object> parametrosRelatorios, String nomeRelatorioSaida, Collection<Item> colecao) {
		StreamedContent arquivoRetorno = null;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			JasperReport jasper = JasperCompileManager.compileReport(context.getExternalContext().getRealPath("relatorios") + "/requisicao.jrxml");
			JasperPrint print = JasperFillManager.fillReport(jasper, parametrosRelatorios, new JRBeanCollectionDataSource(colecao));
			JasperExportManager.exportReportToPdfFile(print, context.getExternalContext().getRealPath("/relatorios/") + nomeRelatorioSaida + ".pdf");
			File arquivoGerado = new File(context.getExternalContext().getRealPath("/relatorios/") + nomeRelatorioSaida + ".pdf");
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
			exporter.exportReport();
			arquivoGerado.deleteOnExit();
			InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
			arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio, "application/pdf", nomeRelatorioSaida + ".pdf");
		} catch(JRException e) {
			throw new UtilException("Não foi possível gerar o relatório.", e);
		} catch(FileNotFoundException e) {
			throw new UtilException("Arquivo do relatório não encontrado.", e);
		}
		return arquivoRetorno;
	}

}
