import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Main{

  private static enum ROTDIR{
    FORWARD,
    UP,
    DOWN,
  };
  private static class moveUnit{
    public ROTDIR way;
    public int intensity;
    public moveUnit(ROTDIR inWay, int value){
      
      way = inWay;
      intensity = value;
      
    }
  };

  public static class reportInput{

    public ArrayList<moveUnit> values = new ArrayList<moveUnit>();
    public int count = 0;

    public reportInput(String filePath) throws IOException{

      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      while(true){
        String StringBuffer;
        StringBuffer = reader.readLine();
        if(StringBuffer == null){
          break;
        }
        values.add(new moveUnit(StringBuffer.charAt(0) == 'f' ? ROTDIR.FORWARD : StringBuffer.charAt(0) == 'u' ? ROTDIR.UP : StringBuffer.charAt(0) == 'd' ? ROTDIR.DOWN : null, (StringBuffer.charAt(StringBuffer.length() - 1)) - 48));
        count++;
      }
      reader.close();

    }

    public void print(){
      for(int i = 0; i < values.size(); i++){
        System.out.println(values.get(i).way + " " + values.get(i).intensity);
      }
    }
  };

  public static class resultOutput{
    public int value = 0;
    private boolean solved = false;
    private reportInput input;

    private int aim = 0;
    private int position = 0;
    private int depth = 0;

    public resultOutput(reportInput in){

      input = in;

    }

    public int calculate(boolean legacy){

      if(legacy){
        for(int i = 0; i < input.count; i++){
          position += input.values.get(i).way == ROTDIR.FORWARD ? input.values.get(i).intensity : 0;
          depth += input.values.get(i).way == ROTDIR.UP ? input.values.get(i).intensity * -1 : input.values.get(i).way == ROTDIR.DOWN ? input.values.get(i).intensity : 0;
        }
        value = position * depth;

        return value;

      }
      for(int i = 0; i < input.count; i++){
        aim += input.values.get(i).way == ROTDIR.UP ? input.values.get(i).intensity * -1 : input.values.get(i).way == ROTDIR.DOWN ? input.values.get(i).intensity : 0;
        position += input.values.get(i).way == ROTDIR.FORWARD ? input.values.get(i).intensity : 0;
        depth += input.values.get(i).way == ROTDIR.FORWARD ? input.values.get(i).intensity * aim : 0;
      }
      value = position * depth;

      return value;

    }
  };

  public static void main(String[] args) throws IOException{
    reportInput Input = new reportInput("Input.txt");
    resultOutput Output = new resultOutput(Input);
    System.out.println(Output.calculate(false));

/************TESTING PARSER****************/
//  Input.print();
/************TESTING PARSER****************/

  }
}