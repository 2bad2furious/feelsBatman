class Fraction(val num: Int, val den: Int) extends Comparable[Fraction] {

  def *(other: Fraction): Fraction = new Fraction(num * other.num, den * other.den).simplify()

  def /(other: Fraction): Fraction = {
    new Fraction(num*other.den,other.num*den).simplify()

  }
  def +(other: Fraction): Fraction = {
    new Fraction((num*other.den)+(other.num*den),den*other.den).simplify()
  }
  def -(other: Fraction): Fraction = {
    new Fraction((num*other.den)-(other.num*den),den*other.den).simplify()
  }
  def isSimple(): Boolean = {
    val s = simplify()
    (s.den == den && s.num == num)
  };

  def simplify(): Fraction = {
   if(num%den == 0)
     new Fraction(num,1)
    else {
     var a:Int = bcd(num,den)
     new Fraction(num/a,den/a)
    }
  }

  // TODO a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
  override def compareTo(other: Fraction): Int = {
    val a = num*other.den
      val b = other.num*den
    return b-a
  }

  override def toString() = "{"+ num + "/" + den + "}"

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

}