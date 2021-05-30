
import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { FeeddataModule } from './feeddata/feeddata.module';


@Injectable({
  providedIn: 'root'
})
export class DetailService {

  private url: string = "http://localhost:8080/api/data/feeds";
  constructor(private http: HttpClient) {}

  public getFeedData() : Observable<FeeddataModule[]>{
    return this.http.get<FeeddataModule[]>(this.url); 
  }
}


