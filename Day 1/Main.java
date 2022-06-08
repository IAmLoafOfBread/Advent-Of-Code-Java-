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

  public static class resultOutput{
    public int value = 0;
    private boolean solved = false;
    private reportInput input;

    public resultOutput(reportInput in){

      input = in;

    }

    public int calculate(int window){

      for(int i = 0; i < input.count - window; i++){
        int Comparer = 0;
        int Comparee = 0;
        for(int j = 0; j < window; j++){
          Comparee += input.values.get(i + j);
          Comparer += input.values.get((i + j) + 1);
        }
        if(Comparee < Comparer){
          value++;
        }
      }

      return value;

    }
  };

  public static void main(String[] args) throws IOException{
    reportInput Input = new reportInput("Input.txt");
    resultOutput Output = new resultOutput(Input);
    System.out.println(Output.calculate(3));

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