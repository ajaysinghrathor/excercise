import { Component, OnInit } from '@angular/core';
import { DetailService } from '../detail.service';
import { FeeddataModule } from '../modules/feeddata.module';


@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})


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
                                'feed'
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
