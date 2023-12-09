package br.com.utfpr.hotelpet.codec;

import br.com.utfpr.hotelpet.model.Tutor;
import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

public class CodecTutor implements CollectibleCodec<Tutor> {

    private Codec<Document> codec;

    public CodecTutor(Codec<Document> codec) {
        this.codec = codec;
    }

    @Override
    public Tutor generateIdIfAbsentFromDocument(Tutor tutor) {
        return documentHasId(tutor) ? tutor.criarId() : tutor;
    }

    @Override
    public boolean documentHasId(Tutor tutor) {
        return tutor.getIdTutor() == null;
    }

    @Override
    public BsonValue getDocumentId(Tutor tutor) {
        if (!documentHasId(tutor)) {
            throw new IllegalStateException("Esse documento não possui um Id");
        } else {
            return new BsonString(tutor.getIdTutor().toHexString());
        }
    }

    @Override
    public void encode(BsonWriter writer, Tutor tutor, EncoderContext ec) {
        ObjectId idTutor = tutor.getIdTutor();
        String nomeTutor = tutor.getNomeTutor();
        String telefoneTutor = tutor.getTelefoneTutor();
        String emailTutor = tutor.getEmailTutor();

        Document doc = new Document();
        doc.put("_id", idTutor);
        doc.put("nomeTutor", nomeTutor);
        doc.put("telefoneTutor", telefoneTutor);
        doc.put("emailTutor", emailTutor);

        codec.encode(writer, doc, ec);

    }

    @Override
    public Class<Tutor> getEncoderClass() {
        return Tutor.class;
    }

    @Override
    public Tutor decode(BsonReader reader, DecoderContext dc) {
        Document doc = codec.decode(reader, dc);
        Tutor tutor = new Tutor();

        tutor.setIdTutor(doc.getObjectId("_id"));
        tutor.setNomeTutor(doc.getString("nomeTutor"));
        tutor.setTelefoneTutor(doc.getString("telefoneTutor"));
        tutor.setEmailTutor(doc.getString("emailTutor"));

        return tutor;
    }

}
