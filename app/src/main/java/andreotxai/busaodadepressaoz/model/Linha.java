package andreotxai.busaodadepressaoz.model;

/**
 * Created by Ândreo on 08/11/2015.
 */
public class Linha {

        int idLinha;
        String codigo;
        String nome;
        String idEmpresa;

        public int getIdLinha() {
                return idLinha;
        }

        public void setIdLinha(int idLinha) {
                this.idLinha = idLinha;
        }

        public String getCodigo() {
                return codigo;
        }

        public void setCodigo(String codigo) {
                this.codigo = codigo;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getIdEmpresa() {
                return idEmpresa;
        }

        public void setIdEmpresa(String idEmpresa) {
                this.idEmpresa = idEmpresa;
        }
}
