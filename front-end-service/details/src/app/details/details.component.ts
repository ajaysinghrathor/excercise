import { Component, OnInit } from '@angular/core';
import { DetailService } from '../detail.service';
import { FeeddataModule } from '../feeddata/feeddata.module';


@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
/*
export interface PeriodicElement {
  id: string;
  date: string;
  type: string;
  symbol: string;
  shares: string;
  price: string;
  costs: string;
  fees: string;
  amount: string;
  status: string;
  feedName: string;
}*/

export class DetailsComponent implements OnInit {
  feedData : FeeddataModule[]= [];

  displayedColumns: string[] = ['position', 
                                'date',
                                'type',
                                'symbol',
                                'shares',
                                'price',
                                'costs',
                                'fees',
                                'amount',
                                'status',
                                'feedName'
                              ];
  

  constructor(private service: DetailService) { }

  public getFeeds(){
    this.service.getFeedData()
                .subscribe((data: FeeddataModule[]) => {
                         this.feedData = data;
                       });
    return this.feedData;
  }

  ngOnInit(): void {
    this.service.getFeedData()
                .subscribe((data: FeeddataModule[]) => {
                         console.log(data);
                         this.feedData = data;
                       });  
  }

}
