package mx.ine.sif.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.sf.sevenzipjbinding.ExtractAskMode;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IArchiveExtractCallback;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.PropID;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;

import org.apache.log4j.Logger;
import org.primefaces.model.NativeUploadedFile;

public class ZipperStream {
	private static final Logger logger = Logger.getLogger(ZipperStream.class);
	
	private static class MyExtractCallback implements IArchiveExtractCallback {
		private int index;
		private OutputStream outputStream;
		private IInArchive inArchive;

		public MyExtractCallback(IInArchive inArchive , OutputStream outputStream) {
			this.inArchive = inArchive;
			this.outputStream = outputStream;
		}

		@Override
		public ISequentialOutStream getStream(int index, 
				ExtractAskMode extractAskMode) throws SevenZipException {
			this.index = index;
			if (extractAskMode != ExtractAskMode.EXTRACT) {
				return null;
			}
//			closeOutputStream();
			
			return new ISequentialOutStream() {
				public int write(byte[] data) throws SevenZipException {
					try {
						outputStream.write(data);
					} catch (IOException e) {
						throw new SevenZipException("Error writing to file");
					}
					
					return data.length; // Return amount of proceed data
				}
			};
		}

		@Override
		public void setOperationResult(
				ExtractOperationResult extractOperationResult)
				throws SevenZipException {
			closeOutputStream();
			
			String path = (String) inArchive.getProperty(index, PropID.PATH);
			if (extractOperationResult != ExtractOperationResult.OK) {
				throw new SevenZipException("Invalid file: " + path);
			}
		}
		
		@Override	
		public void prepareOperation(ExtractAskMode extractAskMode) 
				throws SevenZipException {}
		@Override
		public void setCompleted(long completeValue) throws SevenZipException {}
		@Override
		public void setTotal(long total) throws SevenZipException {}
		
		private void closeOutputStream() throws SevenZipException {
			if (outputStream != null) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (IOException e) {
					throw new SevenZipException("Error closing file");
				}
			}
		}
	}
	
	public static void unzipToStream( File zipPath , String filenameToUnzip , OutputStream outputStream ) throws Exception {
		final int BUFFER = 2048;
		 
		ZipFile zip = new ZipFile( zipPath );

	    for (Enumeration<? extends ZipEntry> e = zip.entries(); e.hasMoreElements(); ) {
	        ZipEntry entry = (ZipEntry) e.nextElement();
	        if( entry.getName().equals( filenameToUnzip ) ){
	        	int count;
	            byte data[] = new byte[BUFFER];
	             
	        	BufferedInputStream is = new BufferedInputStream( zip.getInputStream(entry) ); 
	        	while ((count = is.read(data, 0, BUFFER)) != -1) {
	        		outputStream.write(data, 0, count);
	        	} 
	        	
	        	outputStream.flush();
	        	outputStream.close();
	        }
	    }
	    
	    zip.close();
	}
	
	 public static void nativeUnzipToStream( File zipPath , String filenameToUnzip , OutputStream outputStream ) throws Exception {
		 RandomAccessFile randomAccessFile = null;
		 IInArchive inArchive = null;
		 try {
			 randomAccessFile = new RandomAccessFile(zipPath, "r");
			 inArchive = SevenZip.openInArchive(null, // autodetect archive  type
					 new RandomAccessFileInStream(randomAccessFile));

			 int[] items = transformFileNamesToIndexes( inArchive, filenameToUnzip);

			 long startTime = System.nanoTime();
			 
			 MyExtractCallback myExtCallback = new MyExtractCallback(inArchive,outputStream);
			 	inArchive.extract(items, false, myExtCallback);
			 
			 long endTime = System.nanoTime();
			 long duration = (endTime - startTime);
			 
			 long seconds = TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);
			 logger.info("==>File uncompressed in: " + seconds + " seconds");
		 } catch (Exception e) {
			 System.err.println("Error occurs: " + e);
			 throw new Exception("Error al extraer el archivo");
		 } finally {
			 if (inArchive != null) {
				 try {
					 inArchive.close();
				 } catch (SevenZipException e) {
					 System.err.println("Error closing archive: " + e);
				 }
			 }
			 if (randomAccessFile != null) {
				 try {
					 randomAccessFile.close();
				 } catch (IOException e) {
					 System.err.println("Error closing file: " + e);
				 }
			 }
		 }
	 }
	
	 private static int[] transformFileNamesToIndexes(IInArchive inArchive, String... fileNames) throws SevenZipException {
		 int arrayIndex = 0;
		 int[] filesToExtract = new int[fileNames.length];
		 boolean fileNameFound = false;
		 
		 int count = inArchive.getNumberOfItems();
		 for (String fileName : fileNames) 
			 for (int index = 0; index < count; index++) 
				 if (!((Boolean) inArchive.getProperty(index, PropID.IS_FOLDER)).booleanValue()) 
					 if (fileName.equals(inArchive.getProperty(index, PropID.PATH))) {
						 filesToExtract[arrayIndex++] = index;
						 fileNameFound = true;
						 break;
					 }
		
		 if( fileNameFound == false )
			 throw new SevenZipException("Archivo no encontrado");
		 
		 return filesToExtract;
	 }	
}
