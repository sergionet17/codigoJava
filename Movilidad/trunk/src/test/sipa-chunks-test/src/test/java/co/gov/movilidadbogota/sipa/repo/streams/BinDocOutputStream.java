package co.gov.movilidadbogota.sipa.repo.streams;

import java.io.OutputStream;
import java.util.ArrayList;

import co.gov.movilidadbogota.sipa.repo.model.BinChunk;
import co.gov.movilidadbogota.sipa.repo.model.BinDocument;
import co.gov.movilidadbogota.sipa.repo.service.RepoBinService;

/**
 * Output stream used to store a binary document into the repository.
 * 
 * @author Hermes
 *
 */
public class BinDocOutputStream extends OutputStream {

	private RepoBinService repoService;

	// local chunk buffer
	private byte[] buffer;
	// current position in the local buffer
	private int offset = 0;
	private BinDocument doc;
	// Chunks created
	private ArrayList<BinChunk> chunks = new ArrayList<BinChunk>();

	/**
	 * Creates binary document stream.
	 * 
	 * @param repoService
	 * @param key
	 * @param binarySize
	 */
	public BinDocOutputStream(String key, long binarySize, long maxChunkSize, RepoBinService repoService) {
		this.repoService = repoService;
		// insert into database
		int chunkSize = calculateMaxChunkSize(binarySize, maxChunkSize);
		buffer = new byte[chunkSize];

		doc = repoService.createBinDocument(key, chunkSize);
	}

	/**
	 * return the max chunk size used to create the chunk buffer. Return the
	 * smaller value between the binarySize and maxChunkSize vars.
	 * 
	 * @param binarySize
	 * @param maxChunkSize
	 * @return
	 */
	private int calculateMaxChunkSize(long binarySize, long maxChunkSize) {
		int chunkSize;
		if (binarySize < maxChunkSize)
			chunkSize = Integer.parseInt(binarySize + "");
		else
			chunkSize = Integer.parseInt(maxChunkSize + ""); 
		return chunkSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int b) {
		// fill the local buffer first
		if (offset < buffer.length) {
			buffer[offset++] = (byte) b;
		}

		// push chunk into storage
		if (offset == buffer.length) {
			flush();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.OutputStream#flush()
	 */
	@Override
	public void flush() {
		if (offset > 0) {
			// store chink
			chunks.add(repoService.createBinChunk(offset, buffer));
			// reset
			offset = 0;
		}
	}

	/**
	 * When the write chunk buffer is full then a new chunk binary document is
	 * created into the mongodb repository
	 */
	public void persist() {
		this.doc.addChunks(chunks);
		repoService.saveRepoBinDocument(this.doc);
	}

}
