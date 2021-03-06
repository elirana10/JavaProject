package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
	
	private OutputStream out;
	
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}
	
	@Override
	public void write(int b) throws IOException {
		out.write(b);
	}
	
	@Override
	public void write(byte[] bArr) throws IOException {
		byte b = bArr[0];
		int count = 1;
		for (int i=1; i<bArr.length; i++) {
			if (bArr[i] != b) {
				while (count >= 255) {
					out.write(255);
					out.write(b);
					count -= 255;
				}
				out.write(count);
				out.write(b);
				b = bArr[i];
				count = 1;
			}
			else {
				count++;
			}
		}
		out.write(count);
		out.write(b);
	}
}
