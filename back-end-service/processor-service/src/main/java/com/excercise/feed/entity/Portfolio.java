package com.excercise.feed.entity;

import com.excercise.feed.validation.DateValidator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Portfolio {

    @JsonProperty("position")
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "date", nullable = true)
    private String date;

    public Portfolio(){}

    public Portfolio(String date, String type, String symbol, String shares, String price, String costs, String fees, String amount) {
        this.date = date;
        this.type = type;
        this.symbol = symbol;
        this.shares = shares;
        this.price = price;
        this.costs = costs;
        this.fees = fees;
        this.amount = amount;
    }

    @Column (name = "type", nullable = true)
    private String type;
    @Column (name = "symbol", nullable = true)
    private String symbol;
    @Column (name = "shares", nullable = true)
    private String shares;
    @Column (name = "price", nullable = true)
    private String price;
    @Column (name = "costs", nullable = true)
    private String costs;
    @Column (name = "fees", nullable = true)
    private String fees;
    //@Size(min=11, max = 11, message = "Invalid date.")
    @DateValidator
    @Column (name = "amount", nullable = true)
    private String amount;

    public Portfolio(String date, String type) {
        this.date = date;
        this.type = type;
    }

    /*   @Column (name = "div_amount", nullable = true)
     private String divAmount;
      @Column (name = "shares_affected", nullable = true)
      private String sharesAffected;
      @Column (name = "currency", nullable = true)
      private String currency;
  */
    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCosts() {
        return costs;
    }

    public void setCosts(String costs) {
        this.costs = costs;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

   /* public String getDivAmount() {
        return divAmount;
    }

    public void setDivAmount(String divAmount) {
        this.divAmount = divAmount;
    }

    public String getSharesAffected() {
        return sharesAffected;
    }

    public void setSharesAffected(String sharesAffected) {
        this.sharesAffected = sharesAffected;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
*/

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Portfolio{");
        sb.append("id=").append(id);
        sb.append(", date='").append(date).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", symbol='").append(symbol).append('\'');
        sb.append(", shares='").append(shares).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append(", costs='").append(costs).append('\'');
        sb.append(", fees='").append(fees).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", feed='").append(feed).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Column (name = "status")
    private String status;

    @Column (name = "feed")
    private String feed;

}
