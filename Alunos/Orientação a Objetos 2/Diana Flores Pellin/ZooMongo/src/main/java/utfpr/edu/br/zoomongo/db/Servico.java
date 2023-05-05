package utfpr.edu.br.zoomongo.db;

import org.bson.types.ObjectId;

public class Servico {
    private ObjectId _id;
    private String descricao;
    private String data;
    private String nomeAnimal;
    private String nomeResponsavel;

    @Override
    public String toString() {
        return "Animal {" + "_id:" + _id + ", Descrição do Serviço:" + descricao + ", Data do Serviço:" + data + ", Nome do Animal:" + nomeAnimal + ", Nome do Resposável:" + nomeResponsavel + '}';
    }
}
