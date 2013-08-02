//package core;
//
//import java.io.File;
//import java.io.FileDescriptor;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import android.media.MediaRecorder;
//import android.os.Environment;
//import android.widget.Toast;
//
//public class RecorderConfig
//{
//	public static int OutputFormat = MediaRecorder.OutputFormat.MPEG_4;
//	public static final String AUDIO_RECORDER_FOLDER = "AudioRecordings";
//}
//
//public enum RecorderState
//{
//	Recording,
//	NotStarted,
//	Stopped
//}
//
//public class Recorder
//{
//
//	private static final String AUDIO_RECORDER_FILE_EXT_3GP = ".3gp";
//	private static final String AUDIO_RECORDER_FILE_EXT_MP4 = ".mp4";
//	
//	
//	private static MediaRecorder recorder = null;
//	
//	private static String file_exts[] = { AUDIO_RECORDER_FILE_EXT_MP4, AUDIO_RECORDER_FILE_EXT_3GP };
//	
//	static String  autoFileName = null;
//    
//	static int fileincrement ;
//	
//	static RecorderState State = RecorderState.NotStarted;
//	
//	static File FileInRecording = null; 
//	
//	static int check_dirflag =0;
//	
//	//static String filepath = null;
//	static String Fullpath = null;
//	
//	
//	public static void startRecording()
//	{
//		if(State != RecorderState.Recording)
//		{
//		if(recorder == null)	
//			recorder = new MediaRecorder();
//	    
//		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//	    recorder.setOutputFormat(RecorderConfig.OutputFormat);
//	    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//	    recorder.setOutputFile(generateNewFileName());
//	    recorder.setOnErrorListener(errorListener);
//	    recorder.setOnInfoListener(infoListener);
//	    
//
//	    try {
//	        recorder.prepare();
//	        recorder.start();
//	        State = RecorderState.Recording;
//	    } catch (IllegalStateException e)
//	    {
//	        e.printStackTrace();
//	        State = RecorderState.NotStarted;
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	        State = RecorderState.NotStarted;
//	    }
//		}
//		//autoFileName = generateNewFileName();
//	}
//	
//	
//	public static  void stopRecording()
//	{
//		
//	    if (null != recorder) 
//	    {
//	    	//TODO: Try catch
//	        recorder.stop();
//	        recorder.reset();
//	        recorder.release();
//	        recorder = null;
//	        State = RecorderState.NotStarted;
//	    }
//		
//	}
//	// Returns path of new file name to be used for new records 
////	private static String generateNewFileName() 
////	{
////		
////		  String filepath = Environment.getExternalStorageDirectory().getPath();
////		    File file = new File(filepath, AUDIO_RECORDER_FOLDER);
////		    if (!file.exists()) {
////		        file.mkdirs();
////		    }
////		    return (file.getAbsolutePath() + "/" + System.currentTimeMillis() + file_exts[currentFormat]);
////	
////	}get
//	public static String getupdatedfile()
//	{
//
//		String filepath = Environment.getExternalStorageDirectory().getPath();
//
//        
//		 autoFileName = "Clip" + fileincrement;
//		  Fullpath = filepath+"/AUDIORECORDINGS1";
//		 File file = new File(filepath+"/AUDIORECORDINGS1", autoFileName);
//		
//		
//		File to = new File(filepath+"/AUDIORECORDINGS1","ssssto");
//		file.renameTo(to);
//		
//		 return (to.getAbsolutePath() +  file_exts[currentFormat]);
//		
//	}
//	
////	public static void updateRecordingfile()
////	{
////		recorder.setOutputFile(getupdatedfile());
////		
////	
////	}
//		 
//		
//	
//	private static String generateNewFileName()
//	 {
//		fileincrement++;
//		String filepath = Environment.getExternalStorageDirectory().getPath();
//
//          
//		 autoFileName = "Clip" + fileincrement;
//		  Fullpath = filepath+"/AUDIORECORDINGS1";
//		 File file = new File(filepath+"/AUDIORECORDINGS1", autoFileName);
//		
//		 
//		 if(!file.exists())
//           {
//			
//			 file.mkdirs();
//			
//           }
//		// String renameFile = MyFragment.renameFile;
//		 
//		// File to = new File(filepath+"/AUDIORECORDINGS1",renameFile);
//		//	file.renameTo(to);
//		   //return (file.getAbsolutePath() + "/" + System.currentTimeMillis() + file_exts[currentFormat]);
//		 return (file.getAbsolutePath() +  file_exts[currentFormat]);
//	
//	 }
//	
//	private static MediaRecorder.OnErrorListener errorListener = new MediaRecorder.OnErrorListener()
//	{
//	    @Override
//	    public void onError(MediaRecorder mr, int what, int extra) {
//	        Toast.makeText(MyFragmentPagerAdapter.context, "Error: " + what + ", " + extra, Toast.LENGTH_SHORT).show();
//	    }
//	};
//	
//	private static MediaRecorder.OnInfoListener infoListener = new MediaRecorder.OnInfoListener()
//	{
//	    @Override
//	    public void onInfo(MediaRecorder mr, int what, int extra) {
//	        Toast.makeText(MyFragmentPagerAdapter.context, "Warning: " + what + ", " + extra, Toast.LENGTH_SHORT).show();
//	    }
//	};
//	public static void pauseRecording() throws Throwable, IOException
//	{
////		String filename = getFilename();
////		recorder.stop();
////		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
////		recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
////		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
////	    FileOutputStream paused_file = new FileOutputStream(filename);
////	    recorder.setOutputFile(paused_file.getFD());
//	}
//
//	public static void resumeRecording()
//	{
////		try {
////			recorder.prepare();
////		} catch (IllegalStateException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		recorder.start();
//		
//	}
//	public static void setFileIncriment(int fileIncriment)
//	{
//		start_stopRecorder.fileincrement=fileIncriment;
//	}
//	public static int  getFileIncriment()
//	{
//		return start_stopRecorder.fileincrement;
//	}
//}
