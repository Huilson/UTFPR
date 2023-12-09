package br.com.utfpr.hotelpet.codec;

import br.com.utfpr.hotelpet.model.Andar;
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

public class CodecAndar implements CollectibleCodec<Andar> {

    private Codec<Document> codec;

    public CodecAndar(Codec<Document> codec) {
        this.codec = codec;
    }

    @Override
    public Andar generateIdIfAbsentFromDocument(Andar andar) {
        return documentHasId(andar) ? andar.criarId() : andar;
    }

    @Override
    public boolean documentHasId(Andar andar) {
        return andar.getIdAndar() == null;
    }

    @Override
    public BsonValue getDocumentId(Andar andar) {
        if (!documentHasId(andar)) {
            throw new IllegalStateException("Esse documento não possui um Id");
        } else {
            return new BsonString(andar.getIdAndar().toHexString());
        }
    }

    @Override
    public void encode(BsonWriter writer, Andar andar, EncoderContext ec) {
        ObjectId idAndar = andar.getIdAndar();
        Integer numAndar = andar.getNumAndar();
        String especieAndar = andar.getEspecieAndar();
        Double valorAndar = andar.getValorAndar();

        Document doc = new Document();
        doc.put("_id", idAndar);
        doc.put("numAndar", numAndar);
        doc.put("especieAndar", especieAndar);
        doc.put("valorAndar", valorAndar);

        codec.encode(writer, doc, ec);
    }

    @Override
    public Class<Andar> getEncoderClass() {
        return Andar.class;
    }

    @Override
    public Andar decode(BsonReader reader, DecoderContext dc) {
        Document doc = codec.decode(reader, dc);
        Andar andar = new Andar();
        
        andar.setIdAndar(doc.getObjectId("_id"));
        andar.setNumAndar(doc.getInteger("numAndar"));
        andar.setEspecieAndar(doc.getString("especieAndar"));
        andar.setValorAndar(doc.getDouble("valorAndar"));

        return andar;
    }
}
