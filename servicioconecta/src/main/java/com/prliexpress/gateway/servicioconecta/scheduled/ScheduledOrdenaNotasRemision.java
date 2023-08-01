package com.prliexpress.gateway.servicioconecta.scheduled;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.prliexpress.gateway.servicioconecta.DTO.FechaCreacionNotaRemision;
import com.prliexpress.gateway.servicioconecta.DTO.NotaRemisionFechaImpresion;
import com.prliexpress.gateway.servicioconecta.DTO.TalonesFolioInterno;
import com.prliexpress.gateway.servicioconecta.enums.RutasScheduled;
import com.prliexpress.gateway.servicioconecta.repositoryperli.TalonesEmpleadosRepository;
import com.prliexpress.gateway.servicioconecta.repositoryperli.TalonesFolioInternoRepository;

import com.prliexpress.gateway.servicioconecta.util.AsignaNombreMesSegunConsulta;
import com.prliexpress.gateway.servicioconecta.util.GenerarRutasArchivos;

@Component
public class ScheduledOrdenaNotasRemision {

   private static final Logger logScheduledOrdenaNotasRemision = LogManager.getLogger(ScheduledOrdenaNotasRemision.class);
   AsignaNombreMesSegunConsulta obtengoNombreMes = new AsignaNombreMesSegunConsulta();
   GenerarRutasArchivos rutaMoverArchivos = new GenerarRutasArchivos();
   private String[] nombreArchivo;
   private SimpleDateFormat traerAnio = new SimpleDateFormat("yyyy");
   private SimpleDateFormat traerDia = new SimpleDateFormat("dd");
   private SimpleDateFormat traerMes = new SimpleDateFormat("MM");
   private List<TalonesFolioInterno> obtieneFolioInternoTalones;
   FechaCreacionNotaRemision fechaDocumento = null;
   private static final String TIME_ZONE = "America/Mexico_City"; 

   @Autowired
   private TalonesEmpleadosRepository talonesEmpleadosRepository;

   @Autowired
   private TalonesFolioInternoRepository talonesFolioInterno;

   @Scheduled(cron = "*/10 * * * * *",zone = TIME_ZONE)
   public void ejecucionParaMoverArchivos() throws IOException {
      File carpetaPrincipal = new File(RutasScheduled.RUTAPRINCIPAL.getRuta());
      if(carpetaPrincipal.exists())
    	  logScheduledOrdenaNotasRemision.info("Se pudo acceder a la carpeta //ServConexion/FTP/ pasa al siguiente movimiento");
      leerArchivosDeCarpetaPrincipal(carpetaPrincipal);
   }

   public void leerArchivosDeCarpetaPrincipal(final File carpetaPrincipal) {
      for (final File ficheroEntrada : carpetaPrincipal.listFiles()){
         nombreArchivo = ficheroEntrada.getName().toString().split("_");                 
         if (nombreArchivo.length == 4){
        	logScheduledOrdenaNotasRemision.info("Entra al nombreArchivo.length == 4" + nombreArchivo[2]);
            obtieneFolioInternoTalones = this.talonesFolioInterno.obtieneFolioInternoTalones(Integer.parseInt(nombreArchivo[2]), 1);
            logScheduledOrdenaNotasRemision.info("Entra al query obtieneFolioInternoTalones "+this.talonesFolioInterno.obtieneFolioInternoTalones(Integer.parseInt(nombreArchivo[2]),1));
            if (!obtieneFolioInternoTalones.isEmpty()){
               logScheduledOrdenaNotasRemision.info("Entra aqui a esta condiciones seguimos monitoreando !obtieneFolioInternoTalones.isEmpty()");
               fechaElaboracionDocumentoComplementoPago(obtieneFolioInternoTalones.get(0).getIdTalones());
               logScheduledOrdenaNotasRemision.info("Ahora si a mover porque obtuve las fechas correctamente archivo fecha" + obtieneFolioInternoTalones.get(0).getIdTalones());
               rutaMoverArchivos.generaRutasArchivos(fechaDocumento, nombreArchivo);
               logScheduledOrdenaNotasRemision.info("Ya quedo rutaMoverArchivos se ejecuta correctamente");
            }else{
               logScheduledOrdenaNotasRemision.warn("El numero de control de nota remision no encontro un folio interno talon en la BD / No se podra mover el archivo ");
            }
         }
      }
   }

   public void fechaElaboracionDocumentoComplementoPago(Integer idTalones) {
      try{
         NotaRemisionFechaImpresion fechaCreacionDocumento = talonesEmpleadosRepository.obtieneFechaNotaRemisionImpreso(idTalones);
         fechaDocumento = new FechaCreacionNotaRemision();
         fechaDocumento.setAnio(traerAnio.format(fechaCreacionDocumento.getFechaMovto()));
         fechaDocumento.setMes(obtengoNombreMes.retornaNombreMes(traerMes.format(fechaCreacionDocumento.getFechaMovto())));
         fechaDocumento.setDia(traerDia.format(fechaCreacionDocumento.getFechaMovto()));
      } catch (Exception e){
         logScheduledOrdenaNotasRemision.error("No se pudo obtener la fecha de creacion del archivo motivo" + e.getStackTrace() + " Mensaje" + e.getMessage());
      }
   }
}
