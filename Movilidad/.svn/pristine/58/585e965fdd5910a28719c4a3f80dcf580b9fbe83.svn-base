package co.gov.movilidadbogota.sipa.mongo.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import co.gov.movilidadbogota.sipa.repo.model.BinChunk;
import co.gov.movilidadbogota.sipa.repo.model.BinDocument;

/**
 * Mongodb document that represents a binary document divided into chunks.
 * 
 * @author Hermes
 *
 */
@Document(collection = "repoBinDocument")
public class MongoBinDocument implements BinDocument {

	@Id
	private String key;

	private int chunkSize;

	@DBRef
	private List<BinChunk> chunks = new ArrayList<BinChunk>();

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<BinChunk> getChunks() {
		return chunks;
	}

	public void setChunks(List<BinChunk> chunks) {
		this.chunks = chunks;
	}

	public void addChunk(BinChunk datasaved) {
		this.chunks.add(datasaved);
	}

	public int getChunkSize() {
		return chunkSize;
	}

	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}

	public void addChunks(ArrayList<BinChunk> allChunks) {
		this.chunks.addAll(allChunks);
	}
}
