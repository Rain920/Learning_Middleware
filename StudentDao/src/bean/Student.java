package bean;
public class Student{
   private long iD;
   private String name;

   public Student(){ }

   public Student(String name){
      this.name = name;
   }

   public Student(long id, String name){
      this.iD = id;
      this.name = name;
   }

   public void setID(long iD){
      this.iD = iD; }

   public long getID(){
      return iD;}

   public void setName(String name){
      this.name = name; }

   public String getName(){
      return name; }

   @Override
   public String toString() {
      return "id: " + this.iD + ", name: " + this.name;
   }
}
