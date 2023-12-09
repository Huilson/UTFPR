package br.com.utfpr.hotelpet.repository;

import br.com.utfpr.hotelpet.codec.CodecAndar;
import br.com.utfpr.hotelpet.model.Andar;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository
public class AndarRepository {

    public MongoDatabase conecta() {

        Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);

        CodecAndar andarCodec = new CodecAndar(codec);

        CodecRegistry registro = CodecRegistries.fromRegistries(
                MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(andarCodec)
        );

        MongoClientOptions op = MongoClientOptions.builder()
                .codecRegistry(registro).build();

        MongoClient cliente = new MongoClient("localhost:27017", op);
        MongoDatabase db = cliente.getDatabase("hotelpet");
        return db;
    }

    public void salvar(Andar andar) {
        MongoDatabase db = conecta();
        MongoCollection<Andar> andares = db.getCollection("andares", Andar.class);

        if (andar.getIdAndar() == null) {
            andares.insertOne(andar);
        } else {
            andares.updateOne(Filters.eq("_id", andar.getIdAndar()),
                    new Document("$set", andar));
        }
    }

    public List<Andar> listar() {
        MongoDatabase db = conecta();
        MongoCollection<Andar> andares = db.getCollection("andares", Andar.class);
        MongoCursor<Andar> resultado = andares.find().iterator();

        List<Andar> andarLista = new ArrayList<>();

        while (resultado.hasNext()) {
            Andar andar = resultado.next();
            andarLista.add(andar);
        }

        return andarLista;
    }

    public Andar obterId(String id) {
        MongoDatabase db = conecta();
        MongoCollection<Andar> andares = db.getCollection("andares", Andar.class);

        Andar andar = andares.find(Filters.eq("_id",
                new ObjectId(id))).first();

        return andar;
    }

    public void excluir(String id) {
        MongoDatabase db = conecta();
        MongoCollection<Andar> andares = db.getCollection("andares", Andar.class);

        andares.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }

    public Andar editar(String id) {
        MongoDatabase db = conecta();
        MongoCollection<Andar> andares = db.getCollection("andares", Andar.class);

        return andares.find(eq("_id", new ObjectId(id))).first();
    }
    
    public Double obterValorAndar(int numAndar) {
        MongoDatabase db = conecta();
        MongoCollection<Andar> andares = db.getCollection("andares", Andar.class);

        Andar andar = andares.find(Filters.eq("numAndar", numAndar)).first();

        if (andar != null) {
            return andar.getValorAndar();
        } else {
            return null;
        }
    }
    

}
