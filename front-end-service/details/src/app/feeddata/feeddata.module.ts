import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class FeeddataModule { 
  id: string= "";
  date: string= "";
  type: string= "";
  symbol: string= "";
  shares: string= "";
  price: string= "";
  costs: string= "";
  fees: string= "";
  amount: string= "";
  status: string= "";
  feedName: string= "";

  public FeeddataModule(date : string, type :string,symbol  :string, shares :string , price :string ,
     costs :string, fees :string, amount :string) {
    this.date = date;
    this.type = type;
    this.symbol = symbol;
    this.shares = shares;
    this.price = price;
    this.costs = costs;
    this.fees = fees;
    this.amount = amount;
}

  public getId() : string {
    return this.id;
}

public getDate() : string{
    return this.date;
}

public setDate(date: string){
    this.date = date;
}

public getType() : string{
    return this.type;
}

public setType(type : string) {
    this.type = type;
}

public getSymbol() : string{
    return this.symbol;
}

public setSymbol(symbol : string) {
    this.symbol = symbol;
}

public getShares() : string{
    return this.shares;
}

public setShares(shares : string) {
    this.shares = shares;
}

public getPrice() : string{
    return this.price;
}

public setPrice(price : string) {
    this.price = price;
}

public getCosts() : string{
    return this.costs;
}

public setCosts(costs: string) {
    this.costs = costs;
}

public getFees() {
    return this.fees;
}

public setFees(fees : string) {
    this.fees = fees;
}

public getAmount() : string{
    return this.amount;
}

public setAmount(amount :string) {
    this.amount = amount;
}

}
