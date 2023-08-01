package com.prliexpress.gateway.servicioconecta.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.prliexpress.gateway.servicioconecta.DTO.FechaCreacionNotaRemision;
import com.prliexpress.gateway.servicioconecta.enums.RutasScheduled;

public class GenerarRutasArchivos {

   private static final Logger logScheduledOrdenaNotasRemision = LogManager.getLogger(GenerarRutasArchivos.class);

   private Path rutaAnio;
   private Path rutaMes;
   private Path rutaDia;
   private Path directorioPrincipal;
   private Path directorioMover;
   private File creaCarpeta;

   public void generaRutasArchivos(FechaCreacionNotaRemision fechaDocumento, String nombreArchivo[]) {
      rutaAnio = Paths.get(RutasScheduled.RUTAPRINCIPAL.getRuta() + fechaDocumento.getAnio());
      if (Files.exists(rutaAnio)) {
         rutaMes = Paths.get(RutasScheduled.RUTAPRINCIPAL.getRuta() + fechaDocumento.getAnio() + "/" + fechaDocumento.getMes());
         if (Files.exists(rutaMes)) {
            rutaDia = Paths.get(RutasScheduled.RUTAPRINCIPAL.getRuta() + fechaDocumento.getAnio() + "/" + fechaDocumento.getMes() + "/" + fechaDocumento.getDia());
            if (Files.exists(rutaDia)) {
               directorioPrincipal = Paths.get(RutasScheduled.RUTAPRINCIPALNOTASREMISION.getRuta() + nombreArchivo[2] + "_M.pdf");
               directorioMover = Paths.get((RutasScheduled.RUTAPRINCIPAL.getRuta() + fechaDocumento.getAnio() + "/" + fechaDocumento.getMes() + "/" + fechaDocumento.getDia() + "/" + "Nota_Remision_" + nombreArchivo[2] + "_M.pdf"));
               try {
                  Files.move(directorioPrincipal, directorioMover, StandardCopyOption.REPLACE_EXISTING);
               } catch (FileAlreadyExistsException ex) {
                  logScheduledOrdenaNotasRemision.info(RutasScheduled.RUTADESTINOYAEXISTE.getRuta());
               } catch (IOException exception) {
                  logScheduledOrdenaNotasRemision.info(RutasScheduled.RUTAIOEXCEPTION.getRuta() + "Causa: " + exception);
               }
            } else crearCarpetasPrincipales(3, fechaDocumento);
         } else crearCarpetasPrincipales(2, fechaDocumento);
      } else crearCarpetasPrincipales(1, fechaDocumento);
   }

   public void crearCarpetasPrincipales(Integer condicionCreacionCarpeta, FechaCreacionNotaRemision fechaDocumento) {
      switch (condicionCreacionCarpeta) {
         case 1:
            creaCarpeta = new File(RutasScheduled.RUTAPRINCIPAL.getRuta() + fechaDocumento.getAnio());
            creaCarpeta.mkdir();
            break;

         case 2:
            creaCarpeta = new File(RutasScheduled.RUTAPRINCIPAL.getRuta() + fechaDocumento.getAnio() + "/" + fechaDocumento.getMes());
            creaCarpeta.mkdir();
            break;

         case 3:
            creaCarpeta = new File(RutasScheduled.RUTAPRINCIPAL.getRuta() + fechaDocumento.getAnio() + "/" + fechaDocumento.getMes() + "/" + fechaDocumento.getDia());
            creaCarpeta.mkdir();
            break;
         default:
            logScheduledOrdenaNotasRemision.error(RutasScheduled.CONDICIONINVALIDA.getRuta());
            break;
      }
   }
}
