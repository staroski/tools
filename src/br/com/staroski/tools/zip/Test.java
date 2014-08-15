package br.com.staroski.tools.zip;

import java.io.*;

public class Test {

	public static void main(String[] args) {
		compress();

		extract();
	}

	static void compress() {
		try {
			File dir = new File("C:\\Users\\ricardo.staroski\\Desktop");

			File in = new File(dir, "cr");

			File out = new File(dir, "cr.zip");

			long checksum = ZipUtils.compress(in, out);

			System.out.println("checksum: " + Long.toHexString(checksum));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	static void extract() {
		try {
			File dir = new File("C:\\Users\\ricardo.staroski\\Desktop");

			File in = new File(dir, "cr.zip");

			File out = dir;

			long checksum = ZipUtils.extract(in, out);

			System.out.println("checksum: " + Long.toHexString(checksum));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
