import java.util.*; 
import java.io.*;
import java.util.stream.Collectors;


class PersonalData{

  private Integer age;
  private String name;

  public PersonalData(Integer age , String name){
    this.age = age;
    this.name = name;
  }

  public Integer getAge(){ return age;}

  public void setAge(Integer age){ this.age = age;}

  public String getName(){ return name;}

  public void setName(String name) {this.name = name;}

}

class Main {
  
  public static String Grouper(String str) {

    // parser input and split data
    final List<String> strings = Arrays.asList(str.split(";"));
    final List<PersonalData> data = strings.stream().map(
      s-> { final String [] split = s.split("-");
      return new PersonalData(Integer.parseInt(split[1]),split[0]);
      }).collect(Collectors.toList());

    final HashMap<Integer,List<PersonalData>> resultMap = new HashMap<>();
    data.forEach(register -> {
      // get age range
      final Integer floor = Double.valueOf(Math.floor(register.getAge()/10d) * 10).intValue();

      // frist event
      if(null == resultMap.get(floor)){
        final List<PersonalData> registerList = new ArrayList<>();
        registerList.add(register);
        registerList.sort(Comparator.comparing(PersonalData::getAge).thenComparing((PersonalData::getName)));
        resultMap.put(floor,registerList);
      }else{
        List<PersonalData> registerList = resultMap.get(floor);
        registerList.add(register);
        registerList.sort(Comparator.comparing(PersonalData::getAge).thenComparing((PersonalData::getName)));
        resultMap.put(floor,registerList);
      }
    });

    // no elegant way
    StringBuilder strt = new StringBuilder();
    // print the resoult
    resultMap.forEach((k,v)->{
      strt.append(k+":");
      v.forEach(pdata ->strt.append(pdata.getName()+"-"+pdata.getAge()+";"));
    });

    // code goes here  
    // remove the last semi-colum
    return (strt.substring(0,strt.length()-1));
  }

  public static void main (String[] args) {  
    // keep this function call here     
    Scanner s = new Scanner(System.in);
    System.out.print(Grouper(s.nextLine())); 