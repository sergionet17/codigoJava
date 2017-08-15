package co.gov.movilidadbogota.sipa.mongo.service.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.movilidadbogota.sipa.mongo.model.impl.MongoBinChunk;
import co.gov.movilidadbogota.sipa.mongo.model.impl.MongoBinDocument;
import co.gov.movilidadbogota.sipa.mongo.repository.MongoBinChunkRepository;
import co.gov.movilidadbogota.sipa.mongo.repository.MongoBinDocumentRepository;
import co.gov.movilidadbogota.sipa.repo.model.BinChunk;
import co.gov.movilidadbogota.sipa.repo.model.BinDocument;
import co.gov.movilidadbogota.sipa.repo.service.RepoBinService;

/**
 * Repository document service implementation. Used to create binary documents and
 * binary chunks, store and search from repository.
 * 
 * @author Hermes
 *
 */
@Service(RepoBinService.BEAN_NAME)
public class MongoRepoBinServiceImpl implements RepoBinService {

	/**
	 * Spring binary chunks repository
	 */
	@Autowired
	private MongoBinChunkRepository repoChunks;

	/**
	 * Spring binary documents repository
	 */
	@Autowired
	private MongoBinDocumentRepository repoDocs;

	/* (non-Javadoc)
	 * @see co.gov.movilidadbogota.sipa.repo.service.RepoBinService#createBinDocument(java.lang.String, int)
	 */
	@Override
	public BinDocument createBinDocument(String key, int chunkSize) {
		MongoBinDocument doc = new MongoBinDocument();
		doc.setKey(key);
		doc.setChunkSize(chunkSize);

		return this.repoDocs.save(doc);
	}

	/* (non-Javadoc)
	 * @see co.gov.movilidadbogota.sipa.repo.service.RepoBinService#createBinChunk(int, byte[])
	 */
	@Override
	public BinChunk createBinChunk(int offset, byte[] buffer) {
		MongoBinChunk data = new MongoBinChunk();
		data.setSize(offset);
		data.setBuffer(buffer);

		return this.repoChunks.save(data);
	}

	/* (non-Javadoc)
	 * @see co.gov.movilidadbogota.sipa.repo.service.RepoBinService#existsBinDocument(java.lang.String)
	 */
	@Override
	public boolean existsBinDocument(String key) {
		return this.repoDocs.exists(key);
	}

	/* (non-Javadoc)
	 * @see co.gov.movilidadbogota.sipa.repo.service.RepoBinService#getChunks(java.lang.String)
	 */
	@Override
	public Iterator<BinChunk> getChunks(String docBinkey) {
		return this.repoDocs.findOne(docBinkey).getChunks().iterator();
	}

	/* (non-Javadoc)
	 * @see co.gov.movilidadbogota.sipa.repo.service.RepoBinService#findRepoBinDocument(java.lang.String)
	 */
	@Override
	public BinDocument findRepoBinDocument(String key) {
		return this.repoDocs.findOne(key);
	}

	/* (non-Javadoc)
	 * @see co.gov.movilidadbogota.sipa.repo.service.RepoBinService#saveRepoBinDocument(co.gov.movilidadbogota.sipa.repo.model.BinDocument)
	 */
	@Override
	public BinDocument saveRepoBinDocument(BinDocument doc) {
		return this.repoDocs.save((MongoBinDocument)doc);
	}

	/* (non-Javadoc)
	 * @see co.gov.movilidadbogota.sipa.repo.service.RepoBinService#removeRepoBinDocument(java.lang.String)
	 */
	@Override
	public void removeRepoBinDocument(String key) {
		BinDocument bindoc = this.findRepoBinDocument(key);
		for(BinChunk chunk:bindoc.getChunks())
			this.repoChunks.delete(chunk.getId());
		this.repoDocs.delete(key);
	}
}
