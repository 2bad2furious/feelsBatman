class BankAccount(val money: Int){
  def get(a:Int): BankAccount = {
    new BankAccount(money - a)
  }
  def put(a:Int): BankAccount = {
    new BankAccount(money + a)
  }
  def transfer(a:BankAccount,b:Int) : (BankAccount,BankAccount) = {
    (get(b),a.put(b))
  }
}