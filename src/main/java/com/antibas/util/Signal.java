package com.antibas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class Signal implements Iterable<Byte>{
	byte[] buffer;
	
	public Signal(String filePath) throws IOException {
		File file = new File(filePath);
		InputStream fis = new FileInputStream(file);
		buffer = new byte[(int)file.length()];
		fis.read(buffer, 0, buffer.length);
		fis.close();
	}
	
	public class SignalIterator implements Iterator<Byte>{
		private int currentIndex;
		
		public SignalIterator() {
			this.currentIndex = 0;
		}

		@Override
		public boolean hasNext() {
			return this.currentIndex == buffer.length;
		}

		@Override
		public Byte next() {
			return buffer[this.currentIndex++];
		}
		
	}
	@Override
	public Iterator<Byte> iterator() {
		return new SignalIterator();
	}

	
}
