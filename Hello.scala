//Scala--> my first run program

object Hello extends App{
//  println("Hello Scala Im in ")
var l2 = List(5,6,6,8,8,9)
var listy= List(4,5,6,7,3,4,true,"BLA",l2)
//for(n<- listy)println(n)
  var revList = listy.reverse
  listy.foreach{i:Any =>System.out.println(i)}
println("\n")
  revList.foreach{i:Any=>System.out.println(i)}

 var temp = listy drop 5 take 10//to remove first 2 element and take(2) will w
  println("\n") //without dot operator
  temp.foreach{i:Any=>System.out.println(i)}

  // user defined member variable using class
  case class Stud(rollNo:Int,name:String,marks:Int)
  val students = List( Stud(1,"Rexson",100),Stud(2,"Rexsy",90),Stud(3,"Remsy",50))
println("\n")
  students.foreach{i:Any=>println(i)}
  val first=students.head //this will giive us the first object
  val rest = students.tail //this will give you the rest of the object
  println("\n", first,"  |  " , rest)

  val toppers = students.filter(s => s.marks>=90) //using filter
  print("toppers  --  >  ",toppers)

  print("\n")

  val parts = students.partition(s=>s.marks>=90) //using partition
  print("partition -- > ", parts)

  //tuples

 val (part1,part2 )= students.partition(s=>s.marks>90)


  print("\n")
  //or for tuples

  val part1a =  parts._1
  val part2a = parts._2

print("\n p1a -->", part1a)
print("\n p2a -->",part2a)
}//end of the object
