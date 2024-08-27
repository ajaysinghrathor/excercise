import { Component, OnInit } from '@angular/core';
import { FeedMeta } from '../modules/feedmeta.module';
import { FeedsService } from '../metadata.service';

@Component({
  selector: 'app-metadata',
  templateUrl: './metadata.component.html',
  styleUrls: ['./metadata.component.css']
})
export class MetadataComponent implements OnInit {
  metaData : FeedMeta[]= [];
  displayedColumns: string[] = ['id',
    'date',
    'feed',
    'size',
    'status'
  ];

  constructor(private service: FeedsService) { }

  public getMetaData(){
    this.service.getMeta()
                .subscribe((data: FeedMeta[]) => {
                         console.log(data);
                         this.metaData = data;
                       });
    return this.metaData;
  }

  ngOnInit(): void {
    this.service.getMeta()
    .subscribe((data: FeedMeta[]) => {
             console.log(data);
             this.metaData = data;
           });
  }

}
