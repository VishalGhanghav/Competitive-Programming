package RealLifeCodes.src.importantQuestions;

import java.util.ArrayList;
import java.util.Objects;

public class PrivateMethod {

    private String username="pqr";
    private String password="abc";

    private final User user;

    private final ArrayList<Integer> arrayList=new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
       // this.user = user;
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Integer> arrayList) {
        user.role="PQ";
        if(user.role=="Admin") {

            for(Integer a:arrayList){
                this.arrayList.add(a);
            }
            System.out.println("Successful Login");
        }else{
            System.err.println("Not possible");
        }
    }


    PrivateMethod(User user){
        this.user=user;

    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }


}

 class PublicAccess{

    public static void main(String[] args) {

        User user1=new User(1,"abc","Admin");
        User user2=new User(2,"pqr","Else");
        PrivateMethod pm1=new PrivateMethod(user1);
        PrivateMethod pm2=new PrivateMethod(user2);
        ArrayList<Integer> al=new ArrayList<>();
        al.add(1);
        pm1.setArrayList(al);
        System.out.println("user1"+pm1.getArrayList());
        pm1.setPassword("123");

        al.add(3);
        pm2.setArrayList(al);
        System.out.println("user2"+pm2.getArrayList());
        pm2.getArrayList().add(4);
        System.out.println("user2"+pm2.getArrayList());

        Person p1=new Person("Vishal","Ram",18,182345L);
        Person p2=new Person("Vishal","Ram",18, 183345L);

        System.out.println(p1.equals(p2));


    }
}
class User{
    int id;
    String name;

    String role;

    PrivateMethod privateMethod;

    public User(int id,String name,String role) {
        this.id = id;
        this.name=name;
        this.role=role;
    }

}


class Person{
    String name;
    String fatherName;
    int age;
    Long mobileNumber;

    public Person(String name, String fatherName, int age, Long mobileNumber) {
        this.name = name;
        this.fatherName = fatherName;
        this.age = age;
        this.mobileNumber = mobileNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(fatherName, person.fatherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fatherName, age);
    }
}






