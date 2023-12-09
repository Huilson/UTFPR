package br.com.utfpr.hotelpet.repository;

import br.com.utfpr.hotelpet.codec.CodecTutor;
import br.com.utfpr.hotelpet.model.Tutor;
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
public class TutorRepository {

    public MongoDatabase conecta() {

        Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);

        CodecTutor tutorCodec = new CodecTutor(codec);

        CodecRegistry registro = CodecRegistries.fromRegistries(
                MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(tutorCodec)
        );

        MongoClientOptions op = MongoClientOptions.builder()
                .codecRegistry(registro).build();

        MongoClient cliente = new MongoClient("localhost:27017", op);
        MongoDatabase db = cliente.getDatabase("hotelpet");
        return db;
    }

    public void salvar(Tutor tutor) {
        MongoDatabase db = conecta();
        MongoCollection<Tutor> tutores = db.getCollection("tutores", Tutor.class);

        if (tutor.getIdTutor() == null) {
            tutores.insertOne(tutor);
        } else {
            tutores.updateOne(Filters.eq("_id", tutor.getIdTutor()),
                    new Document("$set", tutor));
        }
    }

    public List<Tutor> listar() {
        MongoDatabase db = conecta();
        MongoCollection<Tutor> tutores = db.getCollection("tutores", Tutor.class);
        MongoCursor<Tutor> resultado = tutores.find().iterator();

        List<Tutor> tutorLista = new ArrayList<>();

        while (resultado.hasNext()) {
            Tutor tutor = resultado.next();
            tutorLista.add(tutor);
        }

        return tutorLista;
    }

    public Tutor obterId(String id) {
        MongoDatabase db = conecta();
        MongoCollection<Tutor> tutores = db.getCollection("tutores", Tutor.class);

        Tutor tutor = tutores.find(Filters.eq("_id",
                new ObjectId(id))).first();

        return tutor;
    }

    public void excluir(String id) {
        MongoDatabase db = conecta();
        MongoCollection<Tutor> tutores = db.getCollection("tutores", Tutor.class);

        tutores.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }

    public Tutor editar(String id) {
        MongoDatabase db = conecta();
        MongoCollection<Tutor> tutores = db.getCollection("tutores", Tutor.class);

        return tutores.find(eq("_id", new ObjectId(id))).first();
    }

    public String obterNomeTutorPorId(String id) {
        MongoDatabase db = conecta();
        MongoCollection<Tutor> tutores = db.getCollection("tutores", Tutor.class);

        Tutor tutor = tutores.find(Filters.eq("_id", new ObjectId(id))).first();
        
        if (tutor != null) {

            return  tutor.getNomeTutor();
        } else {
            return null;
        }

    }

}
