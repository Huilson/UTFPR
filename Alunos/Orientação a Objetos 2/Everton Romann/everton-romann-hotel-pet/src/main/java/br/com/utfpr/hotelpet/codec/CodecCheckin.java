package br.com.utfpr.hotelpet.codec;

import br.com.utfpr.hotelpet.model.Checkin;
import java.util.Date;
import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.Codec;
import org.bson.types.ObjectId;


public class CodecCheckin implements CollectibleCodec<Checkin>{
    
    private Codec<Document> codec;

    public CodecCheckin(Codec<Document> codec) {
        this.codec = codec;
    }

    @Override
    public Checkin generateIdIfAbsentFromDocument(Checkin checkin) {
        return documentHasId(checkin) ? checkin.criarId() : checkin;
    }

    @Override
    public boolean documentHasId(Checkin checkin) {
        return checkin.getIdCheckin()== null;
    }

    @Override
    public BsonValue getDocumentId(Checkin checkin) {
        if (!documentHasId(checkin)) {
            throw new IllegalStateException("Esse documento não possui um Id");
        } else {
            return new BsonString(checkin.getIdCheckin().toHexString());
        }
    }

    @Override
    public void encode(BsonWriter writer, Checkin checkin, EncoderContext ec) {
        ObjectId idCheckin = checkin.getIdCheckin();
        String nomePet = checkin.getNomePet();
        Integer numAndar = checkin.getNumAndar();
        String tutorPet = checkin.getTutorPet();
        Integer tempoHospedagem = checkin.getTempoHospedagem();
        Double valorHospedagem = checkin.getValorHospedagem();
        Date dataCheckin = checkin.getDataCheckin();
        Date dataCheckout = checkin.getDataCheckout();
        
        
        Document doc = new Document();
        doc.put("_id", idCheckin);
        doc.put("nomePet", nomePet);
        doc.put("numAndar", numAndar);
        doc.put("tutorPet", tutorPet);
        doc.put("tempoHospedagem", tempoHospedagem);
        doc.put("valorHospedagem", valorHospedagem);
        doc.put("dataCheckin", dataCheckin);
        doc.put("dataCheckout", dataCheckout);
        
        codec.encode(writer, doc, ec);
    }

    @Override
    public Class<Checkin> getEncoderClass() {
        return Checkin.class;
    }

    @Override
    public Checkin decode(BsonReader reader, DecoderContext dc) {
        Document doc = codec.decode(reader, dc);
        Checkin checkin = new Checkin();
        
        checkin.setIdCheckin(doc.getObjectId("_id"));
        checkin.setNomePet(doc.getString("nomePet"));
        checkin.setNumAndar(doc.getInteger("numAndar"));
        checkin.setTutorPet(doc.getString("tutorPet"));
        checkin.setTempoHospedagem(doc.getInteger("tempoHospedagem"));
        checkin.setValorHospedagem(doc.getDouble("valorHospedagem"));
        checkin.setDataCheckin(doc.getDate("dataCheckin"));
        checkin.setDataCheckout(doc.getDate("dataCheckout"));
        
        return checkin;
    }

}
