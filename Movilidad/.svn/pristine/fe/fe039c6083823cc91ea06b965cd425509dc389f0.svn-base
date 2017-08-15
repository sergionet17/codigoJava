package co.gov.movilidadbogota.sipa.repo.service;

import java.util.Iterator;

import co.gov.movilidadbogota.sipa.repo.model.BinChunk;
import co.gov.movilidadbogota.sipa.repo.model.BinDocument;

/**
 * Service used to store and search binary documents and chunks to the repository
 * repositories.
 * 
 * @author Hermes
 *
 */
public interface RepoBinService {

	public static final String BEAN_NAME = "repoBinDocsService";

	/**
	 * Build and insert a binary document into the repository.
	 * 
	 * @param key
	 * @param chunkSize
	 * @return
	 */
	public BinDocument createBinDocument(String key, int chunkSize);

	/**
	 * Build and insert a binary document into the repository.
	 * 
	 * @param offset
	 * @param buffer
	 * @return
	 */
	public BinChunk createBinChunk(int offset, byte[] buffer);

	/**
	 * Self explained
	 * 
	 * @param key
	 * @return
	 */
	public boolean existsBinDocument(String key);

	/**
	 * Self explained
	 * 
	 * @param docBinkey
	 * @return
	 */
	public Iterator<BinChunk> getChunks(String docBinkey);

	/**
	 * Self explained
	 * 
	 * @param key
	 * @return
	 */
	public BinDocument findRepoBinDocument(String key);

	/**
	 * Self explained
	 * 
	 * @param doc
	 * @return
	 */
	public BinDocument saveRepoBinDocument(BinDocument doc);
	
	/**
	 * Self explained
	 * 
	 * @param key
	 * @return
	 */
	public void removeRepoBinDocument(String key);

}
