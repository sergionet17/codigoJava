package co.gov.movilidadbogota.sipa.repo.model;

public interface BinChunk {
	
	public String getId();

	public void setId(String id);

	public int getSize();

	public void setSize(int size);

	public byte[] getBuffer();

	public void setBuffer(byte[] buffer);

}
