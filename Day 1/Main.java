import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Main{

  public static class reportInput{
    public ArrayList<Integer> values = new ArrayList<Integer>();
    public int count = 0;

    public reportInput(String filePath) throws IOException{

      BufferedReader Reader = new BufferedReader(new FileReader(filePath));
      while(true){
        String StringBuffer;
        StringBuffer = Reader.readLine();
        if(StringBuffer == null){
          break;
        }
        int Multiplier = 1;
        int IntBuffer = 0;
        for(int i = StringBuffer.length(); i > 0; i--){
          IntBuffer += (StringBuffer.charAt(i - 1) - 48) * Multiplier;
          Multiplier *= 10;
        }
        values.add(IntBuffer);
        count++;
      }
      Reader.close();

    }
  };

  public static void main(String[] args) throws IOException{
    reportInput Input = new reportInput("Input.txt");

/************TESTING PARSER****************/
//    for(int i = 0; i < Input.count; i++){
//      System.out.println(Input.values.get(i));
//    }
//    System.out.println("==============FIRST THREE=======================");
//    System.out.println(Input.values.get(0));
//    System.out.println(Input.values.get(1));
//    System.out.println(Input.values.get(2));
/************TESTING PARSER****************/

  }
}