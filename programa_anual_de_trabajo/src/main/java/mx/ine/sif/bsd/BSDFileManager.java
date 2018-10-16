package mx.ine.sif.bsd;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;


public interface BSDFileManager {
	public enum ReturnParamKeys{
		FILE,
		FILENAME,
		INSIDE_ZIP
	}
	public String generateDownloadFileInsideZipUrl(String zipFilePath,
		   String filenameToUnzip, boolean forceDownload) throws UnsupportedEncodingException;
	public EnumMap<ReturnParamKeys, Object> analizeUrl(String url) throws Exception;
}
