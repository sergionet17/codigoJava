package co.gov.movilidadbogota.sipa.mongo.model.impl;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import co.gov.movilidadbogota.sipa.repo.model.BinChunk;

/**
 * Mongodb document that represents a binary document chunk.
 * 
 * @author Hermes
 *
 */
@Document(collection = "repoBinChunk")
public class MongoBinChunk implements BinChunk{

	@Id
	private String id;

	private int size;
	private byte[] buffer;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public byte[] getBuffer() {
		return buffer;
	}

	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}
}
