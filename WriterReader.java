
import java.io.*; 
class WriterReader
{

  public static void writeItem(OutputStream out, String s) throws IOException
  {
   // Get the array of bytes for the string item:
      byte[] bs = s.getBytes(); 
      out.write(bs.length);    
      out.write(bs.length>>>8); 
      out.write(bs.length>>>16); 
      out.write(bs.length>>>24);  
      out.write(bs);  
      out.flush();
  }

  public static String readItem(InputStream in) throws IOException
  {
      int len = in.read();  
      if (len<0) throw new IOException("end of stream");
      for(int i=1;i<4;i++) 
      {
        int n = in.read();
        if (n<0) throw new IOException("partial data");
        len |= n << (i<<3);
      }
      byte[] bs = new byte[len];
      int ofs = 0;
    while (len>0) 
    {
      int n = in.read(bs, ofs, len);  
        if (n<0) throw new IOException("partial data");
      ofs += n;  
        len -= n;  
    }
      return new String(bs);
  }
}
