/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.securityws.service;

import cr.ac.una.securityws.model.UsuariosDto;
import cr.ac.una.securityws.util.Request;
import cr.ac.una.securityws.util.Respuesta;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kendall Fonseca
 */
public class CorreosService {
     private static final Logger LOG = Logger.getLogger(CorreosService.class.getName());
       public Respuesta enviarCorreoActivacion(UsuariosDto usuarioDto) {
        try {
            Request request = new Request("correos/enviarActivacion");
            request.post(usuarioDto);

            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            return new Respuesta(true, "", "Correo de activación enviado correctamente.");

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error enviando el correo de activación.", ex);
            return new Respuesta(false, "Error enviando el correo de activación.", "enviarCorreoActivacion " + ex.getMessage());
        }
    }
}
