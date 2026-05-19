import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
 
@Injectable({
  providedIn: 'root'
})
export class FlightService {
 
  private baseUrl = 'http://localhost:8085/api/flights';
 
  constructor(private http: HttpClient) {}
 
  getAllFlights(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }
 
  searchFlights(origin: string, destination: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/search?origin=${origin}&destination=${destination}`);
  }
 
  getFlightById(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
}
 