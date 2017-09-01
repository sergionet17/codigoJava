package co.gov.movilidadbogota.sipa.mongo.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.gov.movilidadbogota.sipa.mongo.model.impl.MongoBinChunk;

/**
 * Mongodb spring repository used to do CRUD operations over binary chunks.
 * 
 * @author Hermes
 *
 */
@Scope("prototype")
@Repository("chunksRepository")
public interface MongoBinChunkRepository extends MongoRepository<MongoBinChunk, String> {

}
