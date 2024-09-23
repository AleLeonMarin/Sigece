package cr.ac.una.mailapp.model;

import java.io.Serializable;


    public class ParametrosDTO implements Serializable {

        private String parId;
        private String parCorreo;
        private String parClave;
        private long parPuerto;
        private String parServer;
        private String parProtocolo;
        private long parTimeout;
        private long parVersion;


        public ParametrosDTO() {
            this.parId = "1";
            this.parCorreo = "";
            this.parClave = "";
            this.parPuerto = 0;
            this.parServer = "";
            this.parProtocolo = "smtp";
            this.parTimeout = 0;
            this.parVersion = 0;
        }
        // Getters y Setters
        public String getParId() {
            return parId;
        }

        public void setParId(String parId) {
            this.parId = parId;
        }

        public String getParCorreo() {
            return parCorreo;
        }

        public void setParCorreo(String parCorreo) {
            this.parCorreo = parCorreo;
        }

        public String getParClave() {
            return parClave;
        }

        public void setParClave(String parClave) {
            this.parClave = parClave;
        }

        public long getParPuerto() {
            return parPuerto;
        }

        public void setParPuerto(long parPuerto) {
            this.parPuerto = parPuerto;
        }

        public String getParServer() {
            return parServer;
        }

        public void setParServer(String parServer) {
            this.parServer = parServer;
        }

        public String getParProtocolo() {
            return parProtocolo;
        }

        public void setParProtocolo(String parProtocolo) {
            this.parProtocolo = parProtocolo;
        }

        public long getParTimeout() {
            return parTimeout;
        }

        public void setParTimeout(long parTimeout) {
            this.parTimeout = parTimeout;
        }

        public long getParVersion() {
            return parVersion;
        }

        public void setParVersion(long parVersion) {
            this.parVersion = parVersion;
        }
    }


