package br.com.utfpr.hotelpet.repository;

import br.com.utfpr.hotelpet.codec.CodecCheckin;
import br.com.utfpr.hotelpet.model.Checkin;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository
public class CheckinRepository {
    
    public MongoDatabase conecta() {

        Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);

        CodecCheckin checkinCodec = new CodecCheckin(codec);

        CodecRegistry registro = CodecRegistries.fromRegistries(
                MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(checkinCodec)
        );

        MongoClientOptions op = MongoClientOptions.builder()
                .codecRegistry(registro).build();

        MongoClient cliente = new MongoClient("localhost:27017", op);
        MongoDatabase db = cliente.getDatabase("hotelpet");
        return db;
    }
    
    public void salvar(Checkin checkin) {
        MongoDatabase db = conecta();
        MongoCollection<Checkin> checkins = db.getCollection("checkins", Checkin.class);

        if (checkin.getIdCheckin()== null) {
            checkins.insertOne(checkin);
        } else {
            checkins.updateOne(Filters.eq("_id", checkin.getIdCheckin()),
                    new Document("$set", checkin));
        }
    }
    
    public List<Checkin> listar() {
        MongoDatabase db = conecta();
        MongoCollection<Checkin> checkins = db.getCollection("checkins", Checkin.class);
        MongoCursor<Checkin> resultado = checkins.find().iterator();

        List<Checkin> checkinLista = new ArrayList<>();

        while (resultado.hasNext()) {
            Checkin checkin = resultado.next();
            checkinLista.add(checkin);
        }

        return checkinLista;
    }
    
    public Checkin obterId(String id) {
        MongoDatabase db = conecta();
        MongoCollection<Checkin> checkins = db.getCollection("checkins", Checkin.class);
        
        Checkin checkin = checkins.find(Filters.eq("_id", 
                new ObjectId(id))).first();
        
        return checkin;
    }

}
