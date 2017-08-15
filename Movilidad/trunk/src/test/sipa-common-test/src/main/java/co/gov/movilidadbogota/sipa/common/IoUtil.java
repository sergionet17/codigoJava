package co.gov.movilidadbogota.sipa.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IoUtil {

	/**
	 * Write the entire contents of the supplied string to the given stream.
	 * This method always flushes and closes the stream when finished.
	 * 
	 * @param input	        
	 * @param output	 
	 * @throws IOException
	 * @throws IllegalArgumentException            
	 */
	public static void write(InputStream input, OutputStream output) throws IOException {
		int bufferSize = 1024;
		boolean error = false;
		try {
			if (input != null) {
				byte[] buffer = new byte[bufferSize];
				try {
					int numRead = 0;
					while ((numRead = input.read(buffer)) > -1) {
						output.write(buffer, 0, numRead);
					}
				} finally {
					input.close();
				}
			}
		} catch (IOException e) {
			error = true; // this error should be thrown, even if there is an
							// error flushing/closing stream
			throw e;
		} catch (RuntimeException e) {
			error = true; // this error should be thrown, even if there is an
							// error flushing/closing stream
			throw e;
		} finally {
			try {
				output.flush();
			} catch (IOException e) {
				if (!error)
					throw e;
			} finally {
				try {
					output.close();
				} catch (IOException e) {
					if (!error)
						throw e;
				}
			}
		}
	}

}
