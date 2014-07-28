package br.com.staroski.tools.zip;

import static br.com.staroski.tools.io.IO.*;

import java.io.*;
import java.util.zip.*;

import br.com.staroski.tools.io.*;

/**
 * Classe utilitária para compactação e descompactação de arquivos ZIP
 * 
 * @author Ricardo Artur Staroski
 */
public final class ZipUtils {

	/**
	 * Compacta determindado arquivo ou diretório para o arquivo ZIP
	 * especificado
	 * 
	 * @param input
	 *            O arquivo ou diretório de entrada
	 * @param output
	 *            O arquivo ZIP de saída
	 *
	 *@return O checksum da compactação do arquivo
	 */
	public static long compress(final File input, final File output) throws IOException {
		if (!input.exists()) {
			throw new IOException(input.getName() + " não existe!");
		}
		if (output.exists()) {
			if (output.isDirectory()) {
				throw new IllegalArgumentException("\"" + output.getAbsolutePath() + "\" não é um arquivo!");
			}
		} else {
			final File parent = output.getParentFile();
			if (parent != null) {
				parent.mkdirs();
			}
			output.createNewFile();
		}
		CheckedOutputStream checksum = new CheckedOutputStream(new FileOutputStream(output), createChecksum());
		final ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(checksum));
		zip.setLevel(Deflater.BEST_COMPRESSION);
		compress(null, input, zip);
		zip.finish();
		zip.flush();
		zip.close();
		return checksum.getChecksum().getValue();
	}

	/**
	 * Extrai um arquivo ZIP para o diretório especificado
	 * 
	 * @param input
	 *            O arquivo ZIP de entrada
	 * @param output
	 *            O diretório de saída
	 *@return O checksum da descompactação do arquivo
	 */
	public static long extract(final File input, final File output) throws IOException {
		if (input.exists()) {
			if (input.isDirectory()) {
				throw new IllegalArgumentException("\"" + input.getAbsolutePath() + "\" não é um arquivo!");
			}
		} else {
			throw new IllegalArgumentException("\"" + input.getAbsolutePath() + "\" não existe!");
		}
		if (output.exists()) {
			if (output.isFile()) {
				throw new IllegalArgumentException("\"" + output.getAbsolutePath() + "\" não é um diretório!");
			}
		}
		CheckedInputStream checksum = new CheckedInputStream(new FileInputStream(input), createChecksum());
		final ZipInputStream zip = new ZipInputStream(new BufferedInputStream(checksum));
		extract(zip, output);
		zip.close();
		return checksum.getChecksum().getValue();
	}

	// Adiciona determinado arquivo ao ZIP
	private static void compress(final String caminho, final File arquivo, final ZipOutputStream saida) throws IOException {
		final boolean dir = arquivo.isDirectory();
		String nome = arquivo.getName();
		nome = (caminho != null ? caminho + "/" + nome : nome);
		final ZipEntry elemento = new ZipEntry(nome + (dir ? "/" : ""));
		elemento.setSize(arquivo.length());
		elemento.setTime(arquivo.lastModified());
		saida.putNextEntry(elemento);
		if (dir) {
			final File[] arquivos = arquivo.listFiles();
			for (int i = 0; i < arquivos.length; i++) {
				// recursivamente adiciona outro arquivo ao ZIP
				compress(nome, arquivos[i], saida);
			}
		} else {
			final FileInputStream entrada = new FileInputStream(arquivo);
			copy(entrada, saida);
			entrada.close();
		}
	}

	private static Checksum createChecksum() {
		return new CRC32();
	}

	// Retira determinado elemento do arquivo ZIP
	private static void extract(final ZipInputStream zip, final File pasta) throws IOException {
		ZipEntry elemento = null;
		while ((elemento = zip.getNextEntry()) != null) {
			String nome = elemento.getName();
			nome = nome.replace('/', File.separatorChar);
			nome = nome.replace('\\', File.separatorChar);
			File arquivo = new File(pasta, nome);
			if (elemento.isDirectory()) {
				arquivo.mkdirs();
			} else {
				if (!arquivo.exists()) {
					final File parent = arquivo.getParentFile();
					if (parent != null) {
						parent.mkdirs();
					}
					arquivo.createNewFile();
				}
				OutputStream saida = new FileOutputStream(arquivo);
				byte[] buffer = new byte[IO.BLOCK_SIZE];
				for (int lidos = -1; (lidos = zip.read(buffer, 0, IO.BLOCK_SIZE)) != -1; saida.write(buffer, 0, lidos))
					;
				saida.flush();
				saida.close();
			}
			arquivo.setLastModified(elemento.getTime());
		}
	}

	// Construtor privado - Náo há razão em instanciar esta classe
	private ZipUtils() {
	}
}