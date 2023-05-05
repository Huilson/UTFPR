package utfpr.edu.br.zoomongo.db;

import org.bson.types.ObjectId;

public class Animais {
    private ObjectId _id;
    private String especie;
    private String nomeAnimal;
    private String[] descricao;
    private String nomeResponsavel;

    @Override
    public String toString() {
        return "Animal {" + "_id:" + _id + ", Espécie:" + especie + ", Nome do Animal:" + nomeAnimal + ", Descrição:" + descricao + ", Resposável:" + nomeResponsavel + '}';
    }
}
