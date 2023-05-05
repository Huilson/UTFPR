package utfpr.edu.br.zoomongo.db;

import org.bson.types.ObjectId;

import java.math.BigDecimal;

public class Funcionário {
    private ObjectId _id;
    private String nome;
    private String cpf;
    private String endereco;
    private BigDecimal salario;


    @Override
    public String toString() {
        return "Funcionário {" + "id: " + _id + ", Nome: " + nome + ", CPF: " + cpf + ", Endereço: " + endereco + ", Salário: " + salario +'}';
    }
}
