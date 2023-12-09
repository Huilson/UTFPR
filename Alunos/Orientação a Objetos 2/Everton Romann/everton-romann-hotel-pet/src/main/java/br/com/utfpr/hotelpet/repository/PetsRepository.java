package br.com.utfpr.hotelpet.repository;

import br.com.utfpr.hotelpet.codec.CodecPets;
import br.com.utfpr.hotelpet.model.Pets;
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
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PetsRepository {

    @Autowired
    private TutorRepository tutorRepository;

    public MongoDatabase conecta() {

        Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);

        CodecPets petsCodec = new CodecPets(codec);

        CodecRegistry registro = CodecRegistries.fromRegistries(
                MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(petsCodec)
        );

        MongoClientOptions op = MongoClientOptions.builder()
                .codecRegistry(registro).build();

        MongoClient cliente = new MongoClient("localhost:27017", op);
        MongoDatabase db = cliente.getDatabase("hotelpet");
        return db;
    }

    public void salvar(Pets pet) {
        MongoDatabase db = conecta();
        MongoCollection<Pets> pets = db.getCollection("pets", Pets.class);

        if (pet.getIdPet() == null) {
            pets.insertOne(pet);
        } else {
            pets.updateOne(Filters.eq("_id", pet.getIdPet()),
                    new Document("$set", pet));
        }
    }

    public List<Pets> listar() {
        MongoDatabase db = conecta();
        MongoCollection<Pets> pets = db.getCollection("pets", Pets.class);
        MongoCursor<Pets> resultado = pets.find().iterator();

        List<Pets> petsLista = new ArrayList<>();

        while (resultado.hasNext()) {
            Pets pet = resultado.next();
            petsLista.add(pet);
        }

        return petsLista;
    }
    
    public Pets obterId(String id) {
        MongoDatabase db = conecta();
        MongoCollection<Pets> pets = db.getCollection("pets", Pets.class);

        Pets pet = pets.find(Filters.eq("_id",
                new ObjectId(id))).first();

        return pet;
    }

    public void excluir(String id) {
        MongoDatabase db = conecta();
        MongoCollection<Pets> pets = db.getCollection("pets", Pets.class);

        pets.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }

    public List<Pets> listarPetsTutor(String nomeTutor) {
        MongoDatabase db = conecta();
        MongoCollection<Pets> pets = db.getCollection("pets", Pets.class);

        Bson filtro = Filters.eq("tutorPet", nomeTutor);

        MongoCursor<Pets> resultado = pets.find(filtro).iterator();

        List<Pets> petsDoTutor = new ArrayList<>();

        while (resultado.hasNext()) {
            Pets pet = resultado.next();
            petsDoTutor.add(pet);
        }

        return petsDoTutor;
    }

    public Pets editar(String id) {
        MongoDatabase db = conecta();
        MongoCollection<Pets> pets = db.getCollection("pets", Pets.class);

        return pets.find(eq("_id", new ObjectId(id))).first();
    }

}
