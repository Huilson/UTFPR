package br.com.utfpr.hotelpet.codec;

import br.com.utfpr.hotelpet.model.Pets;
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

public class CodecPets implements CollectibleCodec<Pets> {

    private Codec<Document> codec;

    public CodecPets(Codec<Document> codec) {
        this.codec = codec;
    }

    @Override
    public Pets generateIdIfAbsentFromDocument(Pets pet) {
        return documentHasId(pet) ? pet.criarId() : pet;
    }

    @Override
    public boolean documentHasId(Pets pet) {
        return pet.getIdPet() == null;
    }

    @Override
    public BsonValue getDocumentId(Pets pet) {
        if (!documentHasId(pet)) {
            throw new IllegalStateException("Esse documento não possui um Id");
        } else {
            return new BsonString(pet.getIdPet().toHexString());
        }
    }

    @Override
    public void encode(BsonWriter writer, Pets pet, EncoderContext ec) {
        ObjectId idPet = pet.getIdPet();
        String nomePet = pet.getNomePet();
        String tutorPet= pet.getTutorPet();
        String especiePet = pet.getEspeciePet();
        Integer idadePet = pet.getIdadePet();
        String sexoPet = pet.getSexoPet();
        String corPet = pet.getCorPet();

        Document doc = new Document();
        doc.put("_id", idPet);
        doc.put("nomePet", nomePet);
        doc.put("tutorPet", tutorPet);
        doc.put("especiePet", especiePet);
        doc.put("idadePet", idadePet);
        doc.put("sexoPet", sexoPet);
        doc.put("corPet", corPet);

        codec.encode(writer, doc, ec);
    }

    @Override
    public Class<Pets> getEncoderClass() {
        return Pets.class;
    }

    @Override
    public Pets decode(BsonReader reader, DecoderContext dc) {
        Document doc = codec.decode(reader, dc);
        Pets pet = new Pets();

        pet.setIdPet(doc.getObjectId("_id"));
        pet.setNomePet(doc.getString("nomePet"));
        pet.setTutorPet(doc.getString("tutorPet"));
        pet.setEspeciePet(doc.getString("especiePet"));
        pet.setIdadePet(doc.getInteger("idadePet"));
        pet.setSexoPet(doc.getString("sexoPet"));
        pet.setCorPet(doc.getString("corPet"));

        return pet;
    }
}
