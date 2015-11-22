package andreotxai.busaodadepressaoz.model;

/**
 * Created by Ândreo on 08/11/2015.
 */
public class Linha {

        int idLinha;
        int idEmpresa;
        String codigo;
        String nome;

        public Linha(int idLinha, String nome, int idEmpresa) {
                this.idLinha = idLinha;
                this.nome = nome;
                this.idEmpresa = idEmpresa;
        }

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

        public int getIdEmpresa() {
                return idEmpresa;
        }

        public void setIdEmpresa(int idEmpresa) {
                this.idEmpresa = idEmpresa;
        }
}
