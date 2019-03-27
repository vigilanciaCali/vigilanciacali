package co.edu.usbcali.vas.video.control;

public interface IFolderMonitorLogic {

	public String outputFileWatcher(String folder);

	public String getMatLabResultFromFile(String fileLocation);

	public void writeOnFileZero(String file);

	public void deleteVideoFileFromFolder(String videoLocation);

	public void validate_system_folder(String folderLocation);

	public void validate_system_file(String fileLocation);

	public String validateMatLabResult(String result);

	public String getAlgStatusFromFile(String fileLocation);

	public Boolean searchFile(String folder, String videoId);

	
   
}
