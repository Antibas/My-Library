package com.antibas.math.signal;

import com.antibas.util.ArrayUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Function;

public class Signal3 {
	public static final long[] BASIC_SAMPLE_RATES = {22000, 44100, 48000, 88200, 96000};
	
	protected Function<Double, Byte> waveForm;
	protected byte[] samples;
	protected double duration;
	protected long sampleRate;
	
	public Signal3(double duration, long sampleRate, Function<Double, Byte> waveForm) {
		this.waveForm = waveForm;
		this.sampleRate = sampleRate;
		this.duration = duration;
		this.samples = new byte[(int)Math.ceil(this.duration*this.sampleRate)];
		for(int i = 0; i < samples.length; i++) {
			samples[i] = this.waveForm.apply((double)i);
		}
	}
	
	public Signal3(double duration, long s) {
		this(duration, s, (x) -> (byte) 0);
	}
	
	public Signal3(double duration, Function<Double, Byte> w) {
		this(duration, BASIC_SAMPLE_RATES[1], w);
	}
	
	public Signal3(double duration) {
		this(duration, BASIC_SAMPLE_RATES[1], (x) -> (byte) 0);
	}
	
	public Signal3(String file) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

		int read;
		byte[] buff = new byte[1024];
		while ((read = in.read(buff)) > 0)
		{
		    out.write(buff, 0, read);
		}
		out.flush();
		this.samples = out.toByteArray();
		in.close();
	}
	
	private void reform() {
		samples = new byte[(int)(duration*sampleRate)];
		for(int i = 0; i < samples.length; i++) {
			samples[i] = waveForm.apply((double)i);
		}
	}


	public Function<Double, Byte> getWaveForm() {
		return waveForm;
	}

	public void setWaveForm(Function<Double, Byte> waveForm) {
		this.waveForm = waveForm;
		reform();
	}

	public double getAmplitude() {
		return ArrayUtils.max(samples);
	}

	public double sampleAt(int i) {
		return samples[i];
	}

	public void setSampleAt(int i, byte s) {
		this.samples[i] = s;
	}

	public long getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(long sampleRate) {
		this.sampleRate = sampleRate;
		reform();
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
		reform();
	}
	
	
}
