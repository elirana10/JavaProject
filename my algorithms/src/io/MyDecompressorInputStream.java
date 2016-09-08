package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream{
	
	private InputStream in;
	
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
	}

	@Override
	public int read() throws IOException {		
		return in.read();
	}
	
	@Override
	public int read(byte[] bArr) throws IOException {
		int x = 0;
		while (x < bArr.length) {
			byte count = (byte) in.read();
			byte b = (byte) in.read();
			
			for (int i = 1; i <= count; i++) {
				bArr[x++] = b;
			}
		}
		return bArr.length;		
	}
}
