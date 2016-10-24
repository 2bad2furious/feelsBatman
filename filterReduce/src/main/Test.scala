/**
  * Created by user on pssssh (09.10.2016).
  */
object Test {

  def main(args: Array[String]): Unit= {
    var a:BankAccount = new BankAccount(200)
    var b:BankAccount = new BankAccount(100)

    var(c,d):(BankAccount,BankAccount) = a.transfer(b,50)
    println(d.money)
  }




  def bcd(a: Int,b:Int) : Int = {
    if(a <= 0 || b <= 0) {
      println("a nebo b je 0 "+a+" "+b)
      return 0;
    }
    if(a > b)
    return bcd(b,a,a-1)
    else if(b>a)
    return bcd(a,b,b-1)
    else
    return a
  }

  def bcd(a:Int,b:Int,c:Int) : Int = {
    if(a%c == 0 && b%c == 0)
    return c
    else
    return bcd(a,b,c-1)
  }

  def lcm(a: Int,b:Int) : Int = {
    if(a <= 0 || b <= 0) {
      println("a nebo b je 0 "+a+" "+b)
      return 0;
    }
    if(a > b)
      return lcm(b,a,a)
    else if(b>a)
      return lcm(a,b,b)
    else
      return a
  }

  def lcm(a:Int,b:Int,c:Int) : Int = {
    if(c%a == 0 && c%b == 0)
      return c
    else
      return lcm(a,b,c+1)
    }


  def forAll(a: Int, b:Int,f:(Int) => Boolean) : Boolean = {
    if(a==b)
       f(a)
    else if(f(a)) forAll(a+1,b,f)
    else false
  }

  def exists(a:Int,b:Int,f:(Int) => Boolean) : Boolean =  {
    if(a==b)
      f(a)
    else if(f(a)) true
    else exists(a+1,b,f)
  }

  def reduce(a: Int, b: Int, f: (Int,Int) => (Int),even:(Int) => (Boolean)): Int = {
    if (a == b)
      if(even(a)) a
      else 0
    else if(even(a))
      return f(a,reduce(a+1,b,f,even))

    return reduce(a+1,b,f,even)
  }
  def even(a : Int) : Boolean = {
    return (a % 2 == 0)
  }
}

