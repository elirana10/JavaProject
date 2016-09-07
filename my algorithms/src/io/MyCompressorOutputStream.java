package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
	OutputStream out;
	
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}
	@Override
	public void write(int b) throws IOException {
		out.write(b);
	}
	
	public void write(byte[] bytes) throws IOException {
		byte b = bytes[0];
		int count;
		for (int i=1; i<bytes.length; i++) {
			count=1;
			while ((b==bytes[i])&&(count<=255)) {
				count++;
				i++;
			}
			out.write(count);
			out.write(b);
			b=bytes[i];
		}
	}
}
