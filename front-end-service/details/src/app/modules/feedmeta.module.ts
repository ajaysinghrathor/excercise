import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@NgModule({
    declarations: [],
    imports: [
      CommonModule
    ]
  })

  export class FeedMeta {
    id: String = "";
    date: String = "";
    feed: String = "";
    size: String = "";
    status: String = ""

    public FeedMeta(id : string, date : string, feed :string,size :string,status  :string) {
     this.id = id;
     this.date = date;
     this.size = size;
     this.status = status;
     this.feed = feed;
 }

    public getSize() {
      return this.size;
    }

    public setSize(size: string){
      this.size = size;
    }

    public getStatus() {
      return this.status;
    }

    public setStatus(status: string){
      this.status = status;
    }


    public getFeed() {
      return this.feed;
    }

    public setFeed(feed: string){
      this.feed = feed;
    }

  public getDate() {
    return this.date;
  }

  public setDate(date: string){
    this.date = date;
  }

  public getId() {
    return this.id;
  }

  public setId(id: string){
    this.id = id;
  }

  }
