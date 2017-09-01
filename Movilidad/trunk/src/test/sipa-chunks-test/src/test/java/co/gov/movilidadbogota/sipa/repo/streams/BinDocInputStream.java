package co.gov.movilidadbogota.sipa.repo.streams;

import java.io.InputStream;
import java.util.Iterator;

import co.gov.movilidadbogota.sipa.repo.model.BinChunk;
import co.gov.movilidadbogota.sipa.repo.service.RepoBinService;

/**
 * Input stream used to read a binary document from the repository.
 * 
 * @author Hermes
 *
 */
public class BinDocInputStream extends InputStream {
	
	private RepoBinService repoService;

	// list of chunks
    private Iterator<BinChunk> chunks;

    // local buffer and current position inthe buffer
    private byte[] buffer ;
    private int offset = 0;

    // the actual amount of data stored in chunk
    private int size = 0;

    public BinDocInputStream(String docBinkey, RepoBinService repoService) { 
    	this.repoService = repoService;
    	int chunkSize = this.repoService.findRepoBinDocument(docBinkey.toString()).getChunkSize();
    	this.chunks = this.repoService.getChunks(docBinkey);      
    	buffer = new byte[chunkSize];    	
    }

    @Override
    public int read() {
        // read current chunk
        if (offset < size) {
            // make sure it's unsigned (see javadoc)
            return 0xff & buffer[offset++];
        }

        // try to pick up next chunk
        if (chunks.hasNext()){
        	BinChunk chunk = chunks.next();
            size = (Integer)chunk.getSize();
            buffer = (byte[])chunk.getBuffer();
            offset = 0;
        }

        // start reading from new chunk
        if (offset < size) {
            return 0xff & buffer[offset++];
        }

        // end of stream reached
        return -1;
    }

}
