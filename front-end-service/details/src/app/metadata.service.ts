import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { FeeddataModule } from './modules/feeddata.module';
import { FeedMeta } from './modules/feedmeta.module';

@Injectable({
  providedIn: 'root'
})
export class FeedsService {

  private url: string = "http://localhost:6060/api/data/feeds";
  constructor(private http: HttpClient) {}

  public getMeta() : Observable<FeedMeta[]>{
    return this.http.get<FeedMeta[]>(this.url);
  }

}
