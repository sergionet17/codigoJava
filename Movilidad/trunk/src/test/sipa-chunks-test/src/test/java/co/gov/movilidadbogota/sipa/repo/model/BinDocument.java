package co.gov.movilidadbogota.sipa.repo.model;

import java.util.ArrayList;
import java.util.List;

public interface BinDocument {
	
	public String getKey();

	public void setKey(String key);

	public List<BinChunk> getChunks();

	public void setChunks(List<BinChunk> chunks);

	public void addChunk(BinChunk datasaved);

	public int getChunkSize();

	public void setChunkSize(int chunkSize);

	public void addChunks(ArrayList<BinChunk> allChunks);
}
